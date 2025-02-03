import java.util.Objects;
import java.util.Scanner;

/**
 * A simple class with functions to validate passwords
 */
public class PasswordValidation {
    private static final char[] specialCharacters = {'$', '%', '&', '!', '?', '*', '+', '~'};

    /**
     *
     * @param args - not used in function
     */
    public static void main(String[] args) {
        String passwordToBeChecked = "";
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Welcome to George's password validation!");

        while (! passwordToBeChecked.equals("q")) {
            System.out.println();
            System.out.println("Please enter your password, which has to be checked or q to quit: ");
            passwordToBeChecked = inputScanner.nextLine();

            if (Objects.equals(passwordToBeChecked, "q"))
                continue;

            System.out.println();
            System.out.println("Your password meets (or not meets) the following criteria:");
            System.out.println("is at least 8 chars long: " + passwordContainsAtleast8Chars(passwordToBeChecked));
            System.out.println("contains at least 1 digit: " + passwordContainsDigits(passwordToBeChecked));
            System.out.println("contains both uppercase and lowercase: " + passwordContainsBothUppercaseAndLowercase(passwordToBeChecked));
            System.out.println("contains a commonly used password: " + passwordContainsCommonlyUsedPassword(passwordToBeChecked));
            System.out.println("contains a special character: " + passWordContainsSpacialCharacter(passwordToBeChecked));
        }

    }

    public static boolean passwordContainsAtleast8Chars(String password) {
        return password.length() >= 8;
    }

    public static boolean passwordContainsDigits(String password) {
        return password.matches(".*[0-9]+.*");
    }

    public static boolean passwordContainsBothUppercaseAndLowercase(String password) {
        return password.matches(".*[a-z]+.*") & password.matches(".*[A-Z]+.*");
    }

    public static boolean passwordContainsCommonlyUsedPassword(String password) {
        String[] commonlyUsedPasswords = {"Password1", "Aa345678", "test1234", "password"};

        for (String commonlyUsedPassword : commonlyUsedPasswords) {
            if (password.matches(".*" + commonlyUsedPassword + ".*"))
                return true;
        }

        return false;
    }

    public static boolean passWordContainsSpacialCharacter(String password) {
        StringBuilder regExBuilder = new StringBuilder(".*[");
        for (char specialCharacter : specialCharacters)
            regExBuilder.append(specialCharacter);
        regExBuilder.append("]+.*");

        return password.matches(regExBuilder.toString());
    }
}
