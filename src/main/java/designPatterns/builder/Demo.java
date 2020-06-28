package designPatterns.builder;

public class Demo {

    public static void main(String[] args) {

        /** first implementation:
        // suppose we want to show a word in a web page
        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");

        //but what if we wanted to print an array of words
        String[] words = {"hello", "world"};
        // we could use a builder, which works piece by piece:
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>\n");
        for (String word: words) {
            stringBuilder.append(String.format("  <li>%s</li>\n", word));
        }
        stringBuilder.append("</ul>");
        System.out.println(stringBuilder);
        */

        /**
         * Implementation using HtmlBuilder
         */
        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "hello");
        builder.addChild("li", "world");
        System.out.println(builder);
    }
}
