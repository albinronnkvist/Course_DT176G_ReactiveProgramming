package com.dt176g.project.views.chat.subViews;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.dt176g.project.viewModels.chat.subViewModels.ResetViewModel;
import com.dt176g.project.views.chat.components.ResetButton;

/**
 * The reset view, containing controls to reset the chat.
 * 
 * @author Albin Rönnkvist
 */
public class ResetView extends JPanel {
    private ResetButton resetButton = new ResetButton();

    public ResetView() {
        setLayout(new BorderLayout(10, 0));
        add(resetButton, BorderLayout.CENTER);
    }

    /**
     * Binds the view to its corresponding view model.
     * 
     * @param viewModel The {@link ResetViewModel} to bind to.
     */
    public void bind(final ResetViewModel viewModel) {
        resetButton.addActionListener(e -> viewModel.reset());
    }
}
