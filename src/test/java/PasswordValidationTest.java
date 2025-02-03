import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordValidationTest {

    @Test
    public void passwordContainsAtleast8CharsExpectedTrueWhen8CharsGiven() {
        // given
        String password = "1abc5678";
        boolean expected = true;

        // when
        boolean actual = PasswordValidation.passwordContainsAtleast8Chars(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsAtleast8CharsExpectedTrueWhen10CharsGiven() {
        // given
        String password = "12345678ab";
        boolean expected = true;

        // when
        boolean actual = PasswordValidation.passwordContainsAtleast8Chars(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsAtleast8CharsExpectedFalseWhen6CharsGiven() {
        // given
        String password = "12ab56";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsAtleast8Chars(password);

        // then
        assertEquals(expected, actual);
    }
}

