/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mikko
 */
public class Main {
    
    public static void main(String[] args) {
        
        Dijkstra dijkstra = new Dijkstra();
        ArrayList<Integer>[] graph = new ArrayList[25];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        dijkstra.runDijkstra(graph);
        ArrayList<Integer>[] smallGraph = new ArrayList[6];
        double[] smallDistances = new double[6];
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

        AStar aStar = new AStar();
        aStar.runAStar(smallGraph, smallDistances);
    }
    
}
