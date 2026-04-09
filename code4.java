import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Sliding Window Maximum.
 * 
 * Problem: Given an array and a window size k, find the maximum element in
 * each sliding window of size k.
 * 
 * Logic:
 * - Use a Deque (Double-ended Queue) to maintain indices of elements in the window.
 * - The deque will store indices such that the values are in descending order.
 * - For each index 'i':
 *   1. Remove indices that are out of the current window.
 *   2. Remove indices from the back whose values are smaller than the current value.
 *   3. Push current index.
 *   4. The front of the deque is the maximum for the current window.
 * 
 * Time Complexity: O(n) - Each element is added and removed at most once.
 * Space Complexity: O(k) for the deque.
 */
public class code4 {

    /**
     * Finds the maximum element in each sliding window of size k.
     * @param nums Input array of integers.
     * @param k Window size.
     * @return Array containing the maximum in each window.
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resultIndex = 0;

        // Deque to store indices
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are out of the window window [i-k+1, i]
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. Remove indices of elements smaller than the current element from the back
            // They cannot be the maximum for this or any future window starting from this index
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. Add current index to deque
            deque.offerLast(i);

            // 4. If window has at least k elements, the head of the deque is the max
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println("Input Array:  " + Arrays.toString(nums));
        System.out.println("Window Size:   " + k);

        int[] result = maxSlidingWindow(nums, k);

        System.out.println("Sliding Max:   " + Arrays.toString(result));

        // Expected Workflow:
        // [1, 3, -1] -> 3
        // [3, -1, -3] -> 3
        // [-1, -3, 5] -> 5
        // [-3, 5, 3] -> 5
        // [5, 3, 6] -> 6
        // [3, 6, 7] -> 7
        // Result: [3, 3, 5, 5, 6, 7]
    }
}
