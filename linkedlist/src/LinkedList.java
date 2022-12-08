public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

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
    }

    public void addBack(T element) {
        Node<T> newNode = new Node<>(element, tail, null);
        if (head == null)
            head = newNode;
        else
            tail.setNext(newNode);

        tail = newNode;
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

    /**
     * Removes the first appearance of an element.
     *
     * @param element: element to be removed
     */
    public void remove(T element) {
        Node<T> previous = head;
        Node<T> current = head;

        while (current != null) {
            if (current.getValue() == element) {
                previous.setNext(current.getNext());
                current.getNext().setPrevious(previous);
                return;
            }
            previous = current;
            current = current.getNext();
        }
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
