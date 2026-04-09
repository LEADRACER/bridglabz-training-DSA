import java.util.*;

/**
 * Problem: Two Sum (Find the indices).
 *
 * Student Level Explanation:
 * Given an array and a target, find the indices of two numbers that add up to the target.
 * 
 * Logic:
 * We want to find index 'i' and index 'j' such that numbers[i] + numbers[j] = Target.
 * This is the same as saying numbers[j] = Target - numbers[i].
 * 
 * We use a HashMap to store the numbers we've seen so far AND their original positions (indices).
 */
public class code5 {

    /**
     * Finds the indices of the two elements that add up to target.
     */
    public static int[] getTwoSumIndices(int[] numbers, int target) {
        // Map stores: Value -> Index
        Map<Integer, Integer> seenValues = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            int complementNeeded = target - currentNumber;

            // Step 1: Check if the 'Complement' we need was already seen
            if (seenValues.containsKey(complementNeeded)) {
                // Return the index of the complement and the current index
                return new int[] { seenValues.get(complementNeeded), i };
            }

            // Step 2: Store the current number and its index in our map memory
            seenValues.put(currentNumber, i);
        }

        // Return empty array if no pair exists
        return new int[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Two Sum Index Finder ---");
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter element at index " + i + ": ");
            data[i] = scanner.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = scanner.nextInt();

        int[] resultIndices = getTwoSumIndices(data, target);

        if (resultIndices.length == 2) {
            System.out.println("\nResult: Indices found!");
            System.out.println("Index 1: " + resultIndices[0] + " (Value: " + data[resultIndices[0]] + ")");
            System.out.println("Index 2: " + resultIndices[1] + " (Value: " + data[resultIndices[1]] + ")");
            System.out.println("Sum: " + data[resultIndices[0]] + " + " + data[resultIndices[1]] + " = " + target);
        } else {
            System.out.println("\nResult: No two numbers in the array add up to " + target);
        }

        scanner.close();
    }
}
