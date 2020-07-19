package adapterExerciseTest;

import designPatterns.adapter.exercise.Square;
import designPatterns.adapter.exercise.SquareToRectangleAdapter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Evaluate {

    @Test
    public void test() {
        Square sq = new Square(23);
        SquareToRectangleAdapter adapter = new SquareToRectangleAdapter(sq);
        assertEquals(529, adapter.getArea());
    }
}
