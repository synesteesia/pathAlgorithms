/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

/**
 *
 * @author mikko
 */
public class Node {

    private final int index;
    private final double distance;

    public Node(int index, double distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return this.index;
    }

    public double getDistance() {
        return this.distance;
    }
}
