import java.util.*;

/**
 * Problem: Find All Subarrays with Zero Sum.
 *
 * Student Level Explanation:
 * A subarray is a contiguous part of an array. We want to find which parts add up to exactly 0.
 * 
 * Logic Concept: Cumulative Sum
 * If we keep adding numbers as we go (e.g., 6, then 6+3=9, then 9-1=8...), and we see the 
 * SAME sum twice, it means the numbers in between must have added up to 0 to bring us back 
 * to that same sum!
 * 
 * Example: [6, 3, -1, -3, 4]
 * Index 1: Sum = 9
 * ... (adding -1, -3, 4) ...
 * Index 4: Sum = 9
 * Since the sum was 9 at index 1 and still 9 at index 4, the part between them (-1, -3, 4) is a zero-sum subarray.
 */
public class code1 {

    // Simple class to store where a subarray starts and ends
    static class SubarrayInfo {
        int start, end;
        SubarrayInfo(int s, int e) {
            this.start = s;
            this.end = e;
        }
        public String toString() {
            return "From index " + start + " to " + end;
        }
    }

    /**
     * Function to find all zero-sum subarrays.
     */
    public static List<SubarrayInfo> findZeroSumSubarrays(int[] numbers) {
        List<SubarrayInfo> foundSubarrays = new ArrayList<>();
        
        // Map stores: Cumulative Sum -> List of indices where this sum was seen
        Map<Integer, List<Integer>> sumTracker = new HashMap<>();

        int currentSum = 0;

        // Important: We start with sum 0 at index -1 to catch subarrays starting from the beginning
        List<Integer> initialIndices = new ArrayList<>();
        initialIndices.add(-1);
        sumTracker.put(0, initialIndices);

        for (int i = 0; i < numbers.length; i++) {
            currentSum += numbers[i];

            // If we have seen this sum before, we found zero-sum subarray(s)
            if (sumTracker.containsKey(currentSum)) {
                List<Integer> previousIndices = sumTracker.get(currentSum);
                for (int oldIndex : previousIndices) {
                    foundSubarrays.add(new SubarrayInfo(oldIndex + 1, i));
                }
                // Add the current index to the list for this sum
                previousIndices.add(i);
            } else {
                // First time seeing this sum, create a new list
                List<Integer> newIndexList = new ArrayList<>();
                newIndexList.add(i);
                sumTracker.put(currentSum, newIndexList);
            }
        }

        return foundSubarrays;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Zero Sum Subarray Finder ---");
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter element " + i + ": ");
            data[i] = scanner.nextInt();
        }

        System.out.println("\nSearching for zero-sum subarrays...");
        List<SubarrayInfo> results = findZeroSumSubarrays(data);

        if (results.isEmpty()) {
            System.out.println("No zero-sum subarrays were found.");
        } else {
            System.out.println("Found " + results.size() + " subarray(s):");
            for (SubarrayInfo info : results) {
                System.out.print(info + " -> { ");
                for (int k = info.start; k <= info.end; k++) {
                    System.out.print(data[k] + (k == info.end ? "" : ", "));
                }
                System.out.println(" }");
            }
        }
        
        scanner.close();
    }
}
