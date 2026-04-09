import java.util.*;

public class code1 {
    static class Subarray {
        int start, end;
        Subarray(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static List<Subarray> find(int[] arr) {
        List<Subarray> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        map.put(0, list);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                List<Integer> indices = map.get(sum);
                for (int it : indices) {
                    res.add(new Subarray(it + 1, i));
                }
                indices.add(i);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                map.put(sum, newList);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        List<Subarray> result = find(arr);
        for (Subarray s : result) {
            System.out.println("Subarray from " + s.start + " to " + s.end);
        }
    }
}
