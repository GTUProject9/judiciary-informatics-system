import java.util.ArrayList;
import java.util.List;

import SortingAlgorithms.QuickSort;
import enums.LawsuitStatus;

/**
 * Class for Judge User
 */
public class Judge extends Citizen{
    // Data Fields
    /** List of lawsuits those are assigned to judge */
    private List<Integer> assignedLawsuits;
    /** List of lawsuits those are concluded by judge */
    private List<Integer> concludedLawsuits;

    // A constructor.
    public  Judge(int id,String password,String name,String surname, String email,String phone){
        super(id, password, name, surname,  email, phone);
        assignedLawsuits = new ArrayList<>();
        concludedLawsuits = new ArrayList<>();
    }

    // Methods
    /**
     * Displays assigned lawsuits to judge
     * @param systemClassRefReference System object that holds objects by id
     */
    private void showAssignedLawsuits(SystemClass systemClassRefReference) {
        if (assignedLawsuits.isEmpty()) {
            System.out.println("There is no assigned lawsuits.");
            return;
        }
        for (int i = 0; i < assignedLawsuits.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + 
                               systemClassRefReference.getSystemObject(assignedLawsuits.get(i)));
        }
        System.out.println(assignedLawsuits);
    }

    /**
     * Displays concluded lawsuits to judge
     * @param systemClassRefReference System object that holds objects by id
     */
    private void showConcludedLawsuits(SystemClass systemClassRefReference) {
        if (concludedLawsuits.isEmpty()) {
            System.out.println("There is no concluded lawsuits.");
            return;
        }
        for (int i = 0; i < concludedLawsuits.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + 
                               systemClassRefReference.getSystemObject(concludedLawsuits.get(i)));
        }
        System.out.println(concludedLawsuits);
    }

    /**
     * Concludes lawsuit
     * @param systemClassRefReference System object that holds objects by id
     */
    private void concludeLawsuit(SystemClass systemClassRefReference) {
        Lawsuit lawsuit = systemClassRefReference.peekHighestPriorityLawsuit(this.id);
        if (lawsuit == null) {
            System.out.println("\nThere is no assigned lawsuits.");
            return;
        }
        
        // if (lawsuit.getSuingDefence() == null || lawsuit.getSuedDefence() == null ) {
        //     System.out.println("\nLawyers should add defences.");
        //     return;
        // }

        System.out.println("\nDate: "+lawsuit.getDate());
        System.out.println("Lawsuit Type: "+lawsuit.getLawsuitType());
        System.out.println("Status: "+lawsuit.getStatus().getStatus());
        
        Citizen defendant = systemClassRefReference.getCitizen(lawsuit.getSuingCitizen());
        Lawyer defendantLawyer = systemClassRefReference.getLawyer(lawsuit.getSuingLawyer());

        Citizen prosecutor = systemClassRefReference.getCitizen(lawsuit.getSuedCitizen());
        Lawyer prosecutorLawyer = systemClassRefReference.getLawyer(lawsuit.getSuedLawyer());

        System.out.println("Defendant: "+ defendant.firstName + " " + defendant.lastName);
        System.out.println("Defendant's Lawyer: "+ defendantLawyer.firstName + " " + defendantLawyer.lastName);
        System.out.println("Prosecutor: "+ prosecutor.firstName + " " + prosecutor.lastName);
        System.out.println("Prosecutor's Lawyer: "+ prosecutorLawyer.firstName + " " + prosecutorLawyer.lastName);

        System.out.println("\n");

        while (true) {
            System.out.println("1. Show Defense of Defendant");
            System.out.println("2. Show Defense of Prosecutor");
            System.out.println("3. Conclude Trial");
            System.out.println("0. Exit");
            System.out.println("Choice:");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println(Utils.INVALID_INPUT);
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Defense of Defendant: "+lawsuit.getSuingDefence());
                    break;
                case 2:
                    System.out.println("Defense of Prosecutor: "+lawsuit.getSuedDefence());
                    break;
                case 3:
                    changeStatus(systemClassRefReference, lawsuit);
                    if (lawsuit.getStatus() == LawsuitStatus.SUED_WON || 
                        lawsuit.getStatus() == LawsuitStatus.SUING_WON) {
                        concludedLawsuits.add(lawsuit.getId());
                        QuickSort.sort(concludedLawsuits);
                        assignedLawsuits.removeIf(integer -> integer.equals(lawsuit.getId()));
                        QuickSort.sort(assignedLawsuits);
                        systemClassRefReference.pollHighestPriorityLawsuit(this.id);
                    }
                    return;
                case 0:
                    return;
                default:
                    System.out.println(Utils.INVALID_CHOICE);
                    break;
            }
        }
    }

    /**
     * Changes status of given lawsuit
     * @param lawsuit lawsuit
     */
    private void changeStatus(SystemClass systemClassRefReference,Lawsuit lawsuit) {
        while (true) {
            System.out.println("1. "+LawsuitStatus.SUING_WON.getStatus());
            System.out.println("2. "+LawsuitStatus.SUED_WON.getStatus());
            System.out.println("0. Go Back");

            System.out.println("Choice:");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println(Utils.INVALID_INPUT);
                continue;
            }
            if (choice == 0) {
                return;
            }
            if (!(choice == 1 || choice == 2))
            {
                System.out.println(Utils.INVALID_CHOICE);
                continue;
            }

            String courtRecord;
            System.out.print("Enter court record: ");
            courtRecord = Utils.readStringInput();
            lawsuit.addCourtRecord(courtRecord);

            if (choice == 1) {
                lawsuit.setStatus(LawsuitStatus.SUING_WON);
                break;
            }
            if (choice == 2) {
                lawsuit.setStatus(LawsuitStatus.SUED_WON);
                break;
            }
        }
    }

    /**
     * Assigns given lawsuit to judge
     * @param lawsuit lawsuit
     */
    public void assignLawsuit(Integer lawsuit) {
        assignedLawsuits.add(lawsuit);
        QuickSort.sort(assignedLawsuits);
    }

    /**
     * Menu for Judge User
     * @param systemClassRef System object that holds objects by id
     */
    @Override
    public void menu(SystemClass systemClassRef) {
        System.out.println("\n--- Judge Menu ---");
        while (true) {
            System.out.println("\n1. Show Assigned Lawsuits");
            System.out.println("2. Show Concluded Lawsuits");
            System.out.println("3. Conclude a Lawsuit");
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
                    showAssignedLawsuits(systemClassRef);
                    break;
                case 2:
                    showConcludedLawsuits(systemClassRef);
                    break;
                case 3:
                    concludeLawsuit(systemClassRef);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
    public void citizenMenu(SystemClass systemClassRef)
    {
        super.menu(systemClassRef);
    }
}
