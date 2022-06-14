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

    //Ends lawsuit with a decision of the judge.
    public LawsuitStatus concludeLawsuit(LawsuitStatus judgeDecision){
        status = judgeDecision;
        return judgeDecision;
    }

    //Adds case file to the lawsuit
    public void addCaseFile(String caseFile){
        this.caseFile = caseFile;
    }

    //Adds court records to the lawsuit
    public void addCourtRecord(String courtRecords){
        this.courtRecords = courtRecords;
    }


    //---------Setters and Getters---------
    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public void setJudge(int judge){
        this.judge = judge;
    }

    public int getJudge(){
        return this.judge;
    }

    public void setSuingCitizen(int citizen){
        this.suingCitizen = citizen;
    }

    public int getSuingCitizen(){
        return this.suingCitizen;
    }

    public void setSuedCitizen(int citizen){
        this.suedCitizen = citizen;
    }

    public int getSuedCitizen(){
        return this.suedCitizen;
    }

    public void setSuingLawyer(int lawyer){
        this.suingLawyer = lawyer;
    }

    public int getSuingLawyer(){
        return this.suingLawyer;
    }

    public void setSuedLawyer(int lawyer){
        this.suedLawyer = lawyer;
    }

    public int getSuedLawyer(){
        return suedLawyer;
    }

    public void setCaseFile(String file){
        this.caseFile = file;
    }

    public String getCaseFile(){
        return this.caseFile;
    }

    public void setCourtRecords(String records){
        this.courtRecords = records;
    }

    public String getCourtRecords(){
        return this.courtRecords;
    }

    public void setStatus(LawsuitStatus status){
        this.status = status;
    }

    public LawsuitStatus getStatus(){
        return this.status;
    }

    public void setLawsuitType(LawsuitTypes type){
        this.lawsuitType = type;
    }

    public LawsuitTypes getLawsuitType(){
        return lawsuitType;
    }

    public String getSuedDefence() {
        return suedDefence;
    }

    public void setSuedDefence(String suedDefence) {
        this.suedDefence = suedDefence;
    }

    public String getSuingDefence() {
        return suingDefence;
    }

    public void setSuingDefence(String suingDefence) {
        this.suingDefence = suingDefence;
    }

    @Override
    public String toString() {
        return super.toString() + " " + date + " " + judge + " " + suingCitizen + " " + suedCitizen + " " + 
               suingLawyer + " " + suedLawyer + " " + suingDefence + " " + suedDefence + " " + lawsuitType + " " + 
               caseFile + " " + courtRecords + " " + status;
    }
}