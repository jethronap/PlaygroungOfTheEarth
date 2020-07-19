package bridge.ExerciseTest;

import designPatterns.bridge.exercise.Square;
import designPatterns.bridge.exercise.VectorRenderer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Evaluation {

    @Test
    public void test() {
        assertEquals("Drawing Square as lines",
                new Square(new VectorRenderer()).toString());
    }
}
