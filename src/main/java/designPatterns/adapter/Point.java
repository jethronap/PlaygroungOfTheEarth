package designPatterns.adapter;

import java.util.Objects;

public class Point {

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * basically it checks the
     * x & y coordinates of a point
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    /**
     * calculation of a very simple hasCode
     */
    @Override
    public int hashCode() {

        int result  = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
