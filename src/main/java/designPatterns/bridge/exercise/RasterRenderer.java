package designPatterns.bridge.exercise;

public class RasterRenderer implements Renderer {

    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}
