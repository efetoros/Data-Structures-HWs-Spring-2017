package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    Percolation perc;
    double[] statCollector;
    int times;

    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        statCollector = new double[T];
        times = T;
        for (int i = 0; i < T; i++) {
            perc = new Percolation(N);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
            }
            statCollector[i] = perc.numberOfOpenSites() / (N * N);


        }
    } // perform T independent experiments on an N-by-N grid

    public double mean() {
//        return DoubleStream.of(statCollector).sum() / times;
        return StdStats.mean(statCollector);
    }                    // sample mean of percolation threshold

    public double stddev() {
//        double collector = 0.0;
//        for (int i = 0; i < times; i ++) {
//            collector = collector + ((statCollector[i] - this.mean()) * (statCollector[i] - this.mean()));
//        }
//        return collector / (times - 1);
        return StdStats.stddev(statCollector);
    }                  // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return this.mean() - ((1.96 * this.stddev()) / Math.sqrt(times));
        // low  endpoint of 95% confidence interval
    }

    public double confidenceHigh() {
        return this.mean() + ((1.96 * this.stddev()) / Math.sqrt(times));
    }
    public static void main(String[] args) {
        PercolationStats m = new PercolationStats(10, 20);
       double n = m.mean();
    }

}

