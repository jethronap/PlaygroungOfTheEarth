package designPatterns.builder;

import java.util.ArrayList;
import java.util.Collections;

public class HtmlElement {

    public String name, text;
    // this is a recursive structure
    public ArrayList<HtmlElement> elements = new ArrayList<>();

    // utility helpers
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        // we want to get the indentation correct thus the use of Collections
        String i = String.join("", Collections.nCopies(indent * indent, " "));
        // this is were tags are appended
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        // if there is text it's appended
        if (text != null && !text.isEmpty()) {
            // with a new line
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                    .append(text)
                    .append(newLine);
        }
        // check recursively for the elements inside the tags
        // and their children and they get printed as well
        for (HtmlElement e: elements) {
            sb.append(e.toStringImpl(indent + 1));
        }
        // the closing tags are appended
        sb.append(String.format("%s</%s>", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}
