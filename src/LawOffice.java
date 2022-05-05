import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LawOffice
{
    public static class JobAdvertisement
    {
        private int ownerId;
        private String title;
        private String description;

        public JobAdvertisement(int ownerId, String title, String description) {
            this.ownerId = ownerId;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException();
        }
    }

    private String name;
    private int ownerId;

    private List<Integer> employeeIds = new LinkedList<>();
    private List<JobAdvertisement> jobAdvertisements = new LinkedList<>();
    private Queue<Lawyer.JobApplication> jobApplications = new LinkedList<>();

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
        // Check if it already exists, if it is return null / Or change return type to boolean
        employeeIds.add(employeeId);
        return employeeId;
    }

    public int removeEmployee(int index) {
        // Check if it already exists, if it is return null / Or change return type to boolean
        return employeeIds.remove(index);
    }

    public List<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public JobAdvertisement createJobAdvertisement(int id, String title, String description) {
        return new JobAdvertisement(id, title, description);
    }
    
    public void addJobAdvertisement(JobAdvertisement jobAdvertisement) {
        jobAdvertisements.add(jobAdvertisement);
    }
    
    public JobAdvertisement removeJobAdvertisement(int index) {
        return jobAdvertisements.remove(index);
    }

    public Queue<Lawyer.JobApplication> getJobApplications() {
        return jobApplications;
    }
}
