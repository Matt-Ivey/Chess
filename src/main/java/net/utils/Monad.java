package net.utils;

import java.util.function.Function;

public interface Monad<T, M extends Monad<?,?>> extends Functor<T> {

    M flatMap(Function<T, M> fun);
}
