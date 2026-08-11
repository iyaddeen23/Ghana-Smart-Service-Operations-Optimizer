package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void addFirstAndAddLastShouldWork() {
        Deque<String> deque = new Deque<>();

        deque.addFirst("Kumasi");
        deque.addFirst("Accra");
        deque.addLast("Tamale");

        assertEquals(3, deque.size());
        assertEquals("Accra", deque.peekFirst());
        assertEquals("Tamale", deque.peekLast());
    }

    @Test
    void removeFirstShouldRemoveFrontElement() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        assertEquals(10, deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals(20, deque.peekFirst());
    }

    @Test
    void removeLastShouldRemoveRearElement() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        assertEquals(30, deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(20, deque.peekLast());
    }

    @Test
    void dequeShouldHandleSingleElement() {
        Deque<String> deque = new Deque<>();

        deque.addFirst("Test");

        assertEquals("Test", deque.peekFirst());
        assertEquals("Test", deque.peekLast());
        assertEquals("Test", deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    void clearShouldRemoveAllElements() {
        Deque<String> deque = new Deque<>();

        deque.addFirst("Accra");
        deque.addLast("Kumasi");

        deque.clear();

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void emptyDequeShouldThrowExceptions() {
        Deque<String> deque = new Deque<>();

        assertThrows(NoSuchElementException.class, deque::removeFirst);
        assertThrows(NoSuchElementException.class, deque::removeLast);
        assertThrows(NoSuchElementException.class, deque::peekFirst);
        assertThrows(NoSuchElementException.class, deque::peekLast);
    }
}