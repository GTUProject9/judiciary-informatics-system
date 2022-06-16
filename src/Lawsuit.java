import java.util.Date;

import enums.LawsuitTypes;
import enums.LawsuitStatus;

public class Lawsuit extends AbstractSystemObject
{
    private Date date;
    private int judge;
    private int suingCitizen;
    private int suedCitizen;
    private int suingLawyer;
    private int suedLawyer;
    private String suingDefence;
    private String suedDefence;
    //Personal Injury Lawsuit, Product Liability Lawsuit, Divorce and Family Law Disputes, Criminal Cases
    private LawsuitTypes lawsuitType;
    private String caseFile;
    private String courtRecords;
    private LawsuitStatus status;

    // A constructor.
    public Lawsuit(int id, Date date, LawsuitTypes lawsuitType, String caseFile) {
        super(id);
        this.date = date;
        this.judge = -1;
        this.suingCitizen = -1;
        this.suedCitizen = -1;
        this.suingLawyer = -1;
        this.suedLawyer = -1;
        this.lawsuitType = lawsuitType;
        this.caseFile = caseFile;
        this.courtRecords = null;
        this.status = LawsuitStatus.HOLD;
    }

    // A constructor.
    public Lawsuit(Date date, int suingCitizen, int suedCitizen,
                   int suingLawyer, LawsuitTypes lawsuitType,
                   String caseFile) {
        super();
        this.date = date;
        this.judge = -1;
        this.suingCitizen = suingCitizen;
        this.suedCitizen = suedCitizen;
        this.suingLawyer = suingLawyer;
        this.suedLawyer = -1;
        this.lawsuitType = lawsuitType;
        this.caseFile = caseFile;
        this.courtRecords = null;
        this.status = LawsuitStatus.HOLD;
    }
    
    // A constructor.
    public Lawsuit(Date date, int suingCitizen, int suedCitizen,
                   int suingLawyer, int suedLawyer, LawsuitTypes lawsuitType,
                   String caseFile) {
        super();
        this.date = date;
        this.judge = -1;
        this.suingCitizen = suingCitizen;
        this.suedCitizen = suedCitizen;
        this.suingLawyer = suingLawyer;
        this.suedLawyer = suedLawyer;
        this.lawsuitType = lawsuitType;
        this.caseFile = caseFile;
        this.courtRecords = null;
        this.status = LawsuitStatus.HOLD;
    }

    /**
     * Ends lawsuit with a decision of the judge.
     * @param judgeDecision Decision of the judge.
     * @return return judge desicion.
     */
    public LawsuitStatus concludeLawsuit(LawsuitStatus judgeDecision){
        status = judgeDecision;
        return judgeDecision;
    }

    /**
     * Adds case file to the lawsuit
     */
    public void addCaseFile(String caseFile){
        this.caseFile = caseFile;
    }

    /**
     * Adds court records to the lawsuit
     */
    public void addCourtRecord(String courtRecords){
        this.courtRecords = courtRecords;
    }

    /**
     * This function sets the date of the object
     * 
     * @param date The date of the event
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * This function returns the date of the event
     * 
     * @return The date of the event.
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * This function sets the judge variable to the value of the parameter judge
     * 
     * @param judge The judge's score for the contestant.
     */
    public void setJudge(int judge){
        this.judge = judge;
    }

    /**
     * This function returns the judge's score
     * 
     * @return The judge's score.
     */
    public int getJudge(){
        return this.judge;
    }

    /**
     * This function sets the suingCitizen variable to the citizen variable
     * 
     * @param citizen The citizen who is suing
     */
    public void setSuingCitizen(int citizen){
        this.suingCitizen = citizen;
    }

    /**
     * This function returns the suingCitizen variable
     * 
     * @return The suingCitizen variable is being returned.
     */
    public int getSuingCitizen(){
        return this.suingCitizen;
    }

    /**
     * This function sets the value of the suedCitizen variable to the value of the citizen variable
     * 
     * @param citizen The citizen that is suing the other citizen.
     */
    public void setSuedCitizen(int citizen){
        this.suedCitizen = citizen;
    }

