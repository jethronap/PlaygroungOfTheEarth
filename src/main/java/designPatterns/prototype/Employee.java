package designPatterns.prototype;

public class Employee {

    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    /**
     * copy constructor making
     * use of Address copy constructor
     */

    public Employee(Employee other) {
        name = other.name;
        // using Address copy constructor
        address = new Address(other.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
