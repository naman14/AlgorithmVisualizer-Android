package com.naman14.algovisualizer.algorithm;

import android.app.Activity;

import com.naman14.algovisualizer.visualizer.SortingVisualizer;

/**
 * Created by naman on 02/06/16.
 */
public class SortAlgorithm extends Thread {

    public SortingVisualizer visualizer;
    public Activity activity;

    public void setData(final int[] array) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(array);
            }
        });
    }

    public void highlight(final int pos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlight(pos);
            }
        });
    }

    public void sleep() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
