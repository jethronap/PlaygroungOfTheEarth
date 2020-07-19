package designPatterns.bridge.exercise;

public class Square extends Shape {

    public Square(Renderer renderer) {
        super(renderer);
        name = "Square";
    }
}
