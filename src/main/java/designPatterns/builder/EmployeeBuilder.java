package designPatterns.builder;
                                                // we provide EmployeeBuilder as argument here
                                                // in order to preserve the fluent interface
public class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return this;
    }

}
