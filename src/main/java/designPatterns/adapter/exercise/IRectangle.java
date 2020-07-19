package designPatterns.adapter.exercise;

public interface IRectangle {

    int getWidth();
    int getHeight();

    default int getArea() {
        return getHeight() * getWidth();
    }
}
