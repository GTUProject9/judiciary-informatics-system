import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

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
        // Codes starts from 1.
        int index = systemObjectType.getSystemObjectCode() - 1;

        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            Lawsuit lawsuit = (Lawsuit) systemObject;
            if (lawsuit.getJudge() != null)
            {
                lawsuitsByDate.get(lawsuit.getJudge() % JUDGE_NUMBER - 1).add(lawsuit.getId());
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
     * It takes a system object as a parameter, finds the type of the system object, and adds it to the
     * corresponding MapTree
     * 
     * @param systemObject The object to be deleted.
     */
    public void deleteSystemObject(AbstractSystemObject systemObject)
    {
        SystemObjectTypes systemObjectType = findSystemObjectType(systemObject.getId());
        // Codes starts from 1.
        int index = systemObjectType.getSystemObjectCode() - 1;

        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            Lawsuit lawsuit = (Lawsuit) systemObject;
            if (lawsuit.getJudge() != null)
            {
                lawsuitsByDate.get(lawsuit.getJudge() % 10 - 1).remove(lawsuit);
            }
        }
        if (systemObjectType == SystemObjectTypes.LAWYER && ((Lawyer) systemObject).isStateAttorney())
        {
            stateAttorneys.add(systemObject.getId());
        }
        if (systemObjectType == SystemObjectTypes.LAWYER)
        {
            lawyerCounter++;
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
    public void displayHoldLawsuits()
    {
        int i = 1;
        System.out.println("Pending lawsuits: ");
        for (var lawsuit : systemObjects.get(SystemObjectTypes.LAWSUIT.getSystemObjectCode() - 1).values()) {
            if (((Lawsuit)lawsuit).getStatus() == LawsuitStatus.HOLD)
            {
                System.out.println(i + ". " + lawsuit);
                i++;
            }
        }
    }

    public Judge getJudge(int id) {
        return (Judge) getSystemObject(id);
    }

    // ============ JUDGE ============
    /**
     * "Get the highest priority lawsuit for a given judge."
     * 
     * The function is called getHighestPriorityLawsuit and it takes one parameter, judgeId
     * 
     * @param judgeId The ID of the judge who is currently handling the case.
     * @return The highest priority lawsuit for a given judge.
     */
    public Lawsuit getHighestPriorityLawsuit(int judgeId)
    {
        return getLawsuit(lawsuitsByDate.get(judgeId % JUDGE_NUMBER - 1).poll());
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
        lawsuitsByDate.get(lawsuit.getJudge() % 10 - 1).add(lawsuit.getId());
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
            System.out.println(i + ". " + jobAdvertisement);
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
        LawOfficeOwner owner = (LawOfficeOwner) getSystemObject(jobApplication.getOwnerId());
        owner.getOffice().addJobApplication(jobApplication);
    }
    
    public void archiveMenu()
    {
        System.out.println("");
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
