/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

/**
 * @author mikko
 */
public class JPS {

    private Graph graph;
    private double[] distances;
    private boolean[] visited;
    private ArList[] adjacencyLists;

    /**
     * Runs A star algorithm and prints results.
     *
     * @param graph Graph represented as an adjacency list
     * @return A double array of distances from starting vertex.
     */
    public double[] runJPS(Graph graph) {
        this.graph = graph;
        this.adjacencyLists = graph.getArrayGraph();
        this.distances = new double[graph.getNVertices()];
        this.visited = new boolean[graph.getNVertices()];

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
                if (neighbour == null) continue;
                int dist = manhattanDistance(neighbour, vertex);
                if (distances[vertex] + dist < distances[neighbour]) {
                    distances[neighbour] = distances[vertex] + dist;
                    heap.add(new Vertex(neighbour,
                            distances[neighbour] + euclideanDistance(neighbour, graph.getEndVertex())));
                }
            }
            visited[vertex] = true;
        }
    }

    private double euclideanDistance(int vertexOne, int vertexTwo) {
        int v_y = vertexOne / graph.getNColumns();
        int v_x = vertexOne - v_y * graph.getNColumns();

        int e_y = vertexTwo / graph.getNColumns();
        int e_x = vertexTwo - e_y * graph.getNColumns();

        return Math.sqrt((e_x - v_x) * (e_x - v_x) + (e_y - v_y) * (e_y - v_y));
    }

    private int manhattanDistance(int vertexOne, int vertexTwo) {
        int v_y = vertexOne / graph.getNColumns();
        int v_x = vertexOne - v_y * graph.getNColumns();

        int e_y = vertexTwo / graph.getNColumns();
        int e_x = vertexTwo - e_y * graph.getNColumns();

        return Math.abs(e_x - v_x) + Math.abs(e_y - v_y);
    }

    private Integer straightJump(int vertex, int diffToNext, int diffToForced) {
        int helper = vertex + diffToNext;
        while (isValidVertex(helper) && adjacencyLists[helper].size() != 0) {

            if (helper == graph.getEndVertex()
                    || (isValidVertex(helper - diffToForced) && adjacencyLists[helper - diffToForced].size() == 0)
                    || (isValidVertex(helper + diffToForced) && adjacencyLists[helper + diffToForced].size() == 0)) {
                return helper;
            }

            helper += diffToNext;
        }
        return null;
    }

    private Integer diagonalJump(int vertex, int vDiff, int hDiff) {
        int helper = vertex + vDiff + hDiff;
        while (isValidVertex(helper) && adjacencyLists[helper].size() != 0) {

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

    private Integer[] getNeighbours(int vertex) {
        Integer[] neighbours = new Integer[8];

        for (int i = 0; i < 4; i++) {
            int dirOne = (i % 2 == 0 ? 1 : -1) * (i < 2 ? graph.getNColumns() : 1);
            int dirTwo = (i == 0 || i == 3 ? -1 : 1) * (i < 2 ? 1 : graph.getNColumns());
            neighbours[i] = straightJump(vertex, dirOne, dirTwo);
            neighbours[i + 4] = diagonalJump(vertex, dirOne, dirTwo);
        }
        return neighbours;
    }
    // TODO: Check diagonal move is allowed (i.e. it would be possible to perform without the diagonal step
    // TODO: Check the step in vertical direction is fine (no wrapping of the map)
    // TODO: JavaDoc
    // TODO: Tests

    /**
     * Prints results
     */
    public void results() {
        String output = "Number of vertices = " + graph.getNVertices();
        output += ("\nThe shortest distance from start vertex to target is " + distances[graph.getEndVertex()]);
        System.out.println(output);
    }
}
