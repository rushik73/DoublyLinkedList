import java.util.Iterator;

public class DoublyLinkedList<T> implements List<T>, Iterable<T> {
    private Node head, tail;
    private int numberOfElements;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        numberOfElements = 0;
    }

    @Override
    public void addLast(T item) {
        // TODO
        Node n = new Node(item);

        if (tail != null) {
            n.previous = tail;
            tail.next = n;
            tail = n;
        }

        if (tail == null) {
            tail = n;
            head = n;
        }

        this.numberOfElements++;

    }

    @Override
    public void addFirst(T item) {
        // TODO
        Node n = new Node(item);
        if (head != null) {
            n.next = head;
            head.previous = n;
            head = n;
        }

        if (head == null) {
            head = n;
            tail = n;
        }
        this.numberOfElements++;
    }

    @Override
    public T get(int position) {
        // TODO
        if (position < 0 || position >= numberOfElements) {
            return null;
        }
        Node n = head;

        for (int i = 0; i < position && n != null; i++, n = n.next) ;

        return n.data;
    }

    @Override
    public void print() {
        // TODO
        Node n = head;
        while (n != null) {
            System.out.println(n.data);
            n = n.next;
        }
    }

    @Override
    public void printBackwards() {
        // TODO
        Node n = tail;
        while (n != null) {
            System.out.println(n.data);
            n = n.previous;
        }
    }

    @Override
    public boolean remove(T item) {
        // TODO
        Node n;


        for (n = head; n != null && n.data != item; n = n.next) ;
        if (n == null)
            return false;

        if(n==head&&n==tail){
            head = null;
            tail = null;

        }
        else if(n == head){
            Node next = n.next;
            head = next;
            next.previous = null;
        }

        else if(n == tail){
            Node previous = n.previous;
            tail = previous;
            previous.next = null;
        }
        else {
            Node previous = n.previous;
            Node next = n.next;
            previous.next = next;
            next.previous = previous;
        }
        numberOfElements--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        // TODO

        return this.numberOfElements == 0;

    }

    @Override
    public int getLength() {
        // TODO

        return numberOfElements;

    }

    @Override
    public Iterator<T> iterator() {
        DLIterator doubly = new DLIterator();
        doubly.n = this.head;
        return doubly;
    }

    /**
     * Inner class representing a node in the linked list
     */

    private class Node {
        private T data;
        private Node next, previous;

        private Node(T data) {
            this(data, null, null);
        }

        private Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.previous = prev;
        }
    }

    class DLIterator implements Iterator<T> {
        private Node n;
        @Override
        public boolean hasNext() {
            return n !=null;
            
        }

        @Override
        public T next() {
            T data = n.data;
            n = n.next;
            return data;
        }
    }
}


