import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem: Design and Implement a Custom Hash Map.
 *
 * Student Level Explanation:
 * A Hash Map stores data in Key-Value pairs. 
 * Imagine a row of 'Buckets'. We use a 'Hash Function' to decide which bucket a key belongs to.
 * 
 * Logic:
 * 1. Bucket Array: An array where each slot (bucket) is a Linked List.
 * 2. Hash Function: key.hashCode() % number_of_buckets.
 * 3. Separate Chaining: If two keys end up in the same bucket, we just append them to 
 *    the Linked List in that bucket.
 */
public class code4<K, V> {

    // Helper class to store the actual data node
    static class MapNode<K, V> {
        K key;
        V value;
        MapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public String toString() {
            return "[" + key + " : " + value + "]";
        }
    }

    private int numberOfBuckets;
    private LinkedList<MapNode<K, V>>[] bucketList;
    private int elementCount;

    @SuppressWarnings("unchecked")
    public code4() {
        this.numberOfBuckets = 5; // Start small for easier tracing
        this.bucketList = new LinkedList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            bucketList[i] = new LinkedList<>();
        }
        this.elementCount = 0;
    }

    /**
     * Map Step 1: Find which bucket a key belongs to.
     */
    private int findBucketIndex(K key) {
        if (key == null) return 0;
        // Use Math.abs because hashCode can be negative
        int hashCode = Math.abs(key.hashCode());
        return hashCode % numberOfBuckets;
    }

    /**
     * Map Step 2: Store a key-value pair.
     */
    public void put(K key, V value) {
        int bucketIndex = findBucketIndex(key);
        LinkedList<MapNode<K, V>> currentBucket = bucketList[bucketIndex];

        // Check if the key already exists to update its value
        for (MapNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        // If not found, add a new node
        currentBucket.add(new MapNode<>(key, value));
        elementCount++;
    }

    /**
     * Map Step 3: Retrieve a value using a key.
     */
    public V get(K key) {
        int bucketIndex = findBucketIndex(key);
        LinkedList<MapNode<K, V>> currentBucket = bucketList[bucketIndex];

        for (MapNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null; // Key not found
    }

    /**
     * Map Step 4: Remove a key from the map.
     */
    public void remove(K key) {
        int bucketIndex = findBucketIndex(key);
        LinkedList<MapNode<K, V>> currentBucket = bucketList[bucketIndex];

        MapNode<K, V> toRemove = null;
        for (MapNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) {
                toRemove = node;
                break;
            }
        }

        if (toRemove != null) {
            currentBucket.remove(toRemove);
            elementCount--;
        }
    }

    public int size() {
        return elementCount;
    }

    public void display() {
        System.out.println("\n--- Current Internal State of Hash Map ---");
        for (int i = 0; i < numberOfBuckets; i++) {
            System.out.print("Bucket " + i + ": " + bucketList[i]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        code4<String, String> studentMap = new code4<>();

        System.out.println("--- Custom Hash Map Interactive Demo ---");
        
        while (true) {
            System.out.println("\nOptions: 1. Put Item  2. Get Item  3. Remove Item  4. Display Map  5. Exit");
            System.out.print("Select an option (1-5): ");
            int option = scanner.nextInt();

            if (option == 5) break;

            switch (option) {
                case 1:
                    System.out.print("Enter Key (e.g., Name): ");
                    String k = scanner.next();
                    System.out.print("Enter Value (e.g., Phone): ");
                    String v = scanner.next();
                    studentMap.put(k, v);
                    break;
                case 2:
                    System.out.print("Enter Key to find: ");
                    String keyToFind = scanner.next();
                    System.out.println("Value found: " + studentMap.get(keyToFind));
                    break;
                case 3:
                    System.out.print("Enter Key to remove: ");
                    String keyToRem = scanner.next();
                    studentMap.remove(keyToRem);
                    System.out.println("Item removed if it existed.");
                    break;
                case 4:
                    studentMap.display();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        
        System.out.println("Exiting demo...");
        scanner.close();
    }
}
