import java.util.*;

/**
 * Problem: Longest Consecutive Sequence.
 *
 * Student Level Explanation:
 * Given an unsorted array, we want to find the length of the longest 
 * sequence of numbers like 1, 2, 3, 4.
 * 
 * Logic:
 * We use a HashSet to store all numbers. 
 * How do we know if a number 'X' is the START of a sequence?
 * Simple: If 'X - 1' is NOT in the set, then 'X' must be a starting point.
 * From there, we just keep checking if X+1, X+2, X+3... are present.
 */
public class code3 {

    /**
     * Logic to find the length of the longest consecutive elements sequence.
     */
    public static int getLongestSequenceLength(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        // Step 1: Put all numbers into a HashSet for fast lookup
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int val : numbers) {
            uniqueNumbers.add(val);
        }

        int maxStreakLength = 0;

        // Step 2: Iterate through each number to identify potential starts
        for (int currentNum : uniqueNumbers) {
            
            // Step 3: Check if currentNum is the START of a sequence
            // (Only start if currentNum-1 is NOT present)
            if (!uniqueNumbers.contains(currentNum - 1)) {
                
                int startingPoint = currentNum;
                int currentStreak = 1;

                // Step 4: Count how many consecutive numbers exist after the start
                while (uniqueNumbers.contains(startingPoint + 1)) {
                    startingPoint += 1;
                    currentStreak += 1;
                }

                // Step 5: Update the maximum streak found so far
                if (currentStreak > maxStreakLength) {
                    maxStreakLength = currentStreak;
                }
            }
        }

        return maxStreakLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Longest Consecutive Sequence Finder ---");
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            data[i] = scanner.nextInt();
        }

        int maxLength = getLongestSequenceLength(data);
        System.out.println("\nThe length of the longest consecutive sequence is: " + maxLength);

        scanner.close();
    }
}
