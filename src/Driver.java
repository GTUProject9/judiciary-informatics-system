import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import enums.SystemObjectTypes;

public class Driver {
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

        // System.out.println(systemClassObject.getSystemObjects());
        GovernmentOfficial temp = (GovernmentOfficial) systemClassObject.getSystemObject(60001);
        temp.menu(systemClassObject);
        System.out.println(systemClassObject.getLawsuitsByDate() + "\n");


        Scanner scanner = new Scanner(System.in);
        
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
            System.exit(0);
        }
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
                System.exit(0);
            }
            else if (!citizen.getPassword().equals(password))
            {
                System.out.println("\n Invalid password.\n");
                System.exit(0);
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
                System.exit(0);
            }
            else if (!lawyer.getPassword().equals(password))
            {
                System.out.println("\n Invalid password.\n");
                System.exit(0);
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
                System.exit(0);
            }
            else if (!lawOfficeOwner.getPassword().equals(password))
            {
                System.out.println("\n Invalid password.\n");
                System.exit(0);
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
                System.exit(0);
            }
            else if (!judge.getPassword().equals(password))
            {
                System.out.println("\n Invalid password.\n");
                System.exit(0);
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
                System.exit(0);
            }
            else if (!governmentOfficial.getPassword().equals(password))
            {
                System.out.println("\n Invalid password.\n");
                System.exit(0);
            }
            else
            {
                governmentOfficial.menu(systemClassObject);
            }
        }

        scanner.close();
        // System.out.println(systemClassObject.getSystemObjects());
        System.out.println(systemClassObject.getLawsuitsByDate());

    }

}

/*

// System.out.println("--> Create a citizen and print its information.");
// Citizen citizen = new Citizen();
// System.out.println(citizen);
// System.out.println("--> get null: " + systemClassObject.getSystemObject(20001) + "\n");

// System.out.println("--> set id and register it to the system: " + "\n");
// citizen.setId(20001);
// systemClassObject.registerSystemObject(citizen);
// System.out.println("get object: \n" + systemClassObject.getSystemObject(20001) + "\n");

// Lawsuit lawsuit;

// System.out.println("--> Create a lawsuit and register it to the system, then find it.");
// lawsuit = new Lawsuit();
// lawsuit.setId(10002);
// lawsuit.setDate(new SimpleDateFormat("yyyyMMdd").parse("20230413"));
// systemClassObject.registerSystemObject(lawsuit);
// System.out.println("get object: \n" + systemClassObject.getSystemObject(10002) + "\n");

// lawsuit = new Lawsuit();
// lawsuit.setId(10001);
// lawsuit.setDate(new SimpleDateFormat("yyyyMMdd").parse("20230412"));
// systemClassObject.registerSystemObject(lawsuit);
// System.out.println("get object: \n" + systemClassObject.getSystemObject(10001) + "\n");

// lawsuit = new Lawsuit();
// lawsuit.setId(10005);
// lawsuit.setDate(new SimpleDateFormat("yyyyMMdd").parse("20230410"));
// systemClassObject.registerSystemObject(lawsuit);
// System.out.println("get object: \n" + systemClassObject.getSystemObject(10005) + "\n");

// lawsuit = new Lawsuit();
// lawsuit.setId(10003);
// lawsuit.setDate(new SimpleDateFormat("yyyyMMdd").parse("20230411"));
// systemClassObject.registerSystemObject(lawsuit);
// System.out.println("get object: \n" + systemClassObject.getSystemObject(10003) + "\n");

// System.out.println(systemClassObject.getLawsuits() + "\n");

// System.out.println(systemClassObject.getSystemObjects());

*/