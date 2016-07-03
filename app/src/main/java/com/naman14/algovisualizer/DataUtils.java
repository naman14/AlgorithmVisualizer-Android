package com.naman14.algovisualizer;

import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

import java.util.Random;

/**
 * Created by naman on 02/06/16.
 */
public class DataUtils {

    public static final int[] bst_array = {0, 1, 2, 3, 4, 5, 8, 6, 7, 10, 9};

    public static int[] createRandomArray(int size) {
        int[] integers = new int[size];
        for (int i = 0; i < size; i++) {
            integers[i] = new Random().nextInt(8) + 1;
        }
        return integers;
    }

    public static BinarySearchTree createBinaryTree() {
        BinarySearchTree b = new BinarySearchTree();
        for (int i = 0; i < bst_array.length; i++) {
            b.insert(bst_array[i]);
        }
        return b;

    }

    public static int getRandomKeyFromBST() {
        int rnd = new Random().nextInt(bst_array.length);
        return bst_array[rnd];
    }
}
