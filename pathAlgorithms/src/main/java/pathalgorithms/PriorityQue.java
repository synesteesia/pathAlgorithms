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
public class PriorityQue {

    static int[] heap;
    static int heapSize;
    
     public static void heap(int size, int i){
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if(left < size && heap[left] < heap[smallest]){
            smallest = left;
        }
        
        if(right < size && heap[right] < heap[smallest]){
            smallest = right;
        }
        
        if(smallest != i){
            int vaihda = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = vaihda;
            
            heap(size, smallest);
        }
    }

    public static void buildHeap(int[] heap, int n){
        heap = heap;
        heapSize = n;
        for(int i = (n / 2) - 1; i >= 0; i--){
            heap(n, i);
        }
    }
    
    public static int poll(){
        int min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;

        int child;
        int help = 0;
        int temp = heap[0];
        while (2*help + 1 < heapSize) {
            child = smallestChild(help);
            if (temp > heap[child]) {
                heap[help] = heap[child];
                help = child;
            } else {
                break;
            }
        }
        heap[help] = temp;
        
        return min;
    }
    
    private static int smallestChild(int i) {
        int left = 2*i + 1;
        int right = 2*i + 2;
        return heap[left] < heap[right] ? left : right;
    }
}

