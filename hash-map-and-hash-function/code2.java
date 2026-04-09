import java.util.*;

public class code2 {
    public static void checkPair(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        boolean found = false;
        for (int x : arr) {
            int needed = target - x;
            if (set.contains(needed)) {
                System.out.println("Pair: " + needed + ", " + x);
                found = true;
                break;
            }
            set.add(x);
        }
        if (!found) System.out.println("No pair found");
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        checkPair(arr, 16);
    }
}
