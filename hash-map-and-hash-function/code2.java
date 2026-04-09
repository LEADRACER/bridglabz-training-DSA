import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Check for a Pair with Given Sum in an Array (Two Sum Problem).
 * 
 * Problem: Given an array and a target sum, find if there exists a pair of elements
 * whose sum is equal to the target.
 * 
 * Logic:
 * - Use a HashSet to store visited numbers.
 * - For each number 'x', check if (target - x) is already in the set.
 * - This achieves linear time complexity.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class code2 {

    /**
     * Checks if a pair exists with the given target sum.
     * @param arr The input array.
     * @param target The target sum.
     * @return int[] array containing the pair, or null if not found.
     */
    public static int[] findPair(int[] arr, int target) {
        Set<Integer> visited = new HashSet<>();

        for (int num : arr) {
            int complement = target - num;
            if (visited.contains(complement)) {
                return new int[]{complement, num};
            }
            visited.add(num);
        }

        return null; // No pair found
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int target = 16;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target Sum: " + target);

        int[] pair = findPair(arr, target);

        if (pair != null) {
            System.out.println("Pair found: " + pair[0] + " + " + pair[1] + " = " + target);
        } else {
            System.out.println("No pair with the given sum exists.");
        }
    }
}
