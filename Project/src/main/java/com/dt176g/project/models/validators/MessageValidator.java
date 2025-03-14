package com.dt176g.project.models.validators;

import com.dt176g.project.utils.StringHelper;

/**
 * Validator for messages
 * 
 * @author Albin Rönnkvist
 */
public final class MessageValidator {
    private MessageValidator() {}

    /**
     * Checks if a message is valid.
     * A message is considered valid if it is not null and not blank.
     *
     * @param message the message to validate
     * @return true if the message is valid, false otherwise
     */
    public static boolean isValid(final String message) {
        return !StringHelper.isNullOrBlank(message);
    }
}
