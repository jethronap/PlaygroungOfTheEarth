package designPatterns.factory;

public class Point {

    private double x, y;

    /**
     * constructor to be used only inside class
     * forces the user to decide between the
     * following factory methods
     */
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // factory method to produce dedicated cartesian points
    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    // factory method to produce dedicated polar points
    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
    }
}
