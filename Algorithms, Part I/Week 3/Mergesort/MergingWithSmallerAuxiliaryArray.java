import java.util.Comparator;

public class MergingWithSmallerAuxiliaryArray {
    private static Object[] auxiliary;
    private static Comparator comparator;

    public static void  sort(Object[] array, Comparator comparator) {
        auxiliary = new Object[array.length / 2 + array.length % 2];
        MergingWithSmallerAuxiliaryArray.comparator = comparator;
        sort(array, 0, array.length - 1);
    }

    private static void sort(Object[] array, int low, int high) {
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sort(array, low, mid);
        sort(array, mid + 1, high);
        merge(array, low, mid, high);
    }

    private static void merge(Object[] array, int low, int middle, int high) {
        if (high + 1 - low > auxiliary.length)
            finalMerge(array, low, middle, high);
        else
            normalMerge(array, low, middle, high);
    }

    /**
     * The main change from normal merge sort is that push all data to the left side of an
     * auxiliary array.
     */
    private static void normalMerge(Object[] array, int low, int middle, int high) {
        System.arraycopy(array, low, auxiliary, 0, high + 1 - low);
        int i = low, j = middle + 1;
        for (int k = low; k <= high; k++) {
            if (i > middle) array[k] = auxiliary[j++ - low];
            else if (j > high) array[k] = auxiliary[i++ - low];
            else if (less(auxiliary[j - low], auxiliary[i - low])) array[k] = auxiliary[j++ - low];
            else array[k] = auxiliary[i++ - low];
        }
    }

    /**
     * Use only at final merge, when the logic is changing and we need use merge not half of
     * auxiliary array, but auxiliary array and right half of an array to array.
     */
    private static void finalMerge(Object[] array, int low, int middle, int high) {
        System.arraycopy(array, 0, auxiliary, 0, auxiliary.length);
        int i = low, j = auxiliary.length;
        for (int k = low; k <= high; k++) {
            if (i > middle) array[k] = array[j++];
            else  if (j > high) array[k] = auxiliary[i++];
            else if (less(array[j], auxiliary[i])) array[k] = array[j++];
            else array[k] = auxiliary[i++];
        }
    }

    private static boolean less(Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }
}
