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

        /**
         * This function sets the status of the job application
         * 
         * @param status The status of the job application.
         */
        public void setStatus(JobApplicationStatus status) {
            this.status = status;
        }

        /**
         * This function returns the ownerId of the current object
         * 
         * @return The ownerId is being returned.
         */
        public int getOwnerId() {
            return ownerId;
        }

        /**
         * This function returns the applicantId
         * 
         * @return The applicantId is being returned.
         */
        public int getApplicantId() {
            return applicantId;
        }

        /**
         * This function returns the application name
         * 
         * @return The application name.
         */
        public String getApplication() {
            return application;
        }

        /**
         * This function returns the status of the job application
         * 
         * @return The status of the job application.
         */
        public JobApplicationStatus getStatus() {
            return status;
        }

        /**
         * The function returns a string that contains the ownerId, applicantId, application, and
         * status of the job application
         * 
         * @return The toString() method is being returned.
         */
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
        System.out.print("\nEnter employer ID to apply for job (0 to exit): ");
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

        LawOfficeOwner theOwner = systemClassRef.getLawOfficeOwner(ownerId);
        if (theOwner == null) {
            System.out.println("No such employer exists.");
            return;
        }

        System.out.print("Enter application text: ");
        String application = Utils.readStringInput();
        JobApplication jobApplication = createJobApplication(ownerId, id, application);
        systemClassRef.addJobApplication(jobApplication);
        jobApplications.add(jobApplication);
        System.out.println("\nJob application completed.");
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
        System.out.println("0. Go back");
        System.out.println("Select a lawsuit: ");
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
        int lawsuitId;
        try {
            lawsuitId = (int) continuingLawsuits.toArray()[choice - 1];
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

        System.out.println("\nDefense added to the lawsuit.");
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
            return;
        }
        System.out.println("You are already a state attorney.");
    }


    /**
     * This function prints out the job applications in the jobApplications array list
     */
    public void viewJobApplications(){
        if (jobApplications.isEmpty()) {
            System.out.println("You have no job applications.");
            return;
        }
        int i = 0;
        System.out.println("Job applications:");
        for(JobApplication jobApplication : jobApplications){
            System.out.println((i + 1) + ".\n" + jobApplication);
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
        Object[] lawsuits = continuingLawsuits.toArray();
        for(int i = 0; i < lawsuits.length; i++){
            System.out.println((i + 1) + ".\n" + systemClassRef.getLawsuit((int) lawsuits[i]));
        }
    }   

    /**
     * It prints out the concluded lawsuits
     * 
     * @param systemClassRef is an object of the class SystemClass.
     */
    private void displayConcludedLawsuits(SystemClass systemClassRef) {
        System.out.println("Concluded Lawsuits");
        int i = 0;
        for(Integer lawsuitId : concludedLawsuits){
            System.out.println((i + 1) + ".\n" + systemClassRef.getLawsuit(lawsuitId));
            i++;
        }
        if (i == 0)
            System.out.println("No lawsuits have been concluded.");
    }

    /**
     * This function sets the stateAttorney variable to the value of the stateAttorney parameter
     * 
     * @param stateAttorney boolean
     */
    public void setStateAttorney(boolean stateAttorney) {
        this.stateAttorney = stateAttorney;
    }

    /**
     * This function returns a boolean value that indicates whether or not the lawyer accepts lawsuits
     * 
     * @return The boolean value of acceptsLawsuits.
     */
    public boolean acceptsLawsuits() {
        return acceptsLawsuits;
    }

    /**
     * This function sets the value of the boolean variable acceptsLawsuits to the value of the boolean
     * variable passed in as a parameter
     * 
     * @param acceptsLawsuits true if the lawyer accepts lawsuits, false otherwise
     */
    public void setAcceptsLawsuits(boolean acceptsLawsuits) {
        this.acceptsLawsuits = acceptsLawsuits;
    }

    /**
     * This function adds a lawsuit to the list of continuing lawsuits
     * 
     * @param lawsuitId The id of the lawsuit
     */
    public void addLawsuit(Integer lawsuitId) {
        continuingLawsuits.add(lawsuitId);
    }

    /**
     * This function returns the employerId
     * 
     * @return The employerId is being returned.
     */
    public Integer getEmployerId() {
        return employerId;
    }

    /**
     * This function sets the employerId of the object to the employerId passed in as a parameter
     * 
     * @param employerId The employer id of the employer who is logged in.
     */
    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    /**
     * It removes the lawsuit from the continuingLawsuits list and adds it to the concludedLawsuits
     * list.
     * 
     * @param lawsuitId The id of the lawsuit to conclude.
     */
    public void concludeLawsuit(Integer lawsuitId) {
        continuingLawsuits.remove(lawsuitId);
        concludedLawsuits.add(lawsuitId);
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
     * It prints a menu, reads an integer input, and then sets a boolean variable to true or false
     * depending on the input
     */
    private void acceptsLawsuitsMenu() {
        System.out.println("\nYou are currently " + (acceptsLawsuits ? "accepting" : "not accepting") + " lawsuits.");
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
    
    /**
     * This function is a menu for the citizen class
     * 
     * @param systemClassRef This is a reference to the system class.
     */
    public void citizenMenu(SystemClass systemClassRef)
    {
        super.menu(systemClassRef);
    }
}
