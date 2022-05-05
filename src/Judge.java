import java.util.LinkedList;
import java.util.List;

/**
 * Class for Judge User
 */
public class Judge extends Citizen{

    // Creating a list of cases that the judge is assigned to.
    private List<Integer> assignedLawsuits;

    // Creating a list of cases those judge concluded.
    private List<Integer> concludedLawsuits;

    // Constructors

    public Judge() {
        super();
        assignedLawsuits = new LinkedList<Integer>();
        concludedLawsuits = new LinkedList<Integer>();
    }

    // Methods

    /**
     * Displays assigned lawsuits to judge
     */
    private void showAssignedLawsuits() {
        System.out.println(assignedLawsuits);
    }

    /**
     * Displays concluded lawsuits to judge
     */
    private void showConcludedLawsuits() {
        System.out.println(concludedLawsuits);
    }

    /**
     * Concludes given lawsuit
     * @param lawsuit lawsuit
     */
    private void concludeLawsuit(Integer lawsuit) {
        assignedLawsuits.remove(lawsuit);
        concludedLawsuits.add(lawsuit);
    }


    /**
     * Assigns given lawsuit to judge
     * @param lawsuit lawsuit
     */
    public void assignLawsuit(Integer lawsuit) {
        assignedLawsuits.add(lawsuit);
    }

    public static void menu(SystemClass systemClassObject) {
        // Burada id ve password istenir.
        int id = 0;
        
        Citizen Judge = (Judge) systemClassObject.getSystemObject(id);

    }
}
