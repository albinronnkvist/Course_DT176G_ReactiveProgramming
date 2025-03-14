package com.dt176g.project.viewModels.chat.subViewModels;

import java.util.List;

import com.dt176g.project.models.Notification;
import com.dt176g.project.services.ChatService;

import io.reactivex.rxjava3.core.Flowable;

/**
 * A view model for the send message view.
 * 
 * @author Albin Rönnkvist
 */
public class SendMessageViewModel {
    private final ChatService chatService;

    public SendMessageViewModel(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Sends a user message
     * 
     * @param message the message to send
     */
    public void sendMessage(String message) {
        chatService.sendUserMessage(message);
    }

    /**
     * A flowable stream of suggestions for the next message.
     * 
     * @return A list of strings representing suggestions.
     */
    public Flowable<List<String>> getMessageSuggestions() {
        return chatService.getMessageSuggestionsStream();
    }

    /**
     * Sends a notification to the chat service.
     * 
     * @param notification the notification to send
     */
    public void sendNotification(Notification notification) {
        chatService.sendNotification(notification);
    }
}
