import java.util.Iterator;
import java.util.Scanner;

import enums.JobApplicationStatus;

public class LawOfficeOwner extends Lawyer{

    //datafield of lawyers
    //datafield of advert
    private LawOffice lawOffice;

    public LawOfficeOwner(int id, String password, String name, String surname, String email, 
                          String phone,String lawOfficeName) 
    {
        super(id, password, name, surname,  email, phone);
        lawOffice = new LawOffice(lawOfficeName, id);
    }

    /**
     *This function returns the advertisement content to the system.
     *The system saves the advertisement to the system using the officeId and the advertisement message.
     */
    public void publishAdvertisementForLawyers(SystemClass systemClassObject) 
    {
        // Sistem classindaki jobAdvertisementsReferences'a da olusturulan ilan eklenmeli,
        // Aksi taktirde is arayan avukarlar tum owner'lari gezmek zorunda kalacak.
        //input title and description
        // systemClassObject.getJobAdvertisementsReferences()

        // Create a new job advertisement
        String title = "Title";
        String description = "Description";
        
        LawOffice.JobAdvertisement jobAdvertisement = lawOffice.createJobAdvertisement(id, title, description);
        systemClassObject.addJobAdvertisement(jobAdvertisement);

    }

    public LawOffice getOffice() {
        return lawOffice;
    }


    private void displayJobApplications(){
        //get JobAdvertisement first
        //then get JobApplication from JobAdvertisement

        Iterator iterator = lawOffice.getJobApplications().iterator();
        int i = 0;
        while (iterator.hasNext())
        {
            Lawyer.JobApplication jobApplication = (Lawyer.JobApplication)iterator.next();
            if (jobApplication.getStatus() == JobApplicationStatus.PENDING)
            {
                System.out.println(i + ". " + jobApplication);
            }
            else
            {
                System.out.println("Completed job application.");
            }
            i++;
        }
    }

    public void assignJobsToEmployees(SystemClass systemClassObject){
        //display lawyer.
        //select a lawyer.
        //assign a jop to lawyer.

        // Lawyer olan owner, kendisinin devam eden davalari calisanlarina assign edebilir.
        int assignedLawsuitId = continuingLawsuits.remove(0);
        Lawsuit lawsuit = systemClassObject.getLawsuit(assignedLawsuitId);

        int employeeId = lawOffice.getEmployee(0);
        Lawyer employee = systemClassObject.getLawyer(employeeId);

        employee.addLawsuit(assignedLawsuitId);
        if (lawsuit.getSuingLawyer() == id)
        {
            lawsuit.setSuingLawyer(employeeId);
        }
        else
        {
            lawsuit.setSuedLawyer(employeeId);
        }
    }

    private void hireALawyer(SystemClass systemClassObject){
        //display advert and applicant
        //select one
        // change status to approved

        displayJobApplications();
        
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter the index ");
        int index = 0;

        Lawyer.JobApplication approvedjobApplication = lawOffice.getJobApplication(index);
        int applicantId = approvedjobApplication.getApplicantId();

        Lawyer lawyer = systemClassObject.getLawyer(applicantId);
        lawyer.setEmployerId(id);
        Iterator iterator = lawOffice.getJobApplications().iterator();
        while (iterator.hasNext())
        {
            Lawyer.JobApplication jobApplication = (Lawyer.JobApplication) iterator.next();
            if (jobApplication.getApplicantId() == applicantId)
            {
                jobApplication.setStatus(JobApplicationStatus.ACCEPTED);
            }
            else if (jobApplication.getStatus() == JobApplicationStatus.PENDING)
            {
                jobApplication.setStatus(JobApplicationStatus.CANCELLED);
            }
        }
        
        lawOffice.addEmployee(applicantId);

    }
    
    private void rejectJobApplication(SystemClass systemClassObject)
    {
        displayJobApplications();
        
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter the index ");
        int index = 0;

        Lawyer.JobApplication rejectedJobApplication = lawOffice.getJobApplication(index);
        rejectedJobApplication.setStatus(JobApplicationStatus.REJECTED);
    }
    
    private void fireALawyer(SystemClass systemClassObject)
    {
        //display lawyers.
        //select one.
        //delete from lawyer datafield and change jop status of lawyer.

        // Display lawyers
        // lawOffice.displayEmployees(systemClassObject);
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter the index ");
        int index = 0;

        Lawyer lawyer = systemClassObject.getLawyer(lawOffice.getEmployee(index));
        lawyer.setEmployerId(null);
        lawOffice.removeEmployee(index);
    }

    @Override
    public void menu(SystemClass systemClassObject) {
    }
}
