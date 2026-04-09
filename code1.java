import java.util.Stack;

/**
 * A Queue implementation using two Stacks.
 * 
 * Performance:
 * - Enqueue: O(1)
 * - Dequeue: Amortized O(1), Worst-case O(n)
 * - Peek: Amortized O(1), Worst-case O(n)
 * - Empty: O(1)
 */
public class code1<T> {
    private Stack<T> stackEnqueue;
    private Stack<T> stackDequeue;

    public code1() {
        stackEnqueue = new Stack<>();
        stackDequeue = new Stack<>();
    }

    /**
     * Pushes element x to the back of the queue.
     */
    public void enqueue(T x) {
        stackEnqueue.push(x);
    }

    /**
     * Removes the element from the front of the queue and returns it.
     */
    public T dequeue() {
        prepareDequeueStack();
        if (stackDequeue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackDequeue.pop();
    }

    /**
     * Get the front element.
     */
    public T peek() {
        prepareDequeueStack();
        if (stackDequeue.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackDequeue.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean isEmpty() {
        return stackEnqueue.isEmpty() && stackDequeue.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     */
    public int size() {
        return stackEnqueue.size() + stackDequeue.size();
    }

    /**
     * Internal helper to shift elements from enqueue stack to dequeue stack when needed.
     */
    private void prepareDequeueStack() {
        if (stackDequeue.isEmpty()) {
            while (!stackEnqueue.isEmpty()) {
                stackDequeue.push(stackEnqueue.pop());
            }
        }
    }

    public static void main(String[] args) {
        code1<Integer> queue = new code1<>();

        System.out.println("Enqueuing: 1, 2, 3");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Peek: " + queue.peek()); // Should be 1
        System.out.println("Dequeue: " + queue.dequeue()); // Should be 1
        System.out.println("Dequeue: " + queue.dequeue()); // Should be 2

        System.out.println("Enqueuing: 4");
        queue.enqueue(4);

        System.out.println("Dequeue: " + queue.dequeue()); // Should be 3
        System.out.println("Dequeue: " + queue.dequeue()); // Should be 4
        
        System.out.println("Is empty? " + queue.isEmpty()); // Should be true
    }
}
