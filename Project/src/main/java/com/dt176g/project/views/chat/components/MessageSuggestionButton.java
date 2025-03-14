package com.dt176g.project.views.chat.components;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

/**
 * A button for displaying a clickable message suggestion.
 * 
 * @author Albin Rönnkvist
 */
public class MessageSuggestionButton extends JButton {
    public MessageSuggestionButton(String message) {
        setText(message);
        setFocusPainted(false);
        setBackground(Color.BLUE); 
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(true);
        setBorderPainted(false);
    }
}
