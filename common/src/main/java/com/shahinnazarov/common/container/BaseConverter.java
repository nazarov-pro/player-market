package com.shahinnazarov.common.container;

import java.util.Optional;

/**
 * Standard Converter for labeling converters
 * @param <F> type of received object (from)
 * @param <T> type of produced object (to)
 */
@FunctionalInterface
public interface BaseConverter<F, T> {
    /**
     * Main method converting
     * @param item received item
     * @return raw produced element
     */
    T convert(F item);

    /**
     * Validating from element
     * @param item received element
     * @return bool value true if validated
     */
    default boolean validate(F item) {
        return true;
    }

    /**
     * Default value (if from element is not validated
     * @return default produced object
     */
    default T getZeroVal() {
        return null;
    }

    /**
     * Safely converting as {@link Optional}
     * @param item from element
     * @return to element wrapped with {@link Optional}
     */
    default Optional<T> convertSafely(F item) {
        if (validate(item)) {
            return Optional.ofNullable(convert(item));
        }
        return Optional.ofNullable(getZeroVal());
    }
}
