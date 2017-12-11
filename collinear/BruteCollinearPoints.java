import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;


public class BruteCollinearPoints {
	
   private ArrayList<Point> points;
   private int n;
   
   
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points 
   {
	   if (points == null) throw new java.lang.NullPointerException("the argument to the constructor is null");
	   n = points.length;
	   this.points = new ArrayList<Point>();
	   for (int i = 0; i < n; i++) {
		   if (points[i] == null) throw new java.lang.NullPointerException("any point in the array is null");
		   if (this.points.contains(points[i])) throw new java.lang.IllegalArgumentException("the argument to the constructor contains a repeated point");
		   else this.points.add(points[i]);
	   }
   }
   public int numberOfSegments()        // the number of line segments
   {
	   LineSegment[] segments = segments();
	   return segments.length;
   }
   public LineSegment[] segments()      // the line segments
   {	   
	   //Arrays.sort(points);
	   points.sort(null);
	   ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
	   
//	   for(Point point: points) {
//		   StdOut.println(point);
//	   }
//	   StdOut.println("-----------------------------");
	   
	   for (int i = 0; i < n-3; i++) {
		   for (int j = i+1; j < n-2; j++) {
			   for (int k = j+1; k < n-1; k++) {
				   Point p1 = points.get(i);
				   Point p2 = points.get(j);
				   Point p3 = points.get(k);
				   
//				   if (p1.slopeTo(p2) != p2.slopeTo(p3))
//					   break;
				   
				   for (int l = k+1; l < n; l++) {
					   
					   Point p4 = points.get(l);
					   
					   if (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {
						   //add it to segments
						  segments.add(new LineSegment(p1,p4));
					   }
				   }
			   }
		   }
	   }
	   return segments.toArray(new LineSegment[0]);
   }
   
   public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }
	    
//	    for (int i = 0; i < n; i++) {
//	    	for (int j = i + 1; j < n; j++) {
//	    		Point p1 = points[i];
//	    		Point p2 = points[j];
//	    		
//	    		StdOut.println(p1.slopeTo(p2));
//	    	}
//	    }

	    
	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();
	    
	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);	    
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	    
	    
	}
}
