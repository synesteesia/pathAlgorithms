/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathalgorithms.IO.Parser;
import pathalgorithms.dataStructures.ArList;
import pathalgorithms.dataStructures.Graph;
import pathalgorithms.pathFinders.JPS;

/**
 *
 * @author mikko
 */
public class JPSTest {
    
    private JPS jps;
    private Graph emptyGraph;
    private Graph smallGraph;
    private Graph smallGraph2;

    @Before
    public void setUp() {
        jps = new JPS();
        ArList[] adjacencyList = new ArList[25];
        for (int i = 0; i < 25; i++) {
            adjacencyList[i] = new ArList();
        }
        emptyGraph = new Graph(adjacencyList, 5);
        adjacencyList = new ArList[12];
        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new ArList();
        }
        adjacencyList[0] = new ArList(1);
        adjacencyList[1] = new ArList(0, 5);
        adjacencyList[5] = new ArList(1, 6, 9);
        adjacencyList[6] = new ArList(5, 7);
        adjacencyList[7] = new ArList(6);
        adjacencyList[9] = new ArList(5);
        smallGraph = new Graph(adjacencyList, 4);
        smallGraph.setEndVertex(9);
        smallGraph.setStartVertex(0);
        String filePath = "./src/test/java/testmap2.map";
        String[] grid = Parser.readFile(filePath);
        smallGraph2 = Parser.parseGrid(grid, 2, 1, 13, 8);
    }

    @Test
    public void runJpsWorksOnEmptyGraph() {
        double[] distances = jps.runJPS(emptyGraph);
        assertEquals(25, distances.length);
        assertTrue(distances[0] == 0);
        for (int i = 1; i < 25; i++) {
            assertTrue(distances[i] == Integer.MAX_VALUE);
        }
    }

    @Test
    public void runJpsWorksOnSmallGraph() {
        double[] distances = jps.runJPS(smallGraph);
        assertEquals(smallGraph.getNVertices(), distances.length);
        assertTrue(distances[0] == 0);
        assertTrue(distances[1] == Integer.MAX_VALUE);
        assertTrue(distances[2] == Integer.MAX_VALUE);
        assertTrue(distances[3] == Integer.MAX_VALUE);
        assertTrue(distances[4] == Integer.MAX_VALUE);
        assertTrue(distances[5] == 2);
        assertTrue(distances[6] == Integer.MAX_VALUE);
        assertTrue(distances[7] == Integer.MAX_VALUE);
        assertTrue(distances[8] == Integer.MAX_VALUE);
        assertTrue(distances[9] == 3);
        assertTrue(distances[10] == Integer.MAX_VALUE);
        assertTrue(distances[11] == Integer.MAX_VALUE);
    }

    @Test
    public void runJpsWorksOnSmallGraph2() {
        double[] distances = jps.runJPS(smallGraph2);
        assertEquals(smallGraph2.getNVertices(), distances.length);
        assertTrue(distances[smallGraph2.getStartVertex()] == 0);
        assertTrue(distances[smallGraph2.getEndVertex()] == 18);
    }
}
