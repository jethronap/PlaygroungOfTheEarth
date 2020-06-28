package designPatterns.builder;

public class HtmlBuilder {

    // the root element, it's stored because
    // the builder might need to be reset but the root
    // element must be preserved.
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    /**
     * allowing fluent interface,
     * when return type is HtmlBuilder
     */
    public HtmlBuilder addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    /**
     * the constructions takes place here
     * since a string builder is needed
     * the following method is overridden
     */
    @Override
    public String toString() {
        // in order to expose toStringImpl
        return root.toString();
    }
}
