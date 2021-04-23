/**
 * Question 2.
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence
 * of integers followed immediately by a decreasing sequence of integers. Write a program that,
 * given a bitonic array of n distinct integer values, determines whether a given integer is
 * in the array.
 *
 * Standard version: Use ∼3lg(n) compares in the worst case.
 * Signing bonus: Use ∼2lg(n) compares in the worst case (and prove that no algorithm can
 * guarantee to perform fewer than ∼2lg(n) compares in the worst case).
 */
public class SearchInABitonicArray {
    /**
     * Search integer in bitonic array and return true if this array contain this integer.
     * @param bitonicArray bitonic array.
     * @param number integer to be find.
     * @return true if bitonic array contains integer, otherwise false.
     */
    public static boolean searchInBitonicArray(int[] bitonicArray, int number) {
        int switchingPoint = findSwitchingPoint(bitonicArray, 0, bitonicArray.length - 1);
        if (number > switchingPoint) return false;
        if (number == bitonicArray[switchingPoint]) return true;
        return binarySearch(bitonicArray, number, 0, switchingPoint)
                || binarySearch(bitonicArray, number, bitonicArray.length - 1, switchingPoint + 1);
    }

    private static int findSwitchingPoint(int[] bitonicArray, int low, int high) {
        if (low == high) return low;
        if (high == low + 1) return Math.max(low, high);

        int mid = (low + high) / 2;

        if (bitonicArray[mid] < bitonicArray[mid + 1])
            return findSwitchingPoint(bitonicArray, mid + 1, high);
        else if (bitonicArray[mid] > bitonicArray[mid + 1])
            return findSwitchingPoint(bitonicArray, low, mid - 1);
        else
            return mid;
    }

    private static boolean binarySearch(int[] bitonicArray, int key, int low, int high) {
        boolean reversed = false;

        if (low > high) {
            reversed = true;
            int temp = high;
            high = low;
            low = temp;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (bitonicArray[mid] < key) {
                if (reversed) high = mid - 1; else low = mid + 1;
            } else if (bitonicArray[mid] > key) {
                if (reversed) low = mid + 1; else high = mid - 1;
            } else if (bitonicArray[mid] == key) {
                return true;
            }
        }
        return false;
    }
}
