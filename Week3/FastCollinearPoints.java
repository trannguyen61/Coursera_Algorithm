import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private final LineSegment[] lines;

    public FastCollinearPoints(Point[] originPoints)     // finds all line segments containing 4 or more points
    {
        checkNull(originPoints);
        Point[] points = originPoints.clone();
        
        Arrays.sort(points);

        checkDuplicated(points);

        int arrLen = points.length;
        ArrayList<LineSegment> lineSeg = new ArrayList<>();

        for (int i = 0; i < arrLen; i++) {
            Point origin = points[i];
            Point[] modifiedPoints = points.clone();
            Arrays.sort(modifiedPoints, origin.slopeOrder());
            
            int left = 1, right = left + 1;
            while (left < arrLen - 2) {
                while (right < arrLen 
                && origin.slopeTo(modifiedPoints[right]) == origin.slopeTo(modifiedPoints[left])) right++;
                
                if (right - left >= 3 && modifiedPoints[left].compareTo(origin) > 0) {
                    LineSegment newLine = new LineSegment(origin, modifiedPoints[right - 1]);
                    lineSeg.add(newLine);
                }

                left = right;
            }
        }

        lines = lineSeg.toArray(new LineSegment[lineSeg.size()]);
    }

    public           int numberOfSegments()        // the number of line segments
    {
        return lines.length;
    }

    public LineSegment[] segments()                // the line segments
    {
        return lines.clone();
    }

    private void checkNull(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }
    }

    private void checkDuplicated(Point[] sortedPoints) {
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i+1]) == 0) throw new IllegalArgumentException();
        }
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
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
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