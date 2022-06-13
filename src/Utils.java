import java.util.Scanner;

/**
 * It's a utility class that contains methods for reading input from the console
 */
public final class Utils {
    
    private Utils(){}

    public static final String INVALID_INPUT = "Invalid input!";
    public static final String INVALID_CHOICE = "Invalid choice!";

    
    private static Scanner scanner = new Scanner(System.in);

    public static int readIntegerInput() throws NumberFormatException {
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }
    public static String readStringInput() {
        return scanner.nextLine();
    }

    // Read boolean expression.
    public static boolean readBooleanInput() {
        String line = scanner.nextLine();
        return Boolean.parseBoolean(line);
    }
}
