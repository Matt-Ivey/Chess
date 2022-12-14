package net.utils;

import java.util.function.Function;

public interface Functor<T> {

    <V> Functor<V> map(Function<T, V> fun);
}
