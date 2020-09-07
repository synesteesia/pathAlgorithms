/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

import java.util.ArrayList;

/**
 *
 * @author mikko
 */
public class Node {
    
    private boolean visited;
    private ArrayList<Edge> edgeList = new ArrayList<Edge>();
    private int distance = Integer.MAX_VALUE;
    
    public int getDistance() {
        return this.distance;
    }
    
    public void setDistance(int newDistance){
        this.distance = newDistance;
    }
    
    public boolean visitedStatus(){
        return this.visited;
    }
    
    public void setVisitedStatus(boolean visited){
        this.visited = visited;
    }
    
    public ArrayList<Edge> getEdgeList(){
        return edgeList;
    }
    
    public void setEdgeList(ArrayList<Edge> edges){
        this.edgeList = edges;
    }
    
    
    
    
    
}
