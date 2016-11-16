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
