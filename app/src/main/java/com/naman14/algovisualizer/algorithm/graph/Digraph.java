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

    public boolean exists(int from) {
        return neighbors.get(from) != null && neighbors.get(from).size() != 0;
    }

    public List<Integer> getNeighbours(int node) {
        return neighbors.get(node);
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

    public static void main() {
        // Create a Graph with Integer nodes
        Digraph graph = new Digraph();
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(0, 3);
        graph.add(1, 2);
        graph.add(1, 3);
        graph.add(2, 3);
        graph.add(2, 4);
        graph.add(4, 5);
        graph.add(5, 6);    // Tetrahedron with tail
        System.out.println("The current graph: " + graph);
        System.out.println("In-degrees: " + graph.inDegree());
        System.out.println("Out-degrees: " + graph.outDegree());
        System.out.println("A topological sort of the vertices: " + graph.topSort());
        System.out.println("BFS distances starting from " + 0 + ": " + graph.bfsDistance(0));
        System.out.println("BFS distances starting from " + 1 + ": " + graph.bfsDistance(1));
        System.out.println("BFS distances starting from " + 2 + ": " + graph.bfsDistance(2));
        graph.add(4, 1);                                     // Create a cycle
        System.out.println("Cycle created");
        System.out.println("The current graph: " + graph);
        System.out.println("A topological sort of the vertices: " + graph.topSort());
        System.out.println("BFS distances starting from " + 2 + ": " + graph.bfsDistance(2));
    }
}