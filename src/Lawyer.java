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
            return "JobApplication [ownerId=" + ownerId + ", applicantId=" + applicantId +
                                    ", application=" + application + ", status=" + status + "]";
        }
    }

    protected boolean stateAttorney;
    //applyed job advert data field
    //clients request data field
    //lawyer cases data field
    protected boolean acceptsLawsuits;

    protected List<JobApplication> jobApplications = new LinkedList<>();
    protected Integer employerId = null;

    protected TreeSet<Integer> continuingLawsuits =new TreeSet();
    protected  TreeSet<Integer> concludedLawsuits =new TreeSet();

    public Lawyer() {}

    public Lawyer(int id,String password,String name,String surname, String email,String phone){
        super(id, password, name, surname,  email, phone);
        stateAttorney = false;
        acceptsLawsuits = false;
    }

    public Lawyer(int id,String password,String name,String surname, String email,String phone, boolean stateAttorney, boolean acceptsLawsuits){
        super(id, password, name, surname,  email, phone);
        this.stateAttorney = stateAttorney;
        this.acceptsLawsuits = acceptsLawsuits;
    }

    /**
     * This function displays the lawyer's active cases.
     * Allows the defense to add to the selected case.
     *
     */
    public void addDefenseToTheLawsuit(SystemClass systemClassObject){
        //display cases of lawyer.
        //select one.
        //input text.
        //add test to selected case.

        //display cases


        //System.out.println("Select Index For Adding Defense To The Lawsuits");
        systemClassObject.addDefenseToTheLawsuit(continuingLawsuits);
        //accessToTheArchive(systemClassObject);

/*
        Iterator iterate_value = continuingLawsuits.iterator();
        Integer lawsuitId = (Integer) iterate_value.next();

        //Integer lawsuitId = continuingLawsuits.get(0);
        Lawsuit lawsuit = systemClassObject.getLawsuit(lawsuitId);
        if (lawsuit.getSuingLawyer() == id)
        {
            lawsuit.setSuingDefence("suing defence");
        }
        else if (lawsuit.getSuedLawyer() == id)
        {
            lawsuit.setSuedDefence("sued defence");
        }
        else
        {
            System.out.println("You are not the owner of this lawsuit.");
        }

        */
    }

    public void applyForBeingStateAttorney(SystemClass systemClassObject){
        //state attorney degilse basvurabilsin
        if(!stateAttorney){
            systemClassObject.addStateAttorneyApplicant(this);
        }
    }

    /**
     * This function takes the data structure where job postings are kept.
     * It displays these and allows the lawyer to apply to one of them.
     * @param /*Job adverts data structure.
     */
    public void applyForJobs(SystemClass systemClassObject){
        // Menuden, systemClassObject referansi alacak
        //Display job advert.
        //Select one

        //Create a JobApplication, find owner, add reference to its LawOffice jobApplications also.



        systemClassObject.displayJobAdvertisements();

        int employerId = systemClassObject.getEmployerId(0);
        int choice = Utils.readIntegerInput();
        JobApplication jobApplication = new JobApplication(id, choice, "");

    }

    public JobApplication createJobApplication(int ownerId, int applicantId, String application){
        return new JobApplication(ownerId, applicantId, application);
    }

    public void viewJobApplications(){
        //iş başcuruları nerede tutuluyor ?
    }

    /**
     Lawyer can access the archive. That's why the parent function is used.
     * @param /*archive data struture.
     */
    public void accessToTheArchive(SystemClass systemClassObject){
        systemClassObject.accessToTheArchive();
    }

    public boolean getStateAttorney()
    {
        return stateAttorney;
    }

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

    public void concludeLawsuit(Integer lawsuitId) {

        Iterator<Integer> iterator = continuingLawsuits.iterator();
        while(iterator.hasNext()){
            if(iterator.next() .equals( lawsuitId)){
                iterator.remove();
            }
        }
        concludedLawsuits.add(lawsuitId);
    }

    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }


    @Override
    public void menu(SystemClass systemClassObject) {

        System.out.println("Lawyer Menu");
        boolean flag = true;
        while(flag){

            System.out.println("1. Apply for jobs");
            System.out.println("2. Add Defense To The Law Suit");
            System.out.println("3. Apply For Being State Attorney");
            System.out.println("4. Create Jop  Application");
            System.out.println("5. View Jop Application");
            System.out.println("6. Access To The Archive");
            System.out.println("7. Get Jop Applications");
            System.out.println("0. For Exit");
            Scanner myObj = new Scanner(System.in);
            int choice = myObj.nextInt();

            switch (choice){
                case 1:
                    applyForJobs(systemClassObject);
                    break;
                case 2:
                    addDefenseToTheLawsuit(systemClassObject);
                    break;
                case 3:
                    applyForBeingStateAttorney(systemClassObject);
                    break;
                case 4:
                    //createJobApplication();
                    break;
                case 5:
                    //viewJobApplications();
                    break;
                case 6:
                    accessToTheArchive(systemClassObject);
                    break;
                case 7:
                    getJobApplications();
                    break;
                case 0:
                    flag=false;
                    break;
                default:
                    System.out.println("Not Supported Operation");

            }
        }

    }
    public void superMenu(SystemClass systemClassObject)
    {
        super.menu(systemClassObject);
    }
}
