import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private Node root;
	private static final boolean RED = false;
	private static final boolean BLUE = true;
	
   public         KdTree()                               // construct an empty set of points 
   {
	   
   }
   public           boolean isEmpty()                      // is the set empty? 
   {
	   return root == null;
   }
   public               int size()                         // number of points in the set 
   {
	   return size(root);
   }
   public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
   {
	   root = insert(root, p, new RectHV(0,0,1,1), RED);
   }
   public           boolean contains(Point2D p)            // does the set contain point p? 
   {
	   Node x = root;
	   while (x != null) {
		   int cmp = compare(x, p);
		   if      (cmp < 0) x = x.lb;
		   else if (cmp > 0) x = x.rt;
		   else              return true;
	   }
	   return false;
   }
   public              void draw()                         // draw all points to standard draw 
   {
	   draw(root);
   }
   public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
   {
	   
   }
   public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
   {
	   
   }
   private static class Node {
	   private Point2D p;
	   private RectHV rect;
	   private Node lb;
	   private Node rt;
	   
	   boolean color;
	   
	   public Node(Point2D p, RectHV rect, boolean color) {
		   this.p    = p;
		   this.rect = rect;
		   this.color = color;
	   }
   }
   
   private boolean isRED(Node x) {
	   if (x == null) return false;
	   return x.color == RED;
   }
   
   private int size(Node x) {
	   if (x == null) return 0;
	   else           return 1 + size(x.lb) + size(x.rt);
   }
   
   private Node insert(Node x, Point2D p, RectHV rect, boolean color) {
	   if (x == null) return new Node(p, rect, color);
	   int cmp = compare(x,p);
	   x.rect = rect;
	   if (isRED(x)) {
		   if (cmp < 0)      x.lb = insert(x.lb, p, new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax()), !color);
		   else if (cmp > 0) x.rt = insert(x.rt, p, new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax()), !color);
	   } else {
		   if (cmp < 0)      x.lb = insert(x.lb, p, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y()), !color);
		   else if (cmp > 0) x.rt = insert(x.rt, p, new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax()), !color);
	   }
	   return x;
   }
   
   private void draw(Node x) {
	   if (x == null) {
		   return;
	   }
	   StdDraw.setPenColor();
	   StdDraw.setPenRadius();
	   x.rect.draw();
	   
	   StdDraw.setPenRadius(0.01);
	   x.p.draw();
	   
	   if (isRED(x)) {
		   StdDraw.setPenColor(StdDraw.RED);
		   StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
	   } else {
		   StdDraw.setPenColor(StdDraw.BLUE);
		   StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
	   }
	   draw(x.lb);
	   draw(x.rt);
   }
   
   private int compare(Node x, Point2D p) {
	   if (isRED(x)) {
		   Point2D px = x.p;
		   if (p.x() == px.x() && p.y() == px.y()) return 0;
		   else if (p.x() < px.x())                return -1;
		   else                                    return 1;
	   }
	   else {
		   Point2D px = x.p;
		   if (p.x() == px.x() && p.y() == px.y()) return 0;
		   else if (p.y() < px.y())                return -1;
		   else                                    return 1;
	   }
   }
   
   public static void main(String[] args)                  // unit testing of the methods (optional) 
   {
	   
   }
}
