/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.pathalgorithms.AStar;
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
public class AStarTest {
    
    private AStar aStar;
    
    public AStarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        aStar = new AStar();
    }
    
    @After
    public void tearDown() {
    }
    
        @Test
    public void initialiseWorks() {
        ArrayList<Integer>[] graph = new ArrayList[25];
        double[] distances = new double[25];
        aStar.initialise(graph, distances);
        assertEquals(25, aStar.getDistances().length);
        assertEquals(25, aStar.getVisited().length);
        assertFalse(aStar.getVisited()[10]);
    }

}
