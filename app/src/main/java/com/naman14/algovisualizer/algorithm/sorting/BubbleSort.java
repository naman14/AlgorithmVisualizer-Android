package com.naman14.algovisualizer.algorithm.sorting;

import com.naman14.algovisualizer.DataUtils;

/**
 * Created by naman on 02/06/16.
 */
public class BubbleSort {

    public static void run() {

        int array[] = DataUtils.createRandomArray(15);
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
        }
    }

    private static void swapNumbers(int i, int j, int[] array) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
