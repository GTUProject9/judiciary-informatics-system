import java.util.ArrayList;
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
    
    // sistemObjects olusturulurken lawsuit'ler, date'e gore priority queue'e eklenecek
    private PriorityQueue<Lawsuit> lawsuits;

    // Owner, is ilani olustururken, bir referans da buraya eklenecek
    private Queue<LawOffice.JobAdvertisement> jobAdvertisementsReferences;

    public SystemClass() 
    {
        // Her index'e kendilerine karsilik gelen BST'yi eklemek istedim ama BST classi comparable
        // generic aldigi icin beceremedim.
        systemObjects = new ArrayList<>(AbstractSystemObject.NUMBER_OF_SYSTEM_OBJECTS);
        for (int i = 0; i < AbstractSystemObject.NUMBER_OF_SYSTEM_OBJECTS; i++)
            systemObjects.add(new BinarySearchTree<>());

        stateAttorneyReferences = new LinkedList<>();
        jobAdvertisementsReferences = new LinkedList<>();
        lawsuits = new PriorityQueue<>();
    }
    
    // register and get system object
    public void registerSystemObject(AbstractSystemObject systemObject)
    {
        // SystemObjectsCodes'a gore bst'yi getir ve objeyi ekle.
        SystemObjectTypes systemObjectType = findSystemObjectType(systemObject.getId());
        // Codes starts from 1.
        int index = systemObjectType.getSystemObjectCode() - 1;

        // System.out.println("index: " + index);

        if (systemObjectType == SystemObjectTypes.LAWSUIT)
        {
            // Burada tarihe gore siralama yapmasi lazim ama nasil olacagidan emin degilim.
            // compareTo methodu abstract user class'ta id kiyasliyor,
            // lawsuit'te date kiyaslayacak sekilde override ettim.
            // bu sekilde fakat bu sefer de bst'ye eklerken hata aliyorum.
            lawsuits.add((Lawsuit)systemObject);
        }
        if (systemObjectType == SystemObjectTypes.LAWYER && ((Lawyer) systemObject).getStateAttorney())
        {
            stateAttorneyReferences.add((Lawyer) systemObject);
        }
        systemObjects.get(index).add(systemObject);  
        
    }
    
    public AbstractSystemObject getSystemObject(int id)
    {
        SystemObjectTypes systemObjectCode = findSystemObjectType(id);
        // Codes starts from 1.
        int index = systemObjectCode.getSystemObjectCode() - 1;
        // Code'a gore arraylist'ten bst'yi get yap.
        // Local class yaratip oradan find'a pass edip id compare ederek, aradigimiz objeyi buluruz.
        return systemObjects.get(index).find(new AbstractSystemObject(id) {
            @Override
            public int getId() {
                return id;
            }
        });
    }

    // Id'den hangi type system object oldugunu bul
    public static SystemObjectTypes findSystemObjectType(int id)
    {
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

    // Helpers
    public void addStateAttorney(Lawyer stateAttorney)
    {
        stateAttorneyReferences.add(stateAttorney);
    }

    public void addLawsuit(Lawsuit lawsuit)
    {
        lawsuits.add(lawsuit);
    }

    public void addJobAdvertisement(LawOffice.JobAdvertisement jobAdvertisement)
    {
        jobAdvertisementsReferences.add(jobAdvertisement);
    }

    // Getters
    public List<BinarySearchTree<AbstractSystemObject>> getSystemObjects()
    {
        return systemObjects;
    }

    public Queue<Lawyer> getStateAttorneyReferences() {
        return stateAttorneyReferences;
    }

    public PriorityQueue<Lawsuit> getLawsuits() {
        return lawsuits;
    }

    public Queue<LawOffice.JobAdvertisement> getJobAdvertisementsReferences() {
        return jobAdvertisementsReferences;
    }
}
