package com.naman14.algovisualizer.algorithm.graph;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.graph.WeightedGraphVisualizer;

/**
 * Created by naman on 16/11/16.
 */

public class BellmanFordAlgorithm extends Algorithm implements DataHandler {


    private WeightedGraph graph;

    private WeightedGraphVisualizer visualizer;

    public BellmanFordAlgorithm(WeightedGraphVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void onDataRecieved(Object data) {
        this.graph = (WeightedGraph) data;
    }

    private void bellmanford(WeightedGraph graph, int src) {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        addLog("Number of edges: " + E);
        addLog("Finding shortest path from source: " + src + " to all other vertices");

        addLog("Initialising distance from source to all other vertics as INFINITY");
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;

        dist[src] = 0;
        sleep();

        for (int i = 1; i < V; ++i) {
            addLog("Interation " + i);
            sleep();

            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                addLog(u + " -> " + v);
                highlightNode(v);
                highlightLine(u, v);
                sleep();
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    addLog("distatnce[" + u + "]" + " = distance[" + v + "] + " + weight);

                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                addLog("There is no path between source " + src + " and vertex " + i);
            } else {
                addLog("Shortest path from source:" + src + " to vertex " + i + " is " + dist[i]);
            }
        }

        completed();
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(Algorithm.COMMAND_START_ALGORITHM)) {
            startExecution();
            bellmanford(graph, 0);
        }
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

    public void setData(final WeightedGraph g) {
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
