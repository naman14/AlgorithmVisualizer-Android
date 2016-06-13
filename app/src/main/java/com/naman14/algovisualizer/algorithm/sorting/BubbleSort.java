package com.naman14.algovisualizer.algorithm.sorting;

import android.app.Activity;
import android.os.Handler;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

/**
 * Created by naman on 02/06/16.
 */
public class BubbleSort extends SortAlgorithm implements DataHandler {

    int[] array;

    public BubbleSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment, Handler.Callback callback) {
        super(callback);
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void run() {
        super.run();
    }

    private void sort() {
        logArray(array);

        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            highlight(m);
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    addLog("Swapping " + i + " and " + k);
                    int temp;
                    temp = array[i];
                    array[i] = array[k];
                    array[k] = temp;
                }
            }
            sleep();
        }
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
        if (message.equals("start")) {
            startExecution();
            sort();
        }
    }
}
