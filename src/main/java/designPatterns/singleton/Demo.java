package designPatterns.singleton;

import java.io.*;

public class Demo {

    /**
     * serialization code
     */
    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)) {
            out.writeObject(singleton);
        }
    }

    /**
     * deserialization code
     */
    static BasicSingleton readFromFile(String filename) throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {

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
        // System.out.println(singleton.getValue());

        String filename = "singleton.bin";
        saveToFile(singleton, filename);

        // set a new value
        singleton.setValue(5555);

        // deserialize
        BasicSingleton singleton1 = readFromFile(filename);

        // compare the two references
        System.out.println(singleton == singleton1);

        System.out.println(singleton.getValue());
        System.out.println(singleton1.getValue());

        /**
         * Enum based singleton impl
         *
         * the same serialization and deserialization
         * methodology can be used. However, serialization cannot be
         * done properly and inheritance cannot be used for this impl.
         */
    }
}
