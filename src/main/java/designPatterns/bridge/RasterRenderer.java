package designPatterns.bridge;

public class RasterRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.printf("Drawing pixels for a circle of radius " + radius);
    }
}
