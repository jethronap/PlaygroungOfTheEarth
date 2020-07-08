package designPatterns.singleton;

import java.util.HashMap;

/**
 * the behaviour of this class makes it a multiton.
 */
public class Printer {

    // add diagnostics to see how many printers have been created
    private static int instanceCount = 0;
    // private constructor
    private Printer() {
        instanceCount++;
        System.out.println("A total of " + instanceCount + " instances created so far.");
    }

    // dictionary provided to keep the records for the get() method
    private static HashMap<Subsystem, Printer> instances = new HashMap<>();

    /**
     * this methods associates a printer with a subsystem
     * i.e. a subsystem is provided and a singleton for that system
     * is generated
     */
    public static Printer get(Subsystem subsystem) {

        // check whether a subsystem for this printer exists
        if (instances.containsKey(subsystem)) {
            return instances.get(subsystem);
        }
        // if not create one by using lazy loading
        Printer instance = new Printer();
        instances.put(subsystem, instance);
        return instance;
    }
}
