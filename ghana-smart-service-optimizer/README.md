# Ghana Smart Service Operations Optimizer

DCIT 204/308 joint DSA semester project — a Java console system for a
**University of Ghana, Legon** Campus Service Hub: locations, roads, service
requests and resources are stored in SQLite, loaded into custom-built data
structures, and processed with hand-written search/sort/graph/greedy/DP
algorithms.

No built-in Java collections (`HashMap`, `TreeMap`, `PriorityQueue`, `Stack`,
`ArrayDeque`, etc.) are used for structures or algorithms — only for file I/O,
JDBC, and test scaffolding, per the brief.

## Requirements
- Java 17+
- Maven 3.9+

## Build

```bash
mvn clean compile
```

## Run the tests

```bash
mvn test
```

## Load the database (Squad 1 — DB & Indexing)

The program seeds `campus_optimizer.db` (SQLite) from `schema.sql` and the
CSVs in `data/`, then loads everything into the custom structures:

```java
DatabaseConnection.initializeSchema("schema.sql");
DataLoader loader = new DataLoader();
loader.loadAll("data");
```

Squad 6's `ConsoleMenu` / `Main` wires this into the interactive app; until
that lands, the snippet above (or `DataLoaderTest`) is enough to exercise the
DB layer end to end.

## Package a runnable jar

```bash
mvn clean package
java -jar target/ghana-smart-service-optimizer.jar
```

## Project layout

See `docs/` for diagrams, trace tables, and proof sketches, and the PRD for
the full squad/file ownership map. Folder summary:

- `data/` — source CSVs loaded into the database (Squad 1)
- `schema.sql` — the 6-table SQLite schema (Squad 1)
- `src/main/java/com/campus/optimizer/` — application source (`db`,
  `structures`, `algorithms`, `engine`, `ui`)
- `src/test/java/com/campus/optimizer/` — unit tests
- `performance/` — benchmark runner, raw results, exported graphs (Squad 6)
- `docs/` — report, diagrams, trace tables, proof sketches, screenshots
- `ai-disclosure/` — squad prompt logs
- `video/` — demo video link

## Dataset

Script-generated, using real University of Ghana, Legon locations (Balme
Library, Great Hall, the halls of residence, departmental buildings, shuttle
terminals, etc.) per the Ghana-localisation rule. See
`docs/dataset_evidence_note.md` for how it was built.
