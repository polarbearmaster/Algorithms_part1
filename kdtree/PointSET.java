import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	private SET<Point2D> points;
	
   public         PointSET()                               // construct an empty set of points 
   {
	   points = new SET<Point2D>();
   }
   public           boolean isEmpty()                      // is the set empty? 
   {
	  return points.isEmpty();
   }
   public               int size()                         // number of points in the set 
   {
	   return points.size();
   }
   public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
	   points.add(p);
   }
   public           boolean contains(Point2D p)            // does the set contain point p? 
   {
	   return points.contains(p);
   }
   public              void draw()                         // draw all points to standard draw 
   {
	   for (Point2D p: points)
		   p.draw();
   }
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
   {
	   Queue<Point2D> q = new Queue<Point2D>();
	   
	   for (Point2D p : points) {
		   if (rect.contains(p)) q.enqueue(p);
	   }
	   
	   return q;
   }
   public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
   {
	   Point2D res = null;
	   double d = Double.POSITIVE_INFINITY;
	   for (Point2D point: points) {
		   if (point.distanceTo(p) < d) {
			   d = point.distanceTo(p);
			   res = point;
		   }
	   }
	   return res;
   }

   public static void main(String[] args)                  // unit testing of the methods (optional) 
   {
	   
   }
}
