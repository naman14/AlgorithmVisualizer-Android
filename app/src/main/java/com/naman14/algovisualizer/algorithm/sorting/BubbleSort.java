package com.naman14.algovisualizer.algorithm.sorting;

import android.app.Activity;
import android.os.Handler;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

/**
 * Created by naman on 02/06/16.
 */
public class BubbleSort extends SortAlgorithm {

    int[] array;

    public BubbleSort(SortingVisualizer visualizer, Activity activity, LogFragment logFragment, Handler handler, Handler.Callback callback) {
        super(handler, callback);
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    public void setArray(int[] array) {
        this.array = array;
        setData(array);
    }

    @Override
    public void run() {
        super.run();
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
}
