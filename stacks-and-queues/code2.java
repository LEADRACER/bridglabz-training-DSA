import java.util.Stack;
import java.util.Scanner;

/**
 * Problem: Sort an existing Stack using Recursion.
 *
 * Student Level Explanation:
 * We use two recursive functions:
 * 1. sortStack: This function empties the stack by popping elements one by one.
 * 2. sortedInsert: Once the stack is empty, we start putting elements back. 
 *    But instead of just pushing them, we find the correct sorted position for each.
 */
public class code2 {

    /**
     * Recursive Function 1: Sorts the stack.
     */
    public static void sortStack(Stack<Integer> stack) {
        // Base Case: If stack is empty, we are done popping
        if (stack.isEmpty()) {
            return;
        }

        // 1. Remove the top element
        int currentElement = stack.pop();

        // 2. Recursively sort the remaining elements
        sortStack(stack);

        // 3. Put the removed element back into the now-sorted stack
        sortedInsert(stack, currentElement);
    }

    /**
     * Recursive Function 2: Inserts an element back in the correct position.
     */
    public static void sortedInsert(Stack<Integer> stack, int newElement) {
        // Base Case: If stack is empty OR the element is larger than the top, just push it
        // (This maintains the sorted order from bottom to top)
        if (stack.isEmpty() || newElement >= stack.peek()) {
            stack.push(newElement);
            return;
        }

        // If the top element is larger than 'newElement', we must set it aside temporarily
        int tempTop = stack.pop();
        
        // Try inserting 'newElement' again in the remaining stack
        sortedInsert(stack, newElement);

        // Put the temporarily removed element back on top
        stack.push(tempTop);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> studentStack = new Stack<>();

        System.out.println("--- Recursion: Sort a Stack ---");
        System.out.print("Enter how many numbers you want to put in the stack: ");
        int totalNumbers = scanner.nextInt();

        for (int i = 1; i <= totalNumbers; i++) {
            System.out.print("Enter number " + i + ": ");
            studentStack.push(scanner.nextInt());
        }

        System.out.println("\nOriginal Stack (Bottom to Top): " + studentStack);

        // Start the sorting process
        sortStack(studentStack);

        System.out.println("Sorted Stack   (Bottom to Top): " + studentStack);
        
        scanner.close();
    }
}
