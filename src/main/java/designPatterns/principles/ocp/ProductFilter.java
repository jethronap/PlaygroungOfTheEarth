package designPatterns.principles.ocp;

import java.util.List;
import java.util.stream.Stream;

/**
 * Open Closed Principle, is somehow violated here
 */
public class ProductFilter {

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor (List<Product> products, Size size, Color color) {
        return products.stream().filter(p -> p.size == size && p.color == color);
    }

    /**
     * as criteria grow, the methods grow.
     */
}
