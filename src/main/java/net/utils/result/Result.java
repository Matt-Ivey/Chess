package net.utils.result;


import net.utils.Monad;

public interface Result<T> extends Monad<T, Result<?>> {

    T value();

    boolean isOkay();

    String error();
}
