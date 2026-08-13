package com.campus.optimizer.structures;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class CircularQueueTest {

    @Test
    public void testBasicEnqueueDequeue() {
        CircularQueue<Integer> queue = new CircularQueue<>(3);
        
        assertTrue(queue.isEmpty());
        
        queue.enqueue(10);
        queue.enqueue(20);
        
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        assertEquals(10, queue.peek());
        assertEquals(10, queue.dequeue()); 
        assertEquals(20, queue.dequeue()); 
        
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testWrapAroundLogic() {
        CircularQueue<String> queue = new CircularQueue<>(3);
        
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertTrue(queue.isFull());
        
        assertEquals("A", queue.dequeue());
        assertFalse(queue.isFull());
        
        queue.enqueue("D");
        assertTrue(queue.isFull());
        
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals("D", queue.dequeue());
    }

    @Test
    public void testExceptions() {
        CircularQueue<Double> queue = new CircularQueue<>(2);
        
        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
        
        queue.enqueue(1.1);
        queue.enqueue(2.2);
        
        assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(3.3);
        });
    }
}
