-- Ghana Smart Service Operations Optimizer — database schema
-- 6 tables: locations, roads, service_requests, resources, algorithm_runs
-- (plus a join/log table as needed by Squad 1)

CREATE TABLE IF NOT EXISTS locations (
    location_id     INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT NOT NULL,
    latitude        REAL,
    longitude       REAL,
    zone            TEXT
);

CREATE TABLE IF NOT EXISTS roads (
    road_id         INTEGER PRIMARY KEY AUTOINCREMENT,
    from_location_id INTEGER NOT NULL,
    to_location_id  INTEGER NOT NULL,
    distance_km     REAL NOT NULL,
    travel_time_min REAL,
    FOREIGN KEY (from_location_id) REFERENCES locations(location_id),
    FOREIGN KEY (to_location_id) REFERENCES locations(location_id)
);

CREATE TABLE IF NOT EXISTS service_requests (
    request_id      INTEGER PRIMARY KEY AUTOINCREMENT,
    location_id     INTEGER NOT NULL,
    category        TEXT NOT NULL,
    priority        INTEGER NOT NULL,
    status          TEXT NOT NULL DEFAULT 'PENDING',
    created_at      TEXT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES locations(location_id)
);

CREATE TABLE IF NOT EXISTS resources (
    resource_id     INTEGER PRIMARY KEY AUTOINCREMENT,
    name            TEXT NOT NULL,
    type            TEXT NOT NULL,
    capacity        INTEGER,
    location_id     INTEGER,
    FOREIGN KEY (location_id) REFERENCES locations(location_id)
);

CREATE TABLE IF NOT EXISTS algorithm_runs (
    run_id          INTEGER PRIMARY KEY AUTOINCREMENT,
    algorithm_name  TEXT NOT NULL,
    input_size      INTEGER,
    execution_time_ms REAL,
    run_at          TEXT NOT NULL
);
