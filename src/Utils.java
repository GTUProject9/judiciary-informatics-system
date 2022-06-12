import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

    public static int readIntegerInput() throws NumberFormatException {
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }
    public static String readStringInput() {
        return scanner.nextLine();
    }

    // Read boolean input
    public static boolean readBooleanInput() {
        String line = scanner.nextLine();
        return Boolean.parseBoolean(line);
    }
}
