import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * A simple class with functions to validate passwords
 * It tests several criteria for secure passwords.
 * The user can input passwords to test them.
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
            System.out.println("Please enter your password, which has to be checked or or c to create a password or q to quit: ");
            passwordToBeChecked = inputScanner.nextLine();

            if (Objects.equals(passwordToBeChecked, "q"))
                continue;

            if(Objects.equals(passwordToBeChecked, "c"))
            {
                System.out.println(createSecurePassword());
                continue;
            }

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

    public static String createSecurePassword() {
        Random charCodeGenerator = new Random();
        char[] passwordChars = new char[12];

        for (int i = 0; i < 4; i++)
        {
            int charCode = charCodeGenerator.nextInt(97, 123);
            passwordChars[i] = (char)charCode;
        }

        for (int i = 3; i < 8; i++)
        {
            int charCode = charCodeGenerator.nextInt(65, 91);
            passwordChars[i] = (char)charCode;
        }

        for (int i = 8; i < 12; i++)
        {
            int charCode = charCodeGenerator.nextInt(0, specialCharacters.length -  1);
            passwordChars[i] = specialCharacters[charCode];
        }

        // Now randomize the chars by Fisher-Yates Shuffle
        for (int i = passwordChars.length - 1; i > 0; i--)
        {
            // generate a random index within the not already "worked" area
            int j = charCodeGenerator.nextInt(i + 1);

            // swap passwordChars[i] with the element at random index
            char tempChar = passwordChars[i];
            passwordChars[i] = passwordChars[j];
            passwordChars[j] = tempChar;
        }

        StringBuilder passwordBuilder = new StringBuilder();
        for (char currentChar : passwordChars)
            passwordBuilder.append(currentChar);

        return passwordBuilder.toString();
    }
}
