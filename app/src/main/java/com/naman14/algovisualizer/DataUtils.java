package com.naman14.algovisualizer;

import android.content.Context;

import com.naman14.algovisualizer.algorithm.graph.Digraph;
import com.naman14.algovisualizer.algorithm.graph.WeightedGraph;
import com.naman14.algovisualizer.algorithm.list.linkedlist.LinkedList;
import com.naman14.algovisualizer.algorithm.list.stack.Stack;
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

    public static Stack createStack() {
        Stack stack = new Stack(8);
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 10; i <= 13; i++) {
            a.add(i);
        }
        Collections.shuffle(a);

        for (int i = 0; i < a.size(); i++) {
            stack.push(a.get(i));
        }
        return stack;

    }

    public static Digraph createDirectedGraph() {
        Digraph graph = new Digraph();
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(1, 3);
        graph.add(3, 7);
        graph.add(3, 8);
        graph.add(1, 4);
        graph.add(4, 9);
        graph.add(4, 10);
        graph.add(2, 5);
        graph.add(2, 6);

        double[][] array = {
                {0, 2, 6, 5, 1, 4, 10, 9, 3, 8, 7}, //nodes
                {1, 5, -1, -1, 3, 9, -1, -1, 7, -1, -1},//left child
                {2, 6, -1, -1, 4, 10, -1, -1, 8, -1, -1}, //right child
                {0, 1.5, 2.5, 1, 1.5, 0.5, 0, 1, 2.5, 2, 3}, //number of left/right nodes from root in horizontal
                {0, 1, 2.5, 2.5, 1, 2, 3, 3, 2, 3, 3}, //number of left/right nodes from root in vertical
                {-1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1} //is the node left of the root?
        };
        graph.setDirectedArray(array);
        return graph;
    }

    public static WeightedGraph createWeightedGraph(int vertex) {

        int E = 8;

        WeightedGraph graph = new WeightedGraph(vertex, E);

        graph.addEdge(0, 0, 1, 1);
        graph.addEdge(1, 0, 2, 4);
        graph.addEdge(2, 1, 2, 3);
        graph.addEdge(3, 1, 3, 2);
        graph.addEdge(4, 1, 4, 2);
        graph.addEdge(5, 3, 2, 5);
        graph.addEdge(6, 0, 4, 1);
        graph.addEdge(7, 4, 3, 3);

        return graph;

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

    public static int getRandomUniqueInt(int range, int[] array) {
        Random random = new Random();
        int number = random.nextInt(range) + array.length;
        return number;
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