    /**
     * This function returns the number of citizens that have been sued
     * 
     * @return The value of the suedCitizen variable.
     */
    public int getSuedCitizen(){
        return this.suedCitizen;
    }

    /**
     * This function sets the suingLawyer variable to the value of the lawyer parameter
     * 
     * @param lawyer The lawyer that is suing the defendant.
     */
    public void setSuingLawyer(int lawyer){
        this.suingLawyer = lawyer;
    }

    /**
     * This function returns the suingLawyer variable
     * 
     * @return The suingLawyer variable is being returned.
     */
    public int getSuingLawyer(){
        return this.suingLawyer;
    }

    /**
     * This function sets the value of the suedLawyer variable to the value of the lawyer variable
     * 
     * @param lawyer The lawyer that is suing the other lawyer
     */
    public void setSuedLawyer(int lawyer){
        this.suedLawyer = lawyer;
    }

    /**
     * This function returns the number of times the lawyer has been sued
     * 
     * @return suedLawyer
     */
    public int getSuedLawyer(){
        return suedLawyer;
    }

    /**
     * This function sets the case file to the file that is passed in
     * 
     * @param file The file to be read
     */
    public void setCaseFile(String file){
        this.caseFile = file;
    }

    /**
     * This function returns the case file of the case
     * 
     * @return The caseFile variable is being returned.
     */
    public String getCaseFile(){
        return this.caseFile;
    }

    /**
     * This function sets the court records of the defendant
     * 
     * @param records The court records of the case.
     */
    public void setCourtRecords(String records){
        this.courtRecords = records;
    }

    /**
     * This function returns the court records of the defendant
     * 
     * @return The courtRecords variable is being returned.
     */
    public String getCourtRecords(){
        return this.courtRecords;
    }

    /**
     * This function sets the status of the lawsuit to the status passed in as a parameter
     * 
     * @param status The status of the lawsuit.
     */
    public void setStatus(LawsuitStatus status){
        this.status = status;
    }

    /**
     * This function returns the status of the lawsuit
     * 
     * @return The status of the lawsuit.
     */
    public LawsuitStatus getStatus(){
        return this.status;
    }

    /**
     * This function sets the lawsuit type of the lawsuit object
     * 
     * @param type The type of lawsuit.
     */
    public void setLawsuitType(LawsuitTypes type){
        this.lawsuitType = type;
    }

    /**
     * This function returns the lawsuit type of the lawsuit
     * 
     * @return The lawsuitType is being returned.
     */
    public LawsuitTypes getLawsuitType(){
        return lawsuitType;
    }

    /**
     * This function returns the value of the suedDefence variable
     * 
     * @return suedDefence
     */
    public String getSuedDefence() {
        return suedDefence;
    }

    /**
     * This function sets the suedDefence variable to the value of the suedDefence parameter
     * 
     * @param suedDefence The defendant's defence
     */
    public void setSuedDefence(String suedDefence) {
        this.suedDefence = suedDefence;
    }

    /**
     * This function returns the suingDefence variable
     * 
     * @return suingDefence
     */
    public String getSuingDefence() {
        return suingDefence;
    }

    /**
     * This function sets the suingDefence variable to the suingDefence parameter
     * 
     * @param suingDefence The name of the defence that is suing the other defence.
     */
    public void setSuingDefence(String suingDefence) {
        this.suingDefence = suingDefence;
    }

    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Case ID: " + this.id + "\n");
        sb.append("Date: " + this.date + "\n");
        sb.append("Judge ID: " + this.judge + "\n");
        sb.append("Suing Citizen ID: " + this.suingCitizen + "\n");
        sb.append("Sued Citizen ID: " + this.suedCitizen + "\n");
        sb.append("Suing Lawyer ID: " + this.suingLawyer + "\n");
        sb.append("Sued Lawyer ID: " + this.suedLawyer + "\n");
        sb.append("Suing Defence: " + this.suingDefence + "\n");
        sb.append("Sued Defence: " + this.suedDefence + "\n");
        sb.append("Lawsuit Type: " + this.lawsuitType + "\n");
        sb.append("Case File: " + this.caseFile + "\n");
        sb.append("Court Records: " + this.courtRecords + "\n");
        sb.append("Status: " + this.status + "\n");

        return sb.toString();
    }
}