package com.dt176g.project.models.chat;

import java.util.List;

/**
 * Record representing a contextual response with suggestions
 * 
 * @author Albin Rönnkvist
 */
public record ContextualResponseWithSuggestions(ContextualResponse contextualResponse, List<String> suggestions) {}