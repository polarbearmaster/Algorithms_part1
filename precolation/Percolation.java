import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private int[][] a;
   private int[][] b;
   private WeightedQuickUnionUF uf;
   private int n;
   private boolean isValidate(int p) {
       if (p < 0 || p >= n) {
           return false;
       }
       return true;
   }
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
       this.n = n;
       this.a = new int[n][n];
       this.b = new int[n][n];
       for (int i = 0; i < n; i++) {
           for (int j = 0;j < n;j++) {
               a[i][j] = i * n + j;
               b[i][j] = 0;
           }
       }
       this.uf = new WeightedQuickUnionUF(n*n+2); // add 2 virtual points
       for (int j = 0;j < n;j++) {
           uf.union(a[0][j],n*n);
           uf.union(a[n-1][j],n*n+1);
       }
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       if (!isOpen(row,col)) {
           b[row][col] = 1;
           if (isValidate(row-1) && isValidate(col) && isOpen(row-1,col)) {
                uf.union(a[row][col],a[row-1][col]);
           }
           if (isValidate(row+1) && isValidate(col) && isOpen(row+1,col)) {
                uf.union(a[row][col],a[row+1][col]);
           }
           if (isValidate(row) && isValidate(col-1) && isOpen(row,col-1)) {
                uf.union(a[row][col],a[row][col-1]);
           }
           if (isValidate(row) && isValidate(col+1) && isOpen(row,col+1)) {
                uf.union(a[row][col],a[row][col+1]);
           }
       }
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       return b[row][col] == 1;
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       return row == 1 && isOpen(row,col); 
   }
   public     int numberOfOpenSites()       // number of open sites
   {
       int sum = 0;
       for (int i = 0; i < n; i++) {
           for (int j = 0;j < n;j++) {
               if (b[i][j] == 1) {
                   sum++;
               }
           }
       }
       return sum;
   }
   public boolean percolates()              // does the system percolate?
   {
       return uf.connected(n*n,n*n+1);
   }
   public static void main(String[] args)  // test client (optional)
   {
      return;
   }
}