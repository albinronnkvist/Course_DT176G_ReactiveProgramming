package com.dt176g.project.views.chat.components;

import java.awt.*;
import javax.swing.*;

/**
 * An input button for sending user messages.
 * 
 * @author Albin Rönnkvist
 */
public class SendMessageButton extends JButton {
    public SendMessageButton() {
        setText("Send");
        setFocusPainted(false);
        setBackground(Color.BLUE); 
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(true);
        setBorderPainted(false);
    }
}
