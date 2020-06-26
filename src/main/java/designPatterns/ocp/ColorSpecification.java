package designPatterns.ocp;

/**
 * this is a specification class
 */
public class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isCriteriaSatisfied(Product product) {
        return product.color == color;
    }
}
