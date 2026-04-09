import java.util.*;

/**
 * Problem: Check for a Pair with a Given Sum.
 *
 * Student Level Explanation:
 * Given a list of numbers and a target total, we want to see if any two numbers 
 * in the list add up to that total.
 * 
 * Logic:
 * Instead of checking every possible pair (which is slow), we use a 'HashSet' 
 * to remember which numbers we have already seen.
 * For every number 'X' we look at, we calculate 'Target - X'. 
 * If that 'Complement' is already in our set, then we found a pair!
 */
public class code2 {

    /**
     * Logic to find if a pair exists that adds up to the target.
     */
    public static void findAndPrintPair(int[] numbers, int targetSum) {
        // HashSet to store numbers we have already visited
        Set<Integer> visitedNumbers = new HashSet<>();
        boolean found = false;

        for (int currentElement : numbers) {
            int neededComplement = targetSum - currentElement;

            // Step 1: Check if the number we need is in our 'visited' memory
            if (visitedNumbers.contains(neededComplement)) {
                System.out.println("Result: Pair found! " + neededComplement + " + " + currentElement + " = " + targetSum);
                found = true;
                break; // Stop after finding the first pair
            }

            // Step 2: If not found, add the current number to 'visited' memory and move to next
            visitedNumbers.add(currentElement);
        }

        if (!found) {
            System.out.println("Result: No pair found in the array adds up to " + targetSum);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Pair Sum Finder ---");
        System.out.print("How many numbers are in your array? ");
        int totalElements = scanner.nextInt();

        int[] data = new int[totalElements];
        for (int i = 0; i < totalElements; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            data[i] = scanner.nextInt();
        }

        System.out.print("What is your target sum? ");
        int target = scanner.nextInt();

        findAndPrintPair(data, target);

        scanner.close();
    }
}
