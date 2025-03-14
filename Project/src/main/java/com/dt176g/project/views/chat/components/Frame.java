package com.dt176g.project.views.chat.components;

import javax.swing.*;

/**
 * A frame which is used as the main container for the chat view.
 * 
 * @author Albin Rönnkvist
 */
public class Frame extends JFrame {
    public Frame() {
        setTitle("CS Chatbot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
    }
}
