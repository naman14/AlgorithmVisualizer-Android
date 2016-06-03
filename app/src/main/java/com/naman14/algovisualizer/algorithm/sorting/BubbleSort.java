package com.naman14.algovisualizer.algorithm.sorting;

import android.app.Activity;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.algorithm.SortAlgorithm;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

/**
 * Created by naman on 02/06/16.
 */
public class BubbleSort extends SortAlgorithm {

    int m;

    public BubbleSort(SortingVisualizer visualizer, Activity activity) {
        this.visualizer = visualizer;
        this.activity = activity;
    }

    @Override
    public void run() {
        super.run();
        int[] array = DataUtils.createRandomArray(15);
        setData(array);

        int n = array.length;
        int k;
        for (m = n; m >= 0; m--) {
            highlight(m);
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
            sleep();
        }
    }


    private static void swapNumbers(int i, int j, int[] array) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
