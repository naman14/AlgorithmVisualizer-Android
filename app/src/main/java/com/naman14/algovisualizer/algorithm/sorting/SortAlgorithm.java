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

package com.naman14.algovisualizer.algorithm.sorting;

import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

public class SortAlgorithm extends Algorithm implements DataHandler {

    public SortingVisualizer visualizer;

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

    public void highlightSwap(final int one, final int two) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightSwap(one, two);
            }
        });
    }

    public void highlightTrace(final int position) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightTrace(position);
            }
        });
    }

    @Override
    public void onDataRecieved(Object data) {

    }

    @Override
    public void onMessageReceived(String message) {

    }
}
