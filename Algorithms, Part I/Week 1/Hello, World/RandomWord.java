import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * This class reads a sequence of words from standard input and prints one of
 * those words uniformly at random. It do not store the words in an array or
 * list. It uses use Knuth’s method: when reading the i-th word, select it with
 * probability 1/i to be the champion, replacing the previous champion. After
 * reading all of the words, print the surviving champion.
 */
class RandomWord {
    public static void main(String[] args) {
        String result = "";
        for (int i = 1; !StdIn.isEmpty(); i++) {
            String line = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / i)) {
                result = line;
            }
        }
        StdOut.println(result);
    }
}
