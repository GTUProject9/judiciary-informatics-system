import enums.JobApplicationStatus;

import java.util.*;

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
        name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        ownerId = ownerId;
    }

    public void addEmployee(int employeeId) {
        if (!employeeIds.contains(employeeId)) {
            employeeIds.add(employeeId);
        }
    }

    public int removeEmployee(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < employeeIds.size()) {
            return employeeIds.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getEmployee(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < employeeIds.size()) {
            return employeeIds.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Iterator<Integer> getEmployeeIdsIterator() {
        return employeeIds.iterator();
    }

    public boolean areThereEmployees() {
        return employeeIds.size() > 0;
    }

    public LawOffice.JobAdvertisement createJobAdvertisement(int id, String title, String description) {
        return new LawOffice.JobAdvertisement(id, title, description);
    }
    
    public void addJobAdvertisement(LawOffice.JobAdvertisement jobAdvertisement) {
        jobAdvertisements.add(jobAdvertisement);
    }
    
    public LawOffice.JobAdvertisement removeJobAdvertisement(int index)  throws IndexOutOfBoundsException {
        if (index >= 0 && index < jobAdvertisements.size()) {
            return jobAdvertisements.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Iterator<JobAdvertisement> getJobAdvertisementIterator() {
        return jobAdvertisements.iterator();
    }

    public void addJobApplication(Lawyer.JobApplication jobApplication) {
        jobApplications.add(jobApplication);
    }

    public Lawyer.JobApplication getJobApplication(int index) {
        if (index >= 0 && index < jobApplications.size()) {
            return jobApplications.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Iterator<Lawyer.JobApplication> getJobApplicationIterator() {
        return jobApplications.iterator();
    }

    public boolean areThereJobApplications() {
        return jobApplications.size() > 0;
    }

    public void displayEmployees(SystemClass systemClassObject)
    {
        for (int i = 0; i < employeeIds.size(); i++) {
            System.out.println((i + 1) + ". " + systemClassObject.getLawyer(employeeIds.get(i)));
        }
    }

    public void displayJobApplications() {
        int i = 0;
        for (var jobApplication : jobApplications) {
            if (jobApplication.getStatus() == JobApplicationStatus.PENDING) {
                System.out.println((i + 1) + ". " + jobApplication);
            } else {
                System.out.println("Completed job application.");
            }
            i++;
        }
    }
}
