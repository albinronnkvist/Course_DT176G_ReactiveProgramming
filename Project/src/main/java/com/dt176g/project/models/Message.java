package com.dt176g.project.models;

/**
 * Record representing a message, sent by the specified sender type.
 * 
 * @author Albin Rönnkvist
 */
public record Message(String text, SenderType sender) {}
