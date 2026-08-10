package com.campus.optimizer.db.models;

/**
 * Represents a row of the `locations` table.
 * Plain data holder - no business logic here.
 */
public class Location {

    private final String locationId;
    private final String name;
    private final String area;
    private final String type;
    private final String coordinates; // "lat,lng"

    public Location(String locationId, String name, String area, String type, String coordinates) {
        this.locationId = locationId;
        this.name = name;
        this.area = area;
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getLocationId() { return locationId; }
    public String getName() { return name; }
    public String getArea() { return area; }
    public String getType() { return type; }
    public String getCoordinates() { return coordinates; }

    @Override
    public String toString() {
        return "Location{id=" + locationId + ", name=" + name + ", area=" + area +
                ", type=" + type + ", coords=" + coordinates + "}";
    }
}
