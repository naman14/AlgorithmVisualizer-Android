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
import java.util.List;

public class WeightedGraph {

    public int V, E;
    public Edge edge[];

    public WeightedGraph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public class Edge {
        public int src, dest, weight;

        public Edge() {
            src = dest = weight = 0;
        }
    }

    public void addEdge(int index, int v1, int v2, int weight) {
        edge[index].src = v1;
        edge[index].dest = v2;
        edge[index].weight = weight;
    }

    public List<Edge> getAdjacentKeys(int v) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < edge.length; i++) {
            if (edge[i].src == v) {
                edges.add(edge[i]);
            }
        }
        return edges;
    }

}
