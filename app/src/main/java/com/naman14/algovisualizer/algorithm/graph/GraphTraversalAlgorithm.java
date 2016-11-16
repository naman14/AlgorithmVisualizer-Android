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

package com.naman14.algovisualizer.algorithm.graph;

import android.app.Activity;

import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.graph.DirectedGraphVisualizer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversalAlgorithm extends Algorithm implements DataHandler {

    public static final String TRAVERSE_BFS = "traverse_bfs";
    public static final String TRAVERSE_DFS = "traverse_dfs";

    private Digraph graph;

    private DirectedGraphVisualizer visualizer;

    public GraphTraversalAlgorithm(DirectedGraphVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    @Override
    public void onDataRecieved(Object data) {
        this.graph = (Digraph) data;
    }

    private void bfs(int source) {

        addLog("Traversing the graph with breadth-first search");
        Queue<Integer> queue = new LinkedList<Integer>();

        int numberOfNodes = graph.size();
        addLog("Total number of nodes: " + numberOfNodes);
        int[] visited = new int[numberOfNodes + 1];

        int i, element;

        addLog("Starting from source: " + source);
        highlightNode(source);
        visited[source] = 1;
        queue.add(source);
        sleep();

        while (!queue.isEmpty()) {
            element = queue.remove();
            i = element;
            while (i <= numberOfNodes) {
                if (graph.edgeExists(element, i) && visited[i] == 0) {
                    addLog("Going from " + element + " to " + i);
                    highlightNode(i);
                    highlightLine(element, i);
                    queue.add(i);
                    visited[i] = 1;
                    sleep();
                }
                i++;
            }
        }
        addLog("BFS traversing completed");
        completed();

    }

    private void dfs(int source) {

        addLog("Traversing the graph with depth-first search");
        Stack<Integer> stack = new Stack<>();

        int numberOfNodes = graph.size();
        addLog("Total number of nodes: " + numberOfNodes);

        int visited[] = new int[numberOfNodes + 1];
        int element = source;
        int i = source;
        addLog("Starting from source: " + source);
        highlightNode(source);
        visited[source] = 1;
        stack.push(source);
        sleep();

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = element;
            while (i <= numberOfNodes) {
                if (graph.edgeExists(element, i) && visited[i] == 0) {
                    addLog("Going from " + element + " to " + i);
                    highlightNode(i);
                    highlightLine(element, i);
                    stack.push(i);
                    visited[i] = 1;
                    element = i;
                    i = 1;
                    sleep();
                    continue;
                }
                i++;
            }
            stack.pop();
        }
        addLog("DFS traversing completed");
        completed();

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
        if (message.equals(TRAVERSE_BFS)) {
            startExecution();
            bfs(graph.getRoot());
        } else if (message.endsWith(TRAVERSE_DFS)) {
            startExecution();
            dfs(graph.getRoot());
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
