package com.dt176g.project.services;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.dt176g.project.models.*;
import com.dt176g.project.models.chat.ChatModel;
import com.dt176g.project.models.chat.ContextualResponseWithSuggestions;
import com.dt176g.project.models.mappers.MessageMapper;
import com.dt176g.project.models.validators.MessageValidator;
import com.dt176g.project.utils.Result;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.processors.BehaviorProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Exposes processors for emitting and observing events 
 * related to a chat between a user and a chatbot.
 * 
 * @author Albin Rönnkvist
 */
public class ChatService {
    private final BehaviorProcessor<ContextualResponseWithSuggestions> resetProcessor = 
        BehaviorProcessor.createDefault(ChatModel.getInitialContextualBotResponseWithSuggestions());
    private final PublishProcessor<String> userMessageProcessor = PublishProcessor.create();
    private final PublishProcessor<String> botMessageProcessor = PublishProcessor.create();
    private final PublishProcessor<List<String>> messageSuggestionsProcessor = PublishProcessor.create();
    private final PublishProcessor<Notification> notificationProcessor = PublishProcessor.create();

    private final Flowable<ContextualResponseWithSuggestions> resetStream = resetProcessor
        .observeOn(Schedulers.computation())
        .onBackpressureLatest()
        .replay(1)
        .refCount();

    private final Flowable<Result<Message, ErrorDetail>> userMessageStream = resetStream
        .switchMap(__ -> userMessageProcessor
            .observeOn(Schedulers.computation())
            .throttleFirst(300, TimeUnit.MILLISECONDS)
            .onBackpressureBuffer()
            .filter(MessageValidator::isValid)
            .map(MessageMapper::mapToUserMessage)
            .map(message -> Result.<Message, ErrorDetail>success(message))
        )
        .onErrorReturnItem(Result.<Message, ErrorDetail>failure(new ErrorDetail("An error occurred. Please reset the chat.")))
        .share();

    private final Flowable<Result<Message, ErrorDetail>> botMessageStream = resetStream
        .switchMap(__ -> botMessageProcessor
            .observeOn(Schedulers.computation())
            .onBackpressureBuffer()
            .map(MessageMapper::mapToBotMessage)
            .map(message -> Result.<Message, ErrorDetail>success(message))
        )
        .onErrorReturnItem(Result.<Message, ErrorDetail>failure(new ErrorDetail("An error occurred. Please reset the chat.")))
        .share();

    private final Flowable<List<Notification>> notificationStream = resetStream
        .switchMap(__ -> notificationProcessor
            .observeOn(Schedulers.computation())
            .onBackpressureLatest()
            .buffer(300, TimeUnit.MILLISECONDS)
        )
        .share();

    /**
     * This stream is useful for observing when the chat has been reset.
     * 
     * @return An observable stream of {@link ContextualResponseWithSuggestions} records.
     */
    public Flowable<ContextualResponseWithSuggestions> getResetStream() {
        return resetStream;
    }

    /**
     * Gets an observable stream of user messages.
     * 
     * @return An observable stream of {@link Result} records representing user
     *         {@link Message} on success or {@link ErrorDetail} on failure.
     */
    public Flowable<Result<Message, ErrorDetail>> getUserMessageStream() {
        return userMessageStream;
    }

    /**
     * Gets an observable stream of bot messages.
     * 
     * @return An observable stream of {@link Result} records representing bot
     *         {@link Message} on success or {@link ErrorDetail} on failure.
     */
    public Flowable<Result<Message, ErrorDetail>> getBotMessageStream() {
        return botMessageStream;
    }

    /**
     * Gets an observable stream of pairs of user questions and bot answers.
     * 
     * @return An observable stream of {@link Result} records representing 
     *          user questions with their corresponding chatbot answers ({@link MessagePair}) on success 
     *          or {@link ErrorDetail} on failure.
     */
    public Flowable<Result<MessagePair, ErrorDetail>> getMessagePairStream() {
        return resetStream.switchMap(__ -> 
            Flowable.zip(
                getUserMessageStream(),
                getBotMessageStream(),
                (userResult, botResult) -> Stream.of(userResult, botResult)
                    .filter(Result::isFailure)
                    .map(Result::error)
                    .map(ErrorDetail::message)
                    .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                    .map(errorMessage -> Result.<MessagePair, ErrorDetail>failure(new ErrorDetail(errorMessage)))
                    .orElseGet(() -> Result.<MessagePair, ErrorDetail>success(
                        new MessagePair(userResult.value(), botResult.value())
                    )
            ))
            .onBackpressureBuffer()
            .onErrorReturnItem(Result.<MessagePair, ErrorDetail>failure(new ErrorDetail("An error occurred. Please reset the chat.")))
        );
    }

    /**
     * Returns an observable stream of suggestions for the next message.
     * 
     * The stream begins with the initial suggestions given by the chat model,
     * and then continues with suggestions from the message suggestions subject.
     * 
     * @return An observable stream emitting {@link List} of strings representing
     *         suggestions.
     */
    public Flowable<List<String>> getMessageSuggestionsStream() {
        return getResetStream().map(ContextualResponseWithSuggestions::suggestions)
            .mergeWith(messageSuggestionsProcessor)
            .onBackpressureLatest();
    }

    /**
     * Returns an observable stream of notifications.
     * 
     * @return An observable stream emitting a {@link List} of {@link Notification} records.
     */
    public Flowable<List<Notification>> getNotificationStream() {
        return notificationStream;
    }

    /**
     * Resets the chat.
     */
    public void resetChat() {
        resetProcessor.onNext(ChatModel.getInitialContextualBotResponseWithSuggestions());
    }

    /**
     * Publishes a user message to the user message stream.
     * 
     * @param message The message to be published.
     */
    public void sendUserMessage(String message) {
        userMessageProcessor.onNext(message);
    }
            
    /**
     * Publishes a bot message to the bot message stream.
     * 
     * @param message The message to be published.
     */
    public void sendBotMessage(String message) {
        botMessageProcessor.onNext(message);
    }

    /**
     * Publishes a list of suggestions to the message suggestions stream.
     * 
     * @param suggestions The list of suggestions to be published.
     */
    public void sendMessageSuggestions(List<String> suggestions) {
        messageSuggestionsProcessor.onNext(suggestions);
    }

    /**
     * Publishes a notification to the notification stream.
     *
     * @param notification The {@link Notification} to be published.
     */
    public void sendNotification(Notification notification) {
        notificationProcessor.onNext(notification);
    }
}
