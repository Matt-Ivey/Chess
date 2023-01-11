package net.utils;

import javax.management.Query;
import java.util.function.Function;

public class Functions {


    public static <U, T> In<U, T> using(final U input) {
        return fun -> fun.apply(input);
    }

    public interface In<U, T> {
        T in(final Function<U, T> fun);
    }
}
