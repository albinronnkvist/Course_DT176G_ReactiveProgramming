package com.dt176g.project.views.chat.subViews;

import java.awt.*;
import javax.swing.*;

import com.dt176g.project.models.Message;
import com.dt176g.project.models.MessagePair;
import com.dt176g.project.models.Notification;
import com.dt176g.project.models.NotificationType;
import com.dt176g.project.utils.SwingScheduler;
import com.dt176g.project.viewModels.chat.subViewModels.MessageThreadViewModel;
import com.dt176g.project.views.chat.components.MessagePanel;

import com.dt176g.project.models.ErrorDetail;
import com.dt176g.project.utils.Result;

/**
 * The message thread view, containing the full message history for an ongoing chat.
 * 
 * @author Albin Rönnkvist
 */
public class MessageThreadView extends JPanel {
    private JPanel containerPanel = new JPanel();

    public MessageThreadView() {
        setLayout(new BorderLayout());

        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        var scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        add(scrollPane);
    }

    /**
     * Binds the view to its corresponding view model.
     * 
     * @param viewModel The {@link MessageThreadViewModel} to bind to.
     */
    public void bind(final MessageThreadViewModel viewModel) {
        viewModel.onNewMessagePair()
            .observeOn(SwingScheduler.GET)
            .subscribe(
                result -> tryAddMessagePair(result, viewModel),
                error -> viewModel.sendNotification(new Notification(error.getMessage(), NotificationType.ERROR))
            );

        viewModel.onReset()
            .observeOn(SwingScheduler.GET)
            .subscribe(
                resetMessage -> resetMessageThread(resetMessage),
                error -> viewModel.sendNotification(new Notification(error.getMessage(), NotificationType.ERROR))
            );
    }

    private void tryAddMessagePair(Result<MessagePair, ErrorDetail> messagePairResult, MessageThreadViewModel viewModel) {
        if(messagePairResult.isFailure()) {
            viewModel.sendNotification(new Notification(messagePairResult.error().message(), NotificationType.ERROR));
        } else {
            addMessagePair(messagePairResult.value());
        }
    }

    private void addMessagePair(MessagePair messagePair) {
        containerPanel.add(new MessagePanel(messagePair.userMessage()));
        containerPanel.add(new MessagePanel(messagePair.botMessage()));
        revalidate();
        repaint();
    }

    private void resetMessageThread(Message message) {
        containerPanel.removeAll();
        containerPanel.add(new MessagePanel(message));
        revalidate();
        repaint();
    }
}