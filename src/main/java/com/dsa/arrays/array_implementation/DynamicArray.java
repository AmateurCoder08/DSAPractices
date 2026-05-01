package com.dsa.arrays.array_implementation;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0; // The number of occupied slots in the array
    private int capacity; // The total number of slots in the array

    public DynamicArray(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal capacity provided. Please use zero or positive integer");
        this.capacity = capacity;
        T[] arr = (T[]) new Object[capacity];
    }

    public DynamicArray() {
        this(10);
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= len) throw new IndexOutOfBoundsException("Invalid index");
        return arr[index];
    }

    public void set(int index, T obj) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException("Invalid index");
        arr[index] = obj;
    }

    public void clear() {
        for (int i=0; i<capacity; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T object) {
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity = capacity * 2; // Doubling the initial capacity
            T[] new_array = (T[]) new Object[capacity];
            for (int i = 0; i <= len; i++) {
                new_array[i] = arr[i];
            }
            arr = new_array;
        }
        len = len + 1;
        arr[len] = object;
    }

    public T removeAt(int index) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException("Index cannot be greater than array size");

        T returnValue = null;
        if (index == len-1) {
            returnValue = arr[index];
            arr[index] = null;
            len = len - 1;
            return returnValue;
        }
        T[] new_array = (T[]) new Object[len-1];
        for (int i = 0, j = 0; i <= len; i++) {
            if (i == index) {
                returnValue = arr[index];
                continue;
            }
            new_array[j] = arr[i];
            j++;
        }
        arr = new_array;
        capacity = --len;

        return returnValue;
    }

    public boolean remove(T obj) {
        int index = indexOf(obj);
        if (index==-1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    public int indexOf(T obj) {
        for (int i=0; i<len; i++) {
            if (obj==null) {
                if (arr[i]==null) return i;
            } else {
                if (arr[i].equals(obj)) return i;
            }
        }
        return -1;
    }

    public boolean contains(T obj) {
        return indexOf(obj) != 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i=0; i<len-1; i++) sb.append(arr[i]).append(", ");
            sb.append(arr[len - 1]).append("]");
            return sb.toString();
        }
    }
}
