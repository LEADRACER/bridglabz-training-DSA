import java.util.Stack;

public class code1<T> {
    private Stack<T> s1 = new Stack<>();
    private Stack<T> s2 = new Stack<>();

    public void enqueue(T x) {
        s1.push(x);
    }

    public T dequeue() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) s2.push(s1.pop());
        }
        return s2.isEmpty() ? null : s2.pop();
    }

    public static void main(String[] args) {
        code1<Integer> q = new code1<>();
        q.enqueue(1);
        q.enqueue(2);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
