import java.util.Arrays;
import java.util.Stack;

/**
 * Stock Span Problem.
 * 
 * Problem: Calculate the span of stock prices for each day.
 * The span is the number of consecutive days before (including current) 
 * where the price was less than or equal to the current price.
 * 
 * Logic:
 * - Use a stack to store indices of the stock prices.
 * - The stack stores indices in a "monotonic decreasing" fashion of their prices.
 * 
 * Time Complexity: O(n) - Each element is pushed and popped at most once.
 * Space Complexity: O(n) for the stack.
 */
public class code3 {

    /**
     * Calculates the span for each day.
     * @param prices Array of stock prices.
     * @return Array of spans.
     */
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop elements from stack while they are less than or equal to prices[i]
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }

            // If stack is empty, it means prices[i] is the maximum so far
            if (stack.isEmpty()) {
                span[i] = i + 1;
            } else {
                // Span is the difference between current index and the index of the 
                // nearest greater element to the left.
                span[i] = i - stack.peek();
            }

            // Push current index to stack
            stack.push(i);
        }

        return span;
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        
        System.out.println("Stock Prices: " + Arrays.toString(prices));
        
        int[] spans = calculateSpan(prices);
        
        System.out.println("Stock Spans:  " + Arrays.toString(spans));
        
        // Expected Output: [1, 1, 1, 2, 1, 4, 6]
        // 100: 1
        // 80: 1
        // 60: 1
        // 70: index 3 (70) > index 2 (60). 3 - 1 (index of 80) = 2.
        // 60: 1
        // 75: 75 > 60, 75 > 70, 75 > 60. Index 5 - 1 (index of 80) = 4.
        // 85: 85 > 75, 85 > 80. Index 6 - 0 (index of 100) = 6.
    }
}
