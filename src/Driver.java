
import enums.SystemObjectTypes;

/**
 * It starts the system and then it asks the user to login
 */
public class Driver {
    /**
     * It starts the system and then it asks the user to login.
     */
    public static void main(String[] args) throws Exception 
    {
        // Start the system
        SystemClass systemClassObject = new SystemClass();
        
        // Create random system objects
        SystemObjectCreator.createCitizens(systemClassObject);
        SystemObjectCreator.createLawyers(systemClassObject);
        SystemObjectCreator.createLawOfficeOwners(systemClassObject);
        SystemObjectCreator.createGovernmentOfficials(systemClassObject);
        SystemObjectCreator.createJudges(systemClassObject);
        SystemObjectCreator.createLawsuits(systemClassObject);

        while(true)
        {
            System.out.println("\n ---------- Judiciary Informatics System ---------- ");
            System.out.println("\n1. Login as a Citizen");
            System.out.println("2. Login as a Lawyer");
            System.out.println("3. Login as a Law Office Owner");
            System.out.println("4. Login as a Judge");
            System.out.println("5. Login as a Government Official");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println(Utils.INVALID_INPUT);
                continue;
            }

            if (choice == 0)
            {
                System.out.println("Exiting...");
                break;
            }
            if (choice < 0 || choice > 5)
            {
                System.out.println(Utils.INVALID_CHOICE);
            }
            else
            {
                System.out.println("\n Enter your ID: ");
                int id = Utils.readIntegerInput();
                
                System.out.println("\n Enter your password: ");
                String password = Utils.readStringInput();

                if (choice == 1)
                {
                    Citizen citizen = (Citizen) systemClassObject.getSystemObject(id);
                    if (citizen == null)
                    {
                        System.out.println("\n Invalid ID.\n");
                    }
                    else if (!citizen.getPassword().equals(password))
                    {
                        System.out.println("\n Invalid password.\n");
                    }
                    else
                    {
                        SystemObjectTypes systemClassObjectType = SystemClass.findSystemObjectType(id);
                        System.out.println("\n Welcome, " + citizen.getFirstName() + " " + citizen.getLastName() + "!");
                        if (systemClassObjectType == SystemObjectTypes.CITIZEN)
                        {
                            citizen.menu(systemClassObject);
                        }
                        else if (systemClassObjectType == SystemObjectTypes.LAWYER)
                        {
                            ((Lawyer) citizen).citizenMenu(systemClassObject);
                        }
                        else if (systemClassObjectType == SystemObjectTypes.LAWOFFICE_OWNER)
                        {
                            ((LawOfficeOwner) citizen).citizenMenu(systemClassObject);
                        }
                        else if (systemClassObjectType == SystemObjectTypes.JUDGE)
                        {
                            ((Judge) citizen).citizenMenu(systemClassObject);
                        }
                        else if (systemClassObjectType == SystemObjectTypes.GOVERNMENT_OFFICIAL)
                        {
                            ((GovernmentOfficial) citizen).citizenMenu(systemClassObject);
                        }
                    }
                }
                else if (choice == 2)
                {
                    Lawyer lawyer;
                    try {
                        lawyer = (Lawyer) systemClassObject.getSystemObject(id);
                    } catch (Exception e) {
                        System.out.println("\n Invalid ID.\n");
                        continue;
                    }
                    if (lawyer == null)
                    {
                        System.out.println("\n Invalid ID.\n");
                    }
                    else if (!lawyer.getPassword().equals(password))
                    {
                        System.out.println("\n Invalid password.\n");
                    }
                    else
                    {
                        System.out.println("\n Welcome, " + lawyer.getFirstName() + " " + lawyer.getLastName() + "!");
                        SystemObjectTypes systemClassObjectType = SystemClass.findSystemObjectType(id);
                        if (systemClassObjectType == SystemObjectTypes.LAWYER)
                        {
                            lawyer.menu(systemClassObject);
                        }
                        else if (systemClassObjectType == SystemObjectTypes.LAWOFFICE_OWNER)
                        {
                            ((LawOfficeOwner) lawyer).lawyerMenu(systemClassObject);
                        }
                    }
                }
                else if (choice == 3)
                {
                    LawOfficeOwner lawOfficeOwner;
                    try {
                        lawOfficeOwner = (LawOfficeOwner) systemClassObject.getSystemObject(id);
                    } catch (Exception e) {
                        System.out.println("\n Invalid ID.\n");
                        continue;
                    }
                    if (lawOfficeOwner == null)
                    {
                        System.out.println("\n Invalid ID.\n");
                    }
                    else if (!lawOfficeOwner.getPassword().equals(password))
                    {
                        System.out.println("\n Invalid password.\n");
                    }
                    else
                    {
                        System.out.println("\n Welcome, " + lawOfficeOwner.getFirstName() + " " + 
                                            lawOfficeOwner.getLastName() + "!");
                        lawOfficeOwner.menu(systemClassObject);
                    }
                }
                else if (choice == 4)
                {
                    Judge judge;
                    try {
                        judge = (Judge) systemClassObject.getSystemObject(id);
                    } catch (Exception e) {
                        System.out.println("\n Invalid ID.\n");
                        continue;
                    }
                    if (judge == null)
                    {
                        System.out.println("\n Invalid ID.\n");
                    }
                    else if (!judge.getPassword().equals(password))
                    {
                        System.out.println("\n Invalid password.\n");
                    }
                    else
                    {
                        System.out.println("\n Welcome, " + judge.getFirstName() + " " + judge.getLastName() + "!");
                        judge.menu(systemClassObject);
                    }
                }
                else if (choice == 5)
                {
                    GovernmentOfficial governmentOfficial; 
                    try {
                        governmentOfficial = (GovernmentOfficial) systemClassObject.getSystemObject(id);
                        
                    } catch (Exception e) {
                        System.out.println("\n Invalid ID.\n");
                        continue;
                    }

                    if (governmentOfficial == null)
                    {
                        System.out.println("\n Invalid ID.\n");
                    }
                    else if (!governmentOfficial.getPassword().equals(password))
                    {
                        System.out.println("\n Invalid password.\n");
                    }
                    else
                    {
                        System.out.println("\n Welcome, " + governmentOfficial.getFirstName() + " " + 
                                            governmentOfficial.getLastName() + "!");
                        governmentOfficial.menu(systemClassObject);
                    }
                }
            }
        }
    }
}