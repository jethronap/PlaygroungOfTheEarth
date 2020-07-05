package designPatterns.prototype;

import org.apache.commons.lang3.SerializationUtils;

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

        Foo foo = new Foo(666, "life");

        /**
         * making use of serialization utils from apache commons
         * roundTrip() serializes the obj and then deserializes it
         * in order to make a new copy, (something similar to copy by value
         * it does it for the whole obj graph (ie. it has other obj inside)
         */
        Foo foo2 = SerializationUtils.roundtrip(foo);

        foo2.whatever = "robots";

        System.out.println(foo);
        System.out.println(foo2);
    }
}

