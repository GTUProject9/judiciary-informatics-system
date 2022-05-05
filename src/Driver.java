import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver {
    public static void main(String[] args) throws Exception 
    {
        SystemClass systemClassObject = new SystemClass();
        
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

        SystemObjectCreator.createRandomCitizens(systemClassObject);
        // System.out.println(systemClassObject.getSystemObjects());

    }

}
