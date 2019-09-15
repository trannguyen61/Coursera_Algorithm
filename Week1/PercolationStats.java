import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] res;
    private final int size;
    private static final double STATNUM = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        res = new double[trials];
        size = n;
        
        for (int i = 0; i < trials; i++){
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            res[i] = (double) percolation.numberOfOpenSites() / (double)(n*n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(res);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (STATNUM*stddev())/(Math.sqrt(size));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (STATNUM*stddev())/(Math.sqrt(size));
    }

   // test client (see below)
   public static void main(String[] args) {
    PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println("mean                    = " + stats.mean());
    System.out.println("stddev                  = " + stats.stddev());
    System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
   }

}