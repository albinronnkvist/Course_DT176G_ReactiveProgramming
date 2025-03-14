package com.dt176g.project.models.chat;

import com.dt176g.project.models.chat.contexts.Context;

/**
 * Record representing a contextual response, including the context category and the bot's response message.
 * 
 * @author Albin Rönnkvist
 */
public record ContextualResponse(Context context, String response) {}