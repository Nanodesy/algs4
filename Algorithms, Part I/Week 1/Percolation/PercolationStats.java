import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.95;
    private final double[] thresholds;
    private final double mean;

    /**
     * Create percolation with n-by-n grid, with all sites initially blocked and
     * carry out <code>trials</code> trials.
     * @param n size of gird.
     * @param trials number of trials.
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Size of grid or number of trials is less than zero");

        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, n) + 1;
                int col =  StdRandom.uniform(0, n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }

            thresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(thresholds);
    }

    /**
     * Return mean of percolation threshold.
     * @return mean of percolation threshold.
     */
    public double mean() {
        return mean;
    }

    /**
     * Return standard deviation of percolation threshold.
     * @return standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Return low endpoint of 95% confidence interval.
     * @return low endpoint of 95% confidence interval.
     */
    public double confidenceLo() {
        return mean - CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
    }

    /**
     * Return high endpoint of 95% confidence interval.
     * @return high endpoint of 95% confidence interval.
     */
    public double confidenceHi() {
        return mean + CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        StdOut.println("mean = " + percolationStats.mean);
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi() + "]");
    }
}
