public class GovernmentOfficial extends Citizen 
{
    GovernmentOfficial(){}
    GovernmentOfficial(int id, String password, String firstName, String lastName, String phone, String email)
    {
        super(id, password, firstName, lastName, phone, email);
    }

    public boolean addLawyer(SystemClass systemClassObjectReference)
    {
        int id;
        String password, firstName, lastName, email, phone;
        boolean stateAttorney;
        systemClassObjectReference.addLawyer(new Lawyer()); 
        return true;
    }
    public boolean removeLawyer(SystemClass systemClassObjectReference)
    {
        int id = -1;
        Lawyer lawyer = (Lawyer) systemClassObjectReference.getSystemObject(id);
        // controls

        systemClassObjectReference.deleteSystemObject(lawyer);
        return true;
    }
    public boolean assignLawsuit(SystemClass systemClassObjectReference, int lawsuitId)
    {
        int judgeId = 50001;

        Judge judge = (Judge) systemClassObjectReference.getSystemObject(judgeId);
        judge.assignLawsuit(lawsuitId);

        Lawsuit lawsuit = (Lawsuit) systemClassObjectReference.getSystemObject(lawsuitId);
        lawsuit.setJudge(judgeId);

        systemClassObjectReference.addLawsuitByDate(lawsuit);
        return true;
    }
    public boolean assginStateAttorney(SystemClass systemClassObjectReference)
    {
        int localId = -1;
        Lawyer lawyer = (Lawyer) systemClassObjectReference.getSystemObject(localId);
        lawyer.setStateAttorney(true);
        systemClassObjectReference.getStateAttorneyReferences().offer(lawyer);
        return true;
    }
    public boolean publishLawsuit(SystemClass systemClassObjectReference)
    {
        Lawsuit lawsuit = new Lawsuit();
        systemClassObjectReference.addLawsuit(lawsuit);
        return true;
    }

    @Override
    public void menu(SystemClass systemClassObject) {
        // addLawyer(systemClassObject);
        // addLawyer(systemClassObject);

        assignLawsuit(systemClassObject, 10001);
        assignLawsuit(systemClassObject, 10002);
        assignLawsuit(systemClassObject, 10003);
        assignLawsuit(systemClassObject, 10004);
    }
}