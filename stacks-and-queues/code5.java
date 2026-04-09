import java.util.Scanner;

/**
 * Problem: Circular Tour (Gas Station Problem).
 *
 * Student Level Explanation:
 * You have multiple petrol pumps in a circle. Each pump gives some petrol,
 * and there is a distance to the next pump. You want to find a starting pump
 * where you can complete a full circle without running out of petrol.
 * 
 * Instead of checking every possible starting pump (O(n^2)), we use a smart logic:
 * If we start at pump A and fail at pump B, we don't need to try any pump between A and B.
 * We can jump straight to B+1!
 */
public class code5 {

    // Helper class to store pump data
    static class PetrolPump {
        int petrol;
        int distanceToNext;

        public PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distanceToNext = distance;
        }
    }

    /**
     * Determines the starting index to complete a circular tour.
     */
    public static int findStart(PetrolPump[] pumps) {
        int n = pumps.length;
        int currentSurplus = 0; // Petrol currently in my tank
        int totalDeficit = 0;   // Petrol I was short by in earlier attempts
        int startIndex = 0;     // Current candidate starting point

        for (int i = 0; i < n; i++) {
            currentSurplus += (pumps[i].petrol - pumps[i].distanceToNext);

            // If my tank goes below 0, I cannot reach the next pump
            if (currentSurplus < 0) {
                // Keep track of the deficit so I can check if tour is possible at the end
                totalDeficit += currentSurplus;
                // Reset my tank and try starting from the next pump
                currentSurplus = 0;
                startIndex = i + 1;
            }
        }

        // Final sanity check: If total petrol (surplus + deficit) is positive, 
        // it means we have enough gas to cover the whole distance.
        if (currentSurplus + totalDeficit >= 0) {
            return startIndex;
        } else {
            return -1; // Not possible to complete a circle
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Circular Tour Solver ---");
        System.out.print("How many petrol pumps are there? ");
        int totalPumps = scanner.nextInt();

        PetrolPump[] pumps = new PetrolPump[totalPumps];
        for (int i = 0; i < totalPumps; i++) {
            System.out.println("\nPump " + i + ":");
            System.out.print("  Petrol available: ");
            int p = scanner.nextInt();
            System.out.print("  Distance to next pump: ");
            int d = scanner.nextInt();
            pumps[i] = new PetrolPump(p, d);
        }

        int result = findStart(pumps);

        if (result == -1) {
            System.out.println("\nResult: No circular tour is possible from any pump.");
        } else {
            System.out.println("\nResult: You should start at Pump Index " + result);
        }

        scanner.close();
    }
}
