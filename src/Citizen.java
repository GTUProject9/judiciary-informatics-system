import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import enums.LawsuitStatus;
import enums.LawsuitTypes;

public class Citizen extends AbstractUser
{
    private Set<Integer> suingLawsuits;
    private Set<Integer> suedLawsuits;
    
    public Citizen() {
        super();
        suingLawsuits = new ConcurrentSkipListSet<>();
        suedLawsuits = new ConcurrentSkipListSet<>();
    }

    public Citizen(int id, String password, String name, String surname, String email, String phone) {
        super(id, password, name, surname, email, phone);

        suingLawsuits = new ConcurrentSkipListSet<>();
        suedLawsuits = new ConcurrentSkipListSet<>();
    }

    private void createLawsuit(SystemClass systemClassRef) {

        Date date = SystemObjectCreator.randomDate();

        System.out.print("Enter the ID of the person you are claiming: ");
        Integer suedCitizen = Utils.readIntegerInput();

        System.out.print("Enter the type of the lawsuit.\n" +
                "1. Personal Injury Lawsuit\n" +
                "2. Product Liability Lawsuit\n" +
                "3. Divorce and Family Law Disputes\n" +
                "4. Criminal Cases\n" +               
                "Enter the number of the type: ");

        Integer type = Utils.readIntegerInput();
        LawsuitTypes lawsuitType = LawsuitTypes.values()[type - 1];

        System.out.println("Enter the case file: ");
        String caseFile = Utils.readStringInput();

        Lawsuit lawsuit = new Lawsuit(-1, date, id, suedCitizen, null, lawsuitType, caseFile);
        systemClassRef.addLawsuit(lawsuit);

        Integer suingLawyer = selectLawyer(systemClassRef, lawsuit.getId());
        lawsuit.setSuingLawyer(suingLawyer);
        systemClassRef.assignLawyerToLawsuit(suingLawyer, lawsuit.getId());

        addSuingLawsuit(lawsuit.id);

        addLawsuitToSuedCitizen(systemClassRef, suedCitizen, lawsuit.id);
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
            Integer choice = Utils.readIntegerInput();
            if(choice == 1)
            {
                displayLawyersThatAcceptsLawsuits(systemClassRef);
                System.out.print("Enter the ID of the lawyer: ");
                return Utils.readIntegerInput();
            }
            else if(choice == 2)
            {
                Integer lawyerId = systemClassRef.assignStateAttorney(lawsuitId);

                if(lawyerId == null)
                {
                    System.out.println("No lawyers available. Try again later.");
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
        // get citizen
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
        System.out.println("Citizen Menu");
        while (true) {
            System.out.println("1. Create Lawsuit");
            System.out.println("2. View lawsuits that I am suing");
            System.out.println("3. View lawsuits that I have been sued");
            System.out.println("4. View completed lawsuits");
            System.out.println("5. Display lawyers that accepts lawsuits");
            System.out.println("6. Add lawyer as sued citizen");
            System.out.println("0. To return main menu");

            int choice = Utils.readIntegerInput();
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
