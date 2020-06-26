package designPatterns.ocp;

public class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isCriteriaSatisfied(Product product) {
        return product.size == size;
    }
}
