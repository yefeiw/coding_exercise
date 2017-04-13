import java.util.*;

class Mergesort {
    private int[] helper;

    private int number;

    public void sort(int[] values) {
        number = values.length;
        this.helper = new int[number];
        mergesort(values, 0, number - 1);
    }

    private void mergesort(int[] numbers, int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(numbers, low, middle);
            // Sort the right side of the array
            mergesort(numbers, middle + 1, high);
            // Combine them both
            merge(numbers, low, middle, high);
        }
    }

    private void merge(int[] numbers, int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }
}
class QuickSort {
    public void sort(int[] arr) {
        quickSort(arr,0,arr.length-1);
    }
    public  void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }
}

public class sortTest{
    public static void main (String args[]) {
    // int testIteration = 10000;
    //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Mergesort merge = new Mergesort();
    QuickSort quick = new QuickSort();
    for (int j = 0; j < testIteration; j++) {
        int[] input = testInput.generateRandomArray();
        int[] mergesort = input;
        int[] quicksort = input;
        int[] reference = input;
        merge.sort(mergesort);
        quick.sort(quicksort);
        Arrays.sort(reference);
        for (int i = 0; i < input.length; i++) {
            if (mergesort[i] != quicksort[i]) {
                System.out.println("Mismatch found");
                System.exit(1);
            }
            if (mergesort[i] != reference[i]) {
                System.out.println("Mismatch found");
                System.exit(1);
            }
        }
    }
    System.out.println("Test Passed, ready to submit");
}
}