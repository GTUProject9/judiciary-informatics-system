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

    public static int readIntegerInput() throws NumberFormatException {
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }

    public static String readStringInput() {
        return scanner.nextLine();
    }

    public static boolean readBooleanInput() {
        String line = scanner.nextLine();
        return Boolean.parseBoolean(line);
    }

    public static String getPath(String... pathRelativeToRootDir) {
        return Paths.get(ROOT_DIR, pathRelativeToRootDir).toString();
    }

    private static String getRootDirectoryPath() {
        Path workingDirectoryPath = Paths.get(System.getProperty("user.dir"));
        if (workingDirectoryPath.endsWith("bin")) {
            return Paths.get(workingDirectoryPath.toString(), "..", "..").toString();
        }
        return workingDirectoryPath.toString();
    }
}
