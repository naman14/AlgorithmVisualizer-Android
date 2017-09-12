/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.algovisualizer.algorithm;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.preference.PreferenceManager;

import com.naman14.algovisualizer.AlgoCompletionListener;
import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.SettingsFragment;

import java.util.concurrent.atomic.AtomicBoolean;

public class Algorithm extends HandlerThread {

    public static final String KEY_ALGORITHM = "key_algorithm";
    public static final String COMMAND_START_ALGORITHM = "start";
    public static final String BUBBLE_SORT = "bubble_sort";
    public static final String INSERTION_SORT = "insertion_sort";
    public static final String SELECTION_SORT = "selection_sort";
    public static final String QUICKSORT = "quicksort";
    public static final String BINARY_SEARCH = "binary_search";
    public static final String LINEAR_SEARCH = "linear_search";
    public static final String BST_INSERT = "bst_insert";
    public static final String BST_SEARCH = "bst_search";
    public static final String LINKED_LIST = "linked_list";
    public static final String STACK = "stack";
    public static final String QUEUE = "queue";
    public static final String BFS = "bfs";
    public static final String DFS = "dfs";
    public static final String DIJKSTRA = "dijkstra";
    public static final String BELLMAN_FORD = "bellman_ford";
    public static final String N_QUEENS = "n_queens";

    public LogFragment logFragment;
    public Activity activity;
    public AlgoCompletionListener completionListener;

    private boolean started;

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    private Handler workerHandler;

    private static int INTERVAL = 500;

    public Algorithm() {
        super("");
    }

    public void sleep() {
        sleepFor(INTERVAL);
    }

    public void sleepFor(long time) {
        try {
            sleep(time);
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

    public void setStarted(boolean started) {
        this.started = started;
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

    public void clearLog() {
        if (logFragment!=null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logFragment.clearLog();
                }
            });
        }
    }

    public void logArray(String message, final int[] array) {
        String arrayString = "";
        for (int i : array) {
            arrayString = arrayString.concat(" " + String.valueOf(i) + " ");
        }
        addLog(message + "[ "+arrayString +" ] total items - "+ array.length);
    }

    public void setCompletionListener(AlgoCompletionListener completionListener) {
        this.completionListener = completionListener;
    }

    public void prepareHandler(final DataHandler dataHandler) {
        workerHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.obj instanceof String) {
                    dataHandler.onMessageReceived((String) msg.obj);
                } else {
                    dataHandler.onDataRecieved(msg.obj);
                }
                return true;
            }
        });

    }

    public void sendData(Object data) {
        workerHandler.obtainMessage(1, data).sendToTarget();
    }

    public void sendMessage(String message) {
        workerHandler.obtainMessage(1, message).sendToTarget();
    }

    public void completed() {
        started = false;
        if (completionListener != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    completionListener.onAlgoCompleted();
                }
            });
        }
    }

    public static void setInterval(int interval) {
        INTERVAL = interval;
    }

}
