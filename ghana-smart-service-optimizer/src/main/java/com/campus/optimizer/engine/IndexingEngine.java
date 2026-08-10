package com.campus.optimizer.engine;

import com.campus.optimizer.algorithms.search.BinarySearch;
import com.campus.optimizer.algorithms.search.LinearSearch;
import com.campus.optimizer.algorithms.sort.SelectionSort;
import com.campus.optimizer.db.DataLoader;
import com.campus.optimizer.db.models.Location;
import com.campus.optimizer.db.models.ServiceRequest;
import com.campus.optimizer.structures.DynamicArray;
import com.campus.optimizer.structures.HashTable;

import java.util.Comparator;

/**
 * M6 - Indexing engine. Supports search over requests/locations/resources.
 * Demonstrates all three of Squad 1's required algorithms working together:
 *   1. O(1) average lookup by id via HashTable (fastest path)
 *   2. LinearSearch as the unordered fallback / comparison baseline
 *   3. SelectionSort + BinarySearch as the "sort once, then fast-search" path
 */
public class IndexingEngine {

    private final DataLoader dataLoader;
    private final DynamicArray<Location> locationsByName; // sorted lazily on first name search
    private boolean sortedByName = false;

    private static final Comparator<Location> BY_NAME =
            Comparator.comparing(Location::getName);

    public IndexingEngine(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
        this.locationsByName = new DynamicArray<>();
        for (Location loc : dataLoader.getLocations()) {
            locationsByName.insert(loc);
        }
    }

    /** O(1) average - direct hash lookup by primary key. */
    public Location findLocationById(String locationId) {
        HashTable<String, Location> index = dataLoader.getLocationIndex();
        return index.get(locationId);
    }

    /** O(n) - linear scan over all locations, no ordering required. */
    public Location findLocationByNameLinear(String name) {
        Location probe = new Location(null, name, null, null, null);
        int idx = LinearSearch.search(locationsByName, probe,
                Comparator.comparing(Location::getName));
        return idx == -1 ? null : locationsByName.get(idx);
    }

    /** O(log n) after a one-time O(n log n) sort - binary search by name. */
    public Location findLocationByNameBinary(String name) {
        if (!sortedByName) {
            SelectionSort.sort(locationsByName, BY_NAME);
            sortedByName = true;
        }
        Location probe = new Location(null, name, null, null, null);
        int idx = BinarySearch.search(locationsByName, probe, BY_NAME);
        return idx == -1 ? null : locationsByName.get(idx);
    }

    /** O(1) average - direct hash lookup for a service request by id. */
    public ServiceRequest findServiceRequestById(String requestId) {
        return dataLoader.getServiceRequestIndex().get(requestId);
    }

    /** Linear scan for all requests matching a category (no index built for this yet). */
    public DynamicArray<ServiceRequest> findRequestsByCategory(String category) {
        DynamicArray<ServiceRequest> matches = new DynamicArray<>();
        for (ServiceRequest sr : dataLoader.getServiceRequests()) {
            if (sr.getCategory().equalsIgnoreCase(category)) {
                matches.insert(sr);
            }
        }
        return matches;
    }
}
