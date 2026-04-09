import java.util.Stack;
import java.util.Scanner;

/**
 * Problem: Implement a Queue using two Stacks.
 *
 * Student Level Explanation:
 * A Queue follows FIFO (First In First Out), while a Stack follows LIFO (Last In First Out).
 * To make a Queue using Stacks, we use one stack for adding items and another for removing them.
 * When we want to remove an item, we move everything from the first stack to the second stack,
 * which reverses the order and makes it behave like a Queue!
 */
public class code1<T> {
    // Stack to handle adding new elements (Enqueuing)
    private Stack<T> stackEnqueue;
    // Stack to handle removing elements (Dequeuing)
    private Stack<T> stackDequeue;

    public code1() {
        stackEnqueue = new Stack<>();
        stackDequeue = new Stack<>();
    }

    /**
     * Step 1: Add an element to the back of the queue.
     * Logic: Simply push the element into the enqueue stack.
     */
    public void enqueue(T item) {
        stackEnqueue.push(item);
    }

    /**
     * Step 2: Remove the front element from the queue.
     * Logic: If the dequeue stack is empty, transfer everything from the enqueue stack.
     */
    public T dequeue() {
        // Prepare the dequeue stack if it's empty
        fillDequeueStack();
        
        if (stackDequeue.isEmpty()) {
            System.out.println("Error: The queue is empty!");
            return null;
        }
        return stackDequeue.pop();
    }

    /**
     * Step 3: Peek at the front element without removing it.
     */
    public T peek() {
        fillDequeueStack();
        if (stackDequeue.isEmpty()) {
            System.out.println("Error: The queue is empty!");
            return null;
        }
        return stackDequeue.peek();
    }

    /**
     * Helper Method: Moves elements from enqueue stack to dequeue stack.
     * This is only done when the dequeue stack is empty.
     */
    private void fillDequeueStack() {
        if (stackDequeue.isEmpty()) {
            while (!stackEnqueue.isEmpty()) {
                // Pop from one and push to the other to reverse the order
                stackDequeue.push(stackEnqueue.pop());
            }
        }
    }

    public boolean isEmpty() {
        return stackEnqueue.isEmpty() && stackDequeue.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        code1<Integer> myQueue = new code1<>();

        System.out.println("--- Queue Using Stacks Demo ---");
        System.out.println("How many numbers do you want to add to the queue?");
        int count = scanner.nextInt();

        for (int i = 1; i <= count; i++) {
            System.out.print("Enter number " + i + ": ");
            int value = scanner.nextInt();
            myQueue.enqueue(value);
        }

        System.out.println("\nOperations on the Queue:");
        if (!myQueue.isEmpty()) {
            System.out.println("Front element (Peek): " + myQueue.peek());
            System.out.println("Removing (Dequeue) element: " + myQueue.dequeue());
            System.out.println("Next front element: " + myQueue.peek());
        }

        System.out.println("\nFinal Queue Status: " + (myQueue.isEmpty() ? "Empty" : "Not Empty"));
        scanner.close();
    }
}
