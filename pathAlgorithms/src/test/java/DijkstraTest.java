/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.pathalgorithms.Dijkstra;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class DijkstraTest {
    
    private Dijkstra dijkstra;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initialiseWorks() {
        ArrayList<Integer>[] graph = new ArrayList[25];
        dijkstra.initialise(graph);
        assertEquals(25, dijkstra.getDistances().length);
        assertEquals(Integer.MAX_VALUE, dijkstra.getDistances()[10]);
        assertEquals(25, dijkstra.getVisited().length);
        assertFalse(dijkstra.getVisited()[10]);
    }

    @Test
    public void CalculateShortestWorks() {

    }

    @Test
    public void ResultsWorks() {

    }

}
