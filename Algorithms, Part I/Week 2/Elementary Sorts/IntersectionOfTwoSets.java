/**
 * Question 1.
 * Intersection of two sets. Given two arrays a[] and b[], each containing n distinct
 * 2D points in the plane, design a subquadratic algorithm to count the number of
 * points that are contained both in array a[] and array b[].
 */
public class IntersectionOfTwoSets {
    public static int count(Point[] a, Point[] b) {
        int counter = 0;

        sort(a);
        sort(b);

        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            switch (a[i].compareTo(b[j])) {
                case 1:
                    j++;
                    break;
                case 0:
                    counter++;
                    i++;
                    j++;
                    break;
                case -1:
                    i++;
                    break;
            }
        }

        return counter;
    }

    private static void sort(Point[] array) {
        int n = array.length;
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h)
                    exch(array, j, j - h);
            }
            System.out.println(h);
            h = h / 3;
        }
    }

    private static boolean less(Point a, Point b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Point[] array, int i, int j) {
        Point swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    public static class Point implements Comparable<Point> {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p) {
            if (p != null) {
                if (x == p.x) {
                    return Integer.compare(y, p.y);
                } else {
                    return Integer.compare(x, p.x);
                }
            }
            return -1;
        }
    }
}
