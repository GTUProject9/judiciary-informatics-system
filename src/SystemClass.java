import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Date;
import java.text.SimpleDateFormat;

import enums.LawsuitTypes;
import enums.LawsuitStatus;
import enums.SystemObjectTypes;

public class SystemClass 
{
    // System Objects: Lawsuit, citizen, lawyer, lawoffice owner, judge, government official
    private List<TreeMap<Integer, AbstractSystemObject>> systemObjects;

    private Queue<Integer> stateAttorneys;

    private Queue<Integer> stateAttorneyApplicants;

    private List<PriorityQueue<Integer>> lawsuitsByDate;

    private ArrayList<LawOffice.JobAdvertisement> jobAdvertisementsReferences;

    private int lawyerCounter;
    private int lawsuitCounter;

    private static final int JUDGE_NUMBER = 10;

    /**
     * SystemClass constructor.
     */
    public SystemClass() 
    {
        systemObjects = new ArrayList<>();
        for (int i = 0; i < SystemObjectTypes.values().length; i++) {
            systemObjects.add(new TreeMap<>());
        }

        stateAttorneys = new LinkedList<>();
        jobAdvertisementsReferences = new ArrayList<>();
        stateAttorneyApplicants = new LinkedList<>();
        
        lawsuitsByDate = new ArrayList<>(JUDGE_NUMBER);
        for (int i = 0; i < JUDGE_NUMBER; i++)
        {
            lawsuitsByDate.add(new PriorityQueue<>((lawsuit1, lawsuit2) -> 
                                                    getLawsuit(lawsuit1).getDate().compareTo(getLawsuit(lawsuit2).getDate())));
        }
    }
    
