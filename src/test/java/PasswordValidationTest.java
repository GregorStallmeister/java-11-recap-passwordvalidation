import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void passwordContainsDigitsExpectedTrueWhenDigitIsGiven() {
        // given
        String password = "1";
        boolean expected = true;

        // when
        boolean actual = PasswordValidation.passwordContainsDigits(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsDigitsExpectedFalseWhenNoDigitIsGiven() {
        // given
        String password = "d";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsDigits(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsDigitsExpectedTrueWhenCombinationsOfCharsAndDigitsAreGiven() {
        // given
        String[] passwords = {"test1234", "1234test", "te1234st"};
        boolean expected = true;
        boolean actual = true;

        // when
        for (String password : passwords) {
            actual = actual & PasswordValidation.passwordContainsDigits(password);
        }

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsBothUppercaseAndLowercaseExpectedTrueWhenBothAreGiven() {
        // given
        String password = "Cc";
        boolean expected = true;

        // when
        boolean actual = PasswordValidation.passwordContainsBothUppercaseAndLowercase(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsBothUppercaseAndLowercaseExpectedFalseWhenUppercaseOnlyIsGiven() {
        // given
        String password = "C";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsBothUppercaseAndLowercase(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsBothUppercaseAndLowercaseExpectedFalseWhenLowercaseOnlyIsGiven() {
        // given
        String password = "c";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsBothUppercaseAndLowercase(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsBothUppercaseAndLowercaseExpectedFalseWhenDigitsOnlyAreGiven() {
        // given
        String password = "42";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsBothUppercaseAndLowercase(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsBothUppercaseAndLowercaseExpectedTrueWhenSeveralCombinationsAreGiven() {
        // given
        String[] passwords = {"tEst1234", "12A34test", "te1234St"};
        boolean expected = true;
        boolean actual = true;

        // when
        for (String password : passwords) {
            actual = actual & PasswordValidation.passwordContainsBothUppercaseAndLowercase(password);
        }

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsCommonlyUsedPasswordExpectedTrueWhenSomeIsGiven() {
        // given
        String password = "myPassword1IsSafe";
        boolean expected = true;

        // when
        boolean actual = PasswordValidation.passwordContainsCommonlyUsedPassword(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsCommonlyUsedPasswordExpectedFalseWhenNoneIsGiven() {
        // given
        String password = "myPasswordIsNowSafe";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsCommonlyUsedPassword(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsSpacialCharacterExpectedTrueWhenSomeIsGiven() {
        // given
        String password = "$";
        boolean expected = true;

        // when
        boolean actual = PasswordValidation.passwordContainsSpacialCharacter(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void passwordContainsSpacialCharacterExpectedFalseWhenNoneIsGiven() {
        // given
        String password = "C";
        boolean expected = false;

        // when
        boolean actual = PasswordValidation.passwordContainsSpacialCharacter(password);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void createSecurePasswordExpectedPasswordWhenCalled() {
        // given
        String password; // is not initialized, therefore it is null!

        // when
        password = PasswordValidation.createSecurePassword();

        // then
        assertNotNull(password);
        assertNotEquals("", password);
    }

    @Test
    public void createSecurePasswordExpectCreatedPasswordMeetsAllCriteria() {
        // given
        String password; // to be initialized with random password
        boolean actual = true; // to be &-ded with test results, whether it meets the criteria for secure passwords

        // when
        for (int i = 0; i < 100; i++) {
            password = PasswordValidation.createSecurePassword();
            actual = actual & PasswordValidation.passwordContainsAtleast8Chars(password);
            actual = actual & PasswordValidation.passwordContainsDigits(password);
            actual = actual & PasswordValidation.passwordContainsBothUppercaseAndLowercase(password);
            actual = actual & (!PasswordValidation.passwordContainsCommonlyUsedPassword(password));
            actual = actual & PasswordValidation.passwordContainsSpacialCharacter(password);
        }

        // then
        assertTrue(actual);
    }
}

