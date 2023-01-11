package net.utils.result;


import net.utils.Functor;
import net.utils.Monad;

import java.util.function.Function;

public interface Result<T> extends Functor<T> {

    T value();

    boolean isOkay();

    String error();

    <U> Result<U> flatMap(final Function<T, Result<U>> fun);
}
