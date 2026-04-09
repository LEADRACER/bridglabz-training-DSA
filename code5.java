/**
 * Circular Tour Problem (Gas Station Problem).
 * 
 * Problem: Given a set of petrol pumps with (petrol amount, distance to next pump),
 * find the starting pump index to complete a circular tour.
 * 
 * Logic:
 * - If total petrol < total distance, no solution exists.
 * - We maintain a 'surplus' of petrol. If it becomes negative at any point,
 *   it means the starting pump must be after the current pump.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class code5 {

    static class PetrolPump {
        int petrol;
        int distance;

        public PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    /**
     * Finds the starting point for a circular tour.
     * @param pumps Array of PetrolPump objects.
     * @return Index of the starting pump, or -1 if no tour is possible.
     */
    public static int findStartingPoint(PetrolPump[] pumps) {
        int n = pumps.length;
        int start = 0;
        int surplus = 0;
        int deficit = 0;

        for (int i = 0; i < n; i++) {
            surplus += pumps[i].petrol - pumps[i].distance;
            
            // If current surplus is negative, we can't start from any pump
            // between the current start and index i.
            if (surplus < 0) {
                deficit += surplus;
                surplus = 0;
                start = i + 1;
            }
        }

        // Check if total petrol is sufficient for total distance
        return (surplus + deficit >= 0) ? start : -1;
    }

    public static void main(String[] args) {
        // Petrol, Distance
        PetrolPump[] pumps = {
            new PetrolPump(4, 6),
            new PetrolPump(6, 5),
            new PetrolPump(7, 3),
            new PetrolPump(4, 5)
        };

        System.out.println("Petrol Pumps (Petrol, Distance):");
        for (int i = 0; i < pumps.length; i++) {
            System.out.printf("Pump %d: (%d, %d)\n", i, pumps[i].petrol, pumps[i].distance);
        }

        int start = findStartingPoint(pumps);

        if (start == -1) {
            System.out.println("\nNo circular tour possible.");
        } else {
            System.out.println("\nStarting point for circular tour: Pump " + start);
        }
        
        // Logic Trace:
        // Pump 0: 4-6 = -2. surplus=-2. deficit=-2, surplus=0, start=1
        // Pump 1: 6-5 = 1. surplus=1.
        // Pump 2: 7-3 = 4. surplus=5.
        // Pump 3: 4-5 = -1. surplus=4.
        // Final check: surplus(4) + deficit(-2) = 2. 2 >= 0. Return start 1.
    }
}
