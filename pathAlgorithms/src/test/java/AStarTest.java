/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pathalgorithms.AStar;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
        ArrayList<Integer>[] adjacencyList = new ArrayList[25];
        for (int i = 0; i < 25; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        emptyGraph = new Graph(adjacencyList);
        adjacencyList = new ArrayList[6];
        adjacencyList[0] = new ArrayList<>(Arrays.asList(1, 2));
        adjacencyList[1] = new ArrayList<>(Arrays.asList(0, 3, 4));
        adjacencyList[2] = new ArrayList<>(Arrays.asList(0));
        adjacencyList[3] = new ArrayList<>(Arrays.asList(1, 5));
        adjacencyList[4] = new ArrayList<>(Arrays.asList(1));
        adjacencyList[5] = new ArrayList<>(Arrays.asList(3));
        smallGraph = new Graph(adjacencyList);
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
        assertTrue(distances[2] == 1);
        assertTrue(distances[3] == 2);
        assertTrue(distances[4] == 2);
        assertTrue(distances[5] == 3);
    }

}
