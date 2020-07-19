package designPatterns.bridge;

public class Demo {
    public static void main(String[] args) {
        RasterRenderer raster = new RasterRenderer();
        VectorRenderer vector = new VectorRenderer();

        Circle circle = new Circle(vector, 5);
        circle.draw();
        circle.resize(2);
        circle.draw();
    }
}
