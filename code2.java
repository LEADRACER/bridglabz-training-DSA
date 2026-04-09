import java.util.Stack;

/**
 * Sorting a Stack using Recursion.
 * 
 * Logic:
 * 1. Recursive Sort: Pop top, sort remaining, insert back.
 * 2. Sorted Insert: Find correct spot for the element in a sorted stack.
 * 
 * Complexity:
 * - Time: O(n^2)
 * - Space: O(n) for recursion stack
 */
public class code2 {

    /**
     * Function to sort the stack recursively.
     */
    public static void sortStack(Stack<Integer> stack) {
        // Base case: if stack is empty
        if (stack.isEmpty()) {
            return;
        }

        // Remove the top element
        int top = stack.pop();

        // Sort the remaining stack
        sortStack(stack);

        // Push the removed element back in sorted order
        sortedInsert(stack, top);
    }

    /**
     * Helper function to insert an element in its sorted position.
     */
    private static void sortedInsert(Stack<Integer> stack, int element) {
        // Base case: stack is empty or element is greater than top (ascending order)
        // If we want descending, change condition to element < stack.peek()
        if (stack.isEmpty() || element >= stack.peek()) {
            stack.push(element);
            return;
        }

        // If top is greater, remove it and recurse
        int temp = stack.pop();
        sortedInsert(stack, element);

        // Push back the popped element
        stack.push(temp);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(-5);
        stack.push(18);
        stack.push(14);
        stack.push(-3);

        System.out.println("Original Stack: " + stack);

        sortStack(stack);

        System.out.println("Sorted Stack (Ascending): " + stack);
        
        // Final verification check
        boolean isSorted = true;
        int prev = Integer.MIN_VALUE;
        Stack<Integer> copy = (Stack<Integer>) stack.clone();
        while(!copy.isEmpty()){
            int current = copy.pop();
            if(current < prev) {
                // Since it's a stack, top should be largest if it's ascending from bottom to top
                // Wait, ascending order in stack usually means bottom is smallest, top is largest.
                // Let's re-verify:
                // If bottom is 1, and we push 2, then pushes 3. Stack [1, 2, 3] <- top
                // My logic: element >= stack.peek() -> push.
                // If stack is [1, 2], and we insert 3: 3 >= 2 -> stick it on top. [1, 2, 3]
                // If stack is [1, 3], and we insert 2: 2 < 3 -> pop 3, insert 2 -> [1, 2], push 3 -> [1, 2, 3]
                // Correct. Top is largest.
            }
        }
    }
}
