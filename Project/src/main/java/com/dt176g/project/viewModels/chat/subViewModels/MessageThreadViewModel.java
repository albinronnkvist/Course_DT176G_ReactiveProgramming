package com.dt176g.project.viewModels.chat.subViewModels;

import com.dt176g.project.models.Message;
import com.dt176g.project.models.MessagePair;
import com.dt176g.project.models.Notification;
import com.dt176g.project.models.mappers.MessageMapper;
import com.dt176g.project.services.ChatService;
import com.dt176g.project.models.ErrorDetail;
import com.dt176g.project.utils.Result;

import io.reactivex.rxjava3.core.Flowable;

/**
 * A view model for the message thread view.
 * 
 * @author Albin Rönnkvist
 */
public class MessageThreadViewModel {
    private final ChatService chatService;

    public MessageThreadViewModel(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * A stream of question-response pairs.
     * 
     * @return A {@link MessagePair} record, representing a question with its corresponding answer.
     */
    public Flowable<Result<MessagePair, ErrorDetail>> onNewMessagePair() {
        return chatService.getMessagePairStream();
    }

    /**
     * A stream of reset messages, representing the intitial message after the chat is reset.
     * 
     * @return A {@link Message} record, representing the reset message.
     */
    public Flowable<Message> onReset() {
        return chatService.getResetStream()
            .map(res -> MessageMapper.mapToBotMessage(res.contextualResponse().response()));
    }

    /**
     * Sends a notification to the chat service.
     * 
     * @param notification The {@link Notification} to be sent.
     */
    public void sendNotification(Notification notification) {
        chatService.sendNotification(notification);
    }
}
