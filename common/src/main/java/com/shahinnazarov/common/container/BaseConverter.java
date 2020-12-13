package com.shahinnazarov.common.container;

import java.util.Optional;

public interface BaseConverter<F, T> {
    T convert(F item);

    default boolean validate(F item) {
        return true;
    }

    default T getZeroVal() {
        return null;
    }

    default Optional<T> convertSafely(F item) {
        if (validate(item)) {
            return Optional.ofNullable(convert(item));
        }
        return Optional.empty();
    }

    default T convertSafelyDirect(F item) {
        if (validate(item)) {
            return convert(item);
        }
        return getZeroVal();
    }
}
