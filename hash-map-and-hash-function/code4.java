import java.util.*;

public class code4<K, V> {
    static class Node<K, V> {
        K key;
        V val;
        Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    private int nBuckets = 5;
    private LinkedList<Node<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public code4() {
        buckets = new LinkedList[nBuckets];
        for (int i = 0; i < nBuckets; i++) buckets[i] = new LinkedList<>();
    }

    private int getIndex(K k) {
        return k == null ? 0 : Math.abs(k.hashCode()) % nBuckets;
    }

    public void put(K k, V v) {
        int i = getIndex(k);
        for (Node<K, V> node : buckets[i]) {
            if (node.key.equals(k)) {
                node.val = v;
                return;
            }
        }
        buckets[i].add(new Node<>(k, v));
    }

    public V get(K k) {
        int i = getIndex(k);
        for (Node<K, V> node : buckets[i]) {
            if (node.key.equals(k)) return node.val;
        }
        return null;
    }

    public static void main(String[] args) {
        code4<String, Integer> map = new code4<>();
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
    }
}
