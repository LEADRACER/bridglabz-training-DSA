import java.util.*;

public class code3 {
    public static int longest(int[] arr) {
        if (arr.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int x : arr) set.add(x);
        int max = 0;
        for (int x : set) {
            if (!set.contains(x - 1)) {
                int curr = x;
                int streak = 1;
                while (set.contains(curr + 1)) {
                    curr++;
                    streak++;
                }
                max = Math.max(max, streak);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(longest(arr));
    }
}
