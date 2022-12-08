package com.solvd.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @BeforeEach
    void init() {
        list = new LinkedList<>();
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void addBack() {
        list.addBack(1);
        assertTrue(list.contains(1));

        list.addBack(2);
        assertTrue(list.contains(2));

        list.addBack(3);
        assertTrue(list.contains(3));

        assertFalse(list.isEmpty());

        assertTrue(notNullEquals(list.getFront(), 1));
        assertTrue(list.contains(2));
        assertTrue(notNullEquals(list.getBack(), 3));
    }

    @Test
    void addFront() {
        list.addFront(3);
        assertTrue(list.contains(3));

        list.addFront(2);
        assertTrue(list.contains(2));

        list.addFront(1);
        assertTrue(list.contains(1));

        assertFalse(list.isEmpty());

        assertTrue(notNullEquals(list.getFront(), 1));
        assertTrue(list.contains(2));
        assertTrue(notNullEquals(list.getBack(), 3));
    }

    @Test
    void remove() {
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(4);
        list.add(10);

        // Element at head
        assertTrue(list.contains(1));
        list.remove(1);
        assertFalse(list.contains(1));

        // Middle element
        assertTrue(list.contains(5));
        list.remove(5);
        assertFalse(list.contains(5));

        // Element at tail
        assertTrue(list.contains(10));
        list.remove(10);
        assertFalse(list.contains(10));
    }

    @Test
    void size() {
        assertEquals(list.size(), 0);
        list.addBack(1);
        assertEquals(list.size(), 1);

        list.addBack(1);
        list.addBack(2);
        list.addBack(3);

        assertEquals(list.size(), 4);

        list.remove(1);
        list.remove(1);
        assertEquals(list.size(), 2);
    }

    private boolean notNullEquals(Integer element, int actual) {
        return element != null && element == actual;
    }
}
