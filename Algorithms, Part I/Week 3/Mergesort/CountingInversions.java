import java.util.Arrays;

/**
 * Question 2.
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i < j but a[i] >
 * a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.
 */
public class CountingInversions {
    /**
     * Finds inversions in an array using merge sort.
     * @param array the array in which to find inversions.
     * @return number of inversions.
     */
    public static int countInversions(int[] array) {
        int[] result = Arrays.copyOfRange(array, 0, array.length);
        return mergeSortAndCount(result, 0, array.length - 1);
    }

    private static int mergeAndCount(int[] array, int left, int middle, int right) {
        int[] leftSubArray = Arrays.copyOfRange(array, left, middle + 1);
        int[] rightSubArray = Arrays.copyOfRange(array, middle + 1, right + 1);

        int i = 0, j = 0, k = left, swaps = 0;
        while (i < leftSubArray.length && j < rightSubArray.length) {
            if (leftSubArray[i] <= rightSubArray[j])
                array[k++] = leftSubArray[i++];
            else {
                array[k++] = rightSubArray[j++];
                swaps += (middle + 1) - (left + i);
            }
        }
        while (i < leftSubArray.length)
            array[k++] = leftSubArray[i++];
        while (j < rightSubArray.length)
            array[k++] = rightSubArray[j++];
        return swaps;
    }

    private static int mergeSortAndCount(int[] array, int left, int right) {
        int count = 0;

        if (left < right) {
            int m = (left + right) / 2;
            count += mergeSortAndCount(array, left, m);
            count += mergeSortAndCount(array, m + 1, right);

            count += mergeAndCount(array, left, m, right);
        }

        return count;
    }
}
