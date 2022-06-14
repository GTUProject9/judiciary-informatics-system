import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Static utility class
 */
public final class Utils {
    private Utils(){}

    public static final String ROOT_DIR = getRootDirectoryPath();

    private static final Scanner scanner = new Scanner(System.in);
    public static final String INVALID_INPUT = "Invalid input!";
    public static final String INVALID_CHOICE = "Invalid choice!";

    /**
     * It reads a line from the console, converts it to an integer, and returns the integer
     * 
     * @return The integer value of the string that was entered.
     */
    public static int readIntegerInput() throws NumberFormatException {
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    /**
     * It reads a line of input from the user and returns it as a string
     * 
     * @return The next line of input from the user.
     */
    public static String readStringInput() {
        return scanner.nextLine();
    }

    /**
     * It reads a line from the console, and returns a boolean value based on the contents of that line
     * 
     * @return A boolean value.
     */
    public static boolean readBooleanInput() {
        String line = scanner.nextLine();
        return Boolean.parseBoolean(line);
    }

    /**
     * It takes a variable number of arguments, and returns a string that is the concatenation of the
     * root directory and the arguments
     * 
     * @return A string representation of the path.
     */
    public static String getPath(String... pathRelativeToRootDir) {
        return Paths.get(ROOT_DIR, pathRelativeToRootDir).toString();
    }

    /**
     * If the current working directory ends with "bin", then the root directory is two levels up,
     * otherwise it's the current working directory
     * 
     * @return The path to the root directory of the project.
     */
    private static String getRootDirectoryPath() {
        Path workingDirectoryPath = Paths.get(System.getProperty("user.dir"));
        if (workingDirectoryPath.endsWith("bin")) {
            return Paths.get(workingDirectoryPath.toString(), "..", "..").toString();
        }
        return workingDirectoryPath.toString();
    }
}
