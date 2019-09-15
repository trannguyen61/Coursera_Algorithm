import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private final WeightedQuickUnionUF union;
    private boolean[] openSites;
    private int openSitesNum = 0;
    private final int size, top, bot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
            
        size = n;
        top = n*n;
        bot = n*n + 1;
        union = new WeightedQuickUnionUF(n*n + 2);
        openSites = new boolean[n*n + 2];
    }

    private boolean isInvalid(int row, int col) {
        return (row < 1 || row > size || col < 1 || col > size);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isInvalid(row, col))
            throw new IllegalArgumentException();

        if (openSites[(row - 1)*size + (col - 1)]) return;

        openSites[(row - 1)*size + (col - 1)] = true;
        openSitesNum++;

        int currentIndex = (row - 1)*size + (col - 1);

        if (row == 1) union.union(currentIndex, top);
        if (row == size) union.union(currentIndex, bot);
        if (col < size && isOpen(row, col+1)) union.union(currentIndex, currentIndex+1);
        if (col > 1 && isOpen(row, col-1)) union.union(currentIndex, currentIndex-1);
        if (row < size && isOpen(row+1, col)) union.union(currentIndex, (row)*size + (col-1));
        if (row > 1 && isOpen(row-1, col)) union.union(currentIndex, (row-2)*size + (col-1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isInvalid(row, col))
            throw new IllegalArgumentException();

        return openSites[(row - 1)*size + (col - 1)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isInvalid(row, col))
            throw new IllegalArgumentException();

        return union.connected((row - 1)*size + (col - 1), top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return union.connected(top, bot);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            percolation.open(p, q);
        }
        StdOut.println(percolation.numberOfOpenSites() + " components");
        StdOut.println(percolation.percolates());
    }
}