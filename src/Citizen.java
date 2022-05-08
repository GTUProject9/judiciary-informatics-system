import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.LawsuitTypes;


public class Citizen extends AbstractUser
{
    private List<Integer> suingLawsuits;
    private List<Integer> suedLawsuits;
    private List<Integer> personalLawyersIds;
    
    public Citizen() {
        super();
        suingLawsuits = new ArrayList<>();
        suedLawsuits = new ArrayList<>();
        personalLawyersIds = new ArrayList<>();
    }

    public Citizen(int id, String password, String name, String surname, String email, String phone) {
        super(id, password, name, surname, email, phone);

        suingLawsuits = new ArrayList<>();
        suedLawsuits = new ArrayList<>();
        personalLawyersIds = new ArrayList<>();
    }
    
    public void displayPersonalLawyersInfo() {
        // personalLawyers bilgileri yazdirilacak.
    }

    public void displayLawsuitsInfo() {
        // lawsuits bilgileri yazdirilacak
    }

    public void createLawsuit(SystemClass systemClassObject) {
        // bir dava olusturulacak.

        /**
         * public Lawsuit(Integer id, Date date, Integer suingCitizen, Integer suedCitizen,
                   Integer suingLawyer, LawsuitTypes lawsuitType,
                   String caseFile)
         */

        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse("20230413");
            Integer suingCitizen = id;
            Integer suedCitizen = 20010;
            // Avukat IDsi alinacak
            
            // Avukata da davayi ekle.

            LawsuitTypes lawsuitType = LawsuitTypes.PERSONAL_INJURY;
            String caseFile = "caseFile";
            Lawsuit lawsuit = new Lawsuit(null, date, suingCitizen, suedCitizen, null, lawsuitType, caseFile);
            systemClassObject.addLawsuit(lawsuit);

            lawsuit.setSuingLawyer(selectLawyer(systemClassObject, lawsuit.id));
            addSuingLawsuit(lawsuit.id);
            addLawsuitToSuedCitizen(systemClassObject, suedCitizen, lawsuit.id);
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private Integer selectLawyer(SystemClass systemClassObject, Integer lawsuitId) {
        
        // condition 1
        int choice = 1;
        if (choice == 1)
        {
            return selectLawyerFromPersonalLawyers(systemClassObject);
        }        
        else if (choice == 2)
        {
            return selectLawyerFromLawsuitAcceptingLawyers(systemClassObject);
        }
        else if (choice == 3)
        {
            return requestLawyerFromSystem(systemClassObject, lawsuitId);
        }
        else
        {
            // Tekrar iste
            return null;
        }
    }

    private Integer selectLawyerFromPersonalLawyers(SystemClass systemClassObject) {
        // personalLawyers'dan birini secin.
        for (int i = 0; i < personalLawyersIds.size(); i++) {
            System.out.println(i + ". " + systemClassObject.getSystemObject(personalLawyersIds.get(i)));
        }
        // Scanner scanner = new Scanner(System.in);
        Integer lawyerId = personalLawyersIds.get(0);

        return lawyerId;
    }

    private Integer selectLawyerFromLawsuitAcceptingLawyers(SystemClass systemClassObject)
    {
        systemClassObject.getLawsuitAcceptingLawyer();

        // Scanner scanner = new Scanner(System.in);
        Integer lawyerId = -1;
        return lawyerId;
    }

    private Integer requestLawyerFromSystem(SystemClass systemClassObject, Integer lawsuitId) {
        return systemClassObject.assignStateAttorney(lawsuitId);
    }

    private void addLawsuitToSuedCitizen(SystemClass systemClassObject, Integer SuedCitizenId, Integer lawsuitId) {
        // get citizen
        Citizen suedCitizen = (Citizen) systemClassObject.getSystemObject(SuedCitizenId);
        suedCitizen.addSuedLawsuit(lawsuitId);
    }

    private void addSuingLawsuit(Integer lawsuitId) {
        suingLawsuits.add(lawsuitId);
    }

    private void addSuedLawsuit(Integer lawsuitId) {
        suedLawsuits.add(lawsuitId);
    }

    private void addPersonalLawyer(Integer lawyerId) {
        personalLawyersIds.add(lawyerId);
    }

    private void addLawyerAsSuedCitizen(SystemClass systemClassObject) {
        Integer lawsuitId = suingLawsuits.get(0);
        Lawsuit lawsuit = systemClassObject.getLawsuit(lawsuitId);

        lawsuit.setSuedLawyer(selectLawyer(systemClassObject, lawsuitId));
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("suing lawsuits ").append(suingLawsuits).append("\n");
        sb.append("sued lawsuits ").append(suedLawsuits).append("\n");
        sb.append("personalLawyersId: ").append(personalLawyersIds).append("\n");
        return sb.toString();
    }

    @Override
    public void menu(SystemClass systemClassObject) {

    }
    
}
