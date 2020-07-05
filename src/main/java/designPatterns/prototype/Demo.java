package designPatterns.prototype;

public class Demo {
    public static void main(String[] args) {
        Employee john = new Employee("John",
                new Address("123 Somewhere Str", "Somewhere", "NonExistent"));

        // make a copy of john
        // in order to create mary
        Employee mary = new Employee(john);

        mary.name = "Mary";
        mary.address.streetAddress = "124 Somewhere Str";
        System.out.println(john);
        System.out.println(mary);
    }
}
