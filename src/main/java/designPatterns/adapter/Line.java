package designPatterns.adapter;

import java.util.Objects;

public class Line {

    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return start.equals(line.start) &&
                end.equals(line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
