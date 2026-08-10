package com.campus.optimizer.db.models;

/** Represents a row of the `roads` table - a directed/weighted edge in the campus graph. */
public class Road {

    private final int roadId;
    private final String fromLocationId;
    private final String toLocationId;
    private final double distance;             // km
    private final double travelTime;           // minutes
    private final int roadConditionWeight;     // 1 (excellent) .. 5 (poor)

    public Road(int roadId, String fromLocationId, String toLocationId,
                double distance, double travelTime, int roadConditionWeight) {
        this.roadId = roadId;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distance = distance;
        this.travelTime = travelTime;
        this.roadConditionWeight = roadConditionWeight;
    }

    public int getRoadId() { return roadId; }
    public String getFromLocationId() { return fromLocationId; }
    public String getToLocationId() { return toLocationId; }
    public double getDistance() { return distance; }
    public double getTravelTime() { return travelTime; }
    public int getRoadConditionWeight() { return roadConditionWeight; }

    @Override
    public String toString() {
        return "Road{" + fromLocationId + "->" + toLocationId +
                ", dist=" + distance + "km, time=" + travelTime + "min, cond=" + roadConditionWeight + "}";
    }
}
