/**
 * Question 1.
 * Social network connectivity. Given a social network containing n members and
 * a log file containing m timestamps at which times pairs of members formed
 * friendships, design an algorithm to determine the earliest time at which
 * all members are connected(i.e., every member is a friend of a friend of a
 * friend ... of a friend). Assume that the log file is sorted by timestamp
 * and that friendship is an equivalence relation. The running time of your
 * algorithm should be m*log(n) or better and use extra space proportional to n.
 */
public class SocialNetworkConnectivity {
    private final int[] parent;
    private int counter;

    /**
     * Create a union-find structure with size n and initialize counter.
     * @param n number of network members.
     */
    public SocialNetworkConnectivity(int n) {
        parent = new int[n];
        counter = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Return true then all people is connected.
     * @return true if all people connected otherwise false.
     */
    public boolean isAllMembersConnected() {
        return counter == 1;
    }

    /**
     * Form a friend between two people.
     * @param  p one element
     * @param  q the other element
     */
    public void formAFriendship(int p, int q) {
        union(p, q);
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootQ] = rootP;
            counter--;
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
