package designPatterns.builder.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder Coding Exercise
 * You are asked to implement the Builder design pattern for rendering simple chunks of code.
 *
 * Sample use of the builder you are asked to create:
 *
 * CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
 * System.out.println(cb);
 * The expected output of the above code is:
 *
 * public class Person
 * {
 *   public String name;
 *   public int age;
 * }
 * Please observe the same placement of curly braces and use two-space indentation.
 */
public class Class {

    public String name;
    public List<Field> fields = new ArrayList<>();

    public Class() {
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();
        sb.append("public class " + name).append(nl)
                .append("{").append(nl);
        for (Field f : fields)
            sb.append("  " +  f).append(nl);
        sb.append("}").append(nl);
        return sb.toString();
    }
}
