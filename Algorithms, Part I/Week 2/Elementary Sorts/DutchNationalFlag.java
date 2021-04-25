/**
 * Question 3. Dutch national flag. Given an array of n buckets, each containing a red, white, or
 * blue pebble, sort them by color. The allowed operations are:
 * <ul>
 *     <li>swap(i, j): swap the pebble in bucket i with the pebble in bucket j./li>
 *     <li>color(i): determine the color of the pebble in bucket i.</li>
 * </ul>
 * The performance requirements are as follows:
 * <ul>
 *     <li>At most n calls to color().</li>
 *     <li>At most n calls to swap().</li>
 *     <li>Constant extra space.</li>
 * </ul>
 */
public class DutchNationalFlag {
    private final Pebble[] array;

    public DutchNationalFlag(Pebble[] array) {
        this.array = array;
        sort();
    }

    public Pebble[] getArray() {
        return array;
    }

    private void sort() {
        int red = 0,  blue = 0;
        int i = 0;

        while (i < array.length - blue) {
            switch (color(i)) {
                case RED:
                    swap(i, red);
                    red++;
                    i++;
                    break;
                case WHITE:
                    i++;
                    break;
                case BLUE:
                    swap(i, array.length - blue - 1);
                    blue++;
                    break;
            }
        }
    }

    private void swap(int i, int j) {
        Pebble temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private Pebble color(int i) {
        return array[i];
    }

    public enum Pebble {
        RED,
        WHITE,
        BLUE
    }
}
