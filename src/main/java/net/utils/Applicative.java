package net.utils;

import java.util.function.Function;

public interface Applicative<T, A extends Applicative<?, A>> extends Functor<T> {

    <U, F extends Function<T, U>, V extends Applicative<F, A>> Applicative<U, A> applyTo(V aFun);
}
