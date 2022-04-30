package enums;

public enum SystemObjectTypes
{
    LAWSUIT(1), CITIZEN(2), LAWYER(3), LAWOFFICE_OWNER(4), JUDGE(5), GOVERNMENT_OFFICIAL(6);
    
    private int code;
    
    SystemObjectTypes(int code) {
        this.code = code;
    }
    public int getSystemObjectCode() {
        return code;
    }
}
