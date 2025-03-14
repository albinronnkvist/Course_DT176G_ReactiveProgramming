package com.dt176g.project.viewModels.chat;

import java.util.List;

import com.dt176g.project.models.ErrorDetail;
import com.dt176g.project.models.Notification;
import com.dt176g.project.models.NotificationType;
import com.dt176g.project.models.chat.ChatModel;
import com.dt176g.project.models.chat.ContextualResponse;
import com.dt176g.project.services.ChatService;
import com.dt176g.project.utils.Result;
import com.dt176g.project.viewModels.chat.subViewModels.MessageThreadViewModel;
import com.dt176g.project.viewModels.chat.subViewModels.ResetViewModel;
import com.dt176g.project.viewModels.chat.subViewModels.SendMessageViewModel;

import io.reactivex.rxjava3.core.Flowable;

/**
 * A view model for the chat view.
 * 
 * @author Albin Rönnkvist
 */
public class ChatViewModel {
    private final ChatService chatService;
    public final MessageThreadViewModel messageThreadViewModel;
    public final ResetViewModel resetViewModel;
    public final SendMessageViewModel sendMessageViewModel;

    public ChatViewModel() {
        this.chatService = new ChatService();
        this.messageThreadViewModel = new MessageThreadViewModel(chatService);
        this.resetViewModel = new ResetViewModel(chatService);
        this.sendMessageViewModel = new SendMessageViewModel(chatService);
    }

    /**
     * Connects the chat view model to the chat model, which handles domain-specific logic.
     * 
     * @param chatModel The chat model to connect to.
     */
    public void connectTo(final ChatModel chatModel) {
        var contextualChat = chatService.getResetStream()
            .switchMap(__ -> chatService.getUserMessageStream()
                .scan(
                    Result.<ContextualResponse, ErrorDetail>success(ChatModel.getInitialContextualResponse()),
                    (contextualResponseResult, userMessageResult) -> 
                        Result.<ContextualResponse, ErrorDetail>success(ChatModel.getContextualBotResponse(contextualResponseResult.value(), userMessageResult.value()))
                )
                .skip(1)
                .onErrorReturnItem(Result.<ContextualResponse, ErrorDetail>failure(new ErrorDetail("An error occurred. Please reset the chat.")))
            )
            .publish();

        contextualChat
            .filter(Result::isSuccess)
            .map(Result::value)
            .map(ContextualResponse::response)
            .subscribe(
                chatService::sendBotMessage,
                error -> chatService.sendNotification(new Notification(error.getMessage(), NotificationType.ERROR)));

        contextualChat
            .filter(Result::isSuccess)
            .map(Result::value)
            .map(ChatModel::getMessageSuggestions)
            .retry(3)
            .subscribe(
                chatService::sendMessageSuggestions,
                error -> chatService.sendNotification(new Notification(error.getMessage(), NotificationType.ERROR)));

        contextualChat
            .filter(Result::isFailure)
            .map(Result::error)
            .subscribe(
                error -> chatService.sendNotification(new Notification(error.message(), NotificationType.ERROR)),
                error -> chatService.sendNotification(new Notification(error.getMessage(), NotificationType.ERROR)));

        contextualChat.connect();
    }

    /**
     * Returns an observable stream of notifications.
     * 
     * @return An observable stream emitting lists of {@link Notification} records.
     */
    public Flowable<List<Notification>> getNotificationStream() {
        return chatService.getNotificationStream();
    }
}
