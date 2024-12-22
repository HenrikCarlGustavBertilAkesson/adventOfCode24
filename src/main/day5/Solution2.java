package main.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution2 {

    public static Integer[] fixUpdate(ArrayList<Integer> update, HashMap<Integer, ArrayList<Integer>> order) {

        Integer[] arr = update.toArray(new Integer[0]);
        QuickSort qs = new QuickSort();
        qs.quickSort(order, arr, 0, arr.length - 1);

        return arr;
    }

    public static boolean inOrder(ArrayList<Integer> update,
                                    HashMap<Integer, ArrayList<Integer>> order) {

        int left, right;
        for(int i = 0; i < update.size(); i++) {
            left = update.get(i);
            for(int j = i + 1; j < update.size(); j++) {
                right = update.get(j);
                if((order.get(left) != null && !order.get(left).contains(right)) ||
                (order.get(right) != null && order.get(right).contains(left))) return false;
            }
        }

        return true;
    }

    public static int middleNum(ArrayList<Integer> update) { return update.get(update.size() / 2); }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, ArrayList<Integer>> order = new HashMap<>();
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        try {
            String line;
            while (!"".equals((line = br.readLine()))) {
                String[] tuple = line.split("\\|");
                int left = Integer.parseInt(tuple[0]);
                int right = Integer.parseInt(tuple[1]);
                if(order.get(left) == null) {
                    ArrayList<Integer> nums = new ArrayList<>();
                    nums.add(right);
                    order.put(left, nums);
                } else {
                    order.get(left).add(right);
                }
            }

            while((line = br.readLine()) != null) {
                String[] nums = line.split(",");
                ArrayList<Integer> update = new ArrayList<>(); 
                for(String num : nums) {
                    update.add(Integer.parseInt(num));
                }
                updates.add(update);
            }

        } catch (Exception e) {
            throw new IOException(e);
        }
     
        int result = 0;
        for(ArrayList<Integer> update : updates) {
            if(!inOrder(update, order)) {
                Integer[] newUpdate = fixUpdate(update, order);
                result += newUpdate[newUpdate.length / 2];
                for(int i : newUpdate) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }

        System.out.println(result);
    }
}