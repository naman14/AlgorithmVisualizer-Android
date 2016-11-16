package com.naman14.algovisualizer.algorithm.graph;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.graph.WeightedDirectedGraphVisualizer;

/**
 * Created by naman on 16/11/16.
 */

public class DijkstraAgorithm extends Algorithm implements DataHandler {


    private WeightedDigraph graph;

    private WeightedDirectedGraphVisualizer visualizer;

    public DijkstraAgorithm(WeightedDirectedGraphVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void onDataRecieved(Object data) {
        this.graph = (WeightedDigraph) data;
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
        }
    }

    public void setData(final WeightedDigraph g) {
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
