package designPatterns.builder.exercise;

public class CodeBuilder {

    private Class theClass = new Class();

    public CodeBuilder(String rootName) {
        theClass.name = rootName;
    }

    public CodeBuilder addField(String name, String type) {
        theClass.fields.add(new Field(name, type));
        return this;
    }

    @Override
    public String toString() {
        return theClass.toString();
    }
}
