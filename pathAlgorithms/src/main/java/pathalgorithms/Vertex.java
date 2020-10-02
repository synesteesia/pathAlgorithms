/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

/**
 *
 * @author mikko
 */
public class Vertex {

    private final int index;
    private final double distance;

    public Vertex(int index, double distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return this.index;
    }

    public double getDistance() {
        return this.distance;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Vertex.class) {
            return false;
        }
        Vertex v = (Vertex) o;
        return v.distance == this.distance && v.index == this.index;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.index;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
        return hash;
    }
}
