package net.utils.result;

import com.google.common.base.Objects;

import java.util.function.Function;

public class Err<T> implements Result<T> {

    private final String error;

    private Err(final String error) {
        this.error = error;
    }

    public static <T> Err<T> of(final String msg) {
        return new Err<>(msg);
    }

    @Override
    public T value() {
        throw new ResultException("Value accessed on an error result");
    }

    @Override
    public boolean isOkay() {
        return false;
    }

    @Override
    public String error() {
        return error;
    }


    @Override
    public <U> Err<U> map(final Function<T, U> ignoredFun) {
        return Err.of(error);
    }

    @Override
    public <U> Result<U> flatMap(final Function<T, Result<U>> fun) {
        return Err.of(error);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Err<?> err = (Err<?>) o;
        return Objects.equal(error, err.error);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(error);
    }

    @Override
    public String toString() {
        return "Err{" +
                error +
                '}';
    }
}
