import enums.LawsuitStatus;
import enums.LawsuitTypes;

import java.util.Date;

import javax.lang.model.util.ElementScanner14;

public class GovernmentOfficial extends Citizen 
{
    GovernmentOfficial()
    {
        super();
    }

    GovernmentOfficial(int id, String password, String firstName, String lastName, String phone, String email)
    {
        super(id, password, firstName, lastName, phone, email);
    }

    /**
     * It reads the input from the user and creates a new lawyer object with the given data
     * 
     * @param systemClassObject The reference to the system class object.
     */
    public void addLawyer(SystemClass systemClassObject)
    {
        String firstName, lastName, email, phone, password;
        boolean stateAttorney, acceptsLawsuits;

        System.out.print("Enter the first name of the lawyer: ");
        firstName = Utils.readStringInput();

        System.out.print("Enter the last name of the lawyer: ");
        lastName = Utils.readStringInput();

        System.out.print("Enter the email of the lawyer: ");
        email = Utils.readStringInput();

        System.out.print("Enter the phone number of the lawyer: ");
        phone = Utils.readStringInput();

        System.out.print("Enter the password of the lawyer: ");
        password = Utils.readStringInput();

        System.out.print("Enter 'true' if the lawyer is a state attorney, 'false' otherwise: ");
        stateAttorney = Utils.readBooleanInput();

        System.out.print("Enter 'true' if the lawyer accepts lawsuits, 'false' otherwise: ");
        acceptsLawsuits = Utils.readBooleanInput();

        Lawyer lawyer = new Lawyer(-1, password, firstName, lastName, phone, email, stateAttorney, acceptsLawsuits);
        
        systemClassObject.addLawyer(lawyer);
        
        System.out.println("The lawyer has ID: " + lawyer.getId() + "added successfully.");
    }

    public void assignLawsuitToJudge(SystemClass systemClassObject)
    {
        // Get judge id
        System.out.print("Enter the ID of the judge: ");
        int judgeId = Utils.readIntegerInput();

        // Get lawsuit id
        System.out.print("Enter the ID of the lawsuit: ");
        int lawsuitId = Utils.readIntegerInput();

        try {
            Judge judge = (Judge) systemClassObject.getSystemObject(judgeId);
            judge.assignLawsuit(lawsuitId);

            Lawsuit lawsuit = (Lawsuit) systemClassObject.getSystemObject(lawsuitId);
            lawsuit.setJudge(judgeId);
            lawsuit.setStatus(LawsuitStatus.STILL_GOING);
            systemClassObject.addLawsuitByDate(lawsuit);
        } catch (Exception e) {
            System.out.println("The judge or the lawsuit does not exist.");
        }
    }

    private void assignLawsuitToJudge(SystemClass systemClassObject, int lawsuitId)
    {
        // Get judge id
        System.out.print("Enter the ID of the judge: ");
        int judgeId = Utils.readIntegerInput();

        try {
            Judge judge = (Judge) systemClassObject.getSystemObject(judgeId);
            judge.assignLawsuit(lawsuitId);

            Lawsuit lawsuit = (Lawsuit) systemClassObject.getSystemObject(lawsuitId);
            lawsuit.setJudge(judgeId);
            lawsuit.setStatus(LawsuitStatus.STILL_GOING);
            systemClassObject.addLawsuitByDate(lawsuit);
        } catch (Exception e) {
            System.out.println("The judge or the lawsuit does not exist.");
        }
    }

    
    public void addStateAttorney(SystemClass systemClassObject)
    {
        systemClassObject.printStateAttorneyApplicants();
        while(true)
        {
            Lawyer lawyer = systemClassObject.peekStateAttorneyApplicant();
            if (lawyer == null)
            {
                System.out.println("There is no state attorney applicant.");
                break;
            }
            else
            {
                System.out.println("Current applicant in the queue: " + lawyer);
                System.out.print("Do you want to accept the applicant? (y/n)\n" + 
                                   "(to return previous menu enter 'exit') ");
                String answer = Utils.readStringInput();
                if (answer.equals("y") || answer.equals("yes") || answer.equals("Y") || answer.equals("Yes"))
                {
                    System.out.println("herererere");
                    lawyer.setStateAttorney(true);
                    systemClassObject.pollStateAttorney();
                    systemClassObject.addStateAttorney(lawyer);
                }
                else if (answer.equals("n") || answer.equals("no") || answer.equals("N") || answer.equals("No"))
                {
                    systemClassObject.pollStateAttorney();
                }
                else
                {
                    break;
                }
            }
        }
    }

    public void publishLawsuit(SystemClass systemClassObject)
    {
        Date date = SystemObjectCreator.randomDate();
        
        System.out.print("Enter suing citizen ID: ");
        int suingId = Utils.readIntegerInput();
        
        System.out.print("Enter sued citizen ID: ");
        int suedId = Utils.readIntegerInput();

        // Select the lawsuit type
        System.out.println("Select the lawsuit type:");
        System.out.println("1. Personal Injury Lawsuit");
        System.out.println("2. Product Liability Lawsuit");
        System.out.println("3. Divorce and Family Law Disputes");
        System.out.println("4. Criminal Cases");
        
        int choice = Utils.readIntegerInput();
        LawsuitTypes lawsuitType = LawsuitTypes.values()[choice - 1];

        String caseFile = "Dummy case file";

        Lawyer suingLawyer = systemClassObject.getStateAttorney();
        Lawyer suedLawyer = systemClassObject.getStateAttorney();

        Lawsuit lawsuit = new Lawsuit(-1, date, null, suingId, suedId, suingLawyer.getId(), suedLawyer.getId(), lawsuitType, LawsuitStatus.HOLD, caseFile, null);
        systemClassObject.addLawsuit(lawsuit);

        suingLawyer.addLawsuit(lawsuit.getId());
        suedLawyer.addLawsuit(lawsuit.getId());

        Citizen suingCitizen = (Citizen) systemClassObject.getCitizen(suingId);
        Citizen suedCitizen = (Citizen) systemClassObject.getCitizen(suedId);

        suingCitizen.addSuingLawsuit(lawsuit.getId());
        suedCitizen.addSuedLawsuit(lawsuit.getId());

        assignLawsuitToJudge(systemClassObject, lawsuit.getId());
    }

    @Override
    public void menu(SystemClass systemClassObject) {
        while(true)
        {
            System.out.println("1. Add lawyer");
            System.out.println("2. Assign lawsuit to judge");
            System.out.println("3. Add state attorney");
            System.out.println("4. Publish lawsuit");
            System.out.println("5. Return to main menu");
            int choice;
            try
            {
                choice = Utils.readIntegerInput();
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input.");
                continue;
            }
            switch(choice)
            {
                case 1:
                    addLawyer(systemClassObject);
                    break;
                case 2:
                    assignLawsuitToJudge(systemClassObject);
                    break;
                case 3:
                    addStateAttorney(systemClassObject);
                    break;
                case 4:
                    publishLawsuit(systemClassObject);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    // super menu
    public void superMenu(SystemClass systemClassObject)
    {
        super.menu(systemClassObject);
    }
}