package com.naman14.algovisualizer.algorithm.graph;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.graph.WeightedGraphVisualizer2;

/**
 * Created by naman on 16/11/16.
 */

public class DijkstraAgorithm extends Algorithm implements DataHandler {

    private WeightedGraph2 graph;

    private WeightedGraphVisualizer2 visualizer;

    public DijkstraAgorithm(WeightedGraphVisualizer2 visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void onDataRecieved(Object data) {
        this.graph = (WeightedGraph2) data;
    }

    private void dijkstra(WeightedGraph2 G, int s) {

        addLog("Finding shortest path from source: " + s + " to all other vertices");

        final int[] dist = new int[G.size()];
        final int[] pred = new int[G.size()];
        final boolean[] visited = new boolean[G.size()];

        addLog("Initialising distance from source to all other vertics as INFINITY");
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        sleep();

        for (int i = 0; i < dist.length; i++) {
            final int next = minVertex(dist, visited);
            visited[next] = true;

            final int[] n = G.neighbors(next);
            for (int j = 0; j < n.length; j++) {
                final int v = n[j];
                addLog(next + " -> " + v);
                highlightNode(v);
                highlightLine(next, v);
                sleep();
                final int d = dist[next] + G.getWeight(next, v);
                addLog("distatnce[" + d + "]" + " = distance[" + next + "] + " + G.getWeight(next, v));
                if (dist[v] > d) {
                    dist[v] = d;
                    pred[v] = next;
                }
            }
        }

        for (int i = 0; i < G.size(); i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                addLog("There is no path between source " + s + " and vertex " + i);
            } else {
                addLog("Shortest path from source:" + s + " to vertex " + i + " is " + dist[i]);
            }
        }

        completed();
    }

    private static int minVertex(int[] dist, boolean[] v) {
        int x = Integer.MAX_VALUE;
        int y = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!v[i] && dist[i] < x) {
                y = i;
                x = dist[i];
            }
        }
        return y;
    }

    private void highlightNode(final int node) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightNode(node);
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

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            dijkstra(graph, 0);
        }
    }

    public void setData(final WeightedGraph2 g) {
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
