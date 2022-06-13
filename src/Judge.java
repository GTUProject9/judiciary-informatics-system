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

    // Constructors
    public Judge() {
        super();
        assignedLawsuits = new ArrayList<>();
        concludedLawsuits = new ArrayList<>();
    }

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

        Lawsuit lawsuit = systemClassRefReference.getHighestPriorityLawsuit(id);
        while (true) {
            System.out.println("Date: "+lawsuit.getDate());
            System.out.println("Lawsuit Type: "+lawsuit.getLawsuitType());
            System.out.println("Status: "+lawsuit.getStatus());
            System.out.println("Defendant: "+lawsuit.getSuingCitizen());
            System.out.println("Defendant's Lawyer: "+lawsuit.getSuingLawyer());
            System.out.println("Prosecutor: "+lawsuit.getSuedCitizen());
            System.out.println("Prosecutor's Lawyer: "+lawsuit.getSuedLawyer());
            System.out.println("\n");
            System.out.println("1. Show Defense of Defendant");
            System.out.println("2. Show Defense of Prosecutor");
            System.out.println("3. Show Court Records");
            System.out.println("4. Conclude Trial");
            System.out.println("5. Exit");
            System.out.println("Choice:");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
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
                    System.out.println("Court Records: "+lawsuit.getCourtRecords());
                    break;
                case 4:
                    changeStatus(lawsuit);
                    if (lawsuit.checkStatus() == LawsuitStatus.SUED_WON || lawsuit.checkStatus() == LawsuitStatus.SUING_WON) {
                        assignedLawsuits.removeIf(integer -> integer.equals(lawsuit.getId()));
                        concludedLawsuits.add(lawsuit.getId());
                        QuickSort.sort(assignedLawsuits);
                        QuickSort.sort(concludedLawsuits);
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    /**
     * Changes status of given lawsuit
     * @param lawsuit lawsuit
     */
    private void changeStatus(Lawsuit lawsuit) {
        while (true) {
            System.out.println("1. "+LawsuitStatus.HOLD.getStatus());
            System.out.println("2. "+LawsuitStatus.STILL_GOING.getStatus());
            System.out.println("3. "+LawsuitStatus.SUING_WON.getStatus());
            System.out.println("4. "+LawsuitStatus.SUED_WON.getStatus());
            System.out.println("Choice:");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }
            String caseFile;
            switch (choice) {
                case 1 -> {
                    lawsuit.setStatus(LawsuitStatus.HOLD);
                    return;
                }
                case 2 -> {
                    lawsuit.setStatus(LawsuitStatus.STILL_GOING);
                    return;
                }
                case 3 -> {
                    do {
                        System.out.println("Enter Case File:");

                        caseFile = Utils.readStringInput();

                        if (caseFile == null || caseFile.isBlank())
                            System.out.println("Invalid Case File.");

                    }while (caseFile == null || caseFile.isBlank());

                    lawsuit.addCaseFile(caseFile);

                    lawsuit.setStatus(LawsuitStatus.SUING_WON);

                    return;
                }
                case 4 -> {
                    do {
                        System.out.println("Enter Case File:");

                        caseFile = Utils.readStringInput();

                        if (caseFile == null || caseFile.isBlank())
                            System.out.println("Invalid Case File.");

                    }while (caseFile == null || caseFile.isBlank());

                    lawsuit.addCaseFile(caseFile);

                    lawsuit.setStatus(LawsuitStatus.SUED_WON);

                    return;
                }
                default -> System.out.println("Invalid selection.");
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
        while (true) {
            System.out.println("1. Show Assigned Lawsuits");
            System.out.println("2. Show Concluded Lawsuits");
            System.out.println("3. Concluded a Lawsuit");
            System.out.println("4. Exit");
            System.out.println("Choice:");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
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
                case 4:
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
    public void superMenu(SystemClass systemClassRef)
    {
        super.menu(systemClassRef);
    }
}
