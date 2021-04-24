import java.util.Arrays;

/**
 * Question 2. Permutation. Given two integer arrays of size <code>n</code>, design a subquadratic
 * algorithm to determine whether one is a permutation of the other. That is, do they contain
 * exactly the same entries but, possibly, in a different order.
 */
public class Permutation {
    public static boolean isPermutation(int[] a, int[] b) {
        int[] sortedA = Arrays.stream(a).toArray();
        int[] sortedB = Arrays.stream(b).toArray();

        sort(sortedA);
        sort(sortedB);

        for (int i = 0; i < a.length; i++) {
            if (sortedA[i] != sortedB[i])
                return false;
        }
        return true;
    }

    private static void sort(int[] array) {
        int n = array.length;
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h)
                    exch(array, j, j - h);
            }

            h = h / 3;
        }
    }

    private static boolean less(int a, int b) {
        return a < b;
    }

    private static void exch(int[] array, int i, int j) {
        int swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }
}