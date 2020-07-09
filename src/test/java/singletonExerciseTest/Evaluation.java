package singletonExerciseTest;

import designPatterns.singleton.exercise.SingletonTester;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Evaluation {

    @Test
    public void test() {
        Object object = new Object();
        assertTrue(SingletonTester.isSingleton(() -> object));
        assertFalse(SingletonTester.isSingleton(Object::new));
    }
}
