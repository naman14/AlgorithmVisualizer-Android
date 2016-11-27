package com.naman14.algovisualizer.algorithm.search;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.BinarySearchVisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mayank on 27/11/16.
 */

public class LinearSearch extends Algorithm implements DataHandler {

    private BinarySearchVisualizer visualizer;
    private int[] array;

    private List<Integer> positions = new ArrayList<>();

    public LinearSearch(BinarySearchVisualizer visualizer, Activity activity, LogFragment logFragment) {
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

        logArray("Array - ", array);

        int rnd = new Random().nextInt(array.length);
        int data = array[rnd];
        addLog("Searching for - " + data);

        int n = array.length ;

        for (int i = 0 ; i<n ; i++) {

            highlight(0, i-1);
            highlightTrace(i);
            addLog("Searching at index - " + i);

            if (array[i] == data){
                addLog("Result - True");
                addLog("Value found at position - " + i);
                break;
            }

            addLog("Result - False");

            sleep();

        }

        completed();
    }

    private void highlight(int minIndex, int maxIndex) {
        positions.clear();
        for (int i = minIndex; i <= maxIndex; i++) {
            positions.add(i);
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlight(positions);
            }
        });
    }

    private void highlightTrace(final int pos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightTrace(pos);
            }
        });
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