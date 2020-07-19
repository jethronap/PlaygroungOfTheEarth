package designPatterns.bridge;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Demo {
    public static void main(String[] args) {
//        RasterRenderer raster = new RasterRenderer();
//        VectorRenderer vector = new VectorRenderer();
//
//        Circle circle = new Circle(vector, 5);
//        circle.draw();
//        circle.resize(2);
//        circle.draw();
        /**
         * impl using google guice
         */
        Injector injector = Guice.createInjector(new ShapeModule());
        Circle instance = injector.getInstance(Circle.class);
        instance.radius = 23;
        instance.draw();
        instance.resize(2);
        instance.draw();
    }
}
