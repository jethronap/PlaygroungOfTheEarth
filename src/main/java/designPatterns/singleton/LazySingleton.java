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

    // for thread safety we can add synchronized
    // to the signature of getInstance.
    // `has performance issues`.
//    public static LazySingleton getInstance() {
//        // we check if the object is created
//        // if not create it here
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    /**
     * double-checked locking
     * thread-safe way to create a singleton
     * OUTDATED (textbook iml)
     */
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
