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
     * This function returns the advertisement content to the system.
     * The system saves the advertisement to the system using the officeId and the advertisement message.
     */
    public void publishJobAdvertisement(SystemClass systemClassObject) {
        // Create a new job advertisement.
        String title = "Title";
        String description = "Description";

        // Add the advertisement to the system.
        LawOffice.JobAdvertisement jobAdvertisement = lawOffice.createJobAdvertisement(id, title, description);
        systemClassObject.addJobAdvertisement(jobAdvertisement);
    }

    public LawOffice getOffice() {
        return lawOffice;
    }

    public void employeesMenu(SystemClass systemClassObject) {
        if (!lawOffice.areThereEmployees()) {
            System.out.println("There are no employees.");
            return;
        }

        System.out.println("Choose an employee to perform an action:");
        System.out.println("0. Go back");
        lawOffice.displayEmployees(systemClassObject);

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

        Lawyer employee = systemClassObject.getLawyer(employeeId);

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
                assignJobToEmployee(systemClassObject, employee);
            case 2:
                fireEmployee(employee, employeeIndex);
            default:
                System.out.println("Invalid selection.");
        }
    }


    public void assignJobToEmployee(SystemClass systemClassObject, Lawyer employee) {
        if (continuingLawsuits.size() == 0) {
            System.out.println("There are no jobs to assign.");
            return;
        }

        Iterator<Integer> continuingLawSuitsIter = continuingLawsuits.iterator();
        int lawsuitId =  continuingLawSuitsIter.next();
        continuingLawSuitsIter.remove();
        //int lawsuitId = continuingLawsuits.remove(0);
        Lawsuit lawsuit = systemClassObject.getLawsuit(lawsuitId);

        employee.addLawsuit(lawsuitId);

        if (lawsuit.getSuingLawyer() == id) {
            lawsuit.setSuingLawyer(employee.id);
        } else {
            lawsuit.setSuedLawyer(employee.id);
        }
    }

    private void fireEmployee(Lawyer employee, int employeeIndex) {
        employee.setEmployerId(null);
        lawOffice.removeEmployee(employeeIndex);
    }

    private void jobApplicationsMenu(SystemClass systemClassObject) {
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
                acceptJobApplication(systemClassObject, jobApplication);
            case 2:
                rejectJobApplication(jobApplication);
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void acceptJobApplication(SystemClass systemClassObject, Lawyer.JobApplication jobApplication) {
        int applicantId = jobApplication.getApplicantId();
        Lawyer lawyer = systemClassObject.getLawyer(applicantId);

        lawyer.setEmployerId(id);
        lawOffice.addEmployee(applicantId);

        jobApplication.setStatus(JobApplicationStatus.ACCEPTED);
    }
    
    private void rejectJobApplication(Lawyer.JobApplication jobApplication) {
        jobApplication.setStatus(JobApplicationStatus.REJECTED);
    }

    @Override
    public void menu(SystemClass systemClassObject) {
        while (true) {
            System.out.println("1. Employees");
            System.out.println("2. Job applications");
            System.out.println("3. Exit");

            int choice;
            try {
                choice = Utils.readIntegerInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1:
                    employeesMenu(systemClassObject);
                    break;
                case 2:
                    jobApplicationsMenu(systemClassObject);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
}
