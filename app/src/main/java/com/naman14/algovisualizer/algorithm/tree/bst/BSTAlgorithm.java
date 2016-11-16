/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.algovisualizer.algorithm.tree.bst;

import android.app.Activity;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.ArrayVisualizer;
import com.naman14.algovisualizer.visualizer.BSTVisualizer;

public class BSTAlgorithm extends Algorithm implements DataHandler {

    public static final String START_BST_SEARCH = "start_bst_search";
    public static final String START_BST_INSERT = "start_bst_insert";

    private BSTVisualizer visualizer;
    private ArrayVisualizer arrayVisualizer;
    public BinarySearchTree b;

    public BSTAlgorithm(BSTVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void run() {
        super.run();
    }

    private void startBSTSearch() {
        int id = DataUtils.getRandomKeyFromBST();
        addLog("Searching for " + String.valueOf(id));
        BinarySearchTree.Node current = b.getRoot();
        addLog("Starting from root: " + current.data);
        highlightNode(current.data);
        sleep();
        while (current != null) {
            if (current.data == id) {
                addLog("Key " + String.valueOf(id) + " found in binary search tree");
                completed();
                break;
            } else if (current.data > id) {
                addLog("Going from " + current.data + " to " + current.left.data);
                highlightLine(current.data, current.left.data);
                current = current.left;
                highlightNode(current.data);
                sleep();
            } else {
                addLog("Going from " + current.data + " to " + current.right.data);
                highlightLine(current.data, current.right.data);
                current = current.right;
                highlightNode(current.data);
                sleep();
            }
        }

    }

    private void startBSTInsert() {
        int[] array = DataUtils.bst_array;
        BinarySearchTree tree = new BinarySearchTree();
        logArray("Items to be inserted - ",array);
        removeAllNodes();
        sleepFor(800);

        for (int i = 0; i < array.length; i++) {
            addLog("Inserting " + array[i] + " in the binary tree");
            tree.insert(array[i]);
            addNode(array[i]);
            highlightNode(array[i]);
            sleepFor(800);
        }

        highlightNode(-1);
        completed();
    }

    @Override
    public void onDataRecieved(Object data) {
        this.b = (BinarySearchTree) data;
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(START_BST_SEARCH)) {
            startExecution();
            startBSTSearch();
        } else if (message.equals(START_BST_INSERT)) {
            startExecution();
            startBSTInsert();
        }
    }

    public void setData(final BinarySearchTree b) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(b);
                if (arrayVisualizer!=null) {
                    arrayVisualizer.setData(DataUtils.bst_array);
                }
            }
        });
        start();
        prepareHandler(this);
        sendData(b);
    }

    private void highlightNode(final int node) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightNode(node);
                if (arrayVisualizer!=null) {
                    arrayVisualizer.highlightValue(node);
                }
            }
        });
    }

    private void highlightLine(final int start, final int end) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightLine(start, end);
            }
        });
    }

    private void removeAllNodes() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.removeAllNodes();
            }
        });
    }

    private void addNode(final int n) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.addNode(n);
            }
        });
    }


    public void setArrayVisualizer(ArrayVisualizer arrayVisualizer) {
        this.arrayVisualizer = arrayVisualizer;
    }
}
