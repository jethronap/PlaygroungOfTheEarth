package geometry;

public class Triangle implements IPolygon {

    int sides = 3;
    double sideA;
    double sideB;
    double sideC;

    public Triangle(int sides) {
        this.sides = 3;
    }

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    /**
     * We'll use Herons' formula to calculate the area as we only know the lengths of all three sides.
     * <p>
     * Let a,b,c be the lengths of the sides of a triangle. The area is given by:
     * Area	= √p(p − a)(p − b)(p − c)
     * where p is half the perimeter, or (a+b+c)/2.
     *
     * @return
     */
    public double area() {

        double p = (sideA + sideB + sideC) / 2;
        double area = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        return area;
    }

    public double perimeter() {
        return sideA + sideB + sideC;
    }

}
