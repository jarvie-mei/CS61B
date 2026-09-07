package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size, nextFirst, nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length / 2 - 1;
        nextLast = items.length / 2;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size++;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size++;
        nextLast = (nextLast + 1) % items.length;
    }

    private void resize(int capacity) {
        T[] newitems = (T[]) new Object[capacity];
        int firstIndex = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            newitems[i] = items[(firstIndex + i) % items.length];
        }
        items = newitems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int firstIndex = (nextFirst + 1) % items.length;
        T removedItem = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        if(items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    @Override
    public T removeLast() {
        if  (size == 0) {
            return null;
        }
        int lastIndex = (nextLast - 1 + items.length) % items.length;
        T removedItem = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        if(items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = ((nextFirst + 1) % items.length + index) % items.length;
        return items[actualIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        ArrayDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T item = get(pos);
            pos += 1;
            return item;
        }
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (this.get(i).equals(item)) {
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
            for (T x:this) {
                if (!((Deque<T>) o).contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
