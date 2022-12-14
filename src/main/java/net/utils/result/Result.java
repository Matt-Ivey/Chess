package net.utils;


public interface Result<T> extends Monad<T, Result<?>> {

    T value();

    boolean isOkay();

    String error();
}
