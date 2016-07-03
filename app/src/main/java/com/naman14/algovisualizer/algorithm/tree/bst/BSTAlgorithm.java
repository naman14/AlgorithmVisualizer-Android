package com.naman14.algovisualizer.algorithm.tree.bst;

import android.app.Activity;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.BSTVisualizer;

/**
 * Created by naman on 03/07/16.
 */
public class BSTAlgorithm extends Algorithm implements DataHandler {

    public static final String START_BST_SEARCH = "start_bst_search";
    public static final String START_BST_INSERT = "start_bst_insert";

    public BSTVisualizer visualizer;
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
        addLog("Starting from root: "+ current.data);
        while (current != null) {
            if (current.data == id) {
                addLog("Key" + String.valueOf(id) + " found in binary search tree");
                break;
            } else if (current.data > id) {
                current = current.left;
                addLog("Going from "+ current.data+ " to "+current.left);
            } else {
                current = current.right;
                addLog("Going from "+ current.data+ " to "+current.right);
            }
        }
        addLog("Key" + String.valueOf(id) + " does not exist in Binary search tree");


    }

    private void startBSTInsert() {

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
            }
        });
        start();
        prepareHandler(this);
        sendData(b);
    }
}
