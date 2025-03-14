package com.dt176g.project.views.chat.components;

import java.awt.*;
import javax.swing.*;
import com.dt176g.project.models.Message;
import com.dt176g.project.models.SenderType;


/**
 * The message panel for displaying a single message as text a bubble.
 * 
 * @author Albin Rönnkvist
 */
public class MessagePanel extends JPanel {
    public MessagePanel(Message message) {
        setLayout(new BorderLayout());

        var messageArea = new JTextArea(message.text());
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBackground(getColor(message.sender()));
        messageArea.setForeground(Color.WHITE);
        messageArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        var wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperPanel.add(messageArea);

        add(wrapperPanel, getDirection(message.sender()));
    }

    private Color getColor(SenderType senderType) {
        switch (senderType) {
            case SenderType.USER: 
                return Color.BLUE;
            case SenderType.BOT: 
                return Color.DARK_GRAY;
            default:
                throw new IllegalArgumentException("Unknown sender type: " + senderType);
        }
    }

    private String getDirection(SenderType senderType) {
        switch (senderType) {
            case SenderType.USER: 
                return BorderLayout.EAST;
            case SenderType.BOT: 
                return BorderLayout.WEST;
            default:
                throw new IllegalArgumentException("Unknown sender type: " + senderType);
        }
    }
}
