/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

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
     */
    public int[] runDijkstra(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getNVertices()];
        this.distances = new int[graph.getNVertices()];

        for (int i = 0; i < distances.length; i++) {
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
            for (int neighbour : this.graph.getArrayGraph()[vertex]) {
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
        output += ("\nThe shortest distance from start vertex to target is " + distances[graph.getEndVertex()]);
        System.out.println(output);
    }

    /**
     * Getter for tests
     */
    public boolean[] getVisited() {
        return visited;
    }

}
