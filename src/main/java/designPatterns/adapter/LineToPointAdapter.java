package designPatterns.adapter;

import java.util.*;
import java.util.function.Consumer;

/**
 * the adapter class to convert lines
 * into point, provides single line
 * that corresponds to a number of points
 */
public class LineToPointAdapter implements Iterable<Point> {

    // count for produced points
    private static int count = 0;
    // keep track of points in order
    // to create a caching mechanism
    private static Map<Integer, List<Point>> cache = new HashMap<>();
    // which hash corresponds to the line provided
    private int hash;
                            // get the line
    public LineToPointAdapter(Line line) {
        // first create a hash
        hash = line.hashCode();
        if(cache.get(hash) != null) return;
        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)",
                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

        // this is where the generated points will be stored
        ArrayList<Point> points =  new ArrayList<>();
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
                points.add(new Point(left, y));
            }
        }
        else if (dy == 0)
        {
            for (int x = left; x <= right; ++x)
            {
                // generate a set of points
                points.add(new Point(x, top));
            }
        }

        // we now put the points in cache
        cache.put(hash, points);
    }

    @Override
    public void forEach(Consumer<? super Point> action) {
        cache.get(hash).forEach(action);
    }

    @Override
    public Spliterator<Point> spliterator() {
        return cache.get(hash).spliterator();
    }

    @Override
    public Iterator<Point> iterator() {
        return cache.get(hash).iterator();
    }
}
