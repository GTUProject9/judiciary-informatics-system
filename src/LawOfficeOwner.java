import java.util.Iterator;

import enums.JobApplicationStatus;


public class LawOfficeOwner extends Lawyer {

    private final LawOffice lawOffice;

    public LawOfficeOwner(int id, String password, String name, String surname, String email, 
                          String phone,String lawOfficeName) {
        super(id, password, name, surname,  email, phone);
        lawOffice = new LawOffice(lawOfficeName, id);
    }

    /**
     * Creates job advertisement and adds it to the system based on user input.
     * @param systemClassRef the system class reference
     */
    private void publishJobAdvertisement(SystemClass systemClassRef) {
        System.out.print("Enter the title of the job advertisement: ");
        String title = Utils.readStringInput();
        System.out.print("Enter the description of the job advertisement: ");
        String description = Utils.readStringInput();

        // Add the job advertisement to the system.
        LawOffice.JobAdvertisement jobAdvertisement = lawOffice.createJobAdvertisement(id, title, description);
        lawOffice.addJobAdvertisement(jobAdvertisement);
        systemClassRef.addJobAdvertisement(jobAdvertisement);
    }

    protected LawOffice getOffice() {
        return lawOffice;
    }

    /**
     * Lists all the employees and performs an action based on user input.
     * @param systemClassRef the system class reference
     */
    private void employeesMenu(SystemClass systemClassRef) {
        if (!lawOffice.areThereEmployees()) {
            System.out.println("There are no employees.");
            return;
        }

        System.out.println("Choose an employee to perform an action:");
        System.out.println("0. Go back");
        lawOffice.displayEmployees(systemClassRef);

        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (choice == 0) {
            return;
        }

        int employeeIndex = choice - 1;
        int employeeId;
        try {
            employeeId = lawOffice.getEmployee(employeeIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice.");
            return;
        }

        Lawyer employee = systemClassRef.getLawyer(employeeId);

        System.out.println("Choose an action to perform:");
        System.out.println("1. Assign a job");
        System.out.println("2. Fire");

        try {
            choice = Utils.readIntegerInput();
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        switch (choice) {
            case 1:
                assignJobToEmployee(systemClassRef, employee);
                break;
            case 2:
                fireEmployee(employee, employeeIndex);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Assigns a job to the given employee based on user input.
     * @param systemClassRef the system class reference
     * @param employee the employee to assign a job
     */
    private void assignJobToEmployee(SystemClass systemClassRef, Lawyer employee) {
        if (continuingLawsuits.size() == 0) {
            System.out.println("There are no jobs to assign.");
            return;
        }

        // Get a lawsuit from the continuingLawsuits set.
        Iterator<Integer> continuingLawSuitsIter = continuingLawsuits.iterator();
        int lawsuitId = continuingLawSuitsIter.next();
        continuingLawSuitsIter.remove();

        Lawsuit lawsuit = systemClassRef.getLawsuit(lawsuitId);

        // Assign the lawsuit to the employee.
        employee.addLawsuit(lawsuitId);

        if (lawsuit.getSuingLawyer() == id) {
            lawsuit.setSuingLawyer(employee.id);
        } else {
            lawsuit.setSuedLawyer(employee.id);
        }
    }

    /**
     * Fires the given employee.
     * @param employeeIndex the index of the employee to fire
     */
    private void fireEmployee(Lawyer employee, int employeeIndex) {
        employee.setEmployerId(null);
        lawOffice.removeEmployee(employeeIndex);
    }

    /**
     * Lists all the job applications and performs an action based on user input.
     * @param systemClassRef the system class reference
     */
    private void jobApplicationsMenu(SystemClass systemClassRef) {
        if (!lawOffice.areThereJobApplications()) {
            System.out.println("There are no job applications.");
            return;
        }

        System.out.println("Choose a job application to perform an action.");
        System.out.println("0. Go back");
        lawOffice.displayJobApplications();

        int choice;
        try {
            choice = Utils.readIntegerInput();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (choice == 0) {
            return;
        }

        int jobApplicationIndex = choice - 1;
        Lawyer.JobApplication jobApplication;
        try {
            jobApplication = lawOffice.getJobApplication(jobApplicationIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Choose an action to perform:");
        System.out.println("1. Accept");
        System.out.println("2. Reject");

        try {
            choice = Utils.readIntegerInput();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        switch (choice) {
            case 1:
                acceptJobApplication(systemClassRef, jobApplication);
                lawOffice.removeJobApplication(jobApplicationIndex);
                break;
            case 2:
                rejectJobApplication(jobApplication);
                lawOffice.removeJobApplication(jobApplicationIndex);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Accepts the given job application.
     * @param systemClassRef the system class reference
     * @param jobApplication the job application
     */
    private void acceptJobApplication(SystemClass systemClassRef, Lawyer.JobApplication jobApplication) {
        int applicantId = jobApplication.getApplicantId();
        Lawyer lawyer = systemClassRef.getLawyer(applicantId);

        lawyer.setEmployerId(id);
        lawOffice.addEmployee(applicantId);

        jobApplication.setStatus(JobApplicationStatus.ACCEPTED);
    }

    /**
     * Rejects the given job application.
     * @param jobApplication the job application
     */
    private void rejectJobApplication(Lawyer.JobApplication jobApplication) {
        jobApplication.setStatus(JobApplicationStatus.REJECTED);
    }

    @Override
    public void menu(SystemClass systemClassRef) {
        while (true) {
            System.out.println("1. Employees");
            System.out.println("2. Job applications");
            System.out.println("3. Publish job advertisement");
            System.out.println("0. Exit");

            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1:
                    employeesMenu(systemClassRef);
                    break;
                case 2:
                    jobApplicationsMenu(systemClassRef);
                    break;
                case 3:
                    publishJobAdvertisement(systemClassRef);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    public void lawyerMenu(SystemClass systemClassRef)
    {
        super.menu(systemClassRef);
    }
}
