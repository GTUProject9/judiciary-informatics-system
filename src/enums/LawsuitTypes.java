package enums;

public enum LawsuitTypes 
{
    PERSONAL_INJURY (1), PRODUCT_LIABILITY(2), DIVORCE_AND_FAMILY_LAW_DISPUTES(3), CRIMINAL_CASES(4);

    private int code;
    LawsuitTypes(int code) {
        this.code = code;
    }
    public int getLawSuitCode()
    {
        return code;
    }
}
