package designPatterns.singleton;

import java.io.Serializable;

/**
 * enforces that only one instance
 * of this class exists
 */
public class BasicSingleton implements Serializable {

    private int value = 0;

    //make a private constructor
    private BasicSingleton() {}

    // for a client to access the constructor
    private static final BasicSingleton INSTANCE = new BasicSingleton();

    //expose the Singleton
    public static BasicSingleton getINSTANCE() {
        return INSTANCE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
