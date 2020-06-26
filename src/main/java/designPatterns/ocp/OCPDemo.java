package designPatterns.ocp;

import java.util.List;

public class OCPDemo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.MEDIUM);
        Product house = new Product("House", Color.RED, Size.HUGE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        // filter GREEN products
        System.out.println("Green Products (old):");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is green."));

        /**
         * new implementation of filtering using the Specification DesignPattern
         * we can just use inheritance and interface implementation
         * we don't jump into already existing classes.
         */
        ProductFilterNew productFilterNew = new ProductFilterNew();
        System.out.println("Green Products (new):");
        productFilterNew.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name + " is green."));

        /**
         * another implementation using the combinator class
         */
        System.out.println("Huge Red items:");
        productFilterNew.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.RED),
                new SizeSpecification(Size.HUGE)
        )).forEach(p -> System.out.println(" - " + p.name + " is huge and red."));
    }
}
