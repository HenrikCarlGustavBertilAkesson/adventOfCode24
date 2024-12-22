package main.day5;

import java.util.ArrayList;
import java.util.HashMap;

public class QuickSort {

    public void quickSort(HashMap<Integer, ArrayList<Integer>> order, Integer[] arr, int low, int high) {
        if(low < high ) {

            int pivot = partition(order, arr, low, high);
            quickSort(order, arr, low, pivot - 1);
            quickSort(order, arr, pivot + 1, high);
        }
    }

    public boolean isGreater(HashMap<Integer, ArrayList<Integer>> order, int x, int y) {
        return (order.get(x) != null && !order.get(x).contains(y)) ||
                (order.get(y) != null && order.get(y).contains(x));
    }

    public int partition(HashMap<Integer, ArrayList<Integer>> order, Integer[] arr, int low, int high) {
        
        int pivot = arr[low], k = high, swap;

        for(int i = high; i > low; i--) {
            if(isGreater(order, arr[i], pivot)) {
                swap = arr[i];
                arr[i] = arr[k];
                arr[k] = swap;
                k--;
            }
        }

        swap = arr[k];
        arr[k] = arr[low];
        arr[low] = swap;
        
        return k;
    }
}