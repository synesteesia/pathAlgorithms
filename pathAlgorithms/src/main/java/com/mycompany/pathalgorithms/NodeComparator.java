/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

import java.util.Comparator;

/**
 *
 * @author mikko
 */
public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node t, Node t1) {
        double difference = t.getDistance() - t1.getDistance();
        if (difference < 0) {
            return -1;
        } else if (difference > 0) {
            return 1;
        }
        return 0;
    }
}
