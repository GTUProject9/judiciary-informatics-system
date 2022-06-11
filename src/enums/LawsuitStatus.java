package enums;

public enum LawsuitStatus
{
    HOLD, STILL_GOING, SUING_WON, SUED_WON;
    public String getStatus()
    {
        switch(this)
        {
            case HOLD:
                return "Hold";
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
