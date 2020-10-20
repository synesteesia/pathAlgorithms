/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

import pathalgorithms.dataStructures.Vertex;
import pathalgorithms.dataStructures.MinHeap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class MinHeapTest {

    private MinHeap heap;
    private static final Vertex VERTEX1 = new Vertex(1, 4);
    private static final Vertex VERTEX2 = new Vertex(2, 2.5);
    private static final Vertex VERTEX3 = new Vertex(3, 10);
    private static final Vertex VERTEX4 = new Vertex(4, 0.73);

    @Before
    public void setUp() {
        heap = new MinHeap(4);
    }

    /**
     * Test of add method, of class MinHeap.
     */
    @Test
    public void testAddOne() {
        heap.add(VERTEX1);
        assertFalse(heap.isEmpty());
        Vertex polled = heap.poll();
        assertEquals(polled, VERTEX1);
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testHeapify() {
        heap.add(VERTEX1);
        heap.add(VERTEX2);

        Vertex polled = heap.poll();
        assertEquals(polled, VERTEX2);

        heap.add(VERTEX4);
        heap.add(VERTEX3);

        polled = heap.poll();
        assertEquals(polled, VERTEX4);

        polled = heap.poll();
        assertEquals(polled, VERTEX1);

        polled = heap.poll();
        assertEquals(polled, VERTEX3);

        polled = heap.poll();
        assertEquals(polled, null);
    }

    /**
     * Test of isEmpty method, of class MinHeap.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testIsEmptyReturnsFalse() {
        heap.add(new Vertex(1, 1));
        assertFalse(heap.isEmpty());
    }
}
