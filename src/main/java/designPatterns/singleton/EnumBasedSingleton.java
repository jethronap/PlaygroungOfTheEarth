package designPatterns.singleton;

public enum EnumBasedSingleton {
    // it has a single field
    INSTANCE;

    private int value;

    // the constructor is private by default
    EnumBasedSingleton() {
        value = 42;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
