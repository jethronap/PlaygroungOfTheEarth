package designPatterns.singleton;

/**
 * instantiation only through getInstance().
 * only when it's required to get an instance
 * of the obj/
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing lazy singleton");
    }

    public static LazySingleton getInstance() {
        // we check if the object is created
        // if not create it here
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
