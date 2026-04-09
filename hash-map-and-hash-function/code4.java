import java.util.LinkedList;

/**
 * Custom Hash Map Implementation using Separate Chaining.
 * 
 * Logic:
 * - Use an array of buckets (Linked Lists).
 * - Use key.hashCode() and modulo operator to find the bucket index.
 * - Handle collisions by appending to the linked list at the bucket index.
 * 
 * Operations:
 * - put(K key, V value): Adds or updates a pair.
 * - get(K key): Retrieves value for a key.
 * - remove(K key): Deletes a key-value pair.
 * - size(): Returns number of elements.
 */
class MyMapNode<K, V> {
    K key;
    V value;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + key + " = " + value + "}";
    }
}

public class code4<K, V> {
    private int numBuckets;
    private LinkedList<MyMapNode<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public code4() {
        this.numBuckets = 10; // Initial capacity
        this.buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.size = 0;
    }

    /**
     * Internal hash function.
     */
    private int getBucketIndex(K key) {
        int hashCode = (key == null) ? 0 : Math.abs(key.hashCode());
        return hashCode % numBuckets;
    }

    /**
     * Insertion / Update.
     */
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> list = buckets[index];

        for (MyMapNode<K, V> node : list) {
            if (node.key == null && key == null || node.key != null && node.key.equals(key)) {
                node.value = value; // Update existing
                return;
            }
        }

        list.add(new MyMapNode<>(key, value));
        size++;
    }

    /**
     * Retrieval.
     */
    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> list = buckets[index];

        for (MyMapNode<K, V> node : list) {
            if (node.key == null && key == null || node.key != null && node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Deletion.
     */
    public V remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> list = buckets[index];

        MyMapNode<K, V> target = null;
        for (MyMapNode<K, V> node : list) {
            if (node.key == null && key == null || node.key != null && node.key.equals(key)) {
                target = node;
                break;
            }
        }

        if (target != null) {
            list.remove(target);
            size--;
            return target.value;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (LinkedList<MyMapNode<K, V>> list : buckets) {
            for (MyMapNode<K, V> node : list) {
                sb.append(node).append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        code4<String, Integer> map = new code4<>();

        System.out.println("Adding keys: Apple, Banana, Cherry");
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);

        System.out.println("Map: " + map);
        System.out.println("Size: " + map.size());

        System.out.println("\nRetrieving 'Banana': " + map.get("Banana"));
        System.out.println("Updating 'Apple' to 15...");
        map.put("Apple", 15);
        System.out.println("New Apple value: " + map.get("Apple"));

        System.out.println("\nRemoving 'Banana'...");
        map.remove("Banana");
        System.out.println("Map after removal: " + map);
        System.out.println("Size: " + map.size());
        
        System.out.println("\nIs 'Banana' still there? " + (map.get("Banana") != null));
    }
}
