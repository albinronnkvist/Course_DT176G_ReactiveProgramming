package com.dt176g.project.views.chat.components;

import java.awt.*;
import javax.swing.*;

/**
 * The message input text field for sending user messages.
 * 
 * @author Albin Rönnkvist
 */
public class SendMessageTextField extends JTextField {
    public SendMessageTextField() {
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setCaretColor(Color.WHITE);
        setBorder(null);
    }
}
