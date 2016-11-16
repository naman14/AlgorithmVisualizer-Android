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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Digraph {

    private Map<Integer, List<Integer>> neighbors = new HashMap<Integer, List<Integer>>();
    public double[][] directed_array;


    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int v : neighbors.keySet()) s.append("\n    " + v + " -> " + neighbors.get(v));
        return s.toString();
    }

    public void add(int vertex) {
        if (neighbors.containsKey(vertex)) return;
        neighbors.put(vertex, new ArrayList<Integer>());
    }


    public boolean contains(int vertex) {
        return neighbors.containsKey(vertex);
    }


    public void add(int from, int to) {
        this.add(from);
        this.add(to);
        neighbors.get(from).add(to);
    }


    public void remove(int from, int to) {
        if (!(this.contains(from) && this.contains(to)))
            throw new IllegalArgumentException("Nonexistent vertex");
        neighbors.get(from).remove(to);
    }

    public int getRoot() {
        return topSort().get(0);
    }

    public boolean exists(int from) {
        return neighbors.get(from) != null && neighbors.get(from).size() != 0;
    }

    public boolean edgeExists(int from, int to) {
        return neighbors.get(from)!=null && neighbors.get(from).size()!=0 && neighbors.get(from).contains(to);
    }

    public List<Integer> getNeighbours(int node) {
        return neighbors.get(node);
    }

    public int size() {
        return neighbors.size();
    }

    public void setDirectedArray(double[][] directed_array) {
        this.directed_array = directed_array;
    }


    public Map<Integer, Integer> outDegree() {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Integer v : neighbors.keySet()) result.put(v, neighbors.get(v).size());
        return result;
    }


    public Map<Integer, Integer> inDegree() {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Integer v : neighbors.keySet()) result.put(v, 0);       // All in-degrees are 0
        for (Integer from : neighbors.keySet()) {
            for (Integer to : neighbors.get(from)) {
                result.put(to, result.get(to) + 1);           // Increment in-degree
            }
        }
        return result;
    }


    public List<Integer> topSort() {
        Map<Integer, Integer> degree = inDegree();
        Stack<Integer> zeroVerts = new Stack<Integer>();
        for (Integer v : degree.keySet()) {
            if (degree.get(v) == 0) zeroVerts.push(v);
        }
        List<Integer> result = new ArrayList<Integer>();
        while (!zeroVerts.isEmpty()) {
            Integer v = zeroVerts.pop();
            result.add(v);
            for (Integer neighbor : neighbors.get(v)) {
                degree.put(neighbor, degree.get(neighbor) - 1);
                if (degree.get(neighbor) == 0) zeroVerts.push(neighbor);
            }
        }
        if (result.size() != neighbors.size()) return null;
        return result;
    }

    public boolean isAcyclic() {
        return topSort() != null;
    }


    public Map bfsDistance(Integer start) {
        Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
        for (Integer v : neighbors.keySet()) distance.put(v, null);
        distance.put(start, 0);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            int vDist = distance.get(v);
            for (Integer neighbor : neighbors.get(v)) {
                if (distance.get(neighbor) != null) continue;
                distance.put(neighbor, vDist + 1);
                queue.offer(neighbor);
            }
        }
        return distance;
    }
}