package com.naman14.algovisualizer.algorithm;

import com.naman14.algovisualizer.LogFragment;

/**
 * Created by naman on 03/06/16.
 */
public class Algorithm extends Thread {

    public LogFragment logFragment;

    private boolean paused;

    public void sleep() {
        try {
            sleep(500);
            if (paused)
                sleep();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void pause() {
        paused = true;
        sleep();
    }

    public void resumeExecution() {
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void addLog(String log) {
        if (logFragment != null)
            logFragment.addLog(log);
    }
}
