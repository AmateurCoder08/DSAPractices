package com.dsa.linked_lists.implementation;

import java.util.Iterator;
import java.util.Objects;

public class DoublyLinkedList<T> implements Iterable<T>{
    private static class Node<T> {
        private T data;
        private Node<T> prev = null, next = null;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private int size = 0;
    private Node<T> head, tail;

    public void clear() {
        Node<T> trav = head;
        while(Objects.nonNull(trav)) {
            trav.prev = null;
            trav.data = null;
            Node<T> temp = trav.next;
            trav.next = null;
            trav = temp;
        }
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void add(T data) {
        addLast(data);
    }

    public void addLast(T data) {
        if (isEmpty()) {
            head = tail = new Node<>(data, null, null);
        } else {
            Node<T> newLastElement = new Node<>(data, tail, null);
            tail.next = newLastElement;
            tail = newLastElement;
        }
        size++;
    }

    public void addFirst(T data) {
        if (isEmpty()) {
            head = tail = new Node<>(data, null, null);
        } else {
            Node<T> newFirst = new Node<>(data, null, head);
            head.prev = newFirst;
            head = newFirst;
        }
        size++;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        T data = head.data;
        head = head.next;
        --size;
        if (isEmpty()) tail = null;
        else head.prev = null;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        T data = tail.data;
        tail = tail.prev;
        --size;
        if (isEmpty()) head = null;
        else tail.next = null;
        return data;
    }

    private T remove(Node<T> node) {
        if (Objects.isNull(node.next)) return removeLast();
        if (Objects.isNull(node.prev)) return removeFirst();

        node.prev.next = node.next;
        node.next.prev = node.prev;

        T data = node.data;
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    public T removeAt(int index) {
        Node<T> trav;
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");

        if (index < (size/2)) {
            int i = 0;
            for (trav=head; i != index; i++) {
                trav = trav.next;
            }
        } else {
            int i = size-1;
            for (trav=tail; i != index; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    public boolean remove(T data) {
        Node<T> trav = head;
        if (Objects.isNull(data)) {
            for (; Objects.nonNull(trav); trav = trav.next) {
                if (Objects.isNull(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (; Objects.nonNull(trav); trav = trav.next) {
                if (trav.data.equals(data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(T data) {
        int index = 0;
        Node<T> trav = head;
        if (Objects.isNull(data)) {
            for (; index < size; index++) {
                if (Objects.isNull(trav.data)) {
                    return index;
                }
                trav = trav.next;
            }
        } else {
            for (; index < size; index++) {
                if (data.equals(trav.data)) {
                    return index;
                }
                trav = trav.next;
            }
        }
        return -1;
    }

    public boolean contains(T data) {
        return indexOf(data)!=-1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return Objects.nonNull(trav);
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        int index = 0;
        Node<T> trav = head;
        for (; index < size-1; trav = trav.next, index++) {
            sb.append(trav.data).append(", ");
        }
        sb.append(trav.data);
        sb.append("]");
        return sb.toString();
    }
}
