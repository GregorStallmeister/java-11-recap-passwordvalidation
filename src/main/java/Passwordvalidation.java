/**
 * A simple class with functions to validate passwords
 */
public class Passwordvalidation {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to George's password validation!");
    }

    public static boolean passwordContainsAtleast8Chars(String password) {
        return password.length() >= 8;
    }
}
