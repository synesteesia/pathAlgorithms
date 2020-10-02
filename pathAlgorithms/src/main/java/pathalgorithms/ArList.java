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
public class ArList {

    private int size;
    private static final int MAX_SIZE = 4;
    private int[] array;

    public ArList() {
        this.size = 0;
        this.array = new int[MAX_SIZE];
    }

    public ArList(int... elements) {
        this.size = 0;
        this.array = new int[MAX_SIZE];
        
        for (int element : elements) {
            this.array[size++] = element;
        }
    }

    public void add(int i) {
        array[size++] = i;
    }

    public void freeze() {
        int[] help = new int[size];
        for (int i = 0; i < size; i++) {
            help[i] = array[i];
        }
        this.array = help;
    }

    public int[] getArray() {
        return this.array;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(int el) {
        for (int i : array) {
            if (el == i) {
                return true;
            }
        }
        return false;
    }
}
