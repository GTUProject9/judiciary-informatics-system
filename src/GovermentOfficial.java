public class GovermentOfficial extends Citizen 
{
    GovermentOfficial(){}
    GovermentOfficial(int id, String password, String firstName, String lastName, String phone, String email)
    {
        super(id, password, firstName, lastName, phone, email);
    }

    public boolean addLawyer()
    {
        return true;
    }
    public boolean removeLawyer()
    {
        return true;
    }
    public boolean assignLawsuit()
    {
        return true;
    }
    public boolean assgincmkLawyer()
    {
        return true;
    }
    public boolean publishLawsuit()
    {
       return true;
    }

    public static void menu(SystemClass systemClassObject) {
        // Burada id ve password istenir.
        int id = 0;
        GovermentOfficial govermentOfficial = (GovermentOfficial) systemClassObject.getSystemObject(id);
    }
}