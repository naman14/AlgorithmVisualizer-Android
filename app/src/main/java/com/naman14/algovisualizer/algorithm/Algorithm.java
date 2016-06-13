package com.naman14.algovisualizer.algorithm;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.naman14.algovisualizer.AlgoCompletionListener;
import com.naman14.algovisualizer.LogFragment;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by naman on 03/06/16.
 */
public class Algorithm extends HandlerThread {

    public LogFragment logFragment;
    public Activity activity;
    public AlgoCompletionListener completionListener;

    private boolean started;

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    private Handler responseHandler;
    private Handler workerHandler;
    private Handler.Callback callback;
    private DataHandler dataHandler;


    public Algorithm(Handler.Callback callback) {
        super("");
        this.callback = callback;
    }

    public void sleep() {
        try {
            sleep(1000);
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
                    int[] object = (int[]) msg.obj;
                    dataHandler.onDataRecieved(object);
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
}
