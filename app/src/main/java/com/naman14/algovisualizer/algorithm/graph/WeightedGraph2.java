package com.naman14.algovisualizer.algorithm.graph;

/**
 * Created by naman on 16/11/16.
 */

//Weighted graph implementation using adjacency matrix

public class WeightedGraph2 {
    private int[][] edges;  // adjacency matrix
    private int[] labels;

    public WeightedGraph2(int n) {
        edges = new int[n][n];
        labels = new int[n];
    }


    public int size() {
        return labels.length;
    }

    public void setLabel(int vertex, int label) {
        labels[vertex] = label;
    }

    public Object getLabel(int vertex) {
        return labels[vertex];
    }

    public void addEdge(int source, int target, int w) {
        edges[source][target] = w;
    }

    public boolean isEdge(int source, int target) {
        return edges[source][target] > 0;
    }

    public void removeEdge(int source, int target) {
        edges[source][target] = 0;
    }

    public int getWeight(int source, int target) {
        return edges[source][target];
    }

    public int[] neighbors(int vertex) {
        int count = 0;
        for (int i = 0; i < edges[vertex].length; i++) {
            if (edges[vertex][i] > 0) count++;
        }
        final int[] answer = new int[count];
        count = 0;
        for (int i = 0; i < edges[vertex].length; i++) {
            if (edges[vertex][i] > 0) answer[count++] = i;
        }
        return answer;
    }

    public void print() {
        for (int j = 0; j < edges.length; j++) {
            System.out.print(labels[j] + ": ");
            for (int i = 0; i < edges[j].length; i++) {
                if (edges[j][i] > 0) System.out.print(labels[i] + ":" + edges[j][i] + " ");
            }
            System.out.println();
        }
    }

//    public static void main(String args[]) {
//        46 final WeightedGraph t = new WeightedGraph(6);
//        47 t.setLabel(0, "v0");
//        48 t.setLabel(1, "v1");
//        49 t.setLabel(2, "v2");
//        50 t.setLabel(3, "v3");
//        51 t.setLabel(4, "v4");
//        52 t.setLabel(5, "v5");
//        53 t.addEdge(0, 1, 2);
//        54 t.addEdge(0, 5, 9);
//        55 t.addEdge(1, 2, 8);
//        56 t.addEdge(1, 3, 15);
//        57 t.addEdge(1, 5, 6);
//        58 t.addEdge(2, 3, 1);
//        59 t.addEdge(4, 3, 3);
//        60 t.addEdge(4, 2, 7);
//        61 t.addEdge(5, 4, 3);
//        62 t.print();
//        63
//        64 final int[] pred = Dijkstra.dijkstra(t, 0);
//        65 for (int n = 0; n < 6; n++) {
//            66 Dijkstra.printPath(t, pred, 0, n);
//            67
//        }
//        68
//    }
}
