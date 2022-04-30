import java.util.ArrayList;


public class Citizen extends AbstractUser
{
    private ArrayList<Integer> lawsuitsId;
    private ArrayList<Integer> personalLawyersId;
    
    public Citizen() {
        super();
    }

    public Citizen(int id, String password, String name, String surname, String email, String phone) {
        super(id, password, name, surname, email, phone);
        insertPersonalInformations();
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

    public void createLawsuit() {
        // bir dava olusturulacak.
        Object lawsuit = new Object();

        
        // dava status "waiting" olacak.
        selectLawyerFromPersonalLawyers();

        //  -- OR -- 

        // dava lawyer statusu "waiting" olacak.
        selectLawyer();

        //  -- OR --

        // dava status "assigned" olacak.
        assignLawyerByCmk();
    }
    
    private void selectLawyerFromPersonalLawyers() {
    }

    private void selectLawyer() {
        // Is basvurusu eklenir
        // TODO Auto-generated method stub
    }

    private void assignLawyerByCmk() {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("lawsuitsId: ").append(lawsuitsId).append("\n");
        sb.append("personalLawyersId: ").append(personalLawyersId).append("\n");
        return sb.toString();
    }

    public static void menu(SystemClass systemClassObject) {
        // Burada id ve password istenir.
        int id = 0;
        Citizen citizen = (Citizen) systemClassObject.getSystemObject(id);
    }
    
}
