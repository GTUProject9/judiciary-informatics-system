import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import binarysearchtree.BinarySearchTree;
import enums.SystemObjectTypes;

public class SystemClass 
{
    // System Objects: Lawsuit, citizen, lawyer, lawoffice owner, judge, government official
    private List<BinarySearchTree<AbstractSystemObject>> systemObjects;

    // sistemObjects olusturulurken cmk avukatlari da bir taraftan buraya eklenecek, boolean degere gore
    private Queue<Lawyer> stateAttorneyReferences;

    private Queue<Lawyer> stateAttorneyApplicants;

    private List<PriorityQueue<Lawsuit>> lawsuitsByDate;

    // Owner, is ilani olustururken, bir referans da buraya eklenecek
    private ArrayList<LawOffice.JobAdvertisement> jobAdvertisementsReferences;


    private int lawyerCounter = 0;
    private int lawsuitCounter = 0;

    private static final int JUDGE_NUMBER = 10;

    public SystemClass() 
    {
        // Her index'e kendilerine karsilik gelen BST'yi eklemek istedim ama BST classi comparable
        // generic aldigi icin beceremedim.
        systemObjects = new ArrayList<>(AbstractSystemObject.NUMBER_OF_SYSTEM_OBJECTS);
        for (int i = 0; i < AbstractSystemObject.NUMBER_OF_SYSTEM_OBJECTS; i++)
            systemObjects.add(new BinarySearchTree<>());

        stateAttorneyReferences = new LinkedList<>();
        jobAdvertisementsReferences = new ArrayList<>();
        stateAttorneyApplicants = new LinkedList<>();
        
        lawsuitsByDate = new ArrayList<>(JUDGE_NUMBER);
        for (int i = 0; i < JUDGE_NUMBER; i++)
        {
            lawsuitsByDate.add(new PriorityQueue<>((lawsuit1, lawsuit2) -> lawsuit1.getDate().compareTo(lawsuit2.getDate())));
        }
    }
    
