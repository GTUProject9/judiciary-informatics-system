package enums;

public enum LawsuitStatus
{
    ON_HOLD, STILL_GOING, SUING_WON, SUED_WON;
    public String getStatus()
    {
        switch(this)
        {
            case ON_HOLD:
                return "On Hold";
            case STILL_GOING:
                return "Still Going";
            case SUING_WON:
                return "Suing Won";
            case SUED_WON:
                return "Sued Won";
            default:
                return "";
        }
    }
}
