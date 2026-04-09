import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find All Subarrays with Zero Sum.
 * 
 * Problem: Given an array, find all subarrays whose elements sum up to zero.
 * 
 * Logic:
 * - We calculate the cumulative sum of elements as we traverse the array.
 * - If the cumulative sum has been seen before, it means the sum of elements 
 *   between the previous occurrence and the current index is zero.
 * - We use a HashMap to store the cumulative sum as a key and a list of indices 
 *   as the value (since a sum can occur multiple times).
 * 
 * Time Complexity: O(n + count of zero-sum subarrays)
 * Space Complexity: O(n)
 */
public class code1 {

    static class Subarray {
        int start, end;

        Subarray(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    /**
     * Finds and prints all subarrays with zero sum.
     */
    public static List<Subarray> findZeroSumSubarrays(int[] arr) {
        List<Subarray> result = new ArrayList<>();
        // Map to store (cumulative sum, list of indices where this sum occurred)
        Map<Integer, List<Integer>> map = new HashMap<>();

        int cumulativeSum = 0;

        // Base case: to handle subarrays starting from index 0
        List<Integer> initialList = new ArrayList<>();
        initialList.add(-1);
        map.put(0, initialList);

        for (int i = 0; i < arr.length; i++) {
            cumulativeSum += arr[i];

            // If cumulative sum has occurred before
            if (map.containsKey(cumulativeSum)) {
                List<Integer> indices = map.get(cumulativeSum);
                for (int startIdx : indices) {
                    result.add(new Subarray(startIdx + 1, i));
                }
                indices.add(i);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                map.put(cumulativeSum, newList);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        
        System.out.println("Input Array: " + Arrays.toString(arr));
        
        List<Subarray> subarrays = findZeroSumSubarrays(arr);
        
        if (subarrays.isEmpty()) {
            System.out.println("No zero-sum subarrays found.");
        } else {
            System.out.println("Zero-sum subarrays (indices):");
            for (Subarray sub : subarrays) {
                System.out.print(sub + " ");
                // Print values for clarity
                System.out.print("(");
                for(int k = sub.start; k <= sub.end; k++) {
                    System.out.print(arr[k] + (k == sub.end ? "" : ", "));
                }
                System.out.println(")");
            }
        }
    }
}
