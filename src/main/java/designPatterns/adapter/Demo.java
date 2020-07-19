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

    public static void main(String[] args) {

    }
}
