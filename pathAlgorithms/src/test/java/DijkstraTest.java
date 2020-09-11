/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.pathalgorithms.Dijkstra;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class DijkstraTest {
    
    private Dijkstra dijkstra;
    private ArrayList<Integer>[] emptyGraph;
    private ArrayList<Integer>[] smallGraph;

    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
        emptyGraph = new ArrayList[25];
        for (int i = 0; i < 25; i++) {
            emptyGraph[i] = new ArrayList<>();
        }
        smallGraph = new ArrayList[6];
        smallGraph[0] = new ArrayList<>(Arrays.asList(1,2));
        smallGraph[1] = new ArrayList<>(Arrays.asList(0,3,4));
        smallGraph[2] = new ArrayList<>(Arrays.asList(0));
        smallGraph[3] = new ArrayList<>(Arrays.asList(1,5));
        smallGraph[4] = new ArrayList<>(Arrays.asList(1));
        smallGraph[5] = new ArrayList<>(Arrays.asList(3));
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
        assertEquals(smallGraph.length, distances.length);
        boolean[] visited = dijkstra.getVisited();
        assertTrue(distances[0] == 0 && visited[0]);
        assertTrue(distances[1] == 1 && visited[1]);
        assertTrue(distances[2] == 1 && visited[2]);
        assertTrue(distances[3] == 2 && visited[3]);
        assertTrue(distances[4] == 2 && visited[4]);
        assertTrue(distances[5] == 3 && visited[5]);
    }
}


