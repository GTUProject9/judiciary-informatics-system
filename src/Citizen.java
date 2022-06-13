import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import enums.LawsuitStatus;
import enums.LawsuitTypes;

public class Citizen extends AbstractUser
{
    private Set<Integer> suingLawsuits;
    private Set<Integer> suedLawsuits;
    
    // Default constructor.
    public Citizen() {
        super();
        suingLawsuits = new ConcurrentSkipListSet<>();
        suedLawsuits = new ConcurrentSkipListSet<>();
    }

    // Parameterized constructor.
    public Citizen(int id, String password, String name, String surname, String email, String phone) {
        super(id, password, name, surname, email, phone);

        suingLawsuits = new ConcurrentSkipListSet<>();
        suedLawsuits = new ConcurrentSkipListSet<>();
    }

    private void createLawsuit(SystemClass systemClassRef) {

        System.out.print("\nEnter the ID of the person you are claiming: ");
        Integer suedCitizen;
        try {
            suedCitizen = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        System.out.print("\nEnter the type of the lawsuit.\n" +
                         "1. Personal Injury Lawsuit\n" +
                         "2. Product Liability Lawsuit\n" +
                         "3. Divorce and Family Law Disputes\n" +
                         "4. Criminal Cases\n" +               
                         "Type: ");

        Integer type;
        try {
            type = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }

        LawsuitTypes lawsuitType;
        try {
            lawsuitType = getLawsuitType(type - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        System.out.println("Enter the case file: ");
        String caseFile = Utils.readStringInput();
        Date date = SystemObjectCreator.randomDate();

        Lawsuit lawsuit = new Lawsuit(-1, date, id, suedCitizen, null, lawsuitType, caseFile);
        systemClassRef.addLawsuit(lawsuit);

        Integer suingLawyer = selectLawyer(systemClassRef, lawsuit.getId());
        
        lawsuit.setSuingLawyer(suingLawyer);
        systemClassRef.assignLawyerToLawsuit(suingLawyer, lawsuit.getId());

        addSuingLawsuit(lawsuit.id);
        addLawsuitToSuedCitizen(systemClassRef, suedCitizen, lawsuit.id);
    }

    private LawsuitTypes getLawsuitType(int index)
    {
        if (index >= 0 && index < LawsuitTypes.values().length)
        {
            return LawsuitTypes.values()[index];
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    private void displaySuingLawsuits(SystemClass systemClassRef) {
        int i = 1;
        for (Integer lawsuitId : suingLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            System.out.println(i + ".\n" + lawsuit);
            i++;
        }
    }

    private void displaySuedLawsuits(SystemClass systemClassRef) {
        int i = 1;
        for (Integer lawsuitId : suedLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            System.out.println(i + ". " + lawsuit);
            i++;
        }
    }

    private void displayCompletedLawsuits(SystemClass systemClassRef) {
        int i = 1;
        for (Integer lawsuitId : suingLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            if (lawsuit.getStatus() == LawsuitStatus.SUING_WON || 
                lawsuit.getStatus() == LawsuitStatus.SUED_WON) {
                    System.out.println(i + ". " + lawsuit.getCaseFile());
                i++;
            }
        }
        for (Integer lawsuitId : suedLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            if (lawsuit.getStatus() == LawsuitStatus.SUING_WON || 
                lawsuit.getStatus() == LawsuitStatus.SUED_WON) {
                    System.out.println(i + ". " + lawsuit.getCaseFile());
                i++;
            }
        }
    }

    private static void displayLawyersThatAcceptsLawsuits(SystemClass systemClassRef) {
        systemClassRef.getLawsuitAcceptingLawyers();
    }

    private static Integer selectLawyer(SystemClass systemClassRef, Integer lawsuitId) {
        
        System.out.println("1. Select lawyer that accepts lawsuits" + "\n" +
                           "2. Request lawyer from state" + "\n" +
                           "Enter your choice: ");
        while(true)
        {
            Integer choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (Exception e) {
                System.out.println(Utils.INVALID_INPUT);
                continue;
            }
            if(choice == 1)
            {
                displayLawyersThatAcceptsLawsuits(systemClassRef);
                System.out.print("Select lawyer: ");
                return Utils.readIntegerInput();
            }
            else if(choice == 2)
            {
                Integer lawyerId = systemClassRef.assignStateAttorney(lawsuitId);

                if(lawyerId == null)
                {
                    System.out.println("No lawyers available in state. Try again later.");
                }
                else
                {
                    return lawyerId;
                }
            }
            else
            {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void addLawsuitToSuedCitizen(SystemClass systemClassRef, 
                                                Integer suedCitizenId, Integer lawsuitId) {
        Citizen suedCitizen = (Citizen) systemClassRef.getSystemObject(suedCitizenId);
        suedCitizen.addSuedLawsuit(lawsuitId);
    }

    public void addSuingLawsuit(Integer lawsuitId) {
        suingLawsuits.add(lawsuitId);
    }

    public void addSuedLawsuit(Integer lawsuitId) {
        suedLawsuits.add(lawsuitId);
    }

    private void addLawyerAsSuedCitizen(SystemClass systemClassRef) {
        for (Integer lawsuitId : suedLawsuits) {
            System.out.println("1.\n" + systemClassRef.getLawsuit(lawsuitId));
        }
        System.out.println("Enter the ID of the lawsuit: ");
        Integer lawsuitId = Utils.readIntegerInput();
        Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
        Integer suedLawyer = selectLawyer(systemClassRef, lawsuitId);
        lawsuit.setSuedLawyer(suedLawyer);
        systemClassRef.assignLawyerToLawsuit(suedLawyer, lawsuitId);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void menu(SystemClass systemClassRef) {
        System.out.println("\n--- Citizen Menu ---");
        while (true) {
            System.out.println("\n1. Create Lawsuit");
            System.out.println("2. View The Lawsuits That You Are Suing");
            System.out.println("3. View The Lawsuits That You Are The Sued");
            System.out.println("4. View Completed Lawsuits");
            System.out.println("5. Display Lawyers That Accepts Lawsuits");
            System.out.println("6. Add Lawyer As Sued Citizen");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println(Utils.INVALID_INPUT);
                continue;
            }

            switch (choice) {
                case 1:
                    createLawsuit(systemClassRef);
                    break;
                case 2:
                    displaySuingLawsuits(systemClassRef);
                    break;
                case 3:
                    displaySuedLawsuits(systemClassRef);
                    break;
                case 4:
                    displayCompletedLawsuits(systemClassRef);
                    break;
                case 5:
                    displayLawyersThatAcceptsLawsuits(systemClassRef);
                    break;
                case 6:
                    addLawyerAsSuedCitizen(systemClassRef);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
                }
        }
    }


}
