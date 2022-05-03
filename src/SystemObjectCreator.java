import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import enums.SystemObjectTypes;

public class SystemObjectCreator {

    private SystemObjectCreator() {}

    private static final int NUMBER_OF_CITIZENS = 1000;
    // private static final int NUMBER_OF_USERS = 1000;

    private static final String PASSWORD = "1234";
    private static final String PHONE = "+90-262-123-4567";
    private static final String EMAIL_DOMAIN = "@dummy.com";
    
    public static void createRandomCitizens(SystemClass systemClassObject)
    {
        int userCode = SystemObjectTypes.CITIZEN.getSystemObjectCode();
        int id = createInitialId(userCode);
        Random rand = new Random();
        
        for (int i = 0; i < NUMBER_OF_CITIZENS; i++)
        {
            int randIndex = rand.nextInt(1000);
            String firstName = systemClassObject.getFirstNames().get(randIndex);
            String lastName = systemClassObject.getLastNames().get(randIndex);
            String email = firstName + lastName + id + EMAIL_DOMAIN;

            systemClassObject.registerSystemObject(new Citizen(
                id, PASSWORD, firstName, lastName, email, PHONE
            ));
            id++;
        }
    }

    private static int createInitialId(int userCode) {
        return (int) (userCode * Math.pow(10, AbstractSystemObject.ID_LENGTH - 1)) + 1;
    }
}
