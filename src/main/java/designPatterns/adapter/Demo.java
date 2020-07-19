package designPatterns.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    // initialise vector rectangles inside a list of vector objects
    private final static List<VectorObject> vectorObjects
            = new ArrayList<>(Arrays.asList(
                    new VectorRectangle(1,1,10,10),
                    new VectorRectangle(2,2,20,20)
    ));

    // the api for drawing points
    // (i.e no way to build vectors through points,
    // here is where the adapter pattern comes to play)
    public static void drawPoint(Point p) {
        System.out.println(".");
    }

    // this is the method to draw
    private static void draw() {
        for (VectorObject vo: vectorObjects) {
            // every obj consists of lines
            for (Line line: vo) {
                LineToPointAdapter adapter = new LineToPointAdapter(line);
                adapter.forEach(Demo::drawPoint);
            }
        }
    }

    public static void main(String[] args) {

        draw();
        /**
         * if draw(); is called once more
         * it generates another 8 points
         * while what is needed, is two rectangles.
         * the points are not re-usable,
         * without a caching implementation.
         */
        draw();
    }
}
