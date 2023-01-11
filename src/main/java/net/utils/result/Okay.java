package net.utils.result;

import com.google.common.base.Objects;

import java.util.function.Function;

public class Okay<T> implements Result<T> {

    private final T value;

    private Okay(final T value) {
        this.value = value;
    }

    public static <T> Okay<T> of(final T input) {
        return new Okay<>(input);
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public boolean isOkay() {
        return true;
    }

    @Override
    public String error() {
        throw new ResultException("Error accessed on an okay result");
    }

    @Override
    public <U> Result<U> flatMap(final Function<T, Result<U>> fun) {
        return fun.apply(value);
    }

    @Override
    public <U> Okay<U> map(final Function<T, U> fun) {
        return Okay.of(fun.apply(value));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Okay<?> okay = (Okay<?>) o;
        return Objects.equal(value, okay.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Okay{" +
                value +
                '}';
    }
}

