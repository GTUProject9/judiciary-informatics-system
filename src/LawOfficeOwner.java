
public class LawOfficeOwner extends Lawyer{

    //datafield of lawyers
    //datafield of advert
    private LawOffice lawOffice;

    public LawOfficeOwner(int id, String password, String name, String surname, String email, 
                          String phone,String lawOfficeName) 
    {
        super(id, password, name, surname,  email, phone);
        lawOffice = new LawOffice(lawOfficeName,id);
    }

    /**
     *This function returns the advertisement content to the system.
     *The system saves the advertisement to the system using the officeId and the advertisement message.
     */
    public void publishAdvertisementForLawyers(String title, String description, SystemClass systemClassObject) 
    {
        // Sistem classindaki jobAdvertisementsReferences'a da olusturulan ilan eklenmeli,
        // Aksi taktirde is arayan avukarlar tum owner'lari gezmek zorunda kalacak.
        //input title and description
        // systemClassObject.getJobAdvertisementsReferences()
    }


    public void displayJobRequests(){
        //get JobAdvertisement first
        //then get JobApplication from JobAdvertisement
    }

    public void assignJobsToLawyersFromLawOffice(){
        //display lawyer.
        //select a lawyer.
        //assign a jop to lawyer.
    }

    private void hireALawyer(){
        //display advert and applicant
        //select one
        // change status to approved
    }
    private void fireALawyer(){
        //display lawyers.
        //select one.
        //delete from lawyer datafield and change jop status of lawyer.
    }

    @Override
    public void menu(SystemClass systemClassObject) {
    }
}
