import java.util.Stack;
import java.util.Arrays;

public class code3 {
    public static int[] calculate(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && prices[s.peek()] <= prices[i]) s.pop();
            spans[i] = s.isEmpty() ? i + 1 : i - s.peek();
            s.push(i);
        }
        return spans;
    }

    public static void main(String[] args) {
        int[] p = {100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(calculate(p)));
    }
}
