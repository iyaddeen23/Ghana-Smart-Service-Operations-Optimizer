package com.campus.optimizer.db.models;

/** Represents a row of the `service_requests` table. */
public class ServiceRequest {

    private final String requestId;
    private final String source;
    private final String destination;
    private final String category;
    private final String urgency;       // Low | Medium | High | Critical
    private final String timeSubmitted; // "yyyy-MM-dd HH:mm:ss"
    private final String deadline;
    private final String status;        // Pending | Assigned | In Progress | Completed | Cancelled

    public ServiceRequest(String requestId, String source, String destination, String category,
                           String urgency, String timeSubmitted, String deadline, String status) {
        this.requestId = requestId;
        this.source = source;
        this.destination = destination;
        this.category = category;
        this.urgency = urgency;
        this.timeSubmitted = timeSubmitted;
        this.deadline = deadline;
        this.status = status;
    }

    public String getRequestId() { return requestId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getCategory() { return category; }
    public String getUrgency() { return urgency; }
    public String getTimeSubmitted() { return timeSubmitted; }
    public String getDeadline() { return deadline; }
    public String getStatus() { return status; }

    /** Maps urgency to a numeric priority used by the scheduling engine's heap (Squad 4). */
    public int urgencyRank() {
        return switch (urgency) {
            case "Critical" -> 4;
            case "High" -> 3;
            case "Medium" -> 2;
            default -> 1; // Low
        };
    }

    @Override
    public String toString() {
        return "ServiceRequest{" + requestId + ", " + category + ", " + urgency +
                ", status=" + status + "}";
    }
}
