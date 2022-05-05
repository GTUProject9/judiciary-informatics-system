import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import enums.SystemObjectTypes;

/**
 * To generate random SystemObjects
 */
public final class SystemObjectCreator {

    private SystemObjectCreator() {}

    private static final int NUMBER_OF_CITIZENS = 1000;
    // private static final int NUMBER_OF_USERS = 1000;

    private static final String PASSWORD = "1234";
    private static final String PHONE = "+90-262-123-4567";
    private static final String EMAIL_DOMAIN = "@dummy.com";

    // First and last names
    private static ArrayList<String> firstNames;
    private static ArrayList<String> lastNames;

    // Birden fazla defa dosyayi okumasini engeller.
    private static boolean isNamesSetted;


    // ------- Other static creators can be down here -------

    /**
     * It creates a random number citizens, and registers them in the system
     * 
     * @param systemClassObject The SystemClass object that will be used to register the new Citizen
     * objects.
     */
    public static void createRandomCitizens(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.CITIZEN.getSystemObjectCode();
        int id = createInitialId(userCode);
        setFirstNamesAndLastNames();
        Random rand = new Random();
        
        for (int i = 0; i < NUMBER_OF_CITIZENS; i++)
        {
            int randIndex = rand.nextInt(firstNames.size()) - 1;
            String firstName = firstNames.get(randIndex);
            String lastName = lastNames.get(randIndex);
            String email = firstName + lastName + id + EMAIL_DOMAIN;

            systemClassObject.registerSystemObject(new Citizen(
                id, PASSWORD, firstName, lastName, email, PHONE
            ));
            id++;
        }
    }









    
    // ------- helpers -------
    /**
     * It takes a user code and returns the first ID that can be assigned to a system object
     * 
     * @param userCode The user code is a unique number that identifies the user.
     * @return The return value is the userCode multiplied by 10 to the power of the ID_LENGTH minus 1,
     * plus 1.
     */
    private static int createInitialId(int userCode) {
        return (int) (userCode * Math.pow(10, AbstractSystemObject.ID_LENGTH - 1)) + 1;
    }

    /**
     * Reads the first and last names from the text files into the ArrayLists
     */
    private static void setFirstNamesAndLastNames()
    {
        if (!isNamesSetted)
        {
            firstNames = new ArrayList<>();
            lastNames = new ArrayList<>();
            
            readFileIntoAList(firstNames, "text-first-names.txt");
            readFileIntoAList(lastNames, "text-last-names.txt");
            isNamesSetted = true;
        }
    }

    /**
     * Reads the contents of a file into a list
     * 
     * @param theList the list that you want to add the words to
     * @param filePath The path to the file you want to read.
     */
    private static void readFileIntoAList(List<String> theList, String filePath) {
        
        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext()){
                theList.add(s.next());
            }
            s.close();
        } 
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
