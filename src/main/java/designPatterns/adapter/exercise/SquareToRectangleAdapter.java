package designPatterns.adapter.exercise;

public class SquareToRectangleAdapter implements IRectangle {

    private Square square;

    public SquareToRectangleAdapter(Square square) {
        this.square = square;
    }

    @Override
    public int getWidth() {
        return square.side;
    }

    @Override
    public int getHeight() {
        return square.side;
    }

}
