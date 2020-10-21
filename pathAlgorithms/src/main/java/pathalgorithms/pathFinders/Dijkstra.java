/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms.pathFinders;

import pathalgorithms.dataStructures.Graph;
import pathalgorithms.dataStructures.MinHeap;
import pathalgorithms.dataStructures.PerformanceStats;
import pathalgorithms.dataStructures.Vertex;

/**
 *
 * @author mikko
 */
public class Dijkstra {

    private Graph graph;
    private int[] distances;
    private boolean[] visited;

    /**
     * Contains Dijkstra's algorithm as method
     *
     * @param graph The graph to run the algorithm on.
     * @param preprocessing
     * @param runTime
     * @return
     */
    public int[] runDijkstra(Graph graph, PerformanceStats preprocessing, PerformanceStats runTime) {
        long start, stop;

        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            runPreprocessing(graph);
            stop = System.nanoTime();
            preprocessing.addValue(stop - start);
        }

        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            run();
            stop = System.nanoTime();
            runTime.addValue(stop - start);
        }

        return distances;
    }

    public int[] runDijkstra(Graph graph) {
        runPreprocessing(graph);
        run();
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
     * Getter for tests
     */
    public boolean[] getVisited() {
        return visited;
    }

}
