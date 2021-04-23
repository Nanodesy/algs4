import java.util.Random;

/**
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs.
 * An egg breaks if it is dropped from floor T or higher and does not break otherwise. Your goal is
 * to devise a strategy to determine the value of  T given the following limitations on the number
 * of eggs and tosses:
 *
 * Version 0: 1 egg, ≤T tosses.
 * Version 1: ~1*lg(n) eggs and  ~1*lg(n) tosses.
 * Version 2: ~lg(T) eggs and ~2*lg(T) tosses.
 * Version 3: 2 eggs and ~2*sqrt(n) tosses.
 * Version 4: 2 eggs and ≤c*sqrt(T) tosses for some fixed constant c.
 */
public class EggDrop {
    private final boolean[] floors;
    private final int floorT;
    private int numberOfEggs;
    private int numberOfTosses;

    /**
     * Create n-story building with T floor equals <code>floorT</code>.
     * @param n number of floors.
     * @param floorT T floor.
     */
    public EggDrop(int n, int floorT) {
        // n + 1 means we have floor in range [1,n], and we dont use zero cell
        this.floors = new boolean[n + 1];
        this.floorT = floorT;
        for (int i = floorT; i <= n; i++) {
            floors[i] = true;
        }
    }

    /**
     * Create n-story building with randomized T floor.
     * @param n number of floors.
     */
    public EggDrop(int n) {
        this(n, new Random().nextInt(n) + 1);
    }

    /**
     * Return number of floors.
     * @return number of floors.
     */
    public int getNumberOfFloors() {
        return floors.length - 1;
    }

    /**
     * Return T floor.
     * @return T floor.
     */
    public int getFloorT() {
        return floorT;
    }

    /**
     * Find T floor. With version 0.
     * @return T floor.
     */
    public int iterationSearch() {
        // Initial data
        numberOfEggs = 1;
        numberOfTosses = floorT + 1;

        // Solution
        int i = 1;
        while (!throwEgg(i)) {
            i++;
        }
        return i;
    }

    /**
     * Find T floor. With version 1.
     * @return T floor.
     */
    public int binarySearch() {
        // Initial data
        numberOfTosses = (int) Math.ceil(Math.log(getNumberOfFloors()) / Math.log(2)); // numberOfTosses = log2(n) Round up, otherwise not enough attempts (try n=11, T=7)
        numberOfEggs = numberOfTosses;

        // Solution
        if (numberOfTosses == 0)
            return 1;

        int left = 1;
        int right = getNumberOfFloors();
        while (left < right) {
            int middle = (left + right) / 2;
            if (throwEgg(middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * Find T floor. With version 2.
     * @return T floor.
     */
    public int exponentialIterationWithBinarySearch() {
        // Initial data
        numberOfEggs = (int) Math.ceil(Math.log(floorT) / Math.log(2)); // numberOfEggs = log2(T) (round up)
        numberOfTosses = numberOfEggs * 2;

        // Solution
        if (numberOfTosses == 0)
            return 1;

        int floor = 2;
        boolean throwResult = false;
        do {
            throwResult = throwEgg(floor);
            floor *= 2;
        } while (!throwResult && floor <= getNumberOfFloors());

        floor /= 2;

        if (throwResult && numberOfEggs == 0) {
            return 2;
        }

        int left;
        int right;
        if (throwResult) {
            left = floor / 2 == 0 ? 1 : (floor / 2 - 1);
            right = floor;
        } else {
            left = floor;
            right = getNumberOfFloors();
        }

        while (left < right) {
            int middle = (left + right) / 2;
            if (throwEgg(middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * Find T floor. With version 3.
     * @return T floor.
     */
    public int rootIntervalSearch() {
        // Initial data
        numberOfEggs = 2;
        numberOfTosses = (int) Math.sqrt(getNumberOfFloors()) * 2; // numberOfTosses = 2√n (round down)

        // Solution
        int interval = (int) Math.ceil(Math.sqrt(getNumberOfFloors()));
        int floor = interval;
        boolean throwResult = false;
        do {
            throwResult = throwEgg(floor);
            floor += interval;
        } while (!throwResult && floor <= getNumberOfFloors());

        floor -= interval;

        if (throwResult) {
            for (int i = floor - interval + 1; i <= floor; i++) {
                if (numberOfTosses == 0) {
                    return i;
                }
                if (throwEgg(i))
                    return  i;
            }
        } else {
            for (int i = floor + 1; i <= getNumberOfFloors(); i++) {
                if (throwEgg(i))
                    return i;
            }
        }

        throw new RuntimeException("T is not found!");
    }

    /**
     * Find T floor. With version 4.
     * @return T floor.
     */
    public int exponentialIntervalsWithIteration() {
        // Initial data
        double constant = 2 * Math.sqrt(2); // c = 2√2
        numberOfEggs = 2;
        numberOfTosses = (int) (Math.sqrt(getFloorT()) * constant); // numberOfTosses = √T * c (with round down)

        // Solution
        int floor = 1;
        int iteration = 1;
        boolean throwResult = false;
        while (!throwResult && floor <= getNumberOfFloors()) {
            throwResult = throwEgg(floor);
            iteration++;
            floor += iteration;
        }

        floor -= iteration;

        if (throwResult) {
            for (int i = floor - iteration + 2; i <= floor; i++) {
                if (numberOfTosses == 0) {
                    return i;
                }
                if (throwEgg(i))
                    return  i;
            }
        } else {
            for (int i = floor + 1; i <= getNumberOfFloors(); i++) {
                if (throwEgg(i))
                    return i;
            }
        }

        throw new RuntimeException("T is not found!");
    }

    private boolean throwEgg(int floor) {
        isTossOrEggAvailableForThrow();
        if (floor < 1 || floor >= floors.length)
            throw new IllegalArgumentException("Illegal floor number!");

        numberOfTosses--;
        if (floors[floor]) {
            numberOfEggs--;
            return true;
        }
        return false;
    }

    private void isTossOrEggAvailableForThrow() {
        if (numberOfTosses == 0) {
            throw new RuntimeException("Not enough tosses! FloorT: " + floorT);
        } else if (numberOfEggs == 0) {
            throw new RuntimeException("Not enough eggs! FloorT: " + floorT);
        }
    }
}
