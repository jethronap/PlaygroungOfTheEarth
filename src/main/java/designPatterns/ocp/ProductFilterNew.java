package designPatterns.ocp;

import java.util.List;
import java.util.stream.Stream;

public class ProductFilterNew implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(p -> specification.isCriteriaSatisfied(p));
    }
}
