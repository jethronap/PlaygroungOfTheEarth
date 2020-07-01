package designPatterns.builder;

/**
 * It's a friendly to providing Fluent APIs
 * using Recursive Generics
 * https://vyazelenko.com/2012/03/02/recursive-generics-to-the-rescue/
 */
public class PersonBuilder<SELF extends PersonBuilder<SELF>> {

    protected Person person = new Person();

    // changed the return type from PersonBuilder to SELF
    public SELF withName(String name) {
        person.name = name;
        // cast this to SELF
        // return (SELF) this;
        // use helper method
        return self();
    }

    public Person build() {
        return person;
    }

    /**
     * used in order to be able to
     * override self in derived classes
     */
    protected SELF self() {
        return (SELF) this;
    }
}
