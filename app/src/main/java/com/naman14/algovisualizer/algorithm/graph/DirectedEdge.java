package com.naman14.algovisualizer.algorithm.graph;

public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;


    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }


    public int from() {
        return v;
    }


    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }
}