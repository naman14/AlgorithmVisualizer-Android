package com.naman14.algovisualizer.algorithm.sorting;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

/**
 * Created by amit on 21/11/16.
 */

public class SelectionSort extends SortAlgorithm {

    int[] array;

    public SelectionSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void run() {
        super.run();
    }

    private void sort() {
        logArray("Original array - " ,array);

        int n = array.length;
        for (int j = 0; j < n-1; j++) {
            int min_idx = j;
            for (int i = j+1; i < n; i++)
                if (array[i] < array[min_idx]) {
                    highlightSwap(min_idx,i);
                    min_idx = i;
                }
            addLog("Swapping " + array[j] + " and " + array[min_idx]);
            int temp = array[min_idx];
            array[min_idx] = array[j];
            sleep();
            array[j] = temp;
        }
        addLog("Array has been sorted");
        completed();
    }

    @Override
    public void onDataRecieved(Object data) {
        super.onDataRecieved(data);
        this.array = (int[]) data;
    }

    @Override
    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            sort();
        }
    }
}
