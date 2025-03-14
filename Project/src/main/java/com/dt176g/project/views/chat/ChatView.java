package com.dt176g.project.views.chat;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.List;

import com.dt176g.project.utils.SwingScheduler;
import com.dt176g.project.viewModels.chat.ChatViewModel;
import com.dt176g.project.views.chat.components.Frame;
import com.dt176g.project.views.chat.subViews.MessageThreadView;
import com.dt176g.project.views.chat.subViews.ResetView;
import com.dt176g.project.views.chat.subViews.SendMessageView;
import com.dt176g.project.models.Notification;
import com.dt176g.project.models.NotificationType;

/**
 * The main view for the chat, which acts as a container for the interactive sub views.
 * 
 * @author Albin Rönnkvist
 */
public class ChatView {
    private final JFrame frame;
    private final MessageThreadView messageThreadPanel;
    private final ResetView resetView;
    private final SendMessageView sendMessageView;

    public ChatView() {
        frame = new Frame();

        var containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        sendMessageView = new SendMessageView();
        messageThreadPanel = new MessageThreadView();
        resetView = new ResetView();
        
        
        var inputContainerPanel = new JPanel(new BorderLayout());
        inputContainerPanel.add(sendMessageView, BorderLayout.CENTER);
        inputContainerPanel.add(resetView, BorderLayout.SOUTH);

        containerPanel.add(messageThreadPanel, BorderLayout.CENTER);
        containerPanel.add(inputContainerPanel, BorderLayout.SOUTH);

        frame.add(containerPanel);
        frame.setVisible(true);
    }

    /**
     * Binds the view and its subviews to their corresponding view models.
     * 
     * @param viewModel The {@link ChatViewModel} containing the view models
     *                  to bind to.
     */
    public void bind(final ChatViewModel viewModel) {
        sendMessageView.bind(viewModel.sendMessageViewModel);
        messageThreadPanel.bind(viewModel.messageThreadViewModel);
        resetView.bind(viewModel.resetViewModel);

        viewModel.getNotificationStream()
            .observeOn(SwingScheduler.GET)
            .subscribe(
                notifications -> notifyError(notifications),
                error -> showErrorDialog(error.getMessage())
            );
    }

    /**
     * Sets the main view to be visible.
     */
    public void show() {
        frame.setVisible(true);
    }

    private void notifyError(List<Notification> notifications) {
        notifications.stream()
            .filter(notification -> notification.type() == NotificationType.ERROR)
            .map(Notification::message)
            .distinct()
            .reduce((a, b) -> a + "; " + b)
            .ifPresent(this::showErrorDialog);
    }

    private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
