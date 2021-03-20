/**
 * Question 2.
 * Union-find with specific canonical element. Add a method <code>find()</code>
 * to the union-find data type so that <code>find(i)</code> returns the
 * largest element in the connected component containing <code>i</code>. The
 * operations, <code>union()</code>, <code>connected()</code>, and
 * <code>find()</code> should all take logarithmic time or better.
 * For example, if one of the connected components is <code>{1,2,6,9}</code>,
 * then the <code>find()</code> method should return 9 for each of the four
 * elements in the connected components.
 */
public class SpecificCanonicalElementUF {
    private final int[] parent;
    private final int[] size;
    private final int[] maxValue;

    /**
     * Create union-find structure with auxiliary array containing maximal
     * value of each connected group.
     * @param n size of structure.
     */
    public SpecificCanonicalElementUF(int n) {
        parent = new int[n];
        size = new int[n];
        maxValue = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            maxValue[i] = i;
        }
    }

    /**
     * Returns the largest element of the set containing element {@code p}.
     * @param  i an element.
     * @return the canonical element of the set containing {@code p}.
     * @throws IllegalArgumentException unless {@code 0 <= p < n}.
     */
    public int find(int i) {
        return maxValue[findRoot(i)];
    }

    /**
     * Merges the set containing element {@code p} with the the set
     * containing element {@code q}. Also choose final root depending on size
     * of tree. Use maxValue to store maximal value.
     * @param  p one element.
     * @param  q the other element.
     */
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            maxValue[rootQ] = Math.max(maxValue[rootP], maxValue[rootQ]);
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            maxValue[rootP] = Math.max(maxValue[rootP], maxValue[rootQ]);
            size[rootP] += size[rootQ];
        }
    }

    private int findRoot(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            maxValue[p] = maxValue[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
