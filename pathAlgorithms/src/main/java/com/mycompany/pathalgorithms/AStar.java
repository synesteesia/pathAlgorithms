/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author mikko
 */
public class AStar {

    private final ArrayList<Integer>[] graph;
    private final double[] distances;
    private final boolean[] visited;
    /**
     * Contains A star algorithm as method
     */

    public AStar(ArrayList<Integer>[] graph, double[] estDistances) {
        this.graph = graph;
        this.visited = new boolean[graph.length];
        this.distances = estDistances;
    }

    /**
     * Runs A star algorithm and prints results
     */
    public void runAStar() {
        calculateShortestDistances();
        results();
    }

    /**
     * A star algorithm
     */
    public void calculateShortestDistances() {
        // node 0 as source
        distances[0] = 0;
        PriorityQueue<Node> heap = new PriorityQueue(new NodeComparator());
        heap.add(new Node(0, 0));

        while (!heap.isEmpty()) {
            int node = heap.poll().getIndex();
            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            for (int neighbour : this.graph[node]) {
                // only if not visited
                if (!visited[neighbour]) {
                    if (distances[node] + 1 < distances[neighbour]) {
                        distances[neighbour] = distances[node] + 1;
                        heap.add(new Node(neighbour, distances[neighbour]));
                    }
                }
            }
        }
    }

    /**
     * Prints results
     */
    public void results() {
        String output = "Number of nodes = " + this.graph.length;
        for (int i = 0; i < this.graph.length; i++) {
            output += ("\nThe shortest distance from node 0 to node " + i + " is " + distances[i]);
        }
        System.out.println(output);
    }

}
