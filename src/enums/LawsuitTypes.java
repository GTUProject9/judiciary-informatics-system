package enums;

public enum LawsuitTypes 
{
    PERSONAL_INJURY (1), PRODUCT_LIABILITY(2), DIVORCE_AND_FAMILY_LAW_DISPUTES(3), CRIMINAL_CASES(4);

    private int code;
    LawsuitTypes(int code) {
        this.code = code;
    }

    public String getLawSuitType()
    {
        switch(this)
        {
            case PERSONAL_INJURY:
                return "Personal Injury Lawsuit";
            case PRODUCT_LIABILITY:
                return "Product Liability Lawsuit";
            case DIVORCE_AND_FAMILY_LAW_DISPUTES:
                return "Divorce and Family Law Disputes";
            case CRIMINAL_CASES:
                return "Criminal Cases";
            default:
                return "";
        }
    }
    public int getLawSuitCode()
    {
        return code;
    }
}
