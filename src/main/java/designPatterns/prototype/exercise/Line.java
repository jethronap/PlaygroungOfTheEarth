package designPatterns.prototype.exercise;

/**
 * Prototype Coding Exercise
 * Given the following class definitions,
 * you are asked to implement Line.deepCopy()
 * to perform a deep copy of the current Line object.
 */
public class Line {

    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy() {
        Point newStart = new Point(start.x, start.y);
        Point newEnd = new Point(end.x, end.y);
        return new Line(newStart, newEnd);
    }
}
