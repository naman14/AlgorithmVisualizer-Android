package com.naman14.algovisualizer;

import java.util.Random;

/**
 * Created by naman on 02/06/16.
 */
public class DataUtils {

    public static int[] createRandomArray(int size) {
        int[] integers = new int[size];
        for (int i = 0; i < size; i++) {
            integers[i] = new Random().nextInt(8) + 1;
        }
        return integers;
    }
}
