package net.utils;

import org.junit.jupiter.api.Test;

import static net.utils.Functions.using;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    public void usingReturnsTheFunctionResult() {
        final Object input = new Object();
        final Object expected = new Object();

        final Object actual = using(input).in(x -> expected);

        assertThat(actual).isEqualTo(expected);
    }

}