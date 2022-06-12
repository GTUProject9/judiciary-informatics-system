import java.util.Date;

import enums.LawsuitTypes;
import enums.LawsuitStatus;

public class Lawsuit extends AbstractSystemObject
{
    private Date date;
    private Integer judge;
    private Integer suingCitizen;
    private Integer suedCitizen;
    private Integer suingLawyer;
    private Integer suedLawyer;
    private String suingDefence;
    private String suedDefence;
    //Personal Injury Lawsuit, Product Liability Lawsuit, Divorce and Family Law Disputes, Criminal Cases
    private LawsuitTypes lawsuitType;
    private String caseFile;
    private String courtRecords;
    private LawsuitStatus status;

    public Lawsuit(){}

    public Lawsuit(Integer id, Date date, Integer judge, Integer suingCitizen, Integer suedCitizen,
                   Integer suingLawyer, Integer suedLawyer, LawsuitTypes lawsuitType, LawsuitStatus status,
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
    
    public Lawsuit(Integer id, Date date, Integer suingCitizen, Integer suedCitizen,
                   Integer suingLawyer, LawsuitTypes lawsuitType,
                   String caseFile) {
        super(id);
        this.date = date;
        this.judge = null;
        this.suingCitizen = suingCitizen;
        this.suedCitizen = suedCitizen;
        this.suingLawyer = suingLawyer;
        this.suedLawyer = null;
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

    public void setJudge(Integer judge){
        this.judge = judge;
    }

    public Integer getJudge(){
        return this.judge;
    }

    public void setSuingCitizen(Integer citizen){
        this.suingCitizen = citizen;
    }

    public Integer getSuingCitizen(){
        return this.suingCitizen;
    }

    public void setSuedCitizen(Integer citizen){
        this.suedCitizen = citizen;
    }

    public Integer getSuedCitizen(){
        return this.suedCitizen;
    }

    public void setSuingLawyer(Integer lawyer){
        this.suingLawyer = lawyer;
    }

    public Integer getSuingLawyer(){
        return this.suingLawyer;
    }

    public void setSuedLawyer(Integer lawyer){
        this.suedLawyer = lawyer;
    }

    public Integer getSuedLawyer(){
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
    
    public LawsuitStatus checkStatus()
    {
        return status;
    }

    public void setLawsuitType(LawsuitTypes type){
        this.lawsuitType = type;
    }

    public String getLawsuitType(){
        return lawsuitType.getLawSuitType();
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
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + super.toString() + "\n");
        sb.append(date.toString() + "\n");
        return sb.toString();
    }
}