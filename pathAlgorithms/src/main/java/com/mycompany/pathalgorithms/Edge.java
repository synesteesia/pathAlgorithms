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
public class Edge {
    
    private int nodeX;
    private int nodeY;
    
    public Edge(int nodeX, int nodeY){
        this.nodeX = nodeX;
        this.nodeY = nodeY;
    }

    public int getNodeX() {
        return this.nodeX;
    }

    public int getNodeY() {
        return this.nodeY;
    }
    
    public int getNeigbour(int nodeI) {
        if (this.nodeX == nodeI) {
            return this.nodeX;
        } else {
            return this.nodeY;
        }
    }
    
}
