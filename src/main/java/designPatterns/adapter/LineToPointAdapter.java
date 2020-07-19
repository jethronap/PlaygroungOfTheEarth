package designPatterns.adapter;

import java.util.ArrayList;

/**
 * the adapter class to convert lines
 * into point, provides single line
 * that corresponds to a number of points
 */
public class LineToPointAdapter extends ArrayList<Point> {

    // count for produced points
    private static int count = 0;

                            // get the line
    public LineToPointAdapter(Line line) {
        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (no caching)",
                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

        // all the parts
        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0)
        {
            for (int y = top; y <= bottom; ++y)
            {
                // generate a set of points
                add(new Point(left, y));
            }
        }
        else if (dy == 0)
        {
            for (int x = left; x <= right; ++x)
            {
                // generate a set of points
                add(new Point(x, top));
            }
        }
    }
}
