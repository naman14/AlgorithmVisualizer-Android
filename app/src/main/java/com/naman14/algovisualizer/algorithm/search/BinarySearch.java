package com.naman14.algovisualizer.algorithm.search;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.BinarySearchVisualizer;

import java.util.Random;

/**
 * Created by naman on 14/11/16.
 */

public class BinarySearch extends Algorithm implements DataHandler {

    private BinarySearchVisualizer visualizer;
    private int[] array;

    public BinarySearch(BinarySearchVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    public void setData(final int[] array) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(array);
            }
        });
        start();
        prepareHandler(this);
        sendData(array);
    }

    private void search() {
        int rnd = new Random().nextInt(array.length);
        int data = array[rnd];
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            search();
        }
    }

    @Override
    public void onDataRecieved(Object data) {
        this.array = (int[]) data;
    }
}
