package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean percolated;
    Boolean[][] grid;
    WeightedQuickUnionUF earth;
    WeightedQuickUnionUF earth2;
    int howmanyopen;
    int error;
    int water;
    int core;

    public Percolation(int N) {

        if (N < 1) {
            throw new IllegalArgumentException();
        }
        water = N * N;
        core = N * N + 1;
        error = N;
        grid = new Boolean[N][N];
        howmanyopen = 0;
        earth = new WeightedQuickUnionUF(N * N + 2);
        earth2 = new WeightedQuickUnionUF(N * N + 1);
        for (int m = 0; m < N; m++) {
            for (int i = 0; i < N; i++) {
                grid[m][i] = false;
            }
        }
    }

    private int getInt(int x, int y) {
        return (x * grid[0].length) + y;
    }

    private void checkIfNextToOpen(int x, int y) {
            /*Ask gsi what to do if the inedex is out of range, edge cases */

        if (x < 0 || y < 0 || x >= error || y >= error) {
            throw new IndexOutOfBoundsException();
        }
        if (x + 1 < error) {
            if (grid[x + 1][y] == true) {
                earth.union(getInt(x, y), getInt(x + 1, y));
                earth2.union(getInt(x, y), getInt(x + 1, y));
            }
        }
        if (x - 1 >= 0) {
            if (grid[x - 1][y] == true) {
                earth.union(getInt(x, y), getInt(x - 1, y));
                earth2.union(getInt(x, y), getInt(x - 1, y));
            }
        }
        if (y + 1 < error) {
            if (grid[x][y + 1] == true) {
                earth.union(getInt(x, y), getInt(x, y + 1));
                earth2.union(getInt(x, y), getInt(x, y + 1));
            }
        }
        if (y - 1 > 0) {
            if (grid[x][y - 1] == true) {
                earth.union(getInt(x, y), getInt(x, y - 1));
                earth2.union(getInt(x, y), getInt(x, y - 1));
            }
        }

    }

    public void open(int row, int col) {

        if (row < 0 || col < 0 || row >= error || col >= error) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col] == false) {
            grid[row][col] = true;
            howmanyopen++;
        }
        if (row == 0) {
            earth.union(water, getInt(0, col));
            earth2.union(water, getInt(0, col));
        } else if (row == error - 1) {
            earth.union(core, getInt(error - 1, col));

        }

        checkIfNextToOpen(row, col);

    }       // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= error || col >= error) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];

    }// is the site (row, col) open?

    public boolean isFull(int row, int col) {

        if (row < 0 || col < 0 || row >= error || col >= error) {
            throw new IndexOutOfBoundsException();
        }
        return earth2.connected(getInt(row, col), water);

    }// is the site (row, col) full?

    public int numberOfOpenSites() {
        return howmanyopen;
    }           // number of open sites

    public boolean percolates() {
        return earth.connected(water, core);
    }              // does the system percolate?

    public static void main(String[] args) {


        Percolation n = new Percolation(5);
        n.earth.connected(4, 0);
        n.checkIfNextToOpen(4, 4);
        n.checkIfNextToOpen(4, 3);
        n.checkIfNextToOpen(4, 2);
        n.checkIfNextToOpen(4, 1);
        n.checkIfNextToOpen(3, 4);
        n.checkIfNextToOpen(3, 3);
        n.checkIfNextToOpen(3, 2);
        n.checkIfNextToOpen(3, 1);
        n.checkIfNextToOpen(2, 4);
        n.checkIfNextToOpen(2, 3);
        n.checkIfNextToOpen(2, 2);
        n.checkIfNextToOpen(2, 1);
        n.checkIfNextToOpen(1, 4);
        n.checkIfNextToOpen(1, 3);
        n.checkIfNextToOpen(1, 2);
        n.checkIfNextToOpen(1, 1);
        n.checkIfNextToOpen(0, 4);
        n.checkIfNextToOpen(0, 3);
        n.checkIfNextToOpen(0, 2);
        n.checkIfNextToOpen(0, 1);
        n.checkIfNextToOpen(4, 0);
        n.checkIfNextToOpen(3, 0);
        n.checkIfNextToOpen(2, 0);
        n.checkIfNextToOpen(1, 0);
    }  // unit testing (not required)

}