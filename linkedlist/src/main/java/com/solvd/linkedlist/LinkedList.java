package com.solvd.linkedlist;

public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public LinkedList() {}

    public LinkedList(T[] elements) {
        for (T element : elements)
            add(element);
    }

    public void add(T element) {
        addBack(element);
    }

    public void addFront(T element) {
        Node<T> newNode = new Node<>(element, null, head);
        if (head == null)
            tail = newNode;
        else
            head.setPrevious(newNode);

        head = newNode;
        size++;
    }

    public void addBack(T element) {
        Node<T> newNode = new Node<>(element, tail, null);
        if (head == null)
            head = newNode;
        else
            tail.setNext(newNode);

        tail = newNode;
        size++;
    }

    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.getValue() == element)
                return true;
            current = current.getNext();
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Removes the first appearance of an element.
     *
     * @param element: element to be removed
     */
    public void remove(T element) {
        Node<T> current = head;

        while (current != null) {
            if (current.getValue() == element) {
                // Update previous node or head
                if (current.getPrevious() == null)
                    head = current.getNext();
                else
                    current.getPrevious().setNext(current.getNext());

                // Update next node or tail
                if (current.getNext() == null)
                    tail = current.getPrevious();
                else
                    current.getNext().setPrevious(current.getPrevious());

                break;
            }
            current = current.getNext();
        }
        size--;
    }

    public T getFront() {
        return head != null ? head.getValue() : null;
    }

    public T getBack() {
        return tail != null ? tail.getValue() : null;
    }

    @Override
    public String toString() {
        String result = "[";

        Node<T> current = head;
        while (current != null) {
            result += current.getValue() + (current.getNext() == null ? "" : ", ");
            current = current.getNext();
        }
        result += "]";

        return result;
    }
}
