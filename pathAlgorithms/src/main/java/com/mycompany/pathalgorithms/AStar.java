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

    private ArrayList<Integer>[] graph;
    private double[] hScore;
    private double[] distances;

    /**
     * Runs A star algorithm and prints results.
     *
     * @param graph Graph represented as an adjacency list
     * @param hScore List of estimated distances to target
     * @return A double array of distances from starting node.
     */
    public double[] runAStar(ArrayList<Integer>[] graph, double[] hScore) {
        this.graph = graph;
        this.hScore = hScore;
        this.distances = new double[hScore.length];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        run();
        results();
        return distances;
    }

    /**
     * A star algorithm.
     */
    public void run() {
        // node 0 as source
        distances[0] = 0;
        PriorityQueue<Node> heap = new PriorityQueue(new NodeComparator());
        heap.add(new Node(0, 0));

        while (!heap.isEmpty()) {
            int node = heap.poll().getIndex();

            for (int neighbour : this.graph[node]) {
                    if (distances[node] + 1 < distances[neighbour]) {
                        distances[neighbour] = distances[node] + 1;
                        heap.add(new Node(neighbour, distances[neighbour] + hScore[neighbour]));
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

    /**
     * Getter for tests
     */
    public double[] getDistances() {
        return distances;
    }

}
