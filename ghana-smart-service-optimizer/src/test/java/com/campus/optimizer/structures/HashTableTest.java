package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void putAndGet_basicRoundTrip() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("L001", 1);
        table.put("L002", 2);
        assertEquals(1, table.get("L001"));
        assertEquals(2, table.get("L002"));
    }

    @Test
    void put_updatesExistingKeyInPlace() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("x", 1);
        table.put("x", 2);
        assertEquals(1, table.size());
        assertEquals(2, table.get("x"));
    }

    @Test
    void remove_deletesKeyAndDecrementsSize() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("x", 1);
        table.put("y", 2);
        Integer removed = table.remove("x");
        assertEquals(1, removed);
        assertNull(table.get("x"));
        assertEquals(1, table.size());
    }

    @Test
    void getMissingKey_returnsNull() {
        HashTable<String, Integer> table = new HashTable<>();
        assertNull(table.get("nope"));
        assertFalse(table.containsKey("nope"));
    }

    /** Forces two distinct keys into the same bucket to prove chaining works. */
    @Test
    void collisionHandling_twoKeysSameBucketBothRetrievable() {
        HashTable<Integer, String> table = new HashTable<>(4); // tiny capacity -> forced collisions
        table.put(1, "a");
        table.put(5, "b"); // 1 % 4 == 5 % 4 == 1 -> same bucket
        assertEquals("a", table.get(1));
        assertEquals("b", table.get(5));
        assertTrue(table.getCollisionCount() >= 1);
    }

    @Test
    void resize_triggersWhenLoadFactorExceedsThreshold() {
        HashTable<Integer, Integer> table = new HashTable<>(4);
        int initialCapacity = table.capacity();
        for (int i = 0; i < 10; i++) table.put(i, i);
        assertTrue(table.capacity() > initialCapacity, "capacity should have grown");
        for (int i = 0; i < 10; i++) assertEquals(i, table.get(i));
    }

    @Test
    void edgeCase_nullKeyRejected() {
        HashTable<String, Integer> table = new HashTable<>();
        assertThrows(IllegalArgumentException.class, () -> table.put(null, 1));
    }

    @Test
    void edgeCase_emptyTable() {
        HashTable<String, Integer> table = new HashTable<>();
        assertTrue(table.isEmpty());
        assertEquals(0.0, table.loadFactor());
    }
}
