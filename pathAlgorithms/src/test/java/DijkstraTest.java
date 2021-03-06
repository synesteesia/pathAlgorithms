/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pathalgorithms.pathFinders.Dijkstra;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathalgorithms.IO.Parser;
import pathalgorithms.dataStructures.ArList;
import pathalgorithms.dataStructures.Graph;

/**
 *
 * @author mikko
 */
public class DijkstraTest {
    
    private Dijkstra dijkstra;
    private Graph emptyGraph;
    private Graph smallGraph;
    private Graph smallGraph2;

    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
        ArList[] adjacencyList = new ArList[25];
        for (int i = 0; i < 25; i++) {
            adjacencyList[i] = new ArList();
        }
        emptyGraph = new Graph(adjacencyList, 5);
        adjacencyList = new ArList[12];
        adjacencyList[0] = new ArList(1);
        adjacencyList[1] = new ArList(0, 5);
        adjacencyList[5] = new ArList(1, 6, 9);
        adjacencyList[6] = new ArList(5, 7);
        adjacencyList[7] = new ArList(6);
        adjacencyList[9] = new ArList(5);
        smallGraph = new Graph(adjacencyList, 4);
        smallGraph.setEndVertex(9);
        String filePath = "./src/test/java/testmap2.map";
        String[] grid = Parser.readFile(filePath);
        smallGraph2 = Parser.parseGrid(grid, 2, 1, 13, 8);
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
        assertTrue(distances[5] == 2 && visited[5]);
        assertTrue(distances[6] == 3 && visited[6]);
        assertTrue(distances[7] == 4 && !visited[7]);
        assertTrue(distances[9] == 3 && !visited[9]);
    }

    @Test
    public void runDijkstraWorksOnSmallGraph2() {
        int[] distances = dijkstra.runDijkstra(smallGraph2);
        assertEquals(smallGraph2.getNVertices(), distances.length);
        assertTrue(distances[smallGraph2.getStartVertex()] == 0);
        assertTrue(distances[smallGraph2.getEndVertex()] == 18);
    }
}
