package designPatterns.singleton;

/**
 * avoids synchronization
 * and is thread safe
 */
public class InnerStaticSingleton {

    private InnerStaticSingleton() {}

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    // expose inner class
    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}
