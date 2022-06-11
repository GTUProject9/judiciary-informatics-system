
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import enums.SystemObjectTypes;

public class Driver {

    /**
     * It creates the objects and then it asks the user to login.
     */
    public static void main(String[] args) throws Exception 
    {
        SystemClass systemClassObject = new SystemClass();
        // Create other objects
        SystemObjectCreator.createCitizens(systemClassObject);
        SystemObjectCreator.createLawyers(systemClassObject);
        SystemObjectCreator.createLawOfficeOwners(systemClassObject);
        SystemObjectCreator.createGovernmentOfficials(systemClassObject);
        SystemObjectCreator.createJudges(systemClassObject);
        SystemObjectCreator.createLawsuits(systemClassObject);

        GovernmentOfficial temp = (GovernmentOfficial) systemClassObject.getSystemObject(60001);
        temp.menu(systemClassObject);

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
                // Menu
            System.out.println("\n ---------- Judiciary Informatics System ---------- \n\n");
            System.out.println("--- Users ---");
            System.out.println("1. Login as a Citizen");
            System.out.println("2. Login as a Lawyer");
            System.out.println("3. Login as a Law Office Owner");
            System.out.println("4. Login as a Judge");
            System.out.println("5. Login as a Government Official");
            System.out.println("0 - Exit");

            int choice = scanner.nextInt();

            if (choice == 0)
            {
                System.out.println("Exiting...");
                break;
            }
            if (choice < 0 || choice > 5)
            {
                System.out.println("Invalid choice. Please try again.");
            }
            else
            {
                System.out.println("\n Enter your ID: ");
                int id = scanner.nextInt();
                
                System.out.println("\n Enter your password: ");
                String password = scanner.next();

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
                        citizen.menu(systemClassObject);
                    }
                }
                else if (choice == 2)
                {
                    Lawyer lawyer = (Lawyer) systemClassObject.getSystemObject(id);
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
                        lawyer.menu(systemClassObject);

                    }
                }
                else if (choice == 3)
                {
                    LawOfficeOwner lawOfficeOwner = (LawOfficeOwner) systemClassObject.getSystemObject(id);
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
                        lawOfficeOwner.menu(systemClassObject);
                    }
                }
                else if (choice == 4)
                {
                    Judge judge = (Judge) systemClassObject.getSystemObject(id);
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
                        judge.menu(systemClassObject);
                    }
                }
                else if (choice == 5)
                {
                    GovernmentOfficial governmentOfficial = (GovernmentOfficial) systemClassObject.getSystemObject(id);
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
                        governmentOfficial.menu(systemClassObject);
                    }
                }
            }
        }
        scanner.close();
    }

}