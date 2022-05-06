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

        public String setStatus(JobApplicationStatus status) {
            this.status = status;
            return status.getStatus();
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException();
        }
    }

    private boolean stateAttorney;
    //applyed job advert data field
    //clients request data field
    //lawyer cases data field

    private List<JobApplication> jobApplications = new LinkedList<>();

    private Integer employerId = null; 

    public Lawyer() {}

    public Lawyer(int id,String password,String name,String surname, String email,String phone){
        super(id, password, name, surname,  email, phone);
        stateAttorney = false;
    }

    public Lawyer(int id,String password,String name,String surname, String email,String phone, boolean stateAttorney){
       super(id, password, name, surname,  email, phone);
       this.stateAttorney = stateAttorney;
    }

    public void viewJobsRequests(){

    }

    public void editAndViewClients(){

    }

    //viewApprovedCases->viewCases
    public void viewCases(){

    }

    private void viewApprovedCases(){

    }
    
    private void viewOnHoldCases(){

    }

    /**
     * This function displays the lawyer's active cases.
     * Allows the defense to add to the selected case.
     *
     */
    public void addDefenseToTheCase(){
        //display cases of lawyer.
        //select one.
        //input text.
        //add test to selected case.
    }

    /**
     * This function takes the data structure where job postings are kept.
     * It displays these and allows the lawyer to apply to one of them.
     * @param Job adverts data structure.
     */
    public void applyForJobs(){
        // Menuden, systemClassObject referansi alacak
        //Display job advert.
        //Select one

        //Create a JobApplication, find owner, add reference to its LawOffice jobApplications also.
    }
    
    public JobApplication createJobApplication(int ownerId, int applicantId, String application){
        return new JobApplication(ownerId, applicantId, application);
    }

    public void viewJobApplications(){

    }

    public void startToAJob(){
        // Change status of other job applications to Cancelled.
    }

    /*
    * Note !!
    * job postings must keep a list of applicants for that job.
    * */
    public void editJobStatus(boolean stateAttorney){
        this.stateAttorney = stateAttorney;
    }

    /**
     Citizens can access the archive. That's why the parent function is used.
     * @param archive data struture.
     */
    public void accesToTheArchive(){
        
    }


    public boolean getStateAttorney()
    {
        return stateAttorney;
    }
    public void setStateAttorney(boolean stateAttorney) {
        this.stateAttorney = stateAttorney;
    }
    
    @Override
    public void menu(SystemClass systemClassObject) {
        
    }
}
