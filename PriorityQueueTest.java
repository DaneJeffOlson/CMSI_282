import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class PriorityQueueTest {

    @Test
    public void testConstructorProducesEmptyQueue() {
        assertEquals(new PriorityQueue<String>().length(), 0);
        assertEquals(new PriorityQueue<String>().toString(), "[]");
    }

    @Test
    public void testQueueOfSizeOne() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(1000);
        assertTrue(q.length() == 1);
        assertTrue(q.peek() == 1000);
        assertEquals(q.toString(), "[1000]");
        q.remove();
    }

    @Test
    public void testToString() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(7);
        assertEquals(q.toString(), "[7]");
        q.add(4);
        assertEquals(q.toString(), "[4, 7]");
        q.add(12);
        assertEquals(q.toString(), "[4, 7, 12]");
        q.add(5);
        assertEquals(q.toString(), "[4, 5, 12, 7]");
    }

    @Test
    public void testBiggerQueues() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();

        // Add 0..99, scrambled
        List<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) a.add(i);
        Collections.shuffle(a);
        for (int x: a) q.add(x);
        assertEquals(q.length(), 100);
        assertEquals((int)q.peek(), 0);

        // Insert 100
        q.add(100);
        assertFalse(q.peek() == 100);
        assertEquals(q.length(), 101);

        // Insert and remove a highest priority element
        q.add(-1);
        assertEquals((int)q.peek(), -1);
        assertEquals((int)q.remove(), -1);

        // Remove 0..99
        for (int i = 0; i < 100; i++) {
            assertEquals((int)q.remove(), i);
        }

        // Remove 100
        assertTrue(q.remove() == 100);
        assertTrue(q.length() == 0);
    }

    @Test(expected=NoSuchElementException.class)
    public void testPeekIntoEmptyQueueThrowsException() {
        new PriorityQueue<String>().peek();
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveFromEmptyQueueThrowsException() {
        new PriorityQueue<String>().remove();
    }
}
