package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private static double R_FACTOR = 1.5;
    private static double R_RATIO_UPPER = 0.75;
    private static double R_RATIO_LOWER = 0.75;

    private T[] array;
    private int size;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque() {
        this.array = (T[]) new Object[8];
        this.size = 0;
        this.firstIndex = 0;
        this.lastIndex = 0;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator<T>();
    }

    @Override
    public void addFirst(T x) {
        if (getCurrentAbundance() >= R_RATIO_UPPER) {
            upsize();
        }
        size++;
        firstIndex = (firstIndex - 1 == -1) ? array.length - 1 : firstIndex - 1;
        lastIndex = (size == 1) ? firstIndex : lastIndex;
        array[firstIndex] = x;
    }

    public boolean contains(T obj) {
        for (T item : this) {
            if (item.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ArrayDeque oad) {
            if (this.size != oad.size) {
                return false;
            }
            for (T item : this) {
                if (!oad.contains(item)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void addLast(T x) {
        if (getCurrentAbundance() >= R_RATIO_UPPER) {
            upsize();
        }
        size++;
        lastIndex = (lastIndex + 1 == array.length) ? 0 : lastIndex + 1;
        firstIndex = (size == 1) ? lastIndex : firstIndex;
        array[lastIndex] = x;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = firstIndex, iterations = 0; iterations < size; i++, iterations++) {
            list.add(array[i]);
            if (i == array.length - 1) {
                i = -1;
            }
        }
        return list;
    }

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return String.format("[%s]", String.join(", ", listOfItems));
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size() == 0) {
            throw new NullPointerException();
        }
        if (getCurrentAbundance() <= 0.25) {
            downsize();
        }
        T removed = array[firstIndex];
        array[firstIndex] = null;
        size--;
        firstIndex = (firstIndex + 1 == array.length) ? 0 : firstIndex + 1;
        return removed;
    }

    @Override
    public T removeLast() {
        if (size() == 0) {
            throw new NullPointerException();
        }
        if (getCurrentAbundance() <= 0.25) {
            downsize();
        }
        T removed = array[lastIndex];
        array[lastIndex] = null;
        size--;
        lastIndex = (lastIndex - 1 == -1) ? array.length - 1 : lastIndex - 1;
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        if (index + 1 > size) {
            throw new IndexOutOfBoundsException();
        }
        int getIndex = (firstIndex + index >= array.length) ? ((firstIndex + index) % array.length) : firstIndex + index;
        return array[getIndex];
    }

    @Override
    public T getRecursive(int index) {
        return this.get(index);
    }

    private void upsize() {
        T[] biggerArr = (T[]) new Object[(int) Math.round(array.length * R_FACTOR)];
        for (int i = 0; i < size; i++) {
            biggerArr[i] = this.get(i);
        }
        this.array = biggerArr;
        firstIndex = 0;
        lastIndex = size - 1;
    }

    private void downsize() {
        T[] smallerArr = (T[]) new Object[(int) Math.round(array.length / R_FACTOR)];
        for (int i = 0; i < size; i++) {
            smallerArr[i] = this.get(i);
        }
        this.array = smallerArr;
        firstIndex = 0;
        lastIndex = size - 1;
    }

    private double getCurrentAbundance() {
        return (double) size / array.length;
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {

        private int pos;

        public ArrayDequeIterator() {
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            return this.pos < size ? true : false;
        }

        @Override
        public T next() {
            T toReturn = (T) get(this.pos);
            pos++;
            return toReturn;
        }
    }
}
