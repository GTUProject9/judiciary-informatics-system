import java.util.ArrayList;
import java.util.Scanner;


public class Citizen extends AbstractUser
{
    private ArrayList<Integer> lawsuitsIds;
    private ArrayList<Integer> personalLawyersIds;
    
    public Citizen() {
        super();
    }

    public Citizen(int id, String password, String name, String surname, String email, String phone) {
        super(id, password, name, surname, email, phone);
    }
    
    private void insertPersonalInformations() {
        // burada lawsuits ve personalLawyers eklenecek.
    }
    
    public String personalLawyerInformations() {
        // personalLawyers bilgileri döndürülecek.
        return null;
    }

    public String lawsuitInformations() {
        // lawsuits bilgileri döndürülecek.
        return null;
    }

    public static Lawsuit createLawsuit() {
        // bir dava olusturulacak.
        Lawsuit lawsuit = new Lawsuit();

        
        // dava status "waiting" olacak.
        selectLawyerFromPersonalLawyers();

        //  -- OR -- 

        // dava lawyer statusu "waiting" olacak.
        selectLawyer();

        //  -- OR --

        // dava status "assigned" olacak.
        assignLawyerByState();

        return lawsuit;
    }
    
    private static void selectLawyerFromPersonalLawyers() {
    }

    private static void selectLawyer() {
        // Is basvurusu eklenir
        // TODO Auto-generated method stub
    }

    private static void assignLawyerByState() {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("lawsuitsId: ").append(lawsuitsIds).append("\n");
        sb.append("personalLawyersId: ").append(personalLawyersIds).append("\n");
        return sb.toString();
    }

    @Override
    public void menu(SystemClass systemClassObject) {
        
    }
    
}
