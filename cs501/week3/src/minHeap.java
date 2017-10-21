import java.util.*;

/** Min-heap implementation */
class MinHeap {
    private int[] Heap;   // Pointer to the heap array
    private int size;   // Maximum size of the heap
    private int n;      // Number of things in heap

    public MinHeap(int[] input, int num, int max){ 
        Heap = Arrays.copyOf(input,input.length);  
        n = num;  
        size = max;  
        buildheap(); 
    }

    public MinHeap( int max){ 
        Heap = new int[max];  
        n = 0;  
        size = max;  
        Arrays.fill(Heap,Integer.MAX_VALUE);
    }

    /** Return current size of the heap */
    public int size() { return n; }

    /** Insert into heap */
    public void insert(int val) {
        assert n < size : "Heap is full";
        int curr = n++;
        Heap[curr] = val;                 // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0)  && (Heap[curr] < Heap[parent(curr)])) {
            swap(Heap, curr, parent(curr));
            curr = parent(curr);
        }
    }
    public int remove() {     // Remove minimum value
        assert n > 0 : "Removing from empty heap";
        swap(Heap, 0, --n); // Swap minimum with last value
        if (n != 0)      // Not on last element
            siftdown(0);   // Put new heap root val in correct place
        return Heap[n];
    }
    public void print() {
        System.out.printf("current size: %d, max size %d\n",n, size);
        for(int i = 0; i < Heap.length; i++) {
            if ( i > 0 ) System.out.print(",");
            System.out.print(Heap[i]);
        }
        System.out.println();
    }
    //===================Private classes=================================//
    /** Is pos a leaf position? */
    private boolean isLeaf(int pos){ 
        return (pos >= n/2) && (pos < n); 
    }
    /** Return position for left child of pos */
    private int leftchild(int pos) {
        assert pos < n/2 : "Position has no left child";
        return 2*pos + 1;
    }
    /** Return position for right child of pos */
    private int rightchild(int pos) {
        assert pos < (n-1)/2 : "Position has no right child";
        return 2*pos + 2;
    }

    /** Return position for parent */
    private int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos-1)/2;
    }

    /** Heapify contents of Heap */
    private void buildheap(){ 
        for (int i=n/2-1; i>=0; i--) siftdown(i); 
    }


    /** Put element in its correct place */
    private void siftdown(int pos) {
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
            while (!isLeaf(pos)) {
                int j = leftchild(pos);
                if ((j<(n-1)) && (Heap[j] > Heap[j+1])) 
                    j++; // j is now index of child with greater value
                if (Heap[pos] <= Heap[j])
                    return;
                swap(Heap, pos, j);
                pos = j;  // Move down
            }
    }
    private void swap(int[] Heap, int pos1, int pos2) {
        assert (pos1 < Heap.length && pos2 < Heap.length) : "Index out of bounds";
        int temp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = temp;
    }
}

public class minHeap{
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 100000;
        Input  testInput = new Input();

        for (int j = 0; j < testIteration; j++) {
            int [] input = testInput.generateRandomArray(10);
            MinHeap heap1 = new MinHeap(input,input.length,input.length+1);
            MinHeap heap2 = new MinHeap(input.length+1);
            for(int i = 0; i < input.length; i++) {
                heap2.insert(input[i]);
            }
            int[] reference = Arrays.copyOf(input,input.length);
            Arrays.sort(reference);
            // System.out.println("input");
            // testInput.printArray(input);
            // System.out.println("reference");
            // testInput.printArray(reference);
            // System.out.println("output");
            // heap1.print();
            // heap2.print();
            for (int i = 0; i < input.length; i++) {
                int cand1 = heap1.remove();
                int cand2 = heap2.remove();
                if (cand1 != cand2) {
                    System.out.printf("Mismatch found at %d, cand1 is %d, cand2 is %d\n", i, cand1, cand2);
                    System.exit(1);
                }
                if (cand1 != reference[i]) {
                    System.out.printf("Mismatch found at %d, cand1 is %d, ref is %d\n",i, cand1, reference[i]);
                    System.exit(1);
                }
            }
        }
        System.out.println("Test Passed, ready to submit");
    }
}
