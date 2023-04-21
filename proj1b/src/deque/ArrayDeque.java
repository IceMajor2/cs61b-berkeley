package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements deque.Deque<T> {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(5);
        deque.addFirst(-99);
        deque.addLast(4);
        deque.addFirst(-9);
        deque.addLast(-5239);

        //deque.removeFirst();
        System.out.println(deque.toList());
    }

    private static double R_FACTOR = 1.5;
    private static double R_RATIO = 0.75;

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

    @Override
    public void addFirst(T x) {
        size++;
        firstIndex = (firstIndex - 1 == -1) ? array.length - 1 : firstIndex - 1;
        lastIndex = (size == 1) ? firstIndex : lastIndex;
        array[firstIndex] = x;
    }

    @Override
    public void addLast(T x) {
        size++;
        lastIndex = (lastIndex + 1 == array.length) ? 0 : lastIndex + 1;
        firstIndex = (size == 1) ? lastIndex : firstIndex;
        array[lastIndex] = x;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for(int i = firstIndex, iterations = 0; iterations < size; i++, iterations++) {
            list.add(array[i]);
            if(i == array.length - 1) {
                i = -1;
            }
        }
        return list;
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
        T removed = array[firstIndex];
        array[firstIndex] = null;
        size--;
        firstIndex = (firstIndex + 1 == array.length) ? 0 : firstIndex + 1;
        return removed;
    }

    @Override
    public T removeLast() {
        T removed = array[lastIndex];
        array[firstIndex] = null;
        size--;
        lastIndex = (lastIndex - 1 == -1) ? array.length - 1 : lastIndex - 1;
        return removed;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
