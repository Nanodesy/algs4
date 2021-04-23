import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized <code>RandomizedQueue</code>  is similar to a stack or
 * <code>RandomizedQueue</code>, except that the item removed is chosen uniformly at random among
 * items in data structure.
 * @param <Item> the type of elements in data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int head;

    /**
     * Construct an empty randomized <code>RandomizedQueue</code>.
     */
    public RandomizedQueue() {
        data = (Item[]) new Object[1];
    }

    /**
     * Returns <tt>true</tt> if this <code>RandomizedQueue</code> contains no elements.
     * @return <tt>true</tt> if this <code>RandomizedQueue</code> contains no elements.
     */
    public boolean isEmpty() {
        return head == 0;
    }

    /**
     * Returns the number of elements in this <code>RandomizedQueue</code>.
     * @return the number of elements in this <code>RandomizedQueue</code>.
     */
    public int size() {
        return head;
    }

    /**
     * Add item at first position.
     * @param item the element to add.
     * @throws IllegalArgumentException if argument is null.
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Argument is null!");
        if (head == data.length) resize(data.length * 2);
        data[head] = item;
        head++;
    }

    /**
     * Remove and return a random item.
     * @return random item from <code>RandomizedQueue</code>.
     * @throws NoSuchElementException if <code>RandomizedQueue</code> is empty.
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty!");
        if (head > 0 && head == data.length / 4) resize(data.length / 2);

        int random = StdRandom.uniform(head);
        Item item = data[random];
        head--;

        if (random == head) {
            data[random] = null;
            return item;
        }

        data[random] = data[head];
        data[head] = null;
        return item;
    }

    /**
     * Return a random item, but without removing it.
     * @return random item from <code>RandomizedQueue</code>.
     * @throws NoSuchElementException if <code>RandomizedQueue</code> is empty.
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty!");
        return data[StdRandom.uniform(head)];
    }

    /**
     * Returns an independent iterator over items in random order (With modern version of
     * Fisher–Yates shuffle).
     * @return an independent iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Item[] shuffledData;
            private int n = head;

            {
                shuffledData = (Item[]) new Object[data.length];
                for (int i = 0; i < head; i++) {
                    shuffledData[i] = data[i];
                }
            }

            public boolean hasNext() {
                return n > 0;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException("Iteration has no more elements!");

                if (n == 1) {
                    n = 0;
                    return shuffledData[0];
                }

                int random = StdRandom.uniform(n);
                Item result = shuffledData[random];
                n--;

                if (random == n)
                    return result;

                shuffledData[random] = shuffledData[n];
                return result;
            }
        };
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];

        for (int i = 0; i < head; i++) {
            newArray[i] = data[i];
        }

        data = newArray;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        queue.enqueue("Forth");
        queue.enqueue("Fifth");

        StdOut.println("Sample");
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println(queue.sample());
        StdOut.println();

        StdOut.println("For each");
        for (String s : queue) {
            StdOut.println(s);
        }

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }
}
