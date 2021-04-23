import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and
 * a queue that supports adding and removing items from either the front or the back of the data
 * structure.
 * @param <Item> the type of elements in data structure
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    /**
     * Construct an empty deque.
     */
    public Deque() {
    }

    /**
     * Returns <tt>true</tt> if this deque contains no elements.
     * @return <tt>true</tt> if this deque contains no elements.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in this deque.
     * @return the number of elements in this deque.
     */
    public int size() {
        return size;
    }

    /**
     * Inserts the specified element at the beginning of this deque.
     * @param item the element to add.
     * @throws IllegalArgumentException if argument is null.
     */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Null value");

        Node node = new Node();
        node.item = item;
        size++;

        if (isEmpty()) {
            first = node;
            last = node;
            return;
        }

        node.next = first;
        first.prev = node;
        first = node;
    }

    /**
     * Appends the specified element to the end of this deque.
     * @param item the element to add.
     * @throws IllegalArgumentException if argument is null.
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null value");

        Node node = new Node();
        node.item = item;
        size++;

        if (isEmpty()) {
            first = node;
            last = node;
            return;
        }

        node.prev = last;
        last.next = node;
        last = node;
    }

    /**
     * Returns the first element in this deque.
     * @return the first element in this deque.
     * @throws NoSuchElementException if this deque is empty.
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty!");

        Node temp = first;
        size--;

        if (temp.next == null) {
            first = null;
            last = null;
            return temp.item;
        }

        first = temp.next;
        first.prev = null;
        temp.next = null;

        return temp.item;
    }

    /**
     * Returns the last element in this deque.
     * @return the last element in this deque.
     * @throws NoSuchElementException if this deque is empty.
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty!");

        Node temp = last;
        size--;

        if (temp.prev == null) {
            first = null;
            last = null;
            return temp.item;
        }

        last = temp.prev;
        last.next = null;
        temp.prev = null;

        return temp.item;
    }

    /**
     * Returns an iterator over items in order from front to back.
     * @return an iterator over items in order from front to back.
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node head = first;

            public boolean hasNext() {
                return head != null;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("Iteration has no more elements!");
                Node temp = head;
                head = temp.next;
                return temp.item;
            }
        };
    }

    /**
     * Auxiliary class that represents Node with two links.
     */
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("First");
        deque.addFirst("Second");
        deque.addFirst("Third");
        deque.addFirst("Fourth");
        for (String s : deque) {
            StdOut.println(s);
        }
        StdOut.println(deque.removeLast());
    }
}
