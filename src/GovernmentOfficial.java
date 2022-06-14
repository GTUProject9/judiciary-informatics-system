import enums.LawsuitStatus;
import enums.LawsuitTypes;
import enums.SystemObjectTypes;
import java.util.Date;

public class GovernmentOfficial extends Citizen 
{
    // A constructor
    public GovernmentOfficial(int id, String password, String firstName, String lastName, String phone, String email)
    {
        super(id, password, firstName, lastName, phone, email);
    }

    /**
     * It reads the input from the user and creates a new lawyer object with the given data
     * 
     * @param systemClassRef The reference to the system class object.
     */
    public void addLawyerMenu(SystemClass systemClassRef)
    {
        System.out.println( "\nEnter the following information about the lawyer:" );
        System.out.print("First name: ");
        String firstName = Utils.readStringInput();

        System.out.print("Last name: ");
        String lastName = Utils.readStringInput();

        System.out.print("E-mail: ");
        String email = Utils.readStringInput();

        System.out.print("Phone: ");
        String phone = Utils.readStringInput();

        System.out.print("Password: ");
        String password = Utils.readStringInput();

        System.out.print("Enter '1' if the lawyer is a state attorney, '0' otherwise: ");
        boolean stateAttorney = "1.".equals(Utils.readStringInput());

        System.out.print("Enter '1' if the lawyer accepts lawsuits, '0' otherwise: ");
        boolean acceptsLawsuits = "1.".equals(Utils.readStringInput());

        Lawyer lawyer = new Lawyer(-1, password, firstName, lastName, phone, email, stateAttorney, acceptsLawsuits);
        systemClassRef.addLawyer(lawyer);
        System.out.println("The lawyer has been added successfully with ID: " + lawyer.getId());
    }

    /**
     * It assigns a lawsuit to a judge.
     * 
     * @param systemClassRef a reference to the system class
     */
    public void assignLawsuitToJudgeMenu(SystemClass systemClassRef)
    {
        systemClassRef.displayPendingLawsuits();
        System.out.print("Select a lawsuit (0 to exit): ");
        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (choice == 0)
            return;

        Lawsuit lawsuit = systemClassRef.getPendingLawsuitByIndex(choice - 1);
        if (lawsuit == null) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }
        if (lawsuit.getStatus() != LawsuitStatus.HOLD) {
            System.out.println("The lawsuit with ID " + lawsuit.getId() + " is not pending.");
            return;
        }

        systemClassRef.displayJudges();
        System.out.print("Select a judge (0 to exit): ");
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (choice == 0)
            return;
        
        Judge judge = (Judge)systemClassRef.getSystemObjectByIndex(choice - 1, SystemObjectTypes.JUDGE);
        if (judge == null) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        judge.assignLawsuit(lawsuit.getId());
        lawsuit.setJudge(judge.getId());
        lawsuit.setStatus(LawsuitStatus.STILL_GOING);
        systemClassRef.addLawsuitByDate(lawsuit);

