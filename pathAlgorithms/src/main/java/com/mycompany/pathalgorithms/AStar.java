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
     * @return A double array of distances from starting vertex.
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
        // vertex 0 as source
        distances[0] = 0;
        PriorityQueue<Vertex> heap = new PriorityQueue(new VertexComparator());
        heap.add(new Vertex(0, 0));

        while (!heap.isEmpty()) {
            int vertex = heap.poll().getIndex();

            for (int neighbour : this.graph[vertex]) {
                    if (distances[vertex] + 1 < distances[neighbour]) {
                        distances[neighbour] = distances[vertex] + 1;
                        heap.add(new Vertex(neighbour, distances[neighbour] + hScore[neighbour]));
                    }
            }
        }
    }

    /**
     * Prints results
     */
    public void results() {
        String output = "Number of vertices = " + this.graph.length;
        for (int i = 0; i < this.graph.length; i++) {
            output += ("\nThe shortest distance from vertex 0 to vertex " + i + " is " + distances[i]);
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
