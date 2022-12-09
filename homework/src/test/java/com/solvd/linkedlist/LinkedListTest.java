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
    void add() {
        list.add(0, 1);
        assertTrue(list.contains(1));
        assertTrue(notNullEquals(list.get(0), 1));

        list.add(0, 2);
        assertTrue(list.contains(2));
        assertTrue(notNullEquals(list.get(0), 2));

        list.add(2, 5);
        assertTrue(list.contains(5));
        assertTrue(notNullEquals(list.get(2), 5));
    }

    @Test
    void addLast() {
        list.addLast(1);
        assertTrue(list.contains(1));

        list.addLast(2);
        assertTrue(list.contains(2));

        list.addLast(3);
        assertTrue(list.contains(3));

        assertFalse(list.isEmpty());

        assertTrue(notNullEquals(list.getFirst(), 1));
        assertTrue(list.contains(2));
        assertTrue(notNullEquals(list.getLast(), 3));
    }

    @Test
    void addFirst() {
        list.addFirst(3);
        assertTrue(list.contains(3));

        list.addFirst(2);
        assertTrue(list.contains(2));

        list.addFirst(1);
        assertTrue(list.contains(1));

        assertFalse(list.isEmpty());

        assertTrue(notNullEquals(list.getFirst(), 1));
        assertTrue(notNullEquals(list.get(1), 2));
        assertTrue(notNullEquals(list.getLast(), 3));
    }

    @Test
    void get() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertTrue(notNullEquals(list.get(0), 1));
        assertTrue(notNullEquals(list.get(1), 2));
        assertTrue(notNullEquals(list.get(2), 3));
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
        list.addLast(1);
        assertEquals(list.size(), 1);

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(list.size(), 4);

        list.remove(1);
        list.remove(1);
        assertEquals(list.size(), 2);
    }

    private boolean notNullEquals(Integer element, int actual) {
        return element != null && element == actual;
    }
}