        System.out.println("The lawsuit with ID " + lawsuit.getId() + 
                           "\nhas been assigned to the judge with ID " + judge.getId() + ".");
    }

    /**
     * It assigns a lawsuit to a judge.
     * 
     * @param systemClassRef a reference to the system class
     * @param lawsuitId The ID of the lawsuit to be assigned to a judge.
     */
    private void assignLawsuitToJudge(SystemClass systemClassRef, int lawsuitId)
    {
        systemClassRef.displayJudges();
        System.out.print("Select a judge (0 to exit): ");
        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (choice == 0)
            return;
        
        Judge judge = (Judge)systemClassRef.getSystemObjectByIndex(choice - 1, SystemObjectTypes.JUDGE);
        if (judge == null) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }
        
        judge.assignLawsuit(lawsuitId);
        Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
        lawsuit.setJudge(judge.getId());
        lawsuit.setStatus(LawsuitStatus.STILL_GOING);
        systemClassRef.addLawsuitByDate(lawsuit);

        System.out.println("The lawsuit with ID " + lawsuitId + 
                           "\nhas been assigned to the judge with ID " + judge.getId() + ".");
    }

    /**
     * This function prints the state attorney applicants, asks the user if they want to accept the
     * applicant, and if they do, it sets the applicant's state attorney status to true, removes the
     * applicant from the state attorney applicant queue, and adds the applicant to the state attorney
     * queue.
     * 
     * @param systemClassRef This is the reference to the SystemClass object.
     */
    public void addStateAttorney(SystemClass systemClassRef)
    {
        systemClassRef.printStateAttorneyApplicants();
        int applicantId = systemClassRef.peekStateAttorneyApplicant();
        if (applicantId == -1)
        {
            System.out.println("There is no state attorney applicant.");
            return;
        }

        Lawyer applicant = systemClassRef.getLawyer(applicantId);

        System.out.println("Current applicant in the queue:\n" + applicant);
        System.out.println("Do you want to accept the applicant? (y/n)\n");
        System.out.println("(0 to exit)"); 
        System.out.print("Enter: ");
        String answer = Utils.readStringInput();

        if ("y".equalsIgnoreCase(answer))
        {
            applicant.setStateAttorney(true);
            systemClassRef.pollStateAttorneyApplicant();
            systemClassRef.addStateAttorney(applicant.getId());
            System.out.println("The state attorney application has been accepted.");
        }
        else if ("n".equalsIgnoreCase(answer))
        {
            systemClassRef.pollStateAttorney();
            System.out.println("The state attorney application has been rejected.");
        }
        else {
            System.out.println(Utils.INVALID_CHOICE);
        }
    }

    /**
     * The function receives a reference to the system class and publishes a lawsuit.
     * 
     * @param systemClassRef a reference to the system class
     */
    public void publishLawsuit(SystemClass systemClassRef) {
        
        System.out.println("0. Go Back");
        System.out.print("Enter suing citizen ID: ");
        int suingId;
        try {
            suingId = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (suingId == 0)
            return;
        
        Citizen suingCitizen = (Citizen) systemClassRef.getCitizen(suingId);
        if (suingCitizen == null)
        {
            System.out.println("The citizen does not exist.");
            return;
        }

        System.out.print("Enter sued citizen ID: ");
        int suedId;
        try {
            suedId = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (suedId == 0)
            return;
        
        Citizen suedCitizen = (Citizen) systemClassRef.getCitizen(suedId);
        if (suedCitizen == null)
        {
            System.out.println("\nThe citizen does not exist.");
            return;
        }

        // Select the lawsuit type
        System.out.println("\nSelect the lawsuit type:");
        System.out.println("1. Personal Injury Lawsuit");
        System.out.println("2. Product Liability Lawsuit");
        System.out.println("3. Divorce and Family Law Disputes");
        System.out.println("4. Criminal Cases");
        
        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (choice == 0)
            return;

        LawsuitTypes lawsuitType;
        try {
            lawsuitType = LawsuitTypes.values()[choice - 1];
        } catch (Exception e) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        System.out.print("Enter the description of the lawsuit: ");
        String caseFile = Utils.readStringInput();

        int suingLawyerId = systemClassRef.pollStateAttorney();
        int suedLawyerId = systemClassRef.pollStateAttorney();

        if (suingLawyerId == -1 || suedLawyerId == -1)
        {
            System.out.println("There is no state attorney in the queue.");
            return;
        }
        System.out.println("Suing lawyer assigned from state to the lawsuit: " + suingLawyerId);
        System.out.println("Sued lawyer assigned from state to the lawsuit: " + suedLawyerId);
        System.out.println();
        Date date = SystemObjectCreator.randomDate();
        Lawsuit lawsuit = new Lawsuit(date, suingId, suedId, suingLawyerId, 
                                      suedLawyerId, lawsuitType, caseFile);
        
        systemClassRef.addLawsuit(lawsuit);

        Lawyer suingLawyer = systemClassRef.getLawyer(suingLawyerId);
        suingLawyer.addLawsuit(lawsuit.getId());

        Lawyer suedLawyer = systemClassRef.getLawyer(suedLawyerId);
        suedLawyer.addLawsuit(lawsuit.getId());

        suingCitizen.addSuingLawsuit(lawsuit.getId());
        suedCitizen.addSuedLawsuit(lawsuit.getId());

        assignLawsuitToJudge(systemClassRef, lawsuit.getId());

        System.out.println("\nThe lawsuit has been published successfully.");
    }

    /**
     * The function is a menu for the government official, it allows him to add a lawyer, assign a
     * lawsuit to a judge, add a state attorney, and publish a lawsuit.
     * 
     * @param systemClassRef This is a reference to the system class.
     */
    @Override
    public void menu(SystemClass systemClassRef) {
        System.out.println("\n--- Government Offical Menu ---");
        while(true)
        {
            System.out.println("\n1. Add lawyer");
            System.out.println("2. Assign lawsuit to judge");
            System.out.println("3. Add state attorney");
            System.out.println("4. Publish lawsuit");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }
            switch(choice)
            {
                case 1:
                    addLawyerMenu(systemClassRef);
                    break;
                case 2:
                    assignLawsuitToJudgeMenu(systemClassRef);
                    break;
                case 3:
                    addStateAttorney(systemClassRef);
                    break;
                case 4:
                    publishLawsuit(systemClassRef);
                    break;
                case 0:
                    return;
                default:
                    System.out.println(Utils.INVALID_CHOICE);
            }
        }
    }

    // super menu
    public void citizenMenu(SystemClass systemClassRef)
    {
        super.menu(systemClassRef);
    }
}