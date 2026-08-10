package com.campus.optimizer.structures;

/**
 * Custom hash table with separate chaining (linked buckets) for collision handling.
 * No java.util.HashMap used anywhere internally.
 *
 * Load factor alpha = size / capacity. Resizes (doubles) when alpha > 0.75,
 * which is the trigger point recorded in the load-factor experiment (Section 9).
 */
public class HashTable<K, V> {

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private int collisionCount; // cumulative count of put() calls that hit a non-empty bucket
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) initialCapacity = 16;
        buckets = new Node[initialCapacity];
        size = 0;
        collisionCount = 0;
    }

    public HashTable() {
        this(16);
    }

    private int indexFor(K key, int capacity) {
        int h = (key == null) ? 0 : key.hashCode();
        h ^= (h >>> 16);           // spread bits to reduce clustering
        return Math.floorMod(h, capacity);
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("null keys not allowed");
        int idx = indexFor(key, buckets.length);

        if (buckets[idx] != null) {
            collisionCount++; // bucket already occupied by at least one node
        }

        Node<K, V> cur = buckets[idx];
        while (cur != null) {
            if (cur.key.equals(key)) {
                cur.value = value; // update in place
                return;
            }
            cur = cur.next;
        }

        buckets[idx] = new Node<>(key, value, buckets[idx]);
        size++;

        if (loadFactor() > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    public V get(K key) {
        int idx = indexFor(key, buckets.length);
        Node<K, V> cur = buckets[idx];
        while (cur != null) {
            if (cur.key.equals(key)) return cur.value;
            cur = cur.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int idx = indexFor(key, buckets.length);
        Node<K, V> cur = buckets[idx];
        while (cur != null) {
            if (cur.key.equals(key)) return true;
            cur = cur.next;
        }
        return false;
    }

    public V remove(K key) {
        int idx = indexFor(key, buckets.length);
        Node<K, V> cur = buckets[idx];
        Node<K, V> prev = null;
        while (cur != null) {
            if (cur.key.equals(key)) {
                if (prev == null) buckets[idx] = cur.next;
                else prev.next = cur.next;
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public double loadFactor() { return (double) size / buckets.length; }

    public int capacity() { return buckets.length; }

    /** Cumulative collisions observed across all put() calls (evidence for Section 9). */
    public int getCollisionCount() { return collisionCount; }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] old = buckets;
        buckets = new Node[old.length * 2];
        size = 0;
        collisionCount = 0; // recomputed fresh under the new capacity
        for (Node<K, V> head : old) {
            Node<K, V> cur = head;
            while (cur != null) {
                put(cur.key, cur.value);
                cur = cur.next;
            }
        }
    }

    /** Longest chain length - useful diagnostic for the collision-stats evidence. */
    public int longestChain() {
        int max = 0;
        for (Node<K, V> head : buckets) {
            int len = 0;
            Node<K, V> cur = head;
            while (cur != null) { len++; cur = cur.next; }
            if (len > max) max = len;
        }
        return max;
    }
}
