import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LawOffice
{
    public static class JobAdvertisement
    {
        public int ownerId;
        public String title;
        public String description;

        public JobAdvertisement(int ownerId, String title, String description) {
            this.ownerId = ownerId;
            this.title = title;
            this.description = description;
        }
        public int getOwnerId() {
            return ownerId;
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException();
        }
    }

    private String name;
    private int ownerId;

    private List<Integer> employeeIds = new ArrayList<>();
    private List<JobAdvertisement> jobAdvertisements = new LinkedList<>();
    private List<Lawyer.JobApplication> jobApplications = new ArrayList<>();

    public LawOffice()
    {
        name = "";
        ownerId = -1;
    }

    public LawOffice(String name, int ownerId)
    {
        this.name = name;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public int addEmployee(int employeeId) {
        // Check if it already exist, if it is return null / Or change return type to boolean
        employeeIds.add(employeeId);
        return employeeId;
    }

    public int removeEmployee(int index) {
        // Check if it already exist, if it is return null / Or change return type to boolean
        return employeeIds.remove(index);
    }

    public List<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public LawOffice.JobAdvertisement createJobAdvertisement(int id, String title, String description) {
        return new LawOffice.JobAdvertisement(id, title, description);
    }
    
    public void addJobAdvertisement(LawOffice.JobAdvertisement jobAdvertisement) {
        this.jobAdvertisements.add(jobAdvertisement);
    }
    
    public LawOffice.JobAdvertisement removeJobAdvertisement(int index) {
        return this.jobAdvertisements.remove(index);
    }

    public void addJobApplication(Lawyer.JobApplication jobApplication) {
        this.jobApplications.add(jobApplication);
    }

    public List<Lawyer.JobApplication> getJobApplications() {
        return jobApplications;
    }

    public Lawyer.JobApplication getJobApplication(int index) {
        return jobApplications.get(index);
    }

    public void displayEmployees(SystemClass systemClassObject)
    {
        for (int i = 0; i < employeeIds.size(); i++)
        {
            System.out.println(i + ". " + systemClassObject.getLawyer(employeeIds.get(i)));
        }
    }

    public int getEmployee(int index) {
        return employeeIds.get(index);
    }

    
}
