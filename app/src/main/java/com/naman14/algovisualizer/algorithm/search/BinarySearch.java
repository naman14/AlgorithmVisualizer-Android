
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

package com.naman14.algovisualizer.algorithm.search;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.BinarySearchVisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearch extends Algorithm implements DataHandler {

    private BinarySearchVisualizer visualizer;
    private int[] array;

    private List<Integer> positions = new ArrayList<>();

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

        logArray("Array - ", array);

        int rnd = new Random().nextInt(array.length);
        int data = array[rnd];
        addLog("Searching for " + data);

        int low = 0;
        int high = array.length - 1;

        highlight((low + high) / 2, false, false);
        sleepFor(1000);

        while (high >= low) {

            int middle = (low + high) / 2;
            addLog("Searching at index: " + middle);
            highlightTrace(middle);

            if (array[middle] == data) {
                //found
                addLog(data + " is found at position " + middle);
                break;
            }
            if (array[middle] < data) {
                low = middle + 1;
                addLog("Going right");
                highlight(middle, false, true);
                sleepFor(1000);
            }
            if (array[middle] > data) {
                high = middle - 1;
                addLog("Going left");
                highlight(middle, true, false);
                sleepFor(1000);
            }
        }
        completed();
    }

    private void highlight(int middle, boolean left, boolean right) {
        positions.clear();
        if (!left && !right) {
            for (int i = 0; i < array.length; i++) {
                positions.add(i);
            }
        } else {
            if (left) {
                for (int i = 0; i < middle; i++) {
                    positions.add(i);
                }
            } else {
                for (int i = middle; i < array.length; i++) {
                    positions.add(i);
                }
            }
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
