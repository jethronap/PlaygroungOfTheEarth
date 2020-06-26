package designPatterns.ocp;

/**
 * This interface satisfies OCP: Open for extension, closed for modification
 * It is open for extension
 * @param <T>
 */
public interface Specification<T> {

    boolean isCriteriaSatisfied(T item);
}
