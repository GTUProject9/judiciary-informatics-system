import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import enums.LawsuitStatus;

/**
 * Class for Judge User
 */
public class Judge extends Citizen{

    // Creating a list of cases that the judge is assigned to.
    private List<Integer> assignedLawsuits;

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
     */
    private void showAssignedLawsuits(SystemClass systemClassObjectReference) {
        for (int i = 0; i < assignedLawsuits.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + 
                               systemClassObjectReference.getSystemObject(assignedLawsuits.get(i)));
        }
        System.out.println(assignedLawsuits);
    }

    /**
     * Displays concluded lawsuits to judge
     */
    private void showConcludedLawsuits(SystemClass systemClassObjectReference) {
        for (int i = 0; i < concludedLawsuits.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + 
                               systemClassObjectReference.getSystemObject(concludedLawsuits.get(i)));
        }
        System.out.println(concludedLawsuits);
    }

    /**
     * Concludes given lawsuit
     * @param lawsuit lawsuit
     */
    private void concludeLawsuit(SystemClass systemClassObjectReference) {
        // Casefile'i olmayan dava sonlandirilamaz.

        Lawsuit lawsuit = systemClassObjectReference.concludeLawsuit(id);
        lawsuit.concludeLawsuit(LawsuitStatus.SUING_WON);

        String caseFile = "Judge case file olusturup bunu lawsuit'e ekler.";
        lawsuit.addCaseFile(caseFile);
        ListIterator<Integer> listIterator = assignedLawsuits.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().equals(lawsuit.getId())) {
                listIterator.remove();
            }
        }

        concludedLawsuits.add(lawsuit.getId());
    }

    /**
     * Assigns given lawsuit to judge
     * @param lawsuit lawsuit
     */
    public void assignLawsuit(Integer lawsuit) {
        assignedLawsuits.add(lawsuit);
    }

    @Override
    public void menu(SystemClass systemClassObject) {
        assignLawsuit(10001);
        assignLawsuit(10002);
        assignLawsuit(10003);
        assignLawsuit(10004);
        concludeLawsuit(systemClassObject);
    }
}
