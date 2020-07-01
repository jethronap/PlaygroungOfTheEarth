package designPatterns.builder;

/**
 * combination of builder and facade pattern
 */
public class PersonNewBuilder {

    // the obj that gets stored
    protected PersonNew person = new PersonNew();

    public PersonNewAddressBuilder lives() {
        return new PersonNewAddressBuilder(person);
    }

    // method to expose Person
    public PersonNew build() {
        return person;
    }
}
