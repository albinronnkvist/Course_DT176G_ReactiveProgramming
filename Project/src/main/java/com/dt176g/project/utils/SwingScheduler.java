package com.dt176g.project.utils;

import javax.swing.SwingUtilities;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A utility class for scheduling Swing events.
 * 
 * @author Albin Rönnkvist
 */
public final class SwingScheduler {
    private SwingScheduler() {}

    /**
     * A {@link Scheduler} instance that schedules tasks 
     * to be executed on the Swing Event Dispatch Thread.
     */
    public static final Scheduler GET = Schedulers.from(SwingUtilities::invokeLater);
}
