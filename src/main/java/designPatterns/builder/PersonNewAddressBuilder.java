package designPatterns.builder;

/**
 * dedicated builder for addresses
 */
public class PersonNewAddressBuilder extends PersonNewBuilder {

    /**
     * a single person is built up
     * a reference of that person is needed
     * in every separate builder
     */
    public PersonNewAddressBuilder(PersonNew person) {
        this.person = person;
    }

    /**
     * for a fluent interface
     * the same return type : PersonNewAddressBuilder
     * is needed.
     */
    public PersonNewAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonNewAddressBuilder withPostCode(String postCode) {
        person.postcode = postCode;
        return this;
    }

    public PersonNewAddressBuilder in(String city) {
        person.city = city;
        return this;
    }
}
