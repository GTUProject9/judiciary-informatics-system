import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    protected List<Integer> continuingLawsuits = new LinkedList<>();
    protected List<Integer> concludedLawsuits = new LinkedList<>();

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

        Integer lawsuitId = continuingLawsuits.get(0);
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
    }

    public void applyForBeingStateAttorney(SystemClass systemClassObject){
        //state attorney degilse basvurabilsin
        systemClassObject.addStateAttorneyApplicant(this);
    }

    /**
     * This function takes the data structure where job postings are kept.
     * It displays these and allows the lawyer to apply to one of them.
     * @param Job adverts data structure.
     */
    public void applyForJobs(SystemClass systemClassObject){
        // Menuden, systemClassObject referansi alacak
        //Display job advert.
        //Select one

        //Create a JobApplication, find owner, add reference to its LawOffice jobApplications also.

        systemClassObject.displayJobAdvertisements();
        
        int employerId = systemClassObject.getEmployerId(0);
        // Scanner scanner = new Scanner(System.in);
        JobApplication jobApplication = new JobApplication(id, 0, "");

    }
    
    public JobApplication createJobApplication(int ownerId, int applicantId, String application){
        return new JobApplication(ownerId, applicantId, application);
    }

    public void viewJobApplications(){

    }

    /**
     Citizens can access the archive. That's why the parent function is used.
     * @param archive data struture.
     */
    public void accessToTheArchive(){
        
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
            if(iterator.next() == lawsuitId){
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
        
    }
}
