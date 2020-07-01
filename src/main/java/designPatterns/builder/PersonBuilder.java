package designPatterns.builder;

/**
 * It's a friendly to providing Fluent APIs
 * using Recursive Generics
 * https://vyazelenko.com/2012/03/02/recursive-generics-to-the-rescue/
 */
public class PersonBuilder<SELF extends PersonBuilder<SELF>> {

    protected Person person = new Person();

    public PersonBuilder withName(String name) {
        person.name = name;
        return this;
    }
}
