package com.naman14.algovisualizer.algorithm.graph;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeightedGraph {

    private List<Edge>[] edges;


    public WeightedGraph(int size) {
        if (size > 0) {
            edges = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                edges[i] = new ArrayList<Edge>();
            }
        }
    }


    public void addEdge(int v1, int v2, double weight) {
        if (v1 == v2) {
            Log.v("Graph","Error: self loops not allowed.");
        } else if (v1 < 0 || v2 < 0 || v1 >= edges.length || v2 >= edges.length) {
            Log.v("Graph","Error: invalid vertex.");
        } else if (hasEdge(v1, v2)) {
            Log.v("Graph","Error: edge already exists.");
        } else {
            edges[v1].add(new Edge(v1, v2, weight));
            edges[v2].add(new Edge(v2, v1, weight));
        }
    }


    public boolean hasEdge(int v1, int v2) {
        return !Double.isInfinite(getWeight(v1, v2));
    }


    public double getWeight(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= edges.length || v2 >= edges.length) {
            return Double.POSITIVE_INFINITY;
        } else {
            Iterator<Edge> it = getIncidentEdges(v1);
            while (it.hasNext()) {
                Edge e = it.next();
                if (e.vertex2() == v2) {
                    return e.weight();
                }
            }
            return Double.POSITIVE_INFINITY;
        }
    }

    public int size() {
        return edges.length;
    }

    public Iterator<Edge> getIncidentEdges(int vNum) {
        if (vNum < 0 || vNum >= edges.length) return null;
        else return edges[vNum].iterator();
    }


    public String toString() {
        String res = "Edges:\n";
        Iterator<Edge> iter;
        Edge e;
        boolean first;
        for (int i = 0; i < edges.length; i++) {
            res += "  from " + i + " to: [";
            iter = getIncidentEdges(i);
            first = true;
            while (iter.hasNext()) {
                e = iter.next();
                if (first) first = false;
                else {
                    res += ", ";
                }
                res += e.vertex2() + " weight: " + e.weight();
            }
            res += "]\n";
        }
        return res;
    }


    public boolean isAPath(int[] verts) {
        for (int i = 0; i < verts.length - 1; i++) {
            if (!hasEdge(verts[i], verts[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public double totalWeight(int[] verts) {
        double sum = 0;
        for (int i = 0; i < verts.length - 1; i++) {
            sum += getWeight(verts[i], verts[i + 1]);
        }
        return sum;
    }
}
