package designPatterns.ocp;

import java.util.List;
import java.util.stream.Stream;

/**
 * This interface satisfies OCP: Open for extension, closed for modification
 * It is open for extension
 * @param <T>
 */
public interface Filter<T> {

    // we could have used List
    Stream<T> filter(List<T> items, Specification<T> specification);
}
