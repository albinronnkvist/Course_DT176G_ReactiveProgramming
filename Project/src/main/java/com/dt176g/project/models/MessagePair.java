package com.dt176g.project.models;

/**
 * Record representing a message pair, containing a user question with a corresponding bot answer.
 * 
 * @author Albin Rönnkvist
 */
public record MessagePair(Message userMessage, Message botMessage) {}