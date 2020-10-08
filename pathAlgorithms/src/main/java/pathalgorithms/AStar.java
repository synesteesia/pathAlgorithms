/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

/**
 * @author mikko
 */
public class AStar {

    private Graph graph;
    private double[] distances;
    private boolean[] visited;

    /**
     * Runs A star algorithm and prints results.
     *
     * @param graph Graph represented as an adjacency list
     * @return A double array of distances from starting vertex.
     */
    public double[] runAStar(Graph graph) {
        this.graph = graph;
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

            for (int neighbour : this.graph.getArrayGraph()[vertex].getArray()) {
                if (distances[vertex] + 1 < distances[neighbour]) {
                    distances[neighbour] = distances[vertex] + 1;
                    heap.add(new Vertex(neighbour,
                            distances[neighbour] + euclideanDistance(neighbour)));
                }
            }
            visited[vertex] = true;
        }
    }

    private double euclideanDistance(int vertex) {
        int v_y = vertex / graph.getNColumns();
        int v_x = vertex - v_y * graph.getNColumns();

        int e_y = graph.getEndVertex() / graph.getNColumns();
        int e_x = graph.getEndVertex() - e_y * graph.getNColumns();

        return Math.sqrt((e_x - v_x) * (e_x - v_x) + (e_y - v_y) * (e_y - v_y));
    }

    /**
     * Prints results
     */
    public void results() {
        String output = "Number of vertices = " + graph.getNVertices();
        output += ("\nThe shortest distance from start vertex to target is " + distances[graph.getEndVertex()]);
        System.out.println(output);
    }
}
