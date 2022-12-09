package com.solvd.linkedlist;

public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public LinkedList() {
    }

    public LinkedList(T[] elements) {
        for (T element : elements)
            add(element);
    }

    /**
     * Appends the specified element to the end of the list.
     *
     * @param element: element to be added.
     */
    public void add(T element) {
        addLast(element);
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index: position where the element will be placed.
     * @param element: element to be added.
     */
    public void add(int index, T element) {
        if (size < index || index < 0)
            throw new IndexOutOfBoundsException();

        Node<T> current = head;
        for (int j = 0; j < index; j++)
            current = current.getNext();

        Node<T> newNode = new Node<>(element, null, null);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            head = newNode;
            newNode.setNext(current);
            current.setPrevious(newNode);
        } else if (index == size) {
            newNode.setPrevious(tail.getPrevious());
            tail.setNext(newNode);
        } else {
            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());
            current.setPrevious(newNode);
        }
        size++;
    }

    /**
     * Appends the specified element to the end of the list.
     *
     * @param element: element to be added.
     */
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element, null, head);
        if (head == null)
            tail = newNode;
        else
            head.setPrevious(newNode);

        head = newNode;
        size++;
    }

    /**
     * Inserts the specified element at the beginning of the list.
     *
     * @param element: element to be added.
     */
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element, tail, null);
        if (head == null)
            head = newNode;
        else
            tail.setNext(newNode);

        tail = newNode;
        size++;
    }

    /**
     * Removes the first appearance of an element.
     *
     * @param element: element to be removed
     */
    public void remove(T element) {
        Node<T> current = head;

        while (current != null) {
            if (current.getValue().equals(element)) {
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

    /**
     * Returns true if the element exists in the list.
     *
     * @param element: element to search.
     */
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.getValue().equals(element))
                return true;
            current = current.getNext();
        }

        return false;
    }

    /**
     * Returns true if the list is empty.
     *
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the list.
     *
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index: position index.
     */
    public T get(int index) {
        if (!(0 <= index && index < size))
            throw new IndexOutOfBoundsException();

        Node<T> current = head;
        for (int j = 0; j < index; j++)
            current = current.getNext();

        return current.getValue();
    }

    /**
     * Returns the first element of the list.
     */
    public T getFirst() {
        return head != null ? head.getValue() : null;
    }

    /**
     * Returns the last element of the list.
     */
    public T getLast() {
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
