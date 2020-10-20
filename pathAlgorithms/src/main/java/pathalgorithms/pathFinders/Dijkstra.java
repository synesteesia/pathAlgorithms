/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms.pathFinders;

import pathalgorithms.dataStructures.Graph;
import pathalgorithms.dataStructures.MinHeap;
import pathalgorithms.IO.PerformanceStats;
import pathalgorithms.dataStructures.Vertex;

/**
 *
 * @author mikko
 */
public class Dijkstra {

    private Graph graph;
    private int[] distances;
    private boolean[] visited;
    private PerformanceStats preprocessing;
    private PerformanceStats runTime;

    /**
     * Contains Dijkstra's algorithm as method
     *
     * @param graph The graph to run the algorithm on.
     */
    public int[] runDijkstra(Graph graph) {
        preprocessing = new PerformanceStats(1000);
        runTime = new PerformanceStats(1000);
        long start, stop;

        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            runPreprocessing(graph);
            stop = System.nanoTime();
            preprocessing.setValue(i, stop - start);
        }

        preprocessing.computeStats();

        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            run();
            stop = System.nanoTime();
            runTime.setValue(i, stop - start);
        }

        runTime.computeStats();

        results();

        return distances;
    }

    private void runPreprocessing(Graph graph) {

        this.graph = graph;
        this.visited = new boolean[graph.getNVertices()];
        this.distances = new int[graph.getNVertices()];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

    }

    /**
     * Dijkstra algorithm
     */
    private void run() {

        distances[graph.getStartVertex()] = 0;
        MinHeap heap = new MinHeap(graph.getNVertices());
        heap.add(new Vertex(graph.getStartVertex(), 0));

        while (!heap.isEmpty()) {
            int vertex = heap.poll().getIndex();
            if (vertex == graph.getEndVertex()) {

                return;
            }
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            for (int neighbour : this.graph.getArrayGraph()[vertex].getArray()) {
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
        String output = "Number of vertices = " + graph.getNVertices();
        output += "\nThe shortest distance from start vertex to target is " + distances[graph.getEndVertex()];
        output += "\nStatistics for initialising data structures:\n" + preprocessing.toString();
        output += "\nStatistics for running the algorithm:\n" + runTime.toString();

        System.out.println(output);
    }

    /**
     * Getter for tests
     */
    public boolean[] getVisited() {
        return visited;
    }

}
