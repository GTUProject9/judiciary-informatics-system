import java.util.*;

import enums.JobApplicationStatus;

public class Lawyer extends Citizen {
    
    public static class JobApplication
    {
        private int ownerId;
        private int applicantId;
        private String application;
        private JobApplicationStatus status;

        public JobApplication(int ownerId, int applicantId, String application) {
            this.ownerId = ownerId;
            this.applicantId = applicantId;
            this.application = application;
            status = JobApplicationStatus.PENDING;
        }

        public void setStatus(JobApplicationStatus status) {
            this.status = status;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public int getApplicantId() {
            return applicantId;
        }

        public String getApplication() {
            return application;
        }

        public JobApplicationStatus getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "JobApplication \nownerId=" + ownerId + ", \napplicantId=" + applicantId +
                                    ", \napplication=" + application + ", \nstatus=" + status;
        }
    }
    
    protected boolean stateAttorney;
    protected boolean acceptsLawsuits;
    protected Integer employerId;
    protected List<JobApplication> jobApplications = new LinkedList<>();
    protected TreeSet<Integer> concludedLawsuits =new TreeSet<>();
    protected TreeSet<Integer> continuingLawsuits =new TreeSet<>();

    // A constructor.
    public Lawyer(int id,String password,String name,String surname, String email,String phone){
        super(id, password, name, surname,  email, phone);
        stateAttorney = false;
        acceptsLawsuits = false;
    }

    // A constructor.
    public Lawyer(int id,String password,String name,String surname, String email,
                  String phone, boolean stateAttorney, boolean acceptsLawsuits){
        super(id, password, name, surname,  email, phone);
        this.stateAttorney = stateAttorney;
        this.acceptsLawsuits = acceptsLawsuits;
    }

    /**
     * This function takes the data structure where job postings are kept.
     * It displays these and allows the lawyer to apply to one of them.
     * @param systemClassRef This is a reference to the system class.
     */
    public void applyForJobs(SystemClass systemClassRef){
        systemClassRef.displayJobAdvertisements();
        System.out.print("Enter employer ID to apply for job (0 to exit): ");
        int ownerId;
        try {
            ownerId = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (ownerId == 0) {
            return;
        }

        System.out.print("Enter application text: ");
        String application = Utils.readStringInput();
        JobApplication jobApplication = createJobApplication(ownerId, id, application);
        systemClassRef.addJobApplication(jobApplication);

        System.out.println("Job application completed.");
    }

    /**
     * This function creates a new job application
     * 
     * @param ownerId The id of the user who owns the job
     * @param applicantId The id of the user who is applying for the job
     * @param application The application text
     * @return A new JobApplication object is being returned.
     */
    private static JobApplication createJobApplication(int ownerId, int applicantId, String application){
        return new JobApplication(ownerId, applicantId, application);
    }

    /**
     * This function displays the lawyer's active cases.
     * Allows the defense to add to the selected case.
     */
    public void addDefenseToTheLawsuit(SystemClass systemClassRef){
        displayContinuingLawsuits(systemClassRef);
        System.out.println("Please enter the lawsuit ID: ");
        int lawsuitId;
        try {
            lawsuitId = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }

        Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);
        if (lawsuit == null) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }
        if (!continuingLawsuits.contains(lawsuitId)) {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        System.out.println("Please enter the defense: ");
        String defense = Utils.readStringInput();

        if (lawsuit.getSuingLawyer() == id) {
            lawsuit.setSuingDefence(defense);
        }
        else {
            lawsuit.setSuedDefence(defense);
        }
    }

    /**
     * If it is not already a state attorney, apply for being state attorney
     * 
     * @param systemClassRef The object of the SystemClass class.
     */
    public void applyForBeingStateAttorney(SystemClass systemClassRef){
        // If it is not already a state attorney, apply for being one.
        if(!stateAttorney){
            systemClassRef.addStateAttorneyApplicant(this);
            System.out.println("You have applied for being a state attorney.");
        }
    }


