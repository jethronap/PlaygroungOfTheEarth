import geometry.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeometryTests {

    @Test
    public void getTriangleArea(){

        double sideA = 3.0;
        double sideB = 5.0;
        double sideC = 7.0;

        Triangle ABC = new Triangle(sideA, sideB, sideC);

        double areaOfABC = ABC.area();
        assertEquals(areaOfABC,6.4952, 1.0);


    }
}