    // ============ TEMEL SISTEM CLASSI METHODLARI ============
    /**
     * It adds the given system object to the appropriate Binary Search Tree
     * 
     * @param systemObject The object to be registered.
     */
    public void registerSystemObject(AbstractSystemObject systemObject)
    {
        // SystemObjectsCodes'a gore bst'yi getir ve objeyi ekle.
        SystemObjectTypes systemObjectType = findSystemObjectType(systemObject.getId());
        // Codes starts from 1.
        int index = systemObjectType.getSystemObjectCode() - 1;

        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            Lawsuit lawsuit = (Lawsuit) systemObject;
            if (lawsuit.getJudge() != null)
            {
                lawsuitsByDate.get(lawsuit.getJudge() % 10 - 1).add(lawsuit);
            }
        }
        if (systemObjectType == SystemObjectTypes.LAWYER && ((Lawyer) systemObject).getStateAttorney())
        {
            stateAttorneyReferences.offer((Lawyer) systemObject);
        }
        if (systemObjectType == SystemObjectTypes.LAWYER)
        {
            lawyerCounter++;
        }
        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            lawsuitCounter++;
        }
        systemObjects.get(index).add(systemObject);  
    }

    public void deleteSystemObject(AbstractSystemObject systemObject)
    {
        // SystemObjectsCodes'a gore bst'yi getir ve objeyi ekle.
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
        if (systemObjectType == SystemObjectTypes.LAWYER && ((Lawyer) systemObject).getStateAttorney())
        {
            stateAttorneyReferences.add((Lawyer) systemObject);
        }
        if (systemObjectType == SystemObjectTypes.LAWYER)
        {
            lawyerCounter++;
        }
        systemObjects.get(index).add(systemObject);  
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
        // Codes starts from 1.
        int index = systemObjectCode.getSystemObjectCode() - 1;
        // Code'a gore arraylist'ten bst'yi get yap.
        // Anonymous class yaratip oradan find'a pass edip id compare ederek, aradigimiz objeyi buluruz.
        return systemObjects.get(index).find(new AbstractSystemObject(id) {
            @Override
            public int getId() {
                return id;
            }
        });
    }

    /**
     * This function adds a Lawyer object to the stateAttorneyReferences ArrayList.
     * 
     * @param stateAttorney The state attorney to add to the list of state attorneys.
     */
    public void addStateAttorney(Lawyer stateAttorney)
    {
        stateAttorneyReferences.add(stateAttorney);
    }

    
    // ============ HELPERS ============
    // Id'den hangi type system object oldugunu bul
    public static SystemObjectTypes findSystemObjectType(int id)
    {
        // Error check gelebilir

        int code = id / (int) Math.pow(10, AbstractSystemObject.ID_LENGTH - 1);
        int index = code - 1;
        // Kriptik version
        // return SystemObjectTypes.values()[index];

        // -- Daha az kriptik versiyonu --
        switch (SystemObjectTypes.values()[index]) {
            case LAWSUIT:
                return SystemObjectTypes.LAWSUIT; 
            case CITIZEN:
                return SystemObjectTypes.CITIZEN;
            case LAWYER:
                return SystemObjectTypes.LAWYER;
            case LAWOFFICE_OWNER:
                return SystemObjectTypes.LAWOFFICE_OWNER;
            case JUDGE:
                return SystemObjectTypes.JUDGE;
            case GOVERNMENT_OFFICIAL:
                return SystemObjectTypes.GOVERNMENT_OFFICIAL;
            default:
                throw new IllegalArgumentException("Invalid system object type.");
        }
    }

    // Check password from ID
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
    public void addLawyer(Lawyer lawyer)
    {
        int initialId = SystemObjectCreator.createInitialId(SystemObjectTypes.LAWYER.getSystemObjectCode());
        lawyer.setId(initialId + lawyerCounter);
        registerSystemObject(lawyer);
    }

    public Lawyer getStateAttorneyApplicant() {
        return stateAttorneyApplicants.poll();
    }
    
    
    // ============ JUDGE ============
    public Lawsuit getHighestPriorityLawsuit(int judgeId)
    {
        return lawsuitsByDate.get(judgeId % 10 - 1).poll();
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
        lawsuitsByDate.get(lawsuit.getJudge() % 10 - 1).add(lawsuit);
    }

    // ============ CITIZEN ============
    public void getLawsuitAcceptingLawyer()
    {
        int index = SystemObjectTypes.LAWYER.getSystemObjectCode() - 1;
        Iterator<AbstractSystemObject> iterator = systemObjects.get(index).iterator();
        int i = 1;
        while (iterator.hasNext())
        {
            Lawyer lawyer = (Lawyer) iterator.next();
            if (lawyer.acceptsLawsuits())
            {
                System.out.println(i + ". " + lawyer + "\n");
                i++;
            }
        }
    }

    public Integer getStateAttorney(int lawsuitId)
    {   
        Lawyer lawyer = stateAttorneyReferences.poll();
        if (lawyer == null)
            return null;
        lawyer.addLawsuit(lawsuitId);

        stateAttorneyReferences.offer(lawyer);
        return lawyer.getId();
    }

    // ============ LAWYER ============

    public void addStateAttorneyApplicant(Lawyer stateAttorney)
    {
        stateAttorneyApplicants.offer(stateAttorney);
    }

    // get citizen
    public Citizen getCitizen(int id)
    {
        return (Citizen) getSystemObject(id);
    }
    
    // get lawsuit
    public Lawsuit getLawsuit(int id)
    {
        return (Lawsuit) getSystemObject(id);
    }

    /**
     * This function displays the job advertisements.
     */
    public void displayJobAdvertisements()
    {
        System.out.println(jobAdvertisementsReferences);
    }
    
    public int getEmployerId(int index)
    {
        return jobAdvertisementsReferences.get(index).getOwnerId();
    }
    
    public void addJobApplication(Lawyer.JobApplication jobApplication)
    {
        LawOfficeOwner owner = (LawOfficeOwner) getSystemObject(jobApplication.getOwnerId());
        owner.getOffice().addJobApplication(jobApplication);
    }
    

    // ============ LAW OFFICE OWNER ============
    public Lawyer getLawyer(int id)
    {
        return (Lawyer) getSystemObject(id);
    }
    
    // ============ GETTERS ============
    public List<BinarySearchTree<AbstractSystemObject>> getSystemObjects()
    {
        return systemObjects;
    }

    // Gerekli !!
    public Queue<Lawyer> getStateAttorneyReferences() {
        return stateAttorneyReferences;
    }

    public ArrayList<LawOffice.JobAdvertisement> getJobAdvertisementsReferences() {
        return jobAdvertisementsReferences;
    }

    public List<PriorityQueue<Lawsuit>> getLawsuitsByDate() {
        return lawsuitsByDate;
    } 
}
