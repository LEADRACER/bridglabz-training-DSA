import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Longest Consecutive Sequence.
 * 
 * Problem: Given an unsorted array, find the length of the longest consecutive
 * elements sequence.
 * 
 * Logic:
 * - Insert all numbers into a HashSet for O(1) lookup.
 * - Iterate through the set. For each number 'x', check if (x-1) exists.
 * - If (x-1) does NOT exist, 'x' is the potential start of a sequence.
 * - Incrementally check for (x+1), (x+2), ... while they exist in the set.
 * - Track the maximum length found.
 * 
 * Time Complexity: O(n) - Each number is visited a constant number of times.
 * Space Complexity: O(n) - To store the numbers in the HashSet.
 */
public class code3 {

    /**
     * Finds the length of the longest consecutive sequence.
     * @param nums Unsorted input array.
     * @return Length of the longest consecutive sequence.
     */
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1. Store all unique numbers in a set
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 2. Iterate through the set
        for (int num : numSet) {
            // Check if 'num' is the start of a sequence
            // We only start counting if 'num-1' is not present
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Look for consecutive elements
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        
        System.out.println("Input Array: " + Arrays.toString(nums));
        
        int length = longestConsecutive(nums);
        
        System.out.println("Length of longest consecutive sequence: " + length);
        
        // Expected Logic:
        // [1, 2, 3, 4] forms a sequence. Length = 4.
        
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("\nInput Array 2: " + Arrays.toString(nums2));
        System.out.println("Length of longest consecutive sequence: " + longestConsecutive(nums2));
        // [0, 1, 2, 3, 4, 5, 6, 7, 8] forms a sequence. Length = 9.
    }
}
