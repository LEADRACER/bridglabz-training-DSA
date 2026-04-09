import java.util.Stack;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Problem: Stock Span Problem.
 *
 * Student Level Explanation:
 * The 'span' of a stock price on a specific day is how many days back (including today)
 * the price has been less than or equal to today's price.
 * 
 * Instead of comparing every day with every previous day (O(n^2)), 
 * we use a Stack of 'indices' to find the nearest previous day that had a higher price.
 */
public class code3 {

    /**
     * Calculates the span for each day based on stock prices.
     */
    public static int[] findStockSpans(int[] prices) {
        int numberOfDays = prices.length;
        int[] spans = new int[numberOfDays];
        
        // Stack stores indices of days. 
        // Logic: A day's index is kept in the stack if it has a higher price than today's.
        Stack<Integer> indexStack = new Stack<>();

        for (int today = 0; today < numberOfDays; today++) {
            
            // Loop Step 1: Pop indices from the stack while today's price is 
            // higher than the price at the indices stored in the stack.
            while (!indexStack.isEmpty() && prices[indexStack.peek()] <= prices[today]) {
                indexStack.pop();
            }

            // Loop Step 2: If the stack is empty, it means today's price is the highest so far.
            // The span will be (today's index + 1).
            if (indexStack.isEmpty()) {
                spans[today] = today + 1;
            } else {
                // Otherwise, the span is the distance from today to the previous higher price index.
                int previousHighIndex = indexStack.peek();
                spans[today] = today - previousHighIndex;
            }

            // Loop Step 3: Push today's index into the stack
            indexStack.push(today);
        }

        return spans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Stock Span Problem Solver ---");
        System.out.print("Enter the number of days: ");
        int totalDays = scanner.nextInt();

        int[] prices = new int[totalDays];
        for (int i = 0; i < totalDays; i++) {
            System.out.print("Enter stock price for Day " + (i + 1) + ": ");
            prices[i] = scanner.nextInt();
        }

        System.out.println("\nPrices input: " + Arrays.toString(prices));

        // Calculate spans
        int[] spans = findStockSpans(prices);

        System.out.println("Spans output: " + Arrays.toString(spans));
        
        System.out.println("\nExplanation of first result:");
        if (totalDays > 0) {
            System.out.println("The span for the first day is always 1.");
        }
        
        scanner.close();
    }
}
