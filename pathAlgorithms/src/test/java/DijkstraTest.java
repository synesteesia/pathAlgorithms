/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pathalgorithms.Dijkstra;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathalgorithms.ArList;
import pathalgorithms.Graph;

/**
 *
 * @author mikko
 */
public class DijkstraTest {
    
    private Dijkstra dijkstra;
    private Graph emptyGraph;
    private Graph smallGraph;

    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
        ArList[] adjacencyList = new ArList[25];
        for (int i = 0; i < 25; i++) {
            adjacencyList[i] = new ArList();
        }
        emptyGraph = new Graph(adjacencyList);
        adjacencyList = new ArList[6];
        adjacencyList[0] = new ArList(1, 2);
        adjacencyList[1] = new ArList(0, 3, 4);
        adjacencyList[2] = new ArList(0);
        adjacencyList[3] = new ArList(1, 5);
        adjacencyList[4] = new ArList(1);
        adjacencyList[5] = new ArList(3);
        smallGraph = new Graph(adjacencyList);
    }

    @Test
    public void dijkstraWorksOnEmptyGraph() {
        int[] distances = dijkstra.runDijkstra(emptyGraph);
        assertEquals(25, distances.length);
        assertEquals(Integer.MAX_VALUE, distances[10]);
        assertTrue(distances[0] == 0);
        assertEquals(25, dijkstra.getVisited().length);
        assertFalse(dijkstra.getVisited()[10]);
    }

    @Test
    public void dijkstraWorksOnSmallGraph() {
        int[] distances = dijkstra.runDijkstra(smallGraph);
        assertEquals(smallGraph.getNVertices(), distances.length);
        boolean[] visited = dijkstra.getVisited();
        assertTrue(distances[0] == 0 && visited[0]);
        assertTrue(distances[1] == 1 && visited[1]);
        assertTrue(distances[2] == 1 && visited[2]);
        assertTrue(distances[3] == 2 && visited[3]);
        assertTrue(distances[4] == 2 && visited[4]);
        assertTrue(distances[5] == 3 && !visited[5]);
    }
}
