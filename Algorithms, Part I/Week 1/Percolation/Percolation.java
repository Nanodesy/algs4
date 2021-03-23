import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// TODO: (bonus) byte array which represents connectivity to top and bottom hidden nodes.
public class Percolation {
    private int openSites = 0;
    private final int hiddenTopCellIndex;
    private final int hiddenBottomCellIndex;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF percolationUF;
    private final WeightedQuickUnionUF backwashUF;

    /**
     * Creates n-by-n grid, with all sites initially blocked.
     * @param n - side of square grid.
     * @throws IllegalArgumentException if side equal or less than zero.
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Size equal or less than zero!");

        grid = new boolean[n][n];
        int numberOfElements = n * n + 2;
        percolationUF = new WeightedQuickUnionUF(numberOfElements);
        backwashUF = new WeightedQuickUnionUF(numberOfElements);
        hiddenTopCellIndex = numberOfElements - 1;
        hiddenBottomCellIndex = numberOfElements - 2;
    }

    /**
     * Opens the site (row, col) if it is not open already.
     * @param row - grid row in range (1, n).
     * @param col - grid column in range (1, n).
     * @throws IllegalArgumentException if any argument is outside (1, n) range.
     */
    public void open(int row, int col) {
        checkBounds(row, col);

        int x = row - 1;
        int y = col - 1;

        if (!grid[x][y]) {
            grid[x][y] = true;
            openSites++;
        }
        int currentCellNumber = x * grid.length + y;

        // Top Cell Check
        if (x - 1 >= 0) {
            if (grid[x - 1][y]) {
                percolationUF.union(currentCellNumber, rowColumnTo1D(row - 1, col));
                backwashUF.union(currentCellNumber, rowColumnTo1D(row - 1, col));
            }
        } else {
            percolationUF.union(currentCellNumber, hiddenTopCellIndex);
            backwashUF.union(currentCellNumber, hiddenTopCellIndex);
        }

        // Bottom Cell Check
        if (x + 1 < grid.length) {
            if (grid[x + 1][y]) {
                percolationUF.union(currentCellNumber, rowColumnTo1D(row + 1, col));
                backwashUF.union(currentCellNumber, rowColumnTo1D(row + 1, col));
            }
        } else {
            percolationUF.union(currentCellNumber, hiddenBottomCellIndex);
            if (backwashUF.find(hiddenTopCellIndex) == backwashUF.find(rowColumnTo1D(row, col)))
                backwashUF.union(currentCellNumber, hiddenBottomCellIndex);
        }

        // Left Cell Check
        if (y - 1 >= 0 && grid[x][y - 1]) {
            percolationUF.union(currentCellNumber, rowColumnTo1D(row, col - 1));
            backwashUF.union(currentCellNumber, rowColumnTo1D(row, col - 1));
        }

        // Right Cell Check
        if (y + 1 < grid.length && grid[x][y + 1]) {
            percolationUF.union(currentCellNumber, rowColumnTo1D(row, col + 1));
            backwashUF.union(currentCellNumber, rowColumnTo1D(row, col + 1));
        }
    }

    /**
     * Checks if the site (row, col) open.
     * @param row - grid row in range (1, n)
     * @param col - grid column in range (1, n).
     * @return true - if site open and false if not.
     * @throws IllegalArgumentException if any argument is outside (1, n) range.
     */
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return grid[row - 1][col - 1];
    }

    /**
     * A full site is an open site that can be connected to an open site in the top row via chain
     * of neighboring open sites.
     * @param row - grid row in range (1, n)
     * @param col - grid column in range (1, n)
     * @return true if site open and false if not.
     * @throws IllegalArgumentException if any argument is outside (1, n) range.
     */
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return backwashUF.find(hiddenTopCellIndex) == backwashUF.find(rowColumnTo1D(row, col));
    }

    /**
     * Return the number of open sites.
     * @return number of open sites.
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Returns true if system percolates.
     * @return true if this system percolates.
     */
    public boolean percolates() {
        return percolationUF.find(hiddenTopCellIndex) == percolationUF.find(hiddenBottomCellIndex);
    }

    private int rowColumnTo1D(int row, int col) {
        return (row - 1) * grid.length + col - 1;
    }

    private void checkBounds(int row, int col) {
        if (row < 1 || row > grid.length)
            throw new IllegalArgumentException("row index " + row + " is out of bounds");
        else if (col < 1 || col > grid.length)
            throw new IllegalArgumentException("col index " + col + " is out of bounds");
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(2);
        percolation.open(1, 1);
        percolation.open(2, 1);
        StdOut.println(percolation.percolates());
    }
}
