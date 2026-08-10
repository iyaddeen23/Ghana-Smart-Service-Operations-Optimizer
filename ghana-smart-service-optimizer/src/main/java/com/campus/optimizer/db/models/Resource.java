package com.campus.optimizer.db.models;

/** Represents a row of the `resources` table. */
public class Resource {

    private final String resourceId;
    private final String type;
    private final String homeLocation;
    private final int capacity;
    private final String availabilityStatus; // Available | Busy | Off-Duty | Under Repair

    public Resource(String resourceId, String type, String homeLocation,
                     int capacity, String availabilityStatus) {
        this.resourceId = resourceId;
        this.type = type;
        this.homeLocation = homeLocation;
        this.capacity = capacity;
        this.availabilityStatus = availabilityStatus;
    }

    public String getResourceId() { return resourceId; }
    public String getType() { return type; }
    public String getHomeLocation() { return homeLocation; }
    public int getCapacity() { return capacity; }
    public String getAvailabilityStatus() { return availabilityStatus; }

    @Override
    public String toString() {
        return "Resource{" + resourceId + ", " + type + ", home=" + homeLocation +
                ", cap=" + capacity + ", status=" + availabilityStatus + "}";
    }
}
