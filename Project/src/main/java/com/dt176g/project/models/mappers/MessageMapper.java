package com.dt176g.project.models.mappers;

import com.dt176g.project.models.Message;
import com.dt176g.project.models.SenderType;

/**
 * Mapper for mapping to messages.
 * 
 * @author Albin Rönnkvist
 */
public final class MessageMapper {
    private MessageMapper() {}

    /**
     * Maps a string to a bot message.
     *
     * @param message the string to map
     * @return the bot message
     */
    public static Message mapToBotMessage(String message) {
        return new Message(message, SenderType.BOT);
    }

    /**
     * Maps a string to a user message.
     *
     * @param message the string to map
     * @return the user message
     */
    public static Message mapToUserMessage(String message) {
        return new Message(message, SenderType.USER);
    }
}
