
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
	private final int[] tiles;
	private final int n;
	private int ib;
	private int manhattan;
	
    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks    (where blocks[i][j] = block in row i, column j)										// (where blocks[i][j] = block in row i, column j)
    {
    	this.n = blocks[0].length;
    	this.tiles = new int[n*n];
    	this.manhattan = -1;
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (blocks[i][j] == 0) {
    				this.ib = i*n+j;
    			}
    			this.tiles[i*n+j] = blocks[i][j];
    		}
    	}
    }
    public int dimension()                 // board dimension n
    {
    	return n;
    }
    public int hamming()                   // number of blocks out of place
    {
    	int hamming = 0;
    	for (int i = 0; i < n*n; i++) {
    		if (tiles[i] != 0) {
    			if (tiles[i] != i+1) hamming++;
    		}
    	}
    	return hamming;
    		
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	if (manhattan == -1) {
    		manhattan = 0;
    		
    		for (int k = 0; k < n*n; k++) {
    			if (tiles[k] != 0) {
    				int i1 = k / n;
            		int j1 = k - i1*n;
            		int i2 = (tiles[k] - 1) / n;
           			int j2 = tiles[k] - 1 - i2 * n;
           			manhattan += (Math.abs(i1 - i2) + Math.abs(j1 - j2));
    			}
        	}
    	}
    	
    	return manhattan;
    }
    public boolean isGoal()                // is this board the goal board?
    {
    	for (int i = 0; i < n*n; i++) {
    		if (i == n*n - 1) {
    			if (tiles[i] != 0) return false;
    		}
    		else {
    			if (tiles[i] != i+1) return false;
    		}
    	}
    	return true;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
    	Board twin = new Board(tiles);
    	int p,q = 0; 

    	do {
    		p = StdRandom.uniform(n*n);
    		q = StdRandom.uniform(n*n);
    	} while (p == q || tiles[p] == 0 || tiles[q] == 0);
    		
    	twin.tiles[p] = tiles[q];
    	twin.tiles[q] = tiles[p];
    	
    	return twin;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
    	if (y == this) return true;
    	if (y == null) return false;
    	if (y.getClass() != this.getClass()) return false;
    	Board that = (Board) y;
    	if (that.n != this.n) return false;
    	
    	for (int i = 0; i < n*n; i++) {
    		if (that.tiles[i] != this.tiles[i]) return false;
    	}
    	
    	return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
    	Queue<Board> neighbors = new Queue<Board>();
    	Board neigh1 = swap(ib-1);
    	Board neigh2 = swap(ib+1);
    	Board neigh3 = swap(ib-n);
    	Board neigh4 = swap(ib+n);
    	
    	if (neigh1 != null) neighbors.enqueue(neigh1);
    	if (neigh2 != null) neighbors.enqueue(neigh2);
    	if (neigh3 != null) neighbors.enqueue(neigh3);
    	if (neigh4 != null) neighbors.enqueue(neigh4);
    	
    	return neighbors;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
    	StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i*n+j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    private Board(int[] tiles) {
    	this.n = (int)Math.sqrt(tiles.length);
    	this.tiles = new int[n*n];
    	this.manhattan = -1;
    	
    	for (int i = 0; i < n*n; i++) {
    		if (tiles[i] == 0) this.ib = i;
    		
    		this.tiles[i] = tiles[i];
    	}
    }
    
    private Board swap(int k) {
    	// bound condition check
    	if (k < 0 || k >=n*n) return null;
    	int i1 = k / n;
    	int j1 = k - i1*n;
    	
    	int i2 = ib / n;
    	int j2 = ib - i2*n;
    	
    	if (Math.abs(i1-i2) + Math.abs(j1-j2) != 1) return null;
    	
    	Board neigh = new Board(tiles);
    	neigh.tiles[k] = tiles[ib];
    	neigh.tiles[ib] = tiles[k];
    	neigh.ib = k;
    	return neigh;
    }

    public static void main(String[] args) // unit tests (not graded)
    {
    	In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        
        Board initial = new Board(blocks);
        StdOut.println(initial);
        StdOut.println(initial.manhattan());
        StdOut.println("---------------------");
        
//        for (Board b: initial.neighbors()) 
//        	StdOut.println(b);
        Board tw = initial.twin();
        StdOut.println(tw);
        StdOut.println(tw.manhattan());
    }
}

