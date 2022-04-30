package enums;

public enum Status
{
    HOLD_ON, STILL_GOING, SUING_WON, SUED_WON;
    public String getStatus()
    {
        switch(this)
        {
            case HOLD_ON:
                return "Hold On";
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
