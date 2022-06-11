import java.util.Scanner;

public class Utils {
    public static int readIntegerInput() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return Integer.parseInt(line);
    }
    public static String readStringInput() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
