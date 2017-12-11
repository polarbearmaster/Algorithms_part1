import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
	//private ArrayList<Point> points;
	private Point[] points;
	private int n;
	private ArrayList<LineSegment> segments;
	   
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
   {
	   n = points.length;
	   this.points = new Point[n];
	   for (int i = 0; i < n; i++) {
		   if (points[i] == null) throw new java.lang.NullPointerException("any point in the array is null");
		  this.points[i] = points[i];
	   }
   }
   public           int numberOfSegments()        // the number of line segments
   {
	   return segments.size();
   }
   public LineSegment[] segments()                // the line segments
   {
	   //Arrays.sort(points);
//	   for(Point point: points) {
//		   StdOut.println(point);
//	   }
//	   StdOut.println("-----------------------------");
	   
	   segments = new ArrayList<LineSegment>();
	   
	   for (int i = 0; i < n; i++) {
		   Point p = points[i];
		   
//		   StdOut.println("i="+i+"start -----------------------------");
//		   StdOut.println(p);
//		   StdOut.println("i="+i+"end-----------------------------");
		   
		   Point[] tps = points.clone();
		   
		   Arrays.sort(tps, p.slopeOrder());
		   
//		   StdOut.println("slope order start-----------------------------");
//		   for(Point point: tps) {
//			   StdOut.println(point);
//		   }
//		   StdOut.println("slope order end-----------------------------");
		   
		   int j = 0;
		   while (j < n) {
			   if (j+2 >= n) break;
			   
			   if (p.slopeTo(tps[j+2]) == p.slopeTo(tps[j+1]) && 
				p.slopeTo(tps[j+1]) == p.slopeTo(tps[j])) {
				   int k = j+2;

				   while (p.slopeTo(tps[k]) == p.slopeTo(tps[j])) {
					   k++;
					   if (k>=n) break;
				   }
				   
				   Point[] tempPoints = new Point[k-j+1];
				   tempPoints[0] = p;
				   for (int l = 1; l < k-j+1; l++) 
					   tempPoints[l] = tps[j+l-1];
				   
				   Arrays.sort(tempPoints);
				   
				   if (tempPoints[0].compareTo(p) == 0) {
					   LineSegment templs = new LineSegment(tempPoints[0], tempPoints[k-j]);
					   //StdOut.println("i="+i+"j="+j+"k="+k);
					   segments.add(templs);
				   }
				   j = k;
			   } else {
				   j++;
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
	    
//	    Arrays.sort(points);
//	    
//	    for (int i = 0; i < n; i++) {
//	    	for (int j = i + 1; j < n; j++) {
//	    		Point p1 = points[i];
//	    		Point p2 = points[j];
//    		
//	    		StdOut.println("p1="+p1+";p2="+p2);
//	    		StdOut.println(p1.slopeTo(p2));
//	    		StdOut.println("------");
//	    	}
//	    }
//	    
//	    StdOut.println("--------------------");

	    
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);	    
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	    
	}
}
