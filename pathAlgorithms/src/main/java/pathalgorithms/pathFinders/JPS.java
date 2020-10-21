/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms.pathFinders;

import pathalgorithms.dataStructures.ArList;
import pathalgorithms.dataStructures.Graph;
import pathalgorithms.dataStructures.MinHeap;
import pathalgorithms.dataStructures.PerformanceStats;
import pathalgorithms.dataStructures.Vertex;

/**
 * @author mikko
 */
public class JPS {

    private Graph graph;
    private double[] distances;
    private boolean[] visited;
    private ArList[] adjacencyLists;

    /**
     * Runs JPS algorithm and prints results.
     *
     * @param graph Graph represented as an adjacency list
     * @param preprocessing
     * @param runTime
     * @return A double array of distances from starting vertex.
     */
    public double[] runJPS(Graph graph, PerformanceStats preprocessing, PerformanceStats runTime) {

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

    public double[] runJPS(Graph graph) {
        runPreprocessing(graph);
        run();
        return distances;
    }

    private void runPreprocessing(Graph graph) {
        this.graph = graph;
        this.adjacencyLists = graph.getArrayGraph();
        this.distances = new double[graph.getNVertices()];
        this.visited = new boolean[graph.getNVertices()];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

    }

    /**
     * Jump point search algorithm.
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

            for (Integer neighbour : getNeighbours(vertex)) {
                if (neighbour == null) {
                    continue;
                }
                int dist = manhattanDistance(neighbour, vertex);
                if (distances[vertex] + dist < distances[neighbour]) {
                    distances[neighbour] = distances[vertex] + dist;
                    heap.add(new Vertex(neighbour,
                            distances[neighbour] + manhattanDistance(neighbour, graph.getEndVertex())));
                }
            }
            visited[vertex] = true;
        }
    }

    /**
     * Calculate manhattan distance of given vertices
     */
    private int manhattanDistance(int vertexOne, int vertexTwo) {
        int v_y = vertexOne / graph.getNColumns();
        int v_x = vertexOne - v_y * graph.getNColumns();

        int e_y = vertexTwo / graph.getNColumns();
        int e_x = vertexTwo - e_y * graph.getNColumns();

        return Math.abs(e_x - v_x) + Math.abs(e_y - v_y);
    }

    private Integer straightJump(int vertex, int diffToNext, int diffToForced) {
        int helper = vertex + diffToNext;
        while (isValidVertex(helper) && isValidStraightJump(vertex, diffToNext) && adjacencyLists[helper].size() != 0) {

            if (helper == graph.getEndVertex()
                    || (isValidVertex(helper - diffToForced) && adjacencyLists[helper - diffToForced].size() == 0)
                    || (isValidVertex(helper + diffToForced) && adjacencyLists[helper + diffToForced].size() == 0)) {
                return helper;
            }

            helper += diffToNext;
        }
        return null;
    }

    private boolean isValidStraightJump(int vertex, int diffToNext) {
        return Math.abs(diffToNext) != 1 || Math.abs(vertex % graph.getNColumns() - (vertex + diffToNext) % graph.getNColumns()) == 1;
    }

    private Integer diagonalJump(int vertex, int vDiff, int hDiff) {
        int helper = vertex + vDiff + hDiff;
        while (isValidVertex(helper) && isValidDiagonalJump(helper, hDiff)
                && adjacencyLists[helper].size() != 0) {

            if (helper == graph.getEndVertex()
                    || straightJump(helper, vDiff, hDiff) != null
                    || straightJump(helper, hDiff, vDiff) != null) {
                return helper;
            }

            helper += vDiff + hDiff;
        }
        return null;
    }

    private boolean isValidVertex(int vertex) {
        return vertex >= 0 && vertex < adjacencyLists.length;
    }

    private boolean isValidDiagonalJump(int vertex, int hDiff) {
        return (vertex % graph.getNColumns() != 0 || hDiff > 0)
                && ((vertex + 1) % graph.getNColumns() != 0 || hDiff < 0);
    }

    private Integer[] getNeighbours(int vertex) {
        Integer[] neighbours = new Integer[8];

        for (int i = 0; i < 4; i++) {
            int vDiff = (i % 2 == 0 ? 1 : -1) * graph.getNColumns();
            int hDiff = i == 0 || i == 3 ? -1 : 1;
            neighbours[i] = straightJump(vertex, vDiff, hDiff);
            neighbours[i + 4] = diagonalJump(vertex, vDiff, hDiff);
        }
        return neighbours;
    }
}
