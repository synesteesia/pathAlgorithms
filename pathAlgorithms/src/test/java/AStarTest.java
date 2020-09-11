/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.pathalgorithms.AStar;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class AStarTest {

    private AStar aStar;
    ArrayList<Integer>[] emptyGraph;
    double[] emptyDistances;
    ArrayList<Integer>[] smallGraph;
    double[] smallDistances;

    @Before
    public void setUp() {
        aStar = new AStar();
        emptyGraph = new ArrayList[25];
        emptyDistances = new double[25];
        for (int i = 0; i < 25; i++) {
            emptyGraph[i] = new ArrayList<>();
            emptyDistances[i] = i + 0.5;
        }
        smallGraph = new ArrayList[6];
        smallDistances = new double[6];
        smallGraph[0] = new ArrayList<>(Arrays.asList(1, 2));
        smallGraph[1] = new ArrayList<>(Arrays.asList(0, 3, 4));
        smallGraph[2] = new ArrayList<>(Arrays.asList(0));
        smallGraph[3] = new ArrayList<>(Arrays.asList(1, 5));
        smallGraph[4] = new ArrayList<>(Arrays.asList(1));
        smallGraph[5] = new ArrayList<>(Arrays.asList(3));
        smallDistances[0] = 4;
        smallDistances[1] = 3;
        smallDistances[2] = 10;
        smallDistances[3] = 2;
        smallDistances[4] = 2;
        smallDistances[5] = 0;
    }

    @Test
    public void runAStarWorksOnEmptyGraph() {
        double[] distances = aStar.runAStar(emptyGraph, emptyDistances);
        assertEquals(25, distances.length);
        assertTrue(distances[0] == 0);
        for (int i = 1; i < 25; i++) {
            assertTrue(distances[i] == Integer.MAX_VALUE);
        }
    }

    @Test
    public void runAStarWorksOnSmallGraph() {
        double[] distances = aStar.runAStar(smallGraph, smallDistances);
        assertEquals(smallGraph.length, distances.length);
        assertTrue(distances[0] == 0);
        assertTrue(distances[1] == 1);
        assertTrue(distances[2] == 1);
        assertTrue(distances[3] == 2);
        assertTrue(distances[4] == 2);
        assertTrue(distances[5] == 3);
    }

}
