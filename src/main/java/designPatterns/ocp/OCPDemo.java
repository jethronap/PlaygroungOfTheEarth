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

    }
}
