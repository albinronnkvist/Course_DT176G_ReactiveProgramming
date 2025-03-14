package com.dt176g.project;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.dt176g.project.models.chat.ChatModel;
import com.dt176g.project.viewModels.chat.ChatViewModel;
import com.dt176g.project.views.chat.ChatView;

/**
 * The main class for the application.
 * Manages the MVVM setup and starts the application.
 * 
 * @author Albin Rönnkvist
 */
public final class Project {
    private Project() {
        throw new IllegalStateException("Utility class");
    }

    
    /**
     * Main entry point for the application.
     * @param args The command line arguments.
     */
    public static void main(final String... args) {
        var chatModel = new ChatModel();
        var chatViewModel = new ChatViewModel(); 
        chatViewModel.connectTo(chatModel);
        var chatView = new ChatView();
        chatView.bind(chatViewModel);

        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				chatView.show();
			}
		});
    }
}
