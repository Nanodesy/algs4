import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Takes an integer k as a command-line argument; reads a sequence of strings from standard input
 * using StdIn.readString(); and prints exactly k of them, uniformly at random. Print each item
 * from the sequence at most once.
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int n = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < n; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
