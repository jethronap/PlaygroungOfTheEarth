package designPatterns.builder;

/**
 * combination of builder and facade pattern
 */
public class PersonNewBuilder {

    // the obj that gets stored
    protected Person person = new Person();

    // method to expose Person
    public Person build() {
        return person;
    }
}
