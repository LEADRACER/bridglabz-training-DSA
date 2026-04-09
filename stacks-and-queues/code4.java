import java.util.Scanner;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem: Sliding Window Maximum.
 *
 * Student Level Explanation:
 * Imagine a window of size 'K' sliding across an array. We want the maximum number inside the window at each step.
 * We use a Deque (Double-ended Queue) to store indices. 
 * The deque helps us by:
 * 1. Removing items that are no longer in the window.
 * 2. Removing items that are smaller than the new item (because a smaller item can never be the maximum anymore).
 */
public class code4 {

    /**
     * Finds the maximum element in every sliding window of size K.
     */
    public static int[] getSlidingWindowMax(int[] numbers, int windowSize) {
        if (numbers == null || numbers.length == 0 || windowSize <= 0) {
            return new int[0];
        }

        int totalNumbers = numbers.length;
        // The number of windows is (N - K + 1)
        int[] results = new int[totalNumbers - windowSize + 1];
        int resultIndex = 0;

        // Deque stores the indices of elements
        Deque<Integer> windowDeque = new LinkedList<>();

        for (int i = 0; i < totalNumbers; i++) {
            
            // Step 1: Remove indices that are out of bounds for the current window
            // If the front index is too old (outside current index - windowSize), get rid of it.
            if (!windowDeque.isEmpty() && windowDeque.peekFirst() <= i - windowSize) {
                windowDeque.pollFirst();
            }

            // Step 2: Maintain descending order in the deque.
            // If the current number is bigger than the number at the back of the deque,
            // the smaller number will NEVER be the maximum again. So we remove it.
            while (!windowDeque.isEmpty() && numbers[windowDeque.peekLast()] < numbers[i]) {
                windowDeque.pollLast();
            }

            // Step 3: Add the current element's index to the back
            windowDeque.offerLast(i);

            // Step 4: Once we have processed at least windowSize elements, 
            // the element at the front of the deque is the maximum for the current window.
            if (i >= windowSize - 1) {
                results[resultIndex++] = numbers[windowDeque.peekFirst()];
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Sliding Window Maximum ---");
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            data[i] = scanner.nextInt();
        }

        System.out.print("Enter window size (K): ");
        int k = scanner.nextInt();

        if (k > size) {
            System.out.println("Error: Window size cannot be larger than array size!");
        } else {
            System.out.println("\nInput Array: " + Arrays.toString(data));
            int[] maxValues = getSlidingWindowMax(data, k);
            System.out.println("Maximums in each window: " + Arrays.toString(maxValues));
        }

        scanner.close();
    }
}
