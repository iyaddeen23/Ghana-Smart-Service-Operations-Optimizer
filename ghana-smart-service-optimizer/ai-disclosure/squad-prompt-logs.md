# AI disclosure — squad prompt logs

Each squad appends its own entries below as work happens, per the PRD's
`ai-disclosure/` requirement. Keep entries factual: what was asked, what tool
produced it, and what a human then reviewed/changed.

---

## Squad 1 — Database & Indexing

**Date:** 2026-08-10
**Tool:** Claude (Anthropic), web/chat interface with code execution
**Member(s) present:** (fill in names)

**Prompt (summarised):** "I want to work on the db side for this project,
suggest what I will do in detail and start it, using University of Ghana
Legon campus for the dataset. Files need to be pushed to the GitHub repo."

**What was generated:**
- `data/*.csv` (locations, roads, service_requests, resources,
  algorithm_runs) — script-generated with a Python generator using real
  Legon place names, seeded for reproducibility.
- `schema.sql` — the 6-table SQLite schema.
- `pom.xml` — Maven config (sqlite-jdbc + JUnit 5).
- `src/main/java/.../db/DatabaseConnection.java`, `DataLoader.java`
- `src/main/java/.../db/models/*.java`
- `src/main/java/.../structures/DynamicArray.java`, `HashTable.java`,
  `CustomSet.java`
- `src/main/java/.../algorithms/search/LinearSearch.java`,
  `BinarySearch.java`; `algorithms/sort/SelectionSort.java`
- `src/main/java/.../engine/IndexingEngine.java`
- `src/test/.../db/DataLoaderTest.java` and structure/algorithm unit tests
- `README.md`, `docs/dataset_evidence_note.md`

**Human review still required before submission:**
- [ ] Fill in real Squad 1 member index numbers into
      `docs/dataset_evidence_note.md` and wire the 3 required
      index-number-derived parameters into the actual code (currently only
      documented, not yet implemented in `HashTable`/benchmark seeding).
- [ ] Run `mvn clean test` locally/in CI (not possible in the generation
      sandbox — no network/Maven there) and confirm all tests pass.
- [ ] Add `docs/diagrams/` visuals for `DynamicArray` resize and `HashTable`
      chaining, since Section 5's evidence column asks for diagrams.
- [ ] Review CSV realism with the rest of the team (e.g., confirm building
      names/areas match current campus signage) before final submission.
