/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pathalgorithms.AStar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathalgorithms.ArList;
import pathalgorithms.Graph;

/**
 *
 * @author mikko
 */
public class AStarTest {

    private AStar aStar;
    private Graph emptyGraph;
    private Graph smallGraph;

    @Before
    public void setUp() {
        aStar = new AStar();
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
        smallGraph = new Graph(adjacencyList, 3);
    }

    @Test
    public void runAStarWorksOnEmptyGraph() {
        double[] distances = aStar.runAStar(emptyGraph);
        assertEquals(25, distances.length);
        assertTrue(distances[0] == 0);
        for (int i = 1; i < 25; i++) {
            assertTrue(distances[i] == Integer.MAX_VALUE);
        }
    }

    @Test
    public void runAStarWorksOnSmallGraph() {
        double[] distances = aStar.runAStar(smallGraph);
        assertEquals(smallGraph.getNVertices(), distances.length);
        assertTrue(distances[0] == 0);
        assertTrue(distances[1] == 1);
        assertTrue(distances[2] == Integer.MAX_VALUE);
        assertTrue(distances[5] == 2);
        assertTrue(distances[6] == 3);
        assertTrue(distances[7] == 4);
        assertTrue(distances[9] == 3);
    }

}
