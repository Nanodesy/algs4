/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time
 * proportional to n^2 in the worst case. You may assume that you can sort the n integers in time
 * proportional
 * to n^2 or better.
 */
public class ThreeSumInQuadraticTime {
    /**
     * Count number of 3-SUM pairs. Algorithm uses three pointers, first points to current number
     * (main number), second points to number next to main and moves to right side and third
     * points to the end of array and move to the left. Then both at the same place, first
     * pointer increments and it all starts again until first pointer reach end of array.
     * @param array array to be checked.
     * @return number of 3-SUM pairs.
     */
    public static int count(int[] array) {
        sort(array);

        int counter = 0;
        for (int currentNumber = 0; currentNumber < array.length - 2; currentNumber++) {
            int a = array[currentNumber];
            int start = currentNumber + 1;
            int end = array.length - 1;

            while (start < end) {
                int b = array[start];
                int c = array[end];

                if (a + b + c == 0) {
                    counter++;
                    start++;
                    end--;
                } else if (a + b + c > 0)
                    end--;
                else
                    start++;
            }
        }
        return counter;
    }

    /**
     * Sort array using bubble sort.
     * @param array array to be sorted.
     */
    private static void sort(int[] array) {
        // Arrays.sort(array); Easy way, and provide n^2 complexity
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
