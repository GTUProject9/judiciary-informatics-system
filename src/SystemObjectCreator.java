
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import enums.LawsuitTypes;
import enums.SystemObjectTypes;

/**
 * To generate random SystemObjects
 */
public final class SystemObjectCreator {

    private SystemObjectCreator() {}

    private static final int NUMBER_OF_CITIZENS = 1000;
    private static final int NUMBER_OF_LAWYER = 100;
    private static final int NUMBER_OF_LAWOFFICE_OWNER = 10;
    private static final int NUMBER_OF_JUDGE = 10;
    private static final int NUMBER_OF_GOVERNMENT_OFFICIAL = 10;
    private static final int NUMBER_OF_LAWSUITS = 50;
    // private static final int NUMBER_OF_USERS = 1000;

    private static final String PASSWORD = "1234";
    private static final String PHONE = "+90-262-123-4567";
    private static final String EMAIL_DOMAIN = "@dummy.com";
    private static final String LAWOFFICE_NAME = "Dummy Law Office";
    private static final String caseFile = "Dummy Case File";

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
    public static void createCitizens(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.CITIZEN.getSystemObjectCode();
        int id = createInitialId(userCode);
        setFirstNamesAndLastNames();
        
        for (int i = 0; i < NUMBER_OF_CITIZENS; i++)
        {
            String firstName = firstNames.get(getRandomNumber() % firstNames.size());
            String lastName = lastNames.get(getRandomNumber() % lastNames.size());
            String email = firstName + lastName + id + EMAIL_DOMAIN;

            systemClassObject.registerSystemObject(new Citizen(
                id, PASSWORD, firstName, lastName, email, PHONE
            ));
            id++;
        }
    }

    /**
     * It creates a bunch of Government Officials
     * 
     * @param systemClassObject The system class object that will be used to register the system
     * objects.
     */
    public static void createGovernmentOfficials(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.GOVERNMENT_OFFICIAL.getSystemObjectCode();
        int id = createInitialId(userCode);
        for (int i = 0; i < NUMBER_OF_GOVERNMENT_OFFICIAL; i++)
        {
            String firstName = firstNames.get(getRandomNumber() % firstNames.size());
            String lastName = lastNames.get(getRandomNumber() % lastNames.size());
            String email = firstName + lastName + id + EMAIL_DOMAIN;

            systemClassObject.registerSystemObject(new GovernmentOfficial(
                id, PASSWORD, firstName, lastName, email, PHONE
            ));
            id++;
        }
    }

    /**
     * It creates a bunch of lawyers.
     * 
     * @param systemClassObject The system class object that we created in the main method.
     */
    public static void createLawyers(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.LAWYER.getSystemObjectCode();
        int id = createInitialId(userCode);
        for (int i = 0; i < NUMBER_OF_LAWYER; i++)
        {
            String firstName = firstNames.get(getRandomNumber()  % firstNames.size());
            String lastName = lastNames.get(getRandomNumber() % lastNames.size());
            String email = firstName + lastName + id + EMAIL_DOMAIN;
            boolean stateAttorney = getRandomNumber() % 2 == 0;
            boolean acceptsLawsuits = getRandomNumber() % 2 == 0;

            systemClassObject.registerSystemObject(new Lawyer(id, PASSWORD, firstName, lastName, email, PHONE, stateAttorney, acceptsLawsuits));
            id++;
        }
    }

    /**
     * It creates a bunch of judges and adds them to the system
     * 
     * @param systemClassObject The system class object that is used to register the system objects.
     */
    public static void createJudges(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.JUDGE.getSystemObjectCode();
        int id = createInitialId(userCode);
        for (int i = 0; i < NUMBER_OF_JUDGE; i++)
        {
            String firstName = firstNames.get(getRandomNumber() % firstNames.size());
            String lastName = lastNames.get(getRandomNumber() % lastNames.size());
            String email = firstName + lastName + id + EMAIL_DOMAIN;

            systemClassObject.registerSystemObject(new Judge(
                id, PASSWORD, firstName, lastName, email, PHONE
            ));
            id++;
        }
    }

    /**
     * It creates a bunch of LawOfficeOwner objects and registers them with the system.
     * 
     * @param systemClassObject The system class object that is used to register the system objects.
     */
    public static void createLawOfficeOwners(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.LAWOFFICE_OWNER.getSystemObjectCode();
        int id = createInitialId(userCode);
        setFirstNamesAndLastNames();
        for (int i = 0; i < NUMBER_OF_LAWOFFICE_OWNER; i++)
        {
            String firstName = firstNames.get(getRandomNumber() % firstNames.size());
            String lastName = lastNames.get(getRandomNumber() % lastNames.size());
            String email = firstName + lastName + id + EMAIL_DOMAIN;

            systemClassObject.registerSystemObject(new LawOfficeOwner(
                id, PASSWORD, firstName, lastName, email, PHONE, LAWOFFICE_NAME));
            id++;
        }
    }

    /**
     * It creates a random number of lawsuits, with random dates, and random lawsuit types
     * 
     * @param systemClassObject The SystemClass object that is used to register the created objects.
     */
    public static void createLawsuits(SystemClass systemClassObject)
    {
        int lawsuitCode = SystemObjectTypes.LAWSUIT.getSystemObjectCode();
        int id = createInitialId(lawsuitCode);

        try 
        {

            for (int i = 0; i < NUMBER_OF_LAWSUITS; i++)
            {
                Date date = randomDate();
                // Create random LawsuitTypes
                LawsuitTypes lawsuitType = LawsuitTypes.values()[getRandomNumber() % LawsuitTypes.values().length];

                systemClassObject.registerSystemObject(new Lawsuit(id, date, -1, -1, -1, lawsuitType, caseFile)); 
                id++;
            }        
        } 
        catch (Exception e) {
            // TODO
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
    public static int createInitialId(int userCode) {
        return (int) (userCode * Math.pow(10, AbstractSystemObject.ID_LENGTH - 1)) + 1;
    }

    /**
     * Reads the first and last names from the text files into the ArrayLists
     */
    private static void setFirstNamesAndLastNames()
    {
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
        
        readFileIntoAList(firstNames, "text-first-names.txt");
        readFileIntoAList(lastNames, "text-last-names.txt");
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

    /**
     * It returns a random number between 0 and 1000
     * 
     * @return A random number between 0 and 1000.
     */
    private static int getRandomNumber() {
        return (int) (Math.random() * (1000 + 1));
    }


    /**
     * It generates a random date between 2021-01-01 and 2030-12-31
     * 
     * @return A random date between 2021-01-01 and 2030-12-31
     */
    public static Date randomDate() 
    {
        try {
           
            Date startInclusive = new SimpleDateFormat("yyyyMMdd").parse("20220101");
            Date endExclusive = new SimpleDateFormat("yyyyMMdd").parse("20301231");

            long startMillis = startInclusive.getTime();
            long endMillis = endExclusive.getTime();
            long randomMillisSinceEpoch = ThreadLocalRandom
            .current()
            .nextLong(startMillis, endMillis);
        
            return new Date(randomMillisSinceEpoch);
         
        } catch (Exception e) {
            //TODO: handle exception
        }

        return null;
    }
}
