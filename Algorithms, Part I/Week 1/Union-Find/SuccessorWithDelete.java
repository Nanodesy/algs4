/**
 * Successor with delete. Given a set of n integers S = {0, 1, ..., n − 1} and a
 * sequence of requests of the following form:
 * <ul>
 *     <li>Remove x from S
 *     <li>Find the successor of x: the smallest y in S such that y ≥ x.
 * </ul>
 * design a data type so that all operations (except construction) take
 * logarithmic time or better in the worst case.
 */
public class SuccessorWithDelete {
    private final int[] parent;
    private final int[] size;

    /**
     * Initialize set of n integers S = {0, 1, ...,n − 1}.
     * @param n - size of set.
     */
    public SuccessorWithDelete(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Remove number x from set if cell value is equal cell number.
     * @param x is the number to be removed.
     */
    public void remove(int x) {
        if (x == parent[x]) {
            union(x + 1, x);
        }
    }

    /**
     * Find a successor of number x.
     * @param x is the number to find successor.
     * @return smallest number in parent such that number ≥ x.
     */
    public int successor(int x) {
        return find(x);
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootQ] = rootP;
        }
    }

    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