    // ============ TEMEL SISTEM CLASSI METHODLARI ============
    /**
     * It adds the given system object to the appropriate TreeMap
     * 
     * @param systemObject The object to be registered.
     */
    public void registerSystemObject(AbstractSystemObject systemObject)
    {
        SystemObjectTypes systemObjectType = findSystemObjectType(systemObject.getId());
        if (systemObjectType == null)
        {
            System.out.println("Error: SystemObjectType is null.");
            return;
        }
        // Codes starts from 1.
        int index = systemObjectType.getSystemObjectCode() - 1;

        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            Lawsuit lawsuit = (Lawsuit) systemObject;
            if (lawsuit.getJudge() != -1)
            {
                lawsuitsByDate.get((lawsuit.getJudge() - 1) % JUDGE_NUMBER).add(lawsuit.getId());
            }
        }
        if (systemObjectType == SystemObjectTypes.LAWYER && ((Lawyer) systemObject).isStateAttorney())
        {
            stateAttorneys.offer(systemObject.getId());
        }
        if (systemObjectType == SystemObjectTypes.LAWYER)
        {
            lawyerCounter++;
        }
        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            lawsuitCounter++;
        }
        systemObjects.get(index).put(systemObject.getId(), systemObject);  
    }

    /**
     * Find the correct Binary Search Tree, then get the system object.
     * 
     * @param id The id of the system object.
     * @return AbstractSystemObject
     */
    public AbstractSystemObject getSystemObject(int id)
    {
        SystemObjectTypes systemObjectCode = findSystemObjectType(id);
        if (systemObjectCode == null)
            return null;
        // Codes starts from 1.
        int index = systemObjectCode.getSystemObjectCode() - 1;
        return systemObjects.get(index).get(id);
    }


    /**
     * This function adds a Lawyer object to the stateAttorneyReferences ArrayList.
     * 
     * @param stateAttorney The state attorney to add to the list of state attorneys.
     */
    public void addStateAttorney(int stateAttorneyId)
    {
        stateAttorneys.offer(stateAttorneyId);
    }

    
    // ============ HELPERS ============
    /**
     * It takes an integer as an argument and returns the corresponding SystemObjectType
     * 
     * @param id The id of the object.
     * @return SystemObjectTypes enum
     */
    public static SystemObjectTypes findSystemObjectType(int id)
    {
        int code = id / (int) Math.pow(10, AbstractSystemObject.ID_LENGTH - 1);
        int index = code - 1;
        if (index>SystemObjectTypes.values().length-1 || index<0)
            return null;
        return SystemObjectTypes.values()[index];
    }

    /**
     * "Check if the password of the citizen with the given id is equal to the given password."
     * 
     * The function is called "checkPassword" and it takes two parameters: an integer and a string. The
     * integer is the id of the citizen and the string is the password. The function returns a boolean
     * value
     * 
     * @param id The id of the citizen
     * @param password The password to check.
     * @return A boolean value.
     */
    public boolean checkPassword(int id, String password)
    {
        Citizen citizen = (Citizen) getSystemObject(id);
        if (citizen == null)
            return false;
        
        return citizen.getPassword().equals(password);
    }


    // ============ LAW OFFICE OWNER ============
    /**
     * Adds a job advertisement to the list of job advertisements.
     * 
     * @param jobAdvertisement The job advertisement to be added to the list of job advertisements.
     */
    public void addJobAdvertisement(LawOffice.JobAdvertisement jobAdvertisement)
    {
        jobAdvertisementsReferences.add(jobAdvertisement);
    }


    // ============ GOVERNMENT OFFICAL ============
    /**
     * > This function adds a lawyer to the system
     * 
     * @param lawyer The lawyer object to be added to the system.
     */
    public void addLawyer(Lawyer lawyer)
    {
        int initialId = SystemObjectCreator.createInitialId(SystemObjectTypes.LAWYER.getSystemObjectCode());
        lawyer.setId(initialId + lawyerCounter);
        registerSystemObject(lawyer);
    }

    /**
     * Return the first element in the queue and remove it from the queue.
     * 
     * @return The first element in the queue.
     */
    public int peekStateAttorneyApplicant() {
        Integer applicantId = stateAttorneyApplicants.peek();
        return applicantId == null ? -1 : applicantId;
    }

    /**
     * If the queue is empty, return -1, otherwise return the first element in the queue
     * 
     * @return The applicantId is being returned.
     */
    public int pollStateAttorneyApplicant() {
        Integer applicantId = stateAttorneyApplicants.poll();
        return applicantId == null ? -1 : applicantId;
    }

    /**
     * If there are no state attorneys, return -1, otherwise return the first state attorney in the
     * queue and add it to the end of the queue.
     * 
     * @return The first element in the queue.
     */
    public int pollStateAttorney() {
        Integer stateAttorney = stateAttorneys.poll();
        if (stateAttorney == null)
            return -1;
    
        stateAttorneys.offer(stateAttorney);
        return stateAttorney;
    }

    // Print state attorney applicants
    public void printStateAttorneyApplicants() {
        int i = 1;
        System.out.println("State attorney applicants: ");
        for (var applicantId : stateAttorneyApplicants) {
            System.out.println(i + ". " + getLawyer(applicantId));
        }
    }
    
    public void addLawsuitToLawyer(int lawyerId, int lawsuitId) {
        Lawyer lawyer = (Lawyer) getSystemObject(lawyerId);
        lawyer.addLawsuit(lawsuitId);
    }
    
    /**
     * This function displays all the judges in the system
     */
    public void displayJudges() {
        int i = 1;
        System.out.println("Judges: ");
        for (var judge : systemObjects.get(SystemObjectTypes.JUDGE.getSystemObjectCode() - 1).values()) {
            System.out.println(i + ". " + judge);
            i++;
        }
    }

    /**
     * This function displays all the lawsuits that are on hold.
     */
    public void displayPendingLawsuits()
    {
        int i = 1;
        System.out.println("Pending lawsuits: ");
        for (var lawsuit : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values()) {
            if (((Lawsuit) lawsuit).getStatus() == LawsuitStatus.HOLD)
            {
                System.out.println(i + ". " + lawsuit);
                i++;
            }
        }
    }

    public Lawsuit getPendingLawsuitByIndex(int index)
    {
        for (var lawsuit : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values()) {
            if (((Lawsuit) lawsuit).getStatus() == LawsuitStatus.HOLD)
            {
                if (index == 0)
                    return (Lawsuit) lawsuit;
                index--;
            }

        }
        return null;
    }

    public Judge getJudge(int id) {
        return (Judge) getSystemObject(id);
    }

    // ============ JUDGE ============
    /**
     * "Peek the highest priority lawsuit for a given judge."
     * 
     * The function is called getHighestPriorityLawsuit and it takes one parameter, judgeId
     * 
     * @param judgeId The ID of the judge who is currently handling the case.
     * @return The highest priority lawsuit for a given judge.
     */
    public Lawsuit peekHighestPriorityLawsuit(int judgeId)
    {
        Integer lawsuit = lawsuitsByDate.get((judgeId - 1) % JUDGE_NUMBER).peek();
        return lawsuit == null ? null : getLawsuit(lawsuit);
    }

    /**
     * "Poll the highest priority lawsuit for a given judge."
     * 
     * @param judgeId The ID of the judge who is currently handling the case.
     * @return The highest priority lawsuit for a given judge.
     */
    public Lawsuit pollHighestPriorityLawsuit(int judgeId)
    {
        Integer lawsuit = lawsuitsByDate.get((judgeId - 1) % JUDGE_NUMBER).poll();
        return lawsuit == null ? null : getLawsuit(lawsuit);
    }
    

    /**
     * This function adds a lawsuit to the list of lawsuits.
     * 
     * @param lawsuit The lawsuit to add to the list of lawsuits.
     */
    public void addLawsuit(Lawsuit lawsuit)
    {
        int initialId = SystemObjectCreator.createInitialId(SystemObjectTypes.LAWSUIT.getSystemObjectCode());
        lawsuit.setId(initialId + lawsuitCounter);
        registerSystemObject(lawsuit);
    }

    /**
     * This method, set a lawsuit to the priority queue after assignation lawsuit to a judge.
     * @param lawsuit
     */
    public void addLawsuitByDate(Lawsuit lawsuit)
    {
        System.out.println("judge = " + lawsuit.getJudge());
        lawsuitsByDate.get((lawsuit.getJudge() - 1) % JUDGE_NUMBER).add(lawsuit.getId());
    }

    // ============ CITIZEN ============
    /**
     * "Get all the lawyers who accept lawsuits."
     * 
     * The function is a bit long, but it's not complicated. It's just a loop that iterates over all
     * the lawyers and prints the ones who accept lawsuits
     */
    public void displayLawsuitAcceptingLawyers()
    {
        int index = SystemObjectTypes.LAWYER.getSystemObjectCode() - 1;
        int i = 1;
        for (AbstractSystemObject lawyer : systemObjects.get(index).values())
        {
            if (((Lawyer) lawyer).acceptsLawsuits())
            {
                System.out.println(i + ".\n" + lawyer + "\n");
                i++;
            }
        }
        index = SystemObjectTypes.LAWOFFICE_OWNER.getSystemObjectCode() - 1;
        for (AbstractSystemObject lawyer : systemObjects.get(index).values())
        {
            if (((Lawyer) lawyer).acceptsLawsuits())
            {
                System.out.println(i + ".\n" + lawyer + "\n");
                i++;
            }
        }
    }
    
    /**
     * It returns the first element of the stateAttorneyReferences queue.
     * 
     * @return The stateAttorneyReferences.peek() method returns the top element of the queue without
     * removing it.
     */
    public int peekStateAttorney() {
        Integer stateAttorneyId = stateAttorneys.peek();
        return stateAttorneyId == null ? -1 : stateAttorneyId;
    }

    /**
     * Assign a lawyer to a lawsuit.
     * 
     * @param lawyerId The id of the lawyer
     * @param lawsuitId The id of the lawsuit to be assigned to the lawyer.
     */
    public void assignLawyerToLawsuit(int lawyerId, int lawsuitId) {
        Lawyer lawyer = getLawyer(lawyerId);
        lawyer.addLawsuit(lawsuitId);
    }

    /**
     * It returns the id of the nth lawyer that accepts lawsuits
     * 
     * @param index the index of the lawyer in the list of lawyers who accept lawsuits
     * @return The id of the lawyer that accepts lawsuits.
     */
    public int getLawsuitAcceptingLawyerByIndex(int index) {
        for (var object : systemObjects.get(SystemObjectTypes.LAWYER.getSystemObjectCode() - 1).values())
        {
            if (((Lawyer) object).acceptsLawsuits())
            {
                if (index == 0)
                    return object.getId();
                index--;
            }
        }
        for (var object : systemObjects.get(SystemObjectTypes.LAWOFFICE_OWNER.getSystemObjectCode() - 1).values())
        {
            if (((Lawyer) object).acceptsLawsuits())
            {
                if (index == 0)
                    return object.getId();
                index--;
            }
        }
        return -1;
    } 

    /**
     * It returns the system object at the specified index of the specified type
     * 
     * @param index The index of the object you want to get.
     * @param type SystemObjectTypes.class
     * @return The object at the index.
     */
    public AbstractSystemObject getSystemObjectByIndex(int index, SystemObjectTypes type) {
        for (var object : systemObjects.get(type.getSystemObjectCode() - 1).values()) {
            if (index == 0)
                return object;
            index--;
        }
        return null;
    }

    // ============ LAWYER ============
    /**
     * Adds a state attorney to the queue of state attorney applicants.
     * 
     * @param stateAttorney The state attorney to add to the queue.
     */
    public void addStateAttorneyApplicant(Lawyer stateAttorney)
    {
        stateAttorneyApplicants.offer(stateAttorney.getId());
    }

    // get citizen
    /**
     * This function returns a Citizen object with the given id.
     * 
     * @param id The id of the citizen you want to get.
     * @return A Citizen object.
     */
    public Citizen getCitizen(int id)
    {
        return (Citizen) getSystemObject(id);
    }
    
    // get lawsuit
    /**
     * This function returns a Lawsuit object with the given id.
     * 
     * @param id The id of the lawsuit you want to get.
     * @return A Lawsuit object.
     */
    public Lawsuit getLawsuit(int id)
    {
        return (Lawsuit) getSystemObject(id);
    }

    /**
     * This function displays the job advertisements.
     */
    public void displayJobAdvertisements()
    {
        int i = 1;
        for (LawOffice.JobAdvertisement jobAdvertisement : jobAdvertisementsReferences)
        {
            System.out.println(i + ".\n" + jobAdvertisement.toString());
            i++;
        }
    }
    
    /**
     * This function returns the employer id of the job advertisement at the given index.
     * 
     * @param index the index of the job advertisement in the list of job advertisements.
     * @return The employer id of the job advertisement at the given index.
     */
    public int getEmployerId(int index)
    {
        return jobAdvertisementsReferences.get(index).getOwnerId();
    }
    
    /**
     * "Add a job application to the office of the owner of the job application."
     * 
     * The first line of the function is a comment. Comments are ignored by the compiler. They are used
     * to explain what the code does
     * 
     * @param jobApplication The job application to add.
     */
    public void addJobApplication(Lawyer.JobApplication jobApplication)
    {
        LawOfficeOwner owner = getLawOfficeOwner(jobApplication.getOwnerId());
        owner.getOffice().addJobApplication(jobApplication);
    }

    /**
     * This function returns a LawOfficeOwner object from the TreeMap of system objects.
     * 
     * @param id The id of the object you want to get.
     * @return A LawOfficeOwner object.
     */
    public LawOfficeOwner getLawOfficeOwner(int id)
    {
        return (LawOfficeOwner) getSystemObject(id);
    }
    
    /**
     * It displays a menu and calls the appropriate function based on the user's choice
     */
    public void archiveMenu()
    {
        System.out.println("\n\tArchive menu");
        System.out.println("\n1. Display all lawsuits");
        System.out.println("2. Display all lawsuits by date");
        System.out.println("3. Display pending lawsuits");
        System.out.println("4. Display concluded lawsuits");
        System.out.println("5. Display lawsuits by judge");
        System.out.println("6. Display lawsuits by lawyer");
        System.out.println("7. Display lawsuits by citizen");
        System.out.println("8. Display lawsuits filtered by lawsuit types");
        System.out.println("0. Back");
        System.out.println("Enter your choice: ");
        int choice;
        try
        {
            choice = Utils.readIntegerInput();
        }
        catch (NumberFormatException e)
        {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }

        switch (choice)
        {
            case 1:
                displayAllLawsuits();
                break;
            case 2:
                displayLawsuitsByDate();
                break;
            case 3:
                displayPendingLawsuits();
                break;
            case 4:
                displayConcludedLawsuits();
                break;
            case 5:
                displayLawsuitsByJudge();
                break;
            case 6:
                displayLawsuitsByLawyer();
                break;
            case 7:
                displayLawsuitsByCitizen();
                break;
            case 8:
                displayLawsuitsFilteredByLawsuitTypes();
                break;
            case 0:
                return;
            default:
                System.out.println(Utils.INVALID_INPUT);
                break;
        }
        archiveMenu();
    }

    /**
     * This function displays all lawsuits.
     */
    private void displayAllLawsuits()
    {
        int i = 1;
        for (var lawsuit : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            System.out.println(i + ".\n" + lawsuit.toString());
            i++;
        }
    }

    /**
     * This function displays lawsuits by date.
     */
    private void displayLawsuitsByDate()
    {
        System.out.println("Enter initial date (yyyyMMdd): ");
        String input = Utils.readStringInput();
        Date startDate;
        try {
            startDate = new SimpleDateFormat("yyyyMMdd").parse(input);
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }

        System.out.println("Enter final date (yyyyMMdd): ");
        input = Utils.readStringInput();
        Date endDate;
        try {
            endDate = new SimpleDateFormat("yyyyMMdd").parse(input);
        } catch (Exception e) {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }

        int i = 1;
        for (var obj : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            Lawsuit lawsuit = (Lawsuit) obj;
            if (lawsuit.getDate().after(startDate) && lawsuit.getDate().before(endDate))
            {
                System.out.println(i + ".\n" + lawsuit.toString());
                i++;
            }
        }
    }

    /**
     * It displays all concluded lawsuits
     */
    public void displayConcludedLawsuits()
    {
        int i = 1;
        for (var obj : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            Lawsuit lawsuit = (Lawsuit) obj;
            if (lawsuit.getStatus() == LawsuitStatus.SUED_WON || lawsuit.getStatus() == LawsuitStatus.SUING_WON)
            {
                System.out.println(i + ".\n" + lawsuit.toString());
                i++;
            }
        }
    }
   
    /**
     * It displays all the lawsuits that are assigned to a specific judge
     */
    public void displayLawsuitsByJudge()
    {
        System.out.println("Enter the judge id: ");
        int judgeId;
        try
        {
            judgeId = Utils.readIntegerInput();
        }
        catch (NumberFormatException e)
        {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        Judge judge = getJudge(judgeId);
        if (judge == null)
        {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        int i = 1;
        for (var obj : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            Lawsuit lawsuit = (Lawsuit) obj;
            if (lawsuit.getJudge() == judge.getId())
            {
                System.out.println(i + ".\n" + lawsuit.toString());
                i++;
            }
        }
    }

    /**
     * It displays all the lawsuits that a lawyer is involved in
     */
    public void displayLawsuitsByLawyer()
    {
        System.out.println("Enter the lawyer id: ");
        int lawyerId;
        try
        {
            lawyerId = Utils.readIntegerInput();
        }
        catch (NumberFormatException e)
        {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        Lawyer lawyer = getLawyer(lawyerId);
        if (lawyer == null)
        {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        int i = 1;
        for (var obj : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            Lawsuit lawsuit = (Lawsuit) obj;
            if (lawsuit.getSuedLawyer() == lawyer.getId() || lawsuit.getSuingLawyer() == lawyer.getId())
            {
                System.out.println(i + ".\n" + lawsuit.toString());
                i++;
            }
        }
    }

    /**
     * It displays all the lawsuits that a citizen is involved in
     */
    public void displayLawsuitsByCitizen()
    {
        System.out.println("Enter the citizen id: ");
        int citizenId;
        try
        {
            citizenId = Utils.readIntegerInput();
        }
        catch (NumberFormatException e)
        {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        Citizen citizen = getCitizen(citizenId);
        if (citizen == null)
        {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        int i = 1;
        for (var obj : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            Lawsuit lawsuit = (Lawsuit) obj;
            if (lawsuit.getSuingCitizen() == citizen.getId() || lawsuit.getSuedCitizen() == citizen.getId())
            {
                System.out.println(i + ".\n" + lawsuit.toString());
                i++;
            }
        }
    }

    /**
     * It displays all the lawsuits of a particular type.
     */
    public void displayLawsuitsFilteredByLawsuitTypes()
    {
        System.out.println("Types of lawsuits");
        System.out.println("1. Personal Injury");
        System.out.println("2. Product Liability");
        System.out.println("3. Family Law Dispute");
        System.out.println("4. Criminal");
        System.out.println("Choice: ");
        int choice;
        try
        {
            choice = Utils.readIntegerInput();
        }
        catch (NumberFormatException e)
        {
            System.out.println(Utils.INVALID_INPUT);
            return;
        }
        if (choice < 1 || choice > 4)
        {
            System.out.println(Utils.INVALID_CHOICE);
            return;
        }

        LawsuitTypes lawsuitType = LawsuitTypes.values()[choice - 1];
        int i = 1;
        for (var obj : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values())
        {
            Lawsuit lawsuit = (Lawsuit) obj;
            if (lawsuit.getLawsuitType() == lawsuitType)
            {
                System.out.println(i + ".\n" + lawsuit.toString());
                i++;
            }
        }
    }

    // ============ LAW OFFICE OWNER ============
    /**
     * This function returns a lawyer object with the given id.
     * 
     * @param id The id of the lawyer you want to get.
     * @return A lawyer object
     */
    public Lawyer getLawyer(int id)
    {
        return (Lawyer) getSystemObject(id);
    }
}
