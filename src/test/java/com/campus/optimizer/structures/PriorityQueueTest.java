package com.campus.optimizer.structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for custom PriorityQueue (Min-Heap).
 * Squad 3: Tina & Anu
 */
public class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @BeforeEach
    public void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    public void testInsertAndExtractMin() {
        pq.insert(15);
        pq.insert(10);
        pq.insert(20);
        pq.insert(5);

        assertEquals(5, pq.extractMin());
        assertEquals(10, pq.extractMin());
        assertEquals(15, pq.extractMin());
        assertEquals(20, pq.extractMin());
    }

    @Test
    public void testPeek() {
        pq.insert(42);
        pq.insert(12);

        assertEquals(12, pq.peek());
        assertEquals(2, pq.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(pq.isEmpty());
        pq.insert(1);
        assertFalse(pq.isEmpty());
        pq.extractMin();
        assertTrue(pq.isEmpty());
    }

    @Test
    public void testExtractMinFromEmptyQueueThrowsException() {
        assertThrows(NoSuchElementException.class, () -> pq.extractMin());
    }
}

