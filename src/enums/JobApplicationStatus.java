package enums;

public enum JobApplicationStatus {
    PENDING, ACCEPTED, REJECTED, CANCELLED;
    public String getStatus() {
        switch (this) {
            case PENDING:
                return "Pending";
            case CANCELLED:
                return "Cancelled";
            case ACCEPTED:
                return "Accepted";
            case REJECTED:
                return "Rejected";
            default:
                return "";
        }
    }
}