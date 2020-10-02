/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pathalgorithms.ArList;

/**
 *
 * @author mikko
 */
public class ArListTest {

    private ArList list;

    @Test
    public void testAddOne() {
        list.add(1);
        assertTrue(list.size() == 1);

    }

    @Test
    public void testAddThree() {
        list.add(1);
        list.add(5);
        list.add(6);
        assertTrue(list.size() == 3);
        assertFalse(list.size() == 1);

    }

    @Test
    public void testGetArray() {
        list.add(1);
        list.add(56);
        list.add(44);
        list.add(2);
        int[] testList = list.getArray();
        assertEquals(44, testList[2]);

    }

    @Test
    public void testFreeze() {
        list.add(1);
        list.add(56);
        list.add(44);
        assertTrue(list.getArray().length == 4);
        list.freeze();
        assertTrue(list.getArray().length == 3);

    }
    
    @Test
    public void testFreezeEmpty() {
        assertTrue(list.getArray().length == 4);
        list.freeze();
        assertTrue(list.getArray().length == 0);
    }

    @Test
    public void testContains() {
        list.add(1);
        list.add(56);
        list.add(44);
        list.add(2);
        assertTrue(list.contains(44));
        assertFalse(list.contains(0));

    }

    @Before
    public void setUp() {
        list = new ArList();
    }

}
