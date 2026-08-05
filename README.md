# Ghana Smart Service Operations Optimizer

A campus service-operations optimizer built around custom data structures and algorithms, backed by a SQLite database.

## Requirements

- Java 17+
- Maven 3.8+

## Build

```bash
mvn clean install
```

## Run

```bash
mvn exec:java -Dexec.mainClass="com.campus.optimizer.Main"
```

## Test

```bash
mvn test
```

## Project Layout

See the repository root for the full structure:

- `data/` — source CSV datasets loaded into the database
- `src/main/java/com/campus/optimizer/` — application source (db, structures, algorithms, engine, ui)
- `src/test/java/com/campus/optimizer/` — unit tests
- `performance/` — benchmark runner, raw results, and exported graphs
- `docs/` — report, diagrams, trace tables, proof sketches, screenshots
- `ai-disclosure/` — squad prompt logs
- `video/` — demo video link

## Squads

| Squad | Structures | Algorithms |
|-------|-----------|------------|
| 1 | DynamicArray, HashTable, CustomSet | Linear/Binary Search |
| 2 | Graph, CircularQueue | BFS, DFS |
| 3 | DisjointSet, PriorityQueue | Dijkstra, Kruskal, Prim |
| 4 | LinkedList, Stack | Greedy, Dynamic Programming |
| 5 | BST, Deque | Sort algorithms |
| 6 | BalancedTree, BTree | Console UI, Benchmarking |
