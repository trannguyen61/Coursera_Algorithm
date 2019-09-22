import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    
    public Point(int x, int y)                         // constructs the point (x, y)
    {
        this.x = x;
        this.y = y;
    }
 
    public   void draw()                               // draws this point
    {
        StdDraw.point(x, y);
    }

    public   void drawTo(Point that)                   // draws the line segment from this point to that point
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString()                           // string representation
    {
        return "(" + x + ", " + y + ")";
    }
 
    public               int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
    {
        if (this.y == that.y && this.x == that.x) return 0;
        else if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else return 1;
    }

    public            double slopeTo(Point that)       // the slope between this point and that point
    {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        else if (this.y == that.y) return 0.0;
        else if (this.x == that.x) return Double.POSITIVE_INFINITY;
        else {
            return (double) (that.y - this.y)/(that.x - this.x);
        }
    }

    public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
    {
        return new SlopeComparator();
    }

    private class SlopeComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double aSlope = slopeTo(a);
            double bSlope = slopeTo(b);

            if (aSlope < bSlope) return -1;
            else if (aSlope > bSlope) return 1;
            else return 0;
        }
    }
}
