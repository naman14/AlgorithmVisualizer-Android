package com.naman14.algovisualizer.algorithm.graph;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.graph.DirectedGraphVisualizer;

/**
 * Created by naman on 16/11/16.
 */

public class BFSAlgorithm extends Algorithm implements DataHandler {

    private Digraph graph;

    private DirectedGraphVisualizer visualizer;

    public BFSAlgorithm(DirectedGraphVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void onDataRecieved(Object data) {
        this.graph = (Digraph) data;
    }

    private void traverse() {

    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            traverse();
        }
    }

    public void setData(final Digraph g) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(g);
            }
        });
        start();
        prepareHandler(this);
        sendData(g);
    }
}
