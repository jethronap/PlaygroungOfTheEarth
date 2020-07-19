package designPatterns.bridge.exercise;

public class Triangle extends Shape {

    public Triangle(Renderer renderer) {
        super(renderer);
        name = "Triangle";
    }
}
