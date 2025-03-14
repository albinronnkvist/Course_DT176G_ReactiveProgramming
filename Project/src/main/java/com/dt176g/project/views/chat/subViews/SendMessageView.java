package com.dt176g.project.views.chat.subViews;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.dt176g.project.models.Notification;
import com.dt176g.project.models.NotificationType;
import com.dt176g.project.utils.SwingScheduler;
import com.dt176g.project.viewModels.chat.subViewModels.SendMessageViewModel;
import com.dt176g.project.views.chat.components.MessageSuggestionButton;
import com.dt176g.project.views.chat.components.SendMessageButton;
import com.dt176g.project.views.chat.components.SendMessageTextField;

/**
 * The message input panel for sending user messages.
 * 
 * @author Albin Rönnkvist
 */
public class SendMessageView extends JPanel {
    private SendMessageTextField messageTextField = new SendMessageTextField();
    private SendMessageButton sendButton = new SendMessageButton();
    private JPanel messageSuggestionsPanel = new JPanel();

    public SendMessageView() {
        setLayout(new BorderLayout(10, 0));
        
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.DARK_GRAY);

        messageSuggestionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        messageSuggestionsPanel.setBackground(Color.DARK_GRAY);
        
        add(messageSuggestionsPanel, BorderLayout.NORTH);
        add(messageTextField, BorderLayout.CENTER);
        add(sendButton, BorderLayout.EAST);
    }

    /**
     * Binds the send message view to its corresponding view model.
     * 
     * @param viewModel The {@link SendMessageViewModel} to bind to.
     */
    public void bind(final SendMessageViewModel viewModel) {
        sendButton.addActionListener(e -> {
            sendMessageActionListener(viewModel, getMessage());
        });

        messageTextField.addActionListener(e -> {
            sendMessageActionListener(viewModel, getMessage());
        });

        viewModel.getMessageSuggestions()
            .observeOn(SwingScheduler.GET)
            .subscribe(
                messages -> addMessageSuggestions(viewModel, messages),
                error -> viewModel.sendNotification(new Notification(error.getMessage(), NotificationType.ERROR)));        
    }

    private String getMessage() {
        return messageTextField.getText();
    }

    private void clearMessage() {
        messageTextField.setText("");
    }

    private void addMessageSuggestions(final SendMessageViewModel viewModel, final List<String> messages) {
        clearMessageSuggestions(); 
        messages.forEach(message -> addMessageSuggestion(viewModel, message));
        messageSuggestionsPanel.revalidate();
        messageSuggestionsPanel.repaint();
    }

    private void addMessageSuggestion(final SendMessageViewModel viewModel, final String message) {
        var button = new MessageSuggestionButton(message);
        button.addActionListener(e -> sendMessageActionListener(viewModel, message));
        messageSuggestionsPanel.add(button);
    }

    private void clearMessageSuggestions() {
        messageSuggestionsPanel.removeAll();
        messageSuggestionsPanel.revalidate();
        messageSuggestionsPanel.repaint();
    }

    private void sendMessageActionListener(final SendMessageViewModel viewModel, final String message) {
        viewModel.sendMessage(message);
        clearMessage();
        clearMessageSuggestions();
    }
}
