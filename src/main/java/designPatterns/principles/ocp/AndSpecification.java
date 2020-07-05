package designPatterns.principles.ocp;

/**
 * Combinator class for two specifications.
 */
public class AndSpecification<T> implements Specification<T> {

    private Specification<T> first;
    private Specification<T> second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isCriteriaSatisfied(T item) {
        return first.isCriteriaSatisfied(item) && second.isCriteriaSatisfied(item);
    }
}
