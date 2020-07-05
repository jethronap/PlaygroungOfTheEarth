package geometryTest;

import geometry.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeometryTests {

    double sideA = 3.0;
    double sideB = 5.0;
    double sideC = 7.0;

    @Test
    public void getTriangleArea() {

        Triangle ABC = new Triangle(sideA, sideB, sideC);

        double areaOfABC = ABC.area();
        assertEquals(areaOfABC, 6.4952, 1.0);


    }

    @Test
    public void getTrianglePerimeter() {

        Triangle ABC = new Triangle(sideA, sideB, sideC);

        double perimeterOfABC = ABC.perimeter();
        assertEquals(perimeterOfABC, 15.0, 1.0);

    }
}
