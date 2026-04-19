# 🌍 TSP Introduction (Why It Is Famous)

## What is TSP?

The Travelling Salesman Problem (TSP) asks:

- Given a list of cities and travel costs,
- find the minimum-cost tour that visits each city exactly once
- and returns to the start.

It is easy to state and very hard to solve optimally at scale.

## Why it is famous in computer science

- It is a classic **combinatorial optimization** problem.
- It is **NP-hard**, so exact solving can become very expensive as city count grows.
- It appears in real applications: logistics, routing, planning, chip design, and scheduling.
- It is a benchmark for algorithm design (exact, heuristic, and hybrid methods).

## How this repo connects Hungarian ideas to TSP

This repository uses an assignment-style matrix approach as an educational path:

1. Solve a Hungarian-style assignment relaxation (`runHungarianMethod`).
2. If solution is not feasible for tour constraints, identify a conflicting edge.
3. Branch into two child problems (`firstPass`, `secondPass`) with penalties.
4. Explore branches for a chosen number of iterations (`solveProblemTsp(iterations)`).
5. Keep the best feasible route found.

## Two branch rules used here

For a selected edge `(row, col)`:

- `firstPass(...)`:
  - forces that edge choice structure by heavily penalizing alternatives in that row/column,
  - also penalizes the reverse edge to discourage immediate 2-cycles.
- `secondPass(...)`:
  - forbids the selected edge by setting that specific cell to a large penalty.

These rules are implemented in `Hungarian Algorithm/src/Implementations/ProblemTSPTool.java`.

## Important educational caveat

This is a teaching-oriented branch strategy, not a full industrial branch-and-bound framework.

- Result quality depends on iteration budget.
- Sentinel penalties approximate forbidden edges.
- It is excellent for understanding ideas, but not meant as a production-grade TSP solver.

## Where to read next

1. `docs/HUNGARIAN_THEORY.md`
2. `docs/HUNGARIAN_STEP_BY_STEP.md`
3. `docs/HOW_IT_WORKS.md`
