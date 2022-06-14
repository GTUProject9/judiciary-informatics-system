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
            return "Job Advertisement \nownerId=" + ownerId + ", \ntitle=" + title + ", \ndescription=" + description;
        }
    }

    private String name;
    private int ownerId;

    private List<Integer> employeeIds = new ArrayList<>();
    private List<JobAdvertisement> jobAdvertisements = new LinkedList<>();
    private List<Lawyer.JobApplication> jobApplications = new ArrayList<>();

    // A constructor.
    public LawOffice()
    {
        name = "";
        ownerId = -1;
    }

    // A constructor.
    public LawOffice(String name, int ownerId)
    {
        this.name = name;
        this.ownerId = ownerId;
    }

    /**
     * This function returns the name of the person
     * 
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * It does nothing
     * 
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        name = name;
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
     * It sets the ownerId to the value of ownerId
     * 
     * @param ownerId The ID of the owner of the repository.
     */
    public void setOwnerId(int ownerId) {
        ownerId = ownerId;
    }

    /**
     * If the employeeIds list does not contain the employeeId, then add the employeeId to the list
     * 
     * @param employeeId The id of the employee to add to the department.
     */
    public void addEmployee(int employeeId) {
        if (!employeeIds.contains(employeeId)) {
            employeeIds.add(employeeId);
        }
    }

    /**
     * Remove employee by index
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public int removeEmployee(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < employeeIds.size()) {
            return employeeIds.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * If the index is valid, return the employeeId at that index. Otherwise, throw an
     * IndexOutOfBoundsException
     * 
     * @param index The index of the employee to get.
     * @return The employeeIds.get(index) is being returned.
     */
    public int getEmployee(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < employeeIds.size()) {
            return employeeIds.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * This function returns an iterator that can be used to iterate over the employeeIds list.
     * 
     * @return An iterator object.
     */
    public Iterator<Integer> getEmployeeIdsIterator() {
        return employeeIds.iterator();
    }

    /**
     * This function returns true if there are employees in the company, and false otherwise.
     * 
     * @return A boolean value.
     */
    public boolean areThereEmployees() {
        return employeeIds.size() > 0;
    }

    /**
     * This function creates a new job advertisement with the given id, title, and description
     * 
     * @param id The id of the job advertisement.
     * @param title The title of the job advertisement.
     * @param description The description of the job advertisement.
     * @return A new instance of the JobAdvertisement class.
     */
    public LawOffice.JobAdvertisement createJobAdvertisement(int id, String title, String description) {
        return new LawOffice.JobAdvertisement(id, title, description);
    }
    
    /**
     * This function adds a job advertisement to the list of job advertisements
     * 
     * @param jobAdvertisement The job advertisement to be added to the list of job advertisements.
     */
    public void addJobAdvertisement(LawOffice.JobAdvertisement jobAdvertisement) {
        jobAdvertisements.add(jobAdvertisement);
    }
    
    /**
     * This function removes a job advertisement from the list of job advertisements
     * 
     * @param index The index of the element to remove.
     * @return The job advertisement that was removed.
     */
    public LawOffice.JobAdvertisement removeJobAdvertisement(int index)  throws IndexOutOfBoundsException {
        if (index >= 0 && index < jobAdvertisements.size()) {
            return jobAdvertisements.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * This function returns an iterator for the job advertisements
     * 
     * @return An iterator of the job advertisements.
     */
    public Iterator<JobAdvertisement> getJobAdvertisementIterator() {
        return jobAdvertisements.iterator();
    }

    /**
     * This function adds a job application to the list of job applications
     * 
     * @param jobApplication The job application to add.
     */
    public void addJobApplication(Lawyer.JobApplication jobApplication) {
        jobApplications.add(jobApplication);
    }

    /**
     * If the index is valid, return the job application at that index. Otherwise, throw an exception
     * 
     * @param index The index of the job application to return.
     * @return The job application at the specified index.
     */
    public Lawyer.JobApplication getJobApplication(int index) {
        if (index >= 0 && index < jobApplications.size()) {
            return jobApplications.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * This function removes a job application from the list of job applications
     * 
     * @param index The index of the job application to remove.
     * @return The job application that was removed.
     */
    public Lawyer.JobApplication removeJobApplication(int index)  throws IndexOutOfBoundsException {
        if (index >= 0 && index < jobApplications.size()) {
            return jobApplications.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * "This function returns an iterator that iterates over the jobApplications list."
     * 
     * The function is called getJobApplicationIterator() because it returns an iterator that iterates
     * over the jobApplications list
     * 
     * @return An iterator of the jobApplications list.
     */
    public Iterator<Lawyer.JobApplication> getJobApplicationIterator() {
        return jobApplications.iterator();
    }

    /**
     * Return if there is any job applications.
     * 
     * @return true if there is.
     */
    public boolean areThereJobApplications() {
        return jobApplications.size() > 0;
    }

    /**
     * This function displays the employees of a given department
     * 
     * @param systemClassRef This is a reference to the SystemClass object.
     */
    public void displayEmployees(SystemClass systemClassRef)
    {
        for (int i = 0; i < employeeIds.size(); i++) {
            System.out.println((i + 1) + ". " + systemClassRef.getLawyer(employeeIds.get(i)));
        }
    }

    /**
     * This function displays the job applications that are pending
     */
    public void displayJobApplications() {
        int i = 0;
        for (var jobApplication : jobApplications) {
            if (jobApplication.getStatus() == JobApplicationStatus.PENDING) {
                System.out.println((i + 1) + ". " + jobApplication + "\n");
            } else {
                System.out.println("Completed job application.");
            }
            i++;
        }
    }
}
