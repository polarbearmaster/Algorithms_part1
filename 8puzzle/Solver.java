
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private Board initial;
	private boolean isExcutedBFS;
	
	private boolean isSolvable;
	private SearchNode destSearchNode;
	
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {    
    	this.initial = initial;
    	this.isExcutedBFS = false;
    	this.isSolvable = isSolvable();
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
    	if (isExcutedBFS) {
    		return isSolvable;
    	}
    	
    	MinPQ<SearchNode> pq1 = new MinPQ<SearchNode>();
    	MinPQ<SearchNode> pq2 = new MinPQ<SearchNode>();
    	
    	SearchNode initialSearchNode1 = new SearchNode();
    	initialSearchNode1.board = initial;
    	initialSearchNode1.previousSearchNode = null;
    	initialSearchNode1.moves = 0;
    	
    	SearchNode initialSearchNode2 = new SearchNode();
    	initialSearchNode2.board = initial.twin();
    	initialSearchNode2.previousSearchNode = null;
    	initialSearchNode2.moves = 0;
    	
    	pq1.insert(initialSearchNode1);
    	pq2.insert(initialSearchNode2);
    	
    	boolean res;
    	
    	synchronized(this) {
    		while (true) {
        		// first search
        		SearchNode dest1 = pq1.delMin();
        		
        		for (Board board: dest1.board.neighbors()) {    			
        			SearchNode sn = new SearchNode();
        			sn.board = board;
        			sn.previousSearchNode = dest1;
        			sn.moves = dest1.moves + 1;
        			
        			if (sn.co()) {
            			pq1.insert(sn);
        			}
        		}
        		
        		//second search
        		SearchNode dest2 = pq2.delMin();
        		
        		for (Board board: dest2.board.neighbors()) {    			
        			SearchNode sn = new SearchNode();
        			sn.board = board;
        			sn.previousSearchNode = dest2;
        			sn.moves = dest2.moves + 1;
        			
        			if (sn.co()) {
            			pq2.insert(sn);
        			}
        		}
        		
        		if (dest1.board.isGoal()) {
        			res = true;
        			destSearchNode = dest1;
        			isExcutedBFS = true;
        			break;
        		}
        		if (dest2.board.isGoal()) {
        			res = false;
        			isExcutedBFS = true;
        			break;
        		}
        	}
    	}	    	
    	
    	return res;
    }
    public int moves()        			   // min number of moves to solve initial board; -1 if unsolvable
    {
    	if (!isSolvable()) return -1;
    	return destSearchNode.moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	if (!isSolvable()) return null;
    	Stack<Board> s = new Stack<Board>();
    	SearchNode p = destSearchNode;
    	while (p != null) {
    		s.push(p.board);
    		p = p.previousSearchNode;
    	}
    	
    	return s;
    }
    
    private class SearchNode implements Comparable<SearchNode> {
    	Board board;
    	SearchNode previousSearchNode;
    	int moves;
    	
    	public int mpf() {
    		return board.manhattan() + moves;
    	}
    	
    	public boolean co() {
    		boolean res = true;
    		SearchNode p = previousSearchNode;
    		while (p != null) {
    			if (board.equals(p.board)) {
    				res = false;
    				break;
    			}
    			p = p.previousSearchNode;
    		}
    		return res;
    	}
    	
		@Override
		public int compareTo(SearchNode o) {
			if (this.mpf() < o.mpf()) return -1;
			if (this.mpf() > o.mpf()) return 1;
			return 0;
		}
    }
    
//  private SearchNode bfs(Board b) {
//	MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
//	
//	SearchNode initialSearchNode = new SearchNode();
//	initialSearchNode.board = b;
//	initialSearchNode.previousSearchNode = null;
//	initialSearchNode.moves = 0;
//	
//	pq.insert(initialSearchNode);
//	SearchNode res = initialSearchNode;
//	    	
//	while (!res.board.isGoal()) {
//		res = pq.delMin();
//		
//		for (Board board: res.board.neighbors()) {    			
//			SearchNode sn = new SearchNode();
//			sn.board = board;
//			sn.previousSearchNode = res;
//			sn.moves = res.moves + 1;
//			
//			if (sn.co()) {
//    			pq.insert(sn);
//			}
//		}
//	}
//	
//	return res;
//}
  
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    	// create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
