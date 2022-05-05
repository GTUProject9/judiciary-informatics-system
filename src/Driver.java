import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception 
    {
        SystemClass systemClassObject = new SystemClass();
        // Create other objects
        SystemObjectCreator.createCitizens(systemClassObject);
        // System.out.println(systemClassObject.getSystemObjects());


        Scanner scanner = new Scanner(System.in);
        
        // Menu
        System.out.println("\n ---------- Judiciary Informatics System ---------- \n\n");
        System.out.println("1. Login as a Citizen");
        System.out.println("3. Login as a Lawyer");
        System.out.println("4. Login as a Law Office Owner");
        System.out.println("2. Login as a Judge");
        System.out.println("5. Login as a Government Official");
        System.out.println("0 - Exit");
        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        if (choice == 1)
            Citizen.menu(systemClassObject);
        else if (choice == 2)
            Judge.menu(systemClassObject);
        else if (choice == 3)
            Lawyer.menu(systemClassObject);
        else if (choice == 4)
            LawOfficeOwner.menu(systemClassObject);
        else if (choice == 5)
            GovernmentOfficial.menu(systemClassObject);
        else if (choice == 0)
            System.exit(0);
        else
            System.out.println("Invalid choice!");

        scanner.close();
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