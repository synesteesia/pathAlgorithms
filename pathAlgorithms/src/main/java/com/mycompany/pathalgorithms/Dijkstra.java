/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author mikko
 */
public class Dijkstra {

    private ArrayList<Integer>[] graph;
    private int[] distances;
    private boolean[] visited;

    /**
     * Contains Dijkstra's algorithm as method
     */
    public int[] runDijkstra(ArrayList<Integer>[] graph) {
        this.graph = graph;
        this.visited = new boolean[graph.length];
        this.distances = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        run();
        results();
        return distances;
    }

    /**
     * Dijkstra algorithm
     */
    private void run() {
        // vertex 0 as source
        distances[0] = 0;
        PriorityQueue<Vertex> heap = new PriorityQueue(new VertexComparator());
        heap.add(new Vertex(0, 0));

        while (!heap.isEmpty()) {
            int vertex = heap.poll().getIndex();
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            for (int neighbour : this.graph[vertex]) {
                // only if not visited
                if (!visited[neighbour]) {
                    if (distances[vertex] + 1 < distances[neighbour]) {
                        distances[neighbour] = distances[vertex] + 1;
                        heap.add(new Vertex(neighbour, distances[neighbour]));
                    }
                }
            }
        }
    }

    /**
     * Prints results
     */
    public void results() {
        String output = "Number of verteces = " + this.graph.length;
        for (int i = 0; i < this.graph.length; i++) {
            output += ("\nThe shortest distance from vertex 0 to vertex " + i + " is " + distances[i]);
        }
        System.out.println(output);
    }

    /**
     * Getter for tests
     */
    public int[] getDistances() {
        return distances;
    }

    /**
     * Getter for tests
     */
    public boolean[] getVisited() {
        return visited;
    }

}
