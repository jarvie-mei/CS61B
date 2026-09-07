package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementation of a circular LinkedListDeque,
 * in a circular list, the node's prev is point to the previous node,
 * the node's next is point to the next node, in a circular way.
 * @param <T>
 */
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        /**
         * Constructor of class Node
         * @param d
         * @param p
         * @param n
         */
        public Node(T d, Node p, Node n) {
            data = d;
            prev = p;
            next = n;
        }

        /**
         * Constructor for sentinel Node
         */
        public Node() {
            prev = this;
            next = this;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node();
        size = 0;
    }

    @Override
    public void addFirst(T data) {
        size++;
        /**
         * while adding a Node to the front of the Deque,
         * new Node's prev always points to sentinel,
         * new Node's next points to the first item
         * which is sentinel.next; right before the addFirst() is done.
         */
        sentinel.next = new Node(data, sentinel, sentinel.next);
        /**
         * new Node's next Node's prev Node is point to the new added Node;
         */
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T data) {
        size++;
        /** while addding a Node to the back of the Deque,
         * new Node's prev points to the last item
         * which is sentinel.prev; right before the addLast() is done.
         * new Node's next always points to the sentinel Node to form a circular list.
         */
        sentinel.prev.next = new Node(data, sentinel.prev,sentinel);
        /**
         * sentinel's prev points to the new added node now
         */
        sentinel.prev = sentinel.prev.next;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * print the deque from first item to the last,
     * separated by white space,
     * once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size--;
            T removed = sentinel.next.data;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;

            return removed;
        }
    }

    @Override
    public T removeLast() {
        if  (size == 0) {
            return null;
        } else {
            size--;
            T removed = sentinel.prev.data;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;

            return removed;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }


    /**
     * Gets the item at the given index in recursive way
     * @param index
     * @return
     */

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        return getTargetItem(sentinel.next, index);
    }

    /**
     * helper function of getRecursive()
     * @param curr
     * @param index
     * @return
     */
    private T getTargetItem(Node curr, int index) {
        if (index == 0) {
           return curr.data;
        }
        return getTargetItem(curr.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int pos;
        DequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextData = get(pos);
            pos++;
            return nextData;
        }
    }

    @Override
    public boolean contains(T data) {
        for (int i = 0; i < size; i++) {
            if (this.get(i).equals(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> d = (Deque<T>) o;
            if (d.size() != size) {
                return false;
            }

            for (T x: this) {
                if (!d.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
