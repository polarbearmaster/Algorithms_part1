import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private double[] x;
   private int t;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       t = trials;
       x = new double[trials];
       for (int i = 0; i < trials; i++) {
           x[i] = 0;
           Percolation p = new Percolation(n);
           while(!p.percolates()) {
               int row = 0;
               int col = 0;
               do {
                   row = StdRandom.uniform(n);
                   col = StdRandom.uniform(n);
               } while (p.isOpen(row,col));
               p.open(row,col);
               x[i]++;
           }
           x[i] /= (n*n);
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(x);
   }
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(x);
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       double res = (mean() - 1.96 * stddev() / Math.sqrt(t));
       return res;
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       double res = (mean() + 1.96 * stddev() / Math.sqrt(t));
       return res;
   }

   public static void main(String[] args)        // test client (described below)
   {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats ps = new PercolationStats(n,trials);
       System.out.println("mean="+ps.mean()); 
       System.out.println("stddev="+ps.stddev());
       System.out.println("95% confidence interval="+ps.confidenceLo()+","+ps.confidenceHi());
   }
}