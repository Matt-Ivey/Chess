package net.utils;

import net.utils.Err;
import net.utils.Okay;
import net.utils.Result;
import net.utils.ResultException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ResultTest {

    @Test
    public void okayResult() {
        final Object input = new Object();
        final Object output = Okay.of(input).value();
        assertThat(output).isEqualTo(input);
    }

    @Test
    public void isOkay() {
        final Object input = new Object();
        final Result<Object> result = Okay.of(input);
        assertThat(result.isOkay()).isTrue();
    }

    @Test
    public void errorResult() {
        final String msg = "Something bad happened!";
        final Result<Object> errorResult = Err.of(msg);
        assertThat(errorResult.error()).isEqualTo(msg);
    }

    @Test
    public void isNotOkay() {
        final Result<Object> error = Err.of("Oh no!");
        assertThat(error.isOkay()).isFalse();
    }

    @Test
    public void errorGetValueThrowsResultException() {
        assertThatExceptionOfType(ResultException.class)
                .isThrownBy(() -> Err.of("Some error").value())
                .withMessage("Value accessed on an error result");
    }


    @Test
    public void okayGetErrorThrowsResultException() {
        assertThatExceptionOfType(ResultException.class)
                .isThrownBy(() -> Okay.of(new Object()).error())
                .withMessage("Error accessed on an okay result");
    }

    @Test
    public void isFunctor() {
        final Object anObject = new Object();
        final Object anotherObject = new Object();

        final Result<Object> mappedResult = Okay.of(anObject).map(x -> anotherObject);
        assertThat(mappedResult.value()).isEqualTo(anotherObject);
    }

    @Test
    public void errIsUnchangedByMap() {
        final Result<Object> err = Err.of("oh no!");
        assertThat(err.map(x -> new Object())).isEqualTo(err);
    }

    @Test
    public void isMonad() {
        final Result<Object> result = Okay.of(new Object());
        final Result<Object> differentResult = Okay.of(new Object());

        assertThat(result.flatMap(x -> differentResult)).isEqualTo(differentResult);
    }

    @Test
    public void errIsUnchangedByFlatMap() {
        final Result<Object> err = Err.of("oh no!");
        assertThat(err.flatMap(x -> Okay.of(new Object()))).isEqualTo(err);
    }
}
