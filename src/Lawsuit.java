
import java.util.Date;

import enums.LawsuitTypes;
import enums.LawsuitStatus;

public class Lawsuit extends AbstractSystemObject
{
    private Date date;
    private Judge judge;
    private Citizen suingCitizen;
    private Citizen suedCitizen;
    private Lawyer suingLawyer;
    private Lawyer suedLawyer;

    //Personal Injury Lawsuit, Product Liability Lawsuit, Divorce and Family Law Disputes, Criminal Cases
    private LawsuitTypes lawsuitType;
    private String caseFile;
    private String courtRecords;
    private LawsuitStatus status;

    Lawsuit(){}
    public Lawsuit(Integer id, Date date, Judge judge, Citizen suingCitizen, Citizen suedCitizen,
                   Lawyer suingLawyer, Lawyer suedLawyer, LawsuitTypes lawsuitType, LawsuitStatus status,
                   String caseFile, String courtRecords) {
        super(id);
        this.date = date;
        this.judge = judge;
        this.suingCitizen = suingCitizen;
        this.suedCitizen = suedCitizen;
        this.suingLawyer = suingLawyer;
        this.suedLawyer = suedLawyer;
        this.lawsuitType = lawsuitType;
        this.caseFile = caseFile;
        this.courtRecords = courtRecords;
        this.status = status;
    }
    
    //Ends lawsuit with a decision of the judge.
    public LawsuitStatus endLawsuit(LawsuitStatus judgeDecision){
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

    public void setJudge(Judge judge){
        this.judge = judge;
    }

    public Judge getJudge(){
        return this.judge;
    }

    public void setSuingCitizen(Citizen citizen){
        this.suingCitizen = citizen;
    }

    public Citizen getSuingCitizen(){
        return this.suingCitizen;
    }

    public void setSuedCitizen(Citizen citizen){
        this.suedCitizen = citizen;
    }

    public Citizen getSuedCitizen(){
        return this.suedCitizen;
    }

    public void setSuingLawyer(Lawyer lawyer){
        this.suingLawyer = lawyer;
    }

    public Lawyer getSuingLawyer(){
        return this.suingLawyer;
    }

    public void setSuedLawyer(Lawyer lawyer){
        this.suedLawyer = lawyer;
    }

    public Lawyer getSuedLawyer(){
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

    public String getStatus(){
        return status.getStatus();
    }

    public void setLawsuitType(LawsuitTypes type){
        this.lawsuitType = type;
    }

    public String getLawsuitType(){
        return lawsuitType.getLawSuitType();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + super.toString() + "\n");
        sb.append(date.toString() + "\n");
        return sb.toString();
    }
}