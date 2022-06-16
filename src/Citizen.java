import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import enums.LawsuitStatus;
import enums.LawsuitTypes;

public class Citizen extends AbstractUser
{
    private Set<Integer> suingLawsuits;
    private Set<Integer> suedLawsuits;

    // Parameterized constructor.
    public Citizen(int id, String password, String name, String surname, String email, String phone) {
        super(id, password, name, surname, email, phone);

        suingLawsuits = new ConcurrentSkipListSet<>();
        suedLawsuits = new ConcurrentSkipListSet<>();
    }

    /**
     * Creates a lawsuit and assigns it to the sued citizen, then
     * assign suing lawyer to the lawsuit.
     * 
     * @param systemClassRef The system class reference
     */
    private void createLawsuitMenu(SystemClass systemClassRef) {
        System.out.print("\nEnter the ID of the person you are claiming: ");
        int suedCitizen = -1;
        try {
            suedCitizen = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }

        if (systemClassRef.getCitizen(suedCitizen) == null) {
            System.out.println("The ID you entered is not valid.");
            return;
        }
        if (systemClassRef.getCitizen(suedCitizen).getId() == id) {
            System.out.println("You cannot sue yourself.");
            return;
        }

        System.out.println("\nEnter the type of the lawsuit.");
        System.out.println("1. Personal Injury Lawsuit");
        System.out.println("2. Product Liability Lawsuit");
        System.out.println("3. Divorce and Family Law Disputes");
        System.out.println("4. Criminal Cases");
        System.out.println("0. Exit");
        System.out.print("Choice: ");

        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (choice == 0) {
            return;
        }

        LawsuitTypes lawsuitType;
        try {
            lawsuitType = LawsuitTypes.values()[choice - 1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        System.out.println("Enter the case file: ");
        String caseFile = Utils.readStringInput();
        Date date = SystemObjectCreator.randomDate();
        
        int lawyerId = selectLawyer(systemClassRef);
        if (lawyerId == -1) {
            return;
        }
        
        System.out.println("\nSelected Lawyer: ");
        System.out.println(systemClassRef.getLawyer(lawyerId));
        
        Lawsuit lawsuit = new Lawsuit(date, id, suedCitizen, lawyerId, lawsuitType, caseFile);
        systemClassRef.addLawsuit(lawsuit);

        lawsuit.setSuingLawyer(lawyerId);
        systemClassRef.assignLawyerToLawsuit(lawyerId, lawsuit.getId());
        
        addSuingLawsuit(lawsuit.id);
        addLawsuitToSuedCitizen(systemClassRef, suedCitizen, lawsuit.id);

        System.out.println("Lawsuit created successfully.");
    }

    /**
     * It displays a menu, reads the user's choice, and returns the lawyer's id
     * 
     * @param systemClassRef a reference to the system class
     * @return The lawyerId is being returned.
     */
    private static int selectLawyer(SystemClass systemClassRef) {
        System.out.println("\n1. Select lawyer that accepts lawsuits");
        System.out.println("2. Request lawyer from state");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (NumberFormatException e) {
            System.out.println(Utils.INVALID_INPUT);
            return -1;
        }

        if (choice == 0) {
            System.out.println("Canceled.");
            return -1;
        }
        int lawyerId = -1;
        if (choice == 1) {
            systemClassRef.displayLawsuitAcceptingLawyers();
            System.out.print("Select (0. Go Back): ");
            int index;
            try {
                index = Utils.readIntegerInput();
            } catch (Exception e) {
                System.out.println(Utils.INVALID_INPUT);
                return -1;
            }
            
            if (index == 0) 
            {
                return -1;
            }

            lawyerId = systemClassRef.getLawsuitAcceptingLawyerByIndex(index - 1);
            if (lawyerId == -1)
            {
                System.out.println(Utils.INVALID_CHOICE);
                return -1;
            } 
        }
        if (choice == 2) {
            lawyerId = systemClassRef.pollStateAttorney();
            if (lawyerId == -1) {
                System.out.println("\nNo lawyer available in state.");
                return -1;
            }
        }
        return lawyerId;
    }

    /**
     * This function displays the suing lawsuits of a lawyer
     * 
     * @param systemClassRef a reference to the system class
     */
    private void displaySuingLawsuits(SystemClass systemClassRef) {
        if (suingLawsuits.isEmpty()) {
            System.out.println("\nYou have no suing lawsuits.");
            return;
        }
        int i = 0;
        System.out.println("\nYour suing lawsuits:");
        System.out.println();
        for (var lawsuitId : suingLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            System.out.println((i + 1) + ". Lawsuit\n" + lawsuit);
            i++;
        }
    }

    /**
     * This function displays the lawsuits that the user has been sued in
     * 
     * @param systemClassRef a reference to the system class
     */
    private void displaySuedLawsuits(SystemClass systemClassRef) {
        
        if (suedLawsuits.isEmpty()) {
            System.out.println("\nYou have no lawsuits that have been sued.");
            return;
        }
        System.out.println("\nLawsuits that you have been sued:");
        int i = 0;
        System.out.println();
        for (var lawsuitId : suedLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            System.out.println((i + 1) + ". Lawsuit\n" + lawsuit);
            i++;
        }
    }

    /**
     * It displays the completed lawsuits of a lawyer
     * 
     * @param systemClassRef a reference to the system class
     */
    private void displayCompletedLawsuits(SystemClass systemClassRef) {
        System.out.println("\nCompleted lawsuits:");
        int i = 0;
        for (var lawsuitId : suingLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            if (lawsuit.getStatus() == LawsuitStatus.SUING_WON || 
                lawsuit.getStatus() == LawsuitStatus.SUED_WON) {
                    System.out.println((i + 1)+ ". " + lawsuit);
                i++;
            }
        }
        for (var lawsuitId : suedLawsuits) {
            Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
            if (lawsuit.getStatus() == LawsuitStatus.SUING_WON || 
                lawsuit.getStatus() == LawsuitStatus.SUED_WON) {
                    System.out.println((i + 1) + ". " + lawsuit);
                i++;
            }
        }
        if (i == 0) {
            System.out.println("\nYou have no completed lawsuits.");
        }
        
    }
    
    /**
     * This function adds a lawsuit to a citizen's list of sued lawsuits
     * 
     * @param systemClassRef a reference to the system class
     * @param suedCitizenId The id of the citizen who is being sued.
     * @param lawsuitId the id of the lawsuit
     */
    private static void addLawsuitToSuedCitizen(SystemClass systemClassRef, 
                                                int suedCitizenId, int lawsuitId) {
        Citizen suedCitizen = (Citizen) systemClassRef.getSystemObject(suedCitizenId);
        suedCitizen.addSuedLawsuit(lawsuitId);
    }

    /**
     * This function adds a lawsuit id to the list of lawsuits that the user is suing.
     * 
     * @param lawsuitId The id of the lawsuit that is being sued.
     */
    public void addSuingLawsuit(int lawsuitId) {
        suingLawsuits.add(lawsuitId);
    }

    /**
     * This function adds a lawsuit id to the list of lawsuits that the user has sued
     * 
     * @param lawsuitId The id of the lawsuit
     */
    public void addSuedLawsuit(int lawsuitId) {
        suedLawsuits.add(lawsuitId);
    }

    /**
     * It adds a lawyer to a lawsuit
     * 
     * @param systemClassRef is a reference to the system class
     */
    private void addLawyerAsSuedCitizen(SystemClass systemClassRef) {
        if (!areContinuingLawsuits(systemClassRef)) {
            System.out.println("There are no sued lawsuits.");
            return;
        }

        System.out.println("\nSelect the lawsuit you want to add a lawyer to:");

        int i = 0, j = 0;
        for (var lawsuitId : suedLawsuits) {
            if (systemClassRef.getLawsuit(lawsuitId).getStatus() == LawsuitStatus.HOLD) {
                System.out.println((j + 1) + ".\n" + systemClassRef.getLawsuit(lawsuitId));
                j++;
            }
            i++;
        }

        System.out.println("Choice: ");
        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        int lawsuitId;
        try {
            lawsuitId = (int)suedLawsuits.toArray()[choice - 1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        int suedLawyer = selectLawyer(systemClassRef);
        if (suedLawyer == -1) {
            return;
        }
        System.out.println("\nSelected Lawyer: ");
        System.out.println(systemClassRef.getLawyer(suedLawyer));

        Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
        lawsuit.setSuedLawyer(suedLawyer);
        systemClassRef.assignLawyerToLawsuit(suedLawyer, lawsuitId);
    }

    private boolean areContinuingLawsuits(SystemClass systemClassRef) {
        for (var lawsuitId : suedLawsuits) {
            if (systemClassRef.getLawsuit(lawsuitId).getStatus() == LawsuitStatus.HOLD) {
                return true;
            }
        }
        return false;
    }

    /**
     * It returns the string representation of the object.
     * 
     * @return The superclass's toString() method.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * It displays a menu for the citizen 
     * 
     * @param systemClassRef This is a reference to the system class.
     */
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
                    createLawsuitMenu(systemClassRef);
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
                    systemClassRef.displayLawsuitAcceptingLawyers();
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