    /**
     * This function prints out the job applications in the jobApplications array list
     */
    public void viewJobApplications(){
        int i = 1;
        System.out.println("Job applications:");
        for(JobApplication jobApplication : jobApplications){
            System.out.println(i + ".\n" + jobApplication);
            i++;
        }
    }
    
    
    /**
     * This function is used to access the archive.
     * 
     * @param systemClassRef The object of the class that you want to access the archive from.
     */
    public void accessToTheArchive(SystemClass systemClassRef){
        systemClassRef.archiveMenu();
    }

    /**
     * This function returns the stateAttorney boolean variable
     * 
     * @return The stateAttorney variable is being returned.
     */
    public boolean isStateAttorney()
    {
        return stateAttorney;
    }

    /**
     * It displays the continuing lawsuits of a lawyer
     * 
     * @param systemClassRef The object of the SystemClass class.
     */
    private void displayContinuingLawsuits(SystemClass systemClassRef) {
        System.out.println("Continuing Lawsuits");
        int i = 1;
        for(Integer lawsuitId : continuingLawsuits){
            System.out.println(i + ".\n" + systemClassRef.getLawsuit(lawsuitId));
            i++;
        }
    }   

    /**
     * It prints out the concluded lawsuits
     * 
     * @param systemClassRef is an object of the class SystemClass.
     */
    private void displayConcludedLawsuits(SystemClass systemClassRef) {
        System.out.println("Concluded Lawsuits");
        int i = 1;
        for(Integer lawsuitId : concludedLawsuits){
            System.out.println(i + ".\n" + systemClassRef.getLawsuit(lawsuitId));
            i++;
        }
    }

    /**
     * This function sets the stateAttorney variable to the value of the stateAttorney parameter
     * 
     * @param stateAttorney boolean
     */
    public void setStateAttorney(boolean stateAttorney) {
        this.stateAttorney = stateAttorney;
    }

    public boolean acceptsLawsuits() {
        return acceptsLawsuits;
    }

    public void setAcceptsLawsuits(boolean acceptsLawsuits) {
        this.acceptsLawsuits = acceptsLawsuits;
    }

    public void addLawsuit(Integer lawsuitId) {
        continuingLawsuits.add(lawsuitId);
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private void acceptsLawsuitsMenu() {
        System.out.println("You are currently " + (acceptsLawsuits ? "accepting" : "not accepting") + " lawsuits.");
        System.out.println("1. Accept lawsuits");
        System.out.println("2. Does not accept lawsuits");
        System.out.println("0. Back");
        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        switch (choice) {
            case 1:
                acceptsLawsuits = true;
                break;
            case 2:
                acceptsLawsuits = false;
                break;
            case 0:
                return;
            default:
                System.out.println(Utils.INVALID_CHOICE);
                acceptsLawsuitsMenu();
                break;
        }
    }

    /**
     * The function is a menu for the lawyer, it has 8 options, each option calls a different function.
     * 
     * @param systemClassRef is a reference to the system class.
     */
    @Override
    public void menu(SystemClass systemClassRef) {

        System.out.println("\n--- Lawyer Menu ---");
        while(true){
            System.out.println("\n1. Apply for jobs");
            System.out.println("2. Add Defense To The Law Suit");
            System.out.println("3. Apply For Being State Attorney");
            System.out.println("4. View Job Applications");
            System.out.println("5. Access To The Archive");
            System.out.println("6. View continuing lawsuits");
            System.out.println("7. View concluded lawsuits");
            System.out.println("8. Change accepting lawsuits status");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println(Utils.INVALID_INPUT);
                continue;
            }
            switch (choice){
                case 1:
                    applyForJobs(systemClassRef);
                    break;
                case 2:
                    addDefenseToTheLawsuit(systemClassRef);
                    break;
                case 3:
                    applyForBeingStateAttorney(systemClassRef);
                    break;
                case 4:
                    viewJobApplications();
                    break;
                case 5:
                    accessToTheArchive(systemClassRef);
                    break;
                case 6:
                    displayContinuingLawsuits(systemClassRef);
                    break;
                case 7:
                    displayConcludedLawsuits(systemClassRef);
                    break;
                case 8:
                    acceptsLawsuitsMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    
    public void citizenMenu(SystemClass systemClassRef)
    {
        super.menu(systemClassRef);
    }
}
