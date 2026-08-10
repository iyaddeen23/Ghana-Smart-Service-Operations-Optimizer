-- ============================================================
-- Ghana Smart Service Operations Optimizer - Database Schema
-- SQLite. 6 tables per PRD Section 7.
-- Owner: Squad 1 (Database & Indexing)
-- ============================================================

PRAGMA foreign_keys = ON;


DROP TABLE IF EXISTS audit_events;
DROP TABLE IF EXISTS algorithm_runs;
DROP TABLE IF EXISTS resources;
DROP TABLE IF EXISTS service_requests;
DROP TABLE IF EXISTS roads;
DROP TABLE IF EXISTS locations;

-- ------------------------------------------------------------
-- locations
-- ------------------------------------------------------------
CREATE TABLE locations (
    locationId   TEXT PRIMARY KEY,
    name         TEXT NOT NULL,
    area         TEXT NOT NULL,
    type         TEXT NOT NULL,
    -- coordinates stored as "lat,lng" string
    coordinates  TEXT NOT NULL
);

-- ------------------------------------------------------------
-- roads (edges of the campus graph)
-- ------------------------------------------------------------
CREATE TABLE roads (
    roadId               INTEGER PRIMARY KEY AUTOINCREMENT,
    fromLocationId        TEXT NOT NULL,
    toLocationId          TEXT NOT NULL,
    distance              REAL NOT NULL CHECK (distance > 0),
    travelTime            REAL NOT NULL CHECK (travelTime > 0),
    roadConditionWeight   INTEGER NOT NULL CHECK (roadConditionWeight BETWEEN 1 AND 5),
    FOREIGN KEY (fromLocationId) REFERENCES locations(locationId),
    FOREIGN KEY (toLocationId)   REFERENCES locations(locationId)
);

-- ------------------------------------------------------------
-- service_requests
-- ------------------------------------------------------------
CREATE TABLE service_requests (
    requestId      TEXT PRIMARY KEY,
    source         TEXT NOT NULL,
    destination    TEXT NOT NULL,
    category       TEXT NOT NULL,
    urgency        TEXT NOT NULL CHECK (urgency IN ('Low','Medium','High','Critical')),
    -- ISO-ish "YYYY-MM-DD HH:MM:SS" timestamp strings
    timeSubmitted  TEXT NOT NULL,
    deadline       TEXT NOT NULL,
    status         TEXT NOT NULL CHECK (status IN ('Pending','Assigned','In Progress','Completed','Cancelled')),
    FOREIGN KEY (source)      REFERENCES locations(locationId),
    FOREIGN KEY (destination) REFERENCES locations(locationId)
);

-- ------------------------------------------------------------
-- resources
-- ------------------------------------------------------------
CREATE TABLE resources (
    resourceId          TEXT PRIMARY KEY,
    type                TEXT NOT NULL,
    homeLocation        TEXT NOT NULL,
    capacity            INTEGER NOT NULL CHECK (capacity > 0),
    availabilityStatus  TEXT NOT NULL CHECK (availabilityStatus IN ('Available','Busy','Off-Duty','Under Repair')),
    FOREIGN KEY (homeLocation) REFERENCES locations(locationId)
);

-- ------------------------------------------------------------
-- algorithm_runs (empirical timing/memory evidence, M10)
-- ------------------------------------------------------------
CREATE TABLE algorithm_runs (
    runId          TEXT PRIMARY KEY,
    algorithmName  TEXT NOT NULL,
    inputSize      INTEGER NOT NULL CHECK (inputSize > 0),
    timeNs         INTEGER NOT NULL CHECK (timeNs >= 0),
    memoryKb       REAL NOT NULL CHECK (memoryKb >= 0),
    dateRun        TEXT NOT NULL
);

-- ------------------------------------------------------------
-- audit_events (stack-based undo/audit log - LIFO by design)
-- ------------------------------------------------------------
CREATE TABLE audit_events (
    eventId      INTEGER PRIMARY KEY AUTOINCREMENT,
    -- entityType: e.g. 'service_requests', 'resources', ...
    entityType   TEXT NOT NULL,
    -- entityId: primary key of the affected row, e.g. 'R0123'
    entityId     TEXT NOT NULL,
    -- action: one of 'INSERT' | 'UPDATE' | 'DELETE' | 'UNDO'
    action       TEXT NOT NULL,
    -- payload: JSON snapshot used to undo the action
    payload      TEXT,
    createdAt    TEXT NOT NULL DEFAULT (datetime('now'))
);

CREATE INDEX idx_roads_from ON roads(fromLocationId);
CREATE INDEX idx_roads_to   ON roads(toLocationId);
CREATE INDEX idx_requests_status  ON service_requests(status);
CREATE INDEX idx_requests_urgency ON service_requests(urgency);
