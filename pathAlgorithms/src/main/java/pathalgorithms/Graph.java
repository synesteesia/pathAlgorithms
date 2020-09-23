/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mikko
 */
public class Graph {

    private final String[] map;
    private final ArrayList<Integer>[] arrayGraph;
    private int startVertex;
    private int endVertex;

    public Graph(String[] map, int vertices, int start, int end) {
        this.map = map;
        arrayGraph = new ArrayList[vertices];
        startVertex = start;
        endVertex = end;
    }

    public void addAdjacent(int from, int to) {
        if (arrayGraph[from] == null) {
            arrayGraph[from] = new ArrayList<>(Arrays.asList(to));
        } else {
            arrayGraph[from].add(to);
        }

        if (arrayGraph[to] == null) {
            arrayGraph[to] = new ArrayList<>(Arrays.asList(from));
        } else {
            arrayGraph[to].add(from);
        }
    }

    public void addEmptyAdjacency(int from) {
        arrayGraph[from] = new ArrayList<>();
    }

    public ArrayList<Integer>[] getArrayGraph() {
        return arrayGraph;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }

}
