package com.naman14.algovisualizer.algorithm;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by naman on 03/06/16.
 */
public class Algorithm extends Thread {

    public LogFragment logFragment;
    public Activity activity;

    private boolean started;

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    public void sleep() {
        try {
            sleep(500);
            if (isPaused())
                pauseExecution();
            else resumeExecution();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void startExecution() {
        started = true;
        start();
    }

    public void setPaused(boolean b) {
        paused.set(b);
        if (!b) {
            synchronized (getPauseLock()) {
                getPauseLock().notify();
            }
        }
    }

    private void pauseExecution() {
        if (paused.get()) {
            synchronized (getPauseLock()) {
                if (paused.get()) {
                    try {
                        getPauseLock().wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }

    private void resumeExecution() {
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    Object getPauseLock() {
        return pauseLock;
    }


    public boolean isPaused() {
        return paused.get();
    }

    public boolean isStarted() {
        return started;
    }

    public void addLog(final String log) {
        if (logFragment != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logFragment.addLog(log);
                }
            });
        }
    }
}
