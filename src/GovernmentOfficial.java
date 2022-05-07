import enums.LawsuitStatus;

public class GovernmentOfficial extends Citizen 
{
    GovernmentOfficial(){}
    GovernmentOfficial(int id, String password, String firstName, String lastName, String phone, String email)
    {
        super(id, password, firstName, lastName, phone, email);
    }

    public boolean addLawyer(SystemClass systemClassObjectReference)
    {
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
    public boolean assignLawsuitToJudge(SystemClass systemClassObjectReference, int lawsuitId)
    {
        // Eger bir tarafin bile avukati yoksa atama yapamaz.
        int judgeId = 50001;

        Judge judge = (Judge) systemClassObjectReference.getSystemObject(judgeId);
        judge.assignLawsuit(lawsuitId);

        Lawsuit lawsuit = (Lawsuit) systemClassObjectReference.getSystemObject(lawsuitId);
        lawsuit.setJudge(judgeId);
        lawsuit.setStatus(LawsuitStatus.STILL_GOING);
        systemClassObjectReference.addLawsuitByDate(lawsuit);
        return true;
    }
    public boolean addStateAttorney(SystemClass systemClassObjectReference)
    {
        Lawyer lawyer = systemClassObjectReference.getStateAttorneyApplicant();
        lawyer.setStateAttorney(true);
        systemClassObjectReference.getStateAttorneyReferences().offer(lawyer);
        return true;
    }

    public boolean publishLawsuit(SystemClass systemClassObjectReference)
    {
        // Tamamlanmis bir lawsuit olusturur.
        Lawsuit lawsuit = new Lawsuit();
        // cmk'dan davali ve davaci icin 2 avukat atanir ve judge'a assign edilir. 

        lawsuit.setStatus(LawsuitStatus.STILL_GOING);
        systemClassObjectReference.addLawsuit(lawsuit);
        return true;
    }

    @Override
    public void menu(SystemClass systemClassObject) {
        // addLawyer(systemClassObject);
        // addLawyer(systemClassObject);

        assignLawsuitToJudge(systemClassObject, 10001);
        assignLawsuitToJudge(systemClassObject, 10002);
        assignLawsuitToJudge(systemClassObject, 10003);
        assignLawsuitToJudge(systemClassObject, 10004);
    }
}