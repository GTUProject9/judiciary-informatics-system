package enums;

public enum JobApplicationStatus {
    PENDING, CANCELLED, ACCEPTED, REJECTED;
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