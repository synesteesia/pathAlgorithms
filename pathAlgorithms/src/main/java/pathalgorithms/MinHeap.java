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
public class MinHeap {

    private final Vertex[] heap;
    private int heapSize;

    public MinHeap(int n) {
        heap = new Vertex[n];
        heapSize = 0;
    }

    public void add(Vertex i) {
        heap[heapSize] = i;
        heap(0);
    }

    private void heap(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && heap[left].getDistance() - heap[smallest].getDistance() < 0) {
            smallest = left;
        }

        if (right < heapSize && heap[right].getDistance() - heap[smallest].getDistance() < 0) {
            smallest = right;
        }

        if (smallest != i) {
            Vertex change = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = change;

            heap(smallest);
        }
    }

    public Vertex poll() {
        Vertex min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;

        int child;
        int help = 0;
        Vertex temp = heap[0];
        while (2 * help + 1 < heapSize) {
            child = smallestChild(help);
            if (heap[child].getDistance() - temp.getDistance() < 0) {
                heap[help] = heap[child];
                help = child;
            } else {
                break;
            }
        }
        heap[help] = temp;

        return min;
    }

    private int smallestChild(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        return heap[left].getDistance() - heap[right].getDistance() < 0 ? left : right;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }
}
