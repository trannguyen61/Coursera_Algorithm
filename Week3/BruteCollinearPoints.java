import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private final LineSegment[] lines;

    public BruteCollinearPoints(Point[] originPoints)    // finds all line segments containing 4 points
    {
        checkNull(originPoints);
        Point[] points = originPoints.clone();

        ArrayList<LineSegment> lineSeg = new ArrayList<>();
        Arrays.sort(points);

        checkDuplicated(points);

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int n = j + 1; n < points.length - 1; n++) {
                    for (int m = n + 1; m < points.length; m++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[n])
                            && points[i].slopeTo(points[j]) == points[i].slopeTo(points[m])) {
                                LineSegment newLine = new LineSegment(points[i], points[m]);
                                lineSeg.add(newLine);
                            }
                    }
                }
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
 }