package designPatterns.builder.exercise;

public class Field {

    public String name, type;

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("public %s %s;", type, name);
    }
}
