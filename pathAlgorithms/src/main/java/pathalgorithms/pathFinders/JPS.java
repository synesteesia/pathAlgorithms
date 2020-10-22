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
    public int[] directions;

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
        this.directions = new int[graph.getNVertices()];

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
                            distances[neighbour] + manhattanDistance(
                                    neighbour, graph.getEndVertex())));
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
        while (isValidVertex(vertex + diffToNext)
                && isValidStraightJump(vertex, diffToNext)
                && adjacencyLists[vertex + diffToNext].size() != 0) {

            vertex += diffToNext;

            if (vertex == graph.getEndVertex()
                    || isForcedNeighbour(vertex, diffToForced, diffToNext)
                    || isForcedNeighbour(vertex, - diffToForced, diffToNext)) {
                directions[vertex] = diffToNext;
                return vertex;
            }
        }
        return null;
    }

    private boolean isForcedNeighbour(int vertex, int diffForced, int diffToNext) {
        return isValidVertex(vertex + diffForced)
                && adjacencyLists[vertex + diffToNext].size() != 0
                && adjacencyLists[vertex + diffForced].size() == 0
                && isValidVertex(vertex + diffForced + diffToNext)
                && adjacencyLists[vertex + diffForced + diffToNext].size() != 0;
    }

    private boolean isValidStraightJump(int vertex, int diffToNext) {
        return Math.abs(diffToNext) != 1
                || Math.abs((vertex % graph.getNColumns())
                        - ((vertex + diffToNext) % graph.getNColumns())) == 1;
    }

    private Integer diagonalJump(int vertex, int vDiff, int hDiff) {
        while (isValidVertex(vertex + vDiff + hDiff)
                && isValidDiagonalJump(vertex, hDiff, vDiff)
                && adjacencyLists[vertex + vDiff + hDiff].size() != 0) {

            vertex += vDiff + hDiff;

            if (vertex == graph.getEndVertex()
                    || straightJump(vertex, vDiff, hDiff) != null
                    || straightJump(vertex, hDiff, vDiff) != null) {
                directions[vertex] = vDiff + hDiff;
                return vertex;
            }
        }
        return null;
    }

    private boolean isValidVertex(int vertex) {
        return vertex >= 0 && vertex < adjacencyLists.length;
    }

    private boolean isValidDiagonalJump(int vertex, int hDiff, int vDiff) {
        return (vertex % graph.getNColumns() != 0 || hDiff > 0)
                && ((vertex + 1) % graph.getNColumns() != 0 || hDiff < 0)
                && (adjacencyLists[vertex + hDiff].size() != 0
                || adjacencyLists[vertex + vDiff].size() != 0);
    }

    private Integer[] getNeighbours(int vertex) {
        Integer[] neighbours;
        int direction = Math.abs(directions[vertex]);

        if (direction == 1 || direction == graph.getNColumns()) {
            int forcedDir = graph.getNColumns() - directions[vertex] + 1;

            neighbours = new Integer[3];
            neighbours[0] = straightJump(vertex, directions[vertex], forcedDir);

            if (isForcedNeighbour(vertex, forcedDir, directions[vertex])) {
                neighbours[1] = direction == 1
                        ? diagonalJump(vertex, forcedDir, directions[vertex])
                        : diagonalJump(vertex, directions[vertex], forcedDir);
            }

            if (isForcedNeighbour(vertex, -forcedDir, directions[vertex])) {
                neighbours[2] = direction == 1
                        ? diagonalJump(vertex, -forcedDir, directions[vertex])
                        : diagonalJump(vertex, directions[vertex], -forcedDir);
            }

        } else if (direction != 0) {
            int vDiff = (directions[vertex] < 0 ? -1 : 1) * graph.getNColumns();
            int hDiff = directions[vertex] % graph.getNColumns() == 1 ? 1 : -1;

            neighbours = new Integer[3];
            neighbours[0] = straightJump(vertex, vDiff, hDiff);
            neighbours[1] = straightJump(vertex, hDiff, vDiff);
            neighbours[2] = diagonalJump(vertex, vDiff, hDiff);

        } else {
            neighbours = new Integer[8];
            for (int i = 0; i < 4; i++) {
                int vDiff = (i % 2 == 0 ? 1 : -1) * graph.getNColumns();
                int hDiff = i == 0 || i == 3 ? -1 : 1;

                neighbours[i] = i < 2
                        ? straightJump(vertex, vDiff, hDiff)
                        : straightJump(vertex, hDiff, vDiff);
                neighbours[i + 4] = diagonalJump(vertex, vDiff, hDiff);
            }
        }

        return neighbours;
    }

}
