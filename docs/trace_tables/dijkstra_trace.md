# Dijkstra's Algorithm Trace Table
## Graph Setup
- **Source Node:** A (e.g., Main Campus Hub)
- **Nodes:** A, B, C, D
- **Edges & Weights:** 
  - (A, B) = 4.0
  - (A, C) = 2.0
  - (C, B) = 1.0
  - (B, D) = 5.0
  - (C, D) = 8.0
---
## Execution Trace

| Step | Current Node | Distance Array `dist[]` | Priority Queue State | Action / Path Updates |
| :--- | :--- | :--- | :--- | :--- |
| **0** | - | `{A: 0, B: ∞, C: ∞, D: ∞}` | `[(A, 0.0)]` | Initialize source distance to 0, all others to ∞. |
| **1** | **A** | `{A: 0, B: 4, C: 2, D: ∞}` | `[(C, 2.0), (B, 4.0)]` | Extract `A`. Relax edges (A,B) and (A,C). Insert B, C into PQ. |
| **2** | **C** | `{A: 0, B: 3, C: 2, D: 8}` | `[(B, 3.0), (B, 4.0), (D, 8.0)]` | Extract `C` (min weight 2.0). Relax (C,B): 2+1=3 < 4 (update B). Relax (C,D): 2+8=10 (update D). |
| **3** | **B** | `{A: 0, B: 3, C: 2, D: 8}` | `[(B, 4.0), (D, 8.0)]` | Extract `B` (dist 3.0). Relax (B,D): 3+5=8. |
| **4** | **B (stale)** | `{A: 0, B: 3, C: 2, D: 8}` | `[(D, 8.0)]` | Extract stale entry `(B, 4.0)`. Ignore as `4.0 > dist[B] (3.0)`. |
| **5** | **D** | `{A: 0, B: 3, C: 2, D: 8}` | `[]` | Extract `D` (dist 8.0). No outgoing edges to relax. PQ empty. |

---
## Final Shortest Paths from A
- **A → A:** 0.0
- **A → C:** 2.0 (Path: A → C)
- **A → B:** 3.0 (Path: A → C → B)
- **A → D:** 8.0 (Path: A → C → B → D)
