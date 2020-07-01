package designPatterns.builder;

/**
 * combination of builder and faceted pattern
 */
public class PersonNewBuilder {

    // the obj that gets stored
    protected PersonNew person = new PersonNew();

    // expose address builder
    public PersonNewAddressBuilder lives() {
        return new PersonNewAddressBuilder(person);
    }

    // expose job builder
    public PersonNewJobBuilder works() {
        return new PersonNewJobBuilder(person);
    }

    // method to expose Person
    public PersonNew build() {
        return person;
    }
}
