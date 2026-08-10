# Dataset evidence note

## How the dataset was built

All five CSVs in `data/` are **script-generated** (Python, `random.seed(204308)`
for reproducibility — 204/308 is the course code) rather than manually typed
or scraped, per the PRD's locked decision in Section 3.

- **Locations (50 rows)** — real, publicly known University of Ghana, Legon
  place names: halls of residence (Commonwealth, Akuafo, Volta, Legon,
  Mensah Sarbah, ISH, Pentagon cluster halls...), academic buildings (Balme
  Library, JQB, N-Block, Department of Computer Science, School of
  Engineering Sciences, UGBS...), labs, dining spots, sports facilities,
  admin blocks, health services, shuttle terminals, maintenance units, and
  commercial spots (bookshop, bank branches). Coordinates are randomly
  jittered around Legon's approximate campus centroid (5.6500, -0.1870) so
  they plot sensibly on a map without claiming survey-grade accuracy.
- **Roads (100 rows)** — built as a random spanning structure over the 50
  locations first (guaranteeing the campus graph is connected, which several
  algorithms — BFS/DFS/Dijkstra/MST — depend on), then topped up with extra
  random edges to 100 total, giving a realistic road density. Distance,
  travel time, and a 1–5 road-condition weight are randomly assigned within
  plausible campus-walking/shuttle ranges.
- **Service requests (300 rows)** — random `source`/`destination` pairs drawn
  from the 50 locations, realistic campus service categories (Plumbing,
  Electrical, IT Support, Cleaning, Security, Shuttle Request, WiFi Outage,
  etc.), weighted urgency levels, and deadlines derived from urgency (2h for
  Critical up to 72h for Low). No real personal data is used anywhere — no
  student names, IDs, or contact details appear in any record.
- **Resources (30 rows)** — maintenance vans, electrician/plumber teams, IT
  technicians, security patrols, shuttle buses, cleaning crews, and medical
  responders, each with a home location and capacity.
- **Algorithm runs (30 seed rows)** — placeholder timing/memory rows so the
  `algorithm_runs` table isn't empty before Squad 6's `BenchmarkRunner`
  appends real, machine-measured results from the performance experiments in
  Section 9.

## Ghana-localisation rule

Satisfied via real, verifiable Legon place names throughout `locations.csv`,
and campus-relevant service categories in `service_requests.csv`. No
fabricated foreign place names are used.

## Index-number-derived parameters (Section 7 requirement)

At least 3 algorithm parameters must be derived from team members' index
numbers. Recommended for Squad 1's structures/algorithms:

- `HashTable` initial capacity — derive from `(sum of last 3 digits of two
  members' index numbers) % 32 + 16` so it's reproducible per squad member.
- `BinarySearch`/`SelectionSort` tie-break or benchmark seed — use a team
  member's index number as the `random.seed()` for any synthetic benchmark
  input generation in `performance/BenchmarkRunner.java`.
- Document the exact formula and resulting values in this file once the two
  Squad 1 members' index numbers are finalised, so the oral defense can point
  to it directly.
