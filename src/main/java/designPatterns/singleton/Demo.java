package designPatterns.singleton;

import java.io.*;

public class Demo {

    public static void main(String[] args) {

        /**
         * implementing the basic singleton class
         *
         * PROBLEMS: they contracted can be broken through
         * 1. reflection
         * 2. serialization: the JVM will construct the obj irrespectively
         * of the private constructor
         */
        BasicSingleton singleton = BasicSingleton.getINSTANCE();
        singleton.setValue(1234);
        System.out.println(singleton.getValue());
    }
}
