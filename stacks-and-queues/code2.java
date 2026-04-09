import java.util.Stack;

public class code2 {
    public static void sort(Stack<Integer> s) {
        if (s.isEmpty()) return;
        int x = s.pop();
        sort(s);
        insert(s, x);
    }

    private static void insert(Stack<Integer> s, int x) {
        if (s.isEmpty() || x >= s.peek()) {
            s.push(x);
            return;
        }
        int temp = s.pop();
        insert(s, x);
        s.push(temp);
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(30); s.push(-5); s.push(18);
        sort(s);
        System.out.println(s);
    }
}
