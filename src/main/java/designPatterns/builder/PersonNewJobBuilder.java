package designPatterns.builder;

/**
 * dedicated builder for jobs
 */
public class PersonNewJobBuilder extends PersonNewBuilder {

    public PersonNewJobBuilder(PersonNew person) {
        this.person = person;
    }

    public PersonNewJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
    }

    public PersonNewJobBuilder asA(String position) {
        person.position = position;
        return this;
    }

    public PersonNewJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
    }
}
