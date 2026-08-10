package com.campus.optimizer;

import com.campus.optimizer.db.DataLoader;
import com.campus.optimizer.db.DatabaseConnection;
import com.campus.optimizer.engine.IndexingEngine;
import com.campus.optimizer.structures.DynamicArray;
import com.campus.optimizer.db.models.Location;
import com.campus.optimizer.db.models.ServiceRequest;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ghana Smart Service Operations Optimizer ===");
        System.out.println("Initialising database and loading data...");

        DatabaseConnection.initializeSchema("schema.sql");

        DataLoader loader = new DataLoader();
        loader.loadAll("data");

        System.out.println("Data load complete:");
        System.out.println("  Locations: " + loader.getLocations().size());
        System.out.println("  Roads: " + loader.getRoads().size());
        System.out.println("  Service Requests: " + loader.getServiceRequests().size());
        System.out.println("  Resources: " + loader.getResources().size());
        System.out.println("  Algorithm Runs: " + loader.getAlgorithmRuns().size());

        IndexingEngine engine = new IndexingEngine(loader);

        System.out.println("\n--- Sample Indexing Lookups ---");

        Location byId = engine.findLocationById("L001");
        System.out.println("Location L001 (HashTable O(1)): " + byId);

        if (loader.getLocations().size() > 0) {
            Location first = loader.getLocations().get(0);
            Location byLinear = engine.findLocationByNameLinear(first.getName());
            System.out.println("Find '" + first.getName() + "' (Linear O(n)): " + byLinear);
            Location byBinary = engine.findLocationByNameBinary(first.getName());
            System.out.println("Find '" + first.getName() + "' (Binary O(log n)): " + byBinary);
        }

        if (loader.getServiceRequests().size() > 0) {
            ServiceRequest firstReq = loader.getServiceRequests().get(0);
            ServiceRequest reqById = engine.findServiceRequestById(firstReq.getRequestId());
            System.out.println("Request " + firstReq.getRequestId() + " (HashTable O(1)): " + reqById);

            DynamicArray<ServiceRequest> byCat = engine.findRequestsByCategory(firstReq.getCategory());
            System.out.println("Requests in category '" + firstReq.getCategory() + "': " + byCat.size());
        }

        DatabaseConnection.close();
        System.out.println("\nDone.");
    }
}
