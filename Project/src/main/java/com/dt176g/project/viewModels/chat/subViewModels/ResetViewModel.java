package com.dt176g.project.viewModels.chat.subViewModels;

import com.dt176g.project.services.ChatService;

/**
 * A view model for the reset chat view.
 * 
 * @author Albin Rönnkvist
 */
public class ResetViewModel {
    private final ChatService chatService;

    public ResetViewModel(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Resets the chat
     */
    public void reset() {
        chatService.resetChat();
    }
}
