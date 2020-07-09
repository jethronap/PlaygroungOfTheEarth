package designPatterns.singleton;

import java.io.File;
import java.io.IOException;

/**
 * a modification for a singleton
 * where the constructor throws an
 * exception
 */
public class StaticBlockSingleton {

    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    // no longer final
    private static StaticBlockSingleton instance;

    /**
     * we add a static block
     * sth similar to a static constructor
     */
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.err.println("failed to create Singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
