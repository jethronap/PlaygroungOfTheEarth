package factoryExerciseTest;

import designPatterns.factory.exercise.Person;
import designPatterns.factory.exercise.PersonFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactoryTest {

    @Test
    public void test() {
        PersonFactory personFactory = new PersonFactory();

        Person jonas = personFactory.createPerson("Jonas");
        Person martha = personFactory.createPerson("Martha");

        assertEquals("Jonas", jonas.name);
        assertEquals(0, jonas.id);

        assertEquals("Martha", martha.name);
        assertEquals(1, martha.id);
    }
}
