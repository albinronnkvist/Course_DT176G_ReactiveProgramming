package com.dt176g.project.views.chat.components;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * An input button for resetting the chat.
 * 
 * @author Albin Rönnkvist
 */
public class ResetButton extends JButton {
    public ResetButton() {
        setText("Reset");
        setFocusPainted(false);
        setBackground(Color.RED); 
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(true);
        setBorderPainted(false);
    }
}
