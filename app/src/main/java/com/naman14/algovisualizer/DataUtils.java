package com.naman14.algovisualizer;

import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

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

    public static BinarySearchTree createBinaryTree() {
        BinarySearchTree b = new BinarySearchTree();
        b.insert(3);
        b.insert(8);
        b.insert(1);
        b.insert(4);
        b.insert(6);
        b.insert(2);
        b.insert(10);
        b.insert(9);
        b.insert(15);
        b.insert(12);
        b.insert(16);

        return b;

    }
}
