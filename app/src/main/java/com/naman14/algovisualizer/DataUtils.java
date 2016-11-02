package com.naman14.algovisualizer;

import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

import java.util.Random;

/**
 * Created by naman on 02/06/16.
 */
public class DataUtils {

    public static final int[] bst_array = {5, 8, 10, 3, 1, 6, 9, 7, 2, 0, 4};

    public static final int[][] bst = {
            {5, 8, 10, 3, 1, 6, 9, 7, 2, 0, 4}, //nodes
            {3, 6, 9, 1, 0, 7, -1, -1, -1, -1, -1}, //left child of nodes
            {8, 10, 9, 4, 2, 7, -1, -1, -1, -1, -1} //right child of nodes
    };

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
