package com.naman14.algovisualizer;

import android.content.Context;

import com.naman14.algovisualizer.algorithm.list.linkedlist.LinkedList;
import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by naman on 02/06/16.
 */
public class DataUtils {

    public static final int[] bst_array = {5, 8, 10, 3, 1, 6, 9, 7, 2, 0};

    public static final int[][] bst = {
            {5, 8, 10, 3, 1, 6, 9, 7, 2, 0}, //nodes
            {3, 6, 9, 1, 0, 7, -1, -1, -1, -1}, //left child of nodes
            {8, 10, 9, -1, 2, 7, -1, -1, -1, -1} //right child of nodes
    };

    public static int[] createRandomArray(int size) {

        int[] integers = new int[size];
        for (int i = 0; i < size; i++) {
            integers[i] = new Random().nextInt(8) + 1;
        }
        return integers;
    }

    public static int[] createUniqueRandomArray(int size) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            a.add(i);
        }
        Collections.shuffle(a);

        int[] intArray = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            intArray[i] = a.get(i);
        }
        return intArray;
    }

    public static BinarySearchTree createBinaryTree() {
        BinarySearchTree b = new BinarySearchTree();
        for (int i = 0; i < bst_array.length; i++) {
            b.insert(bst_array[i]);
        }
        return b;

    }

    public static LinkedList createLinkedList() {
        LinkedList ll = new LinkedList();
        int[] array = createUniqueRandomArray(5);
        for (int i = 0; i < array.length; i++) {
            ll.add(array[i]);
        }
        return ll;

    }

    public static int[] createSortedArray(int size) {
        int[] integers = new int[size];
        for (int i = 0; i < size; i++) {
            integers[i] = new Random().nextInt(88) + 10;
        }
        Arrays.sort(integers);
        return integers;
    }

    public static int getRandomKeyFromBST() {
        int rnd = new Random().nextInt(bst_array.length);
        return bst_array[rnd];
    }

    public static int getRandomInt(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static String readDescJson(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("desc.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
