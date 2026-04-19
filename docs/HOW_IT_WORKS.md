# 🧠 How It Works

## 📌 What is implemented

- A learning-focused Hungarian-method pipeline for square cost matrices.
- A branching TSP exploration (`solveProblemTsp`) that penalizes non-feasible assignments and keeps the best feasible route found.
- LaTeX-friendly console output for matrix snapshots and line maps.

## 🧩 Why this design

- `Control/ImpHungarian` owns algorithm orchestration so beginners can follow one control flow first.
- `Implementations/*Tool` classes isolate individual operations (row reduction, column reduction, line cover, zero creation, feasibility check).
- `Control/HungarianLatexReporter` keeps output formatting separate from solver logic.
- `Main/ExampleMatrices` provides ready-to-run inputs without editing algorithm classes.

## ⚖️ Tradeoffs

- The project is easy to read and run, but has no automated tests yet.
- Sentinel values (`100000` and `10000`) are simple and explicit, but can be fragile if changed without understanding penalties.
- Output is verbose by design for teaching.

## 🔄 End-to-end flow

1. `Main.Main` loads a demo matrix from `Main.ExampleMatrices`.
2. `ImpHungarian.runHungarianMethod()`:
   - prints initial matrix
   - subtracts row minima
   - subtracts column minima
   - finds minimum line cover for zeros
   - creates additional zeros until line count reaches matrix size
   - checks feasibility with `SolutionTool`
3. If feasible, prints route and objective `Z*`.
4. If not feasible, stores the last rejected edge as a penalty pivot.
5. `ImpHungarian.solveProblemTsp(iterations)` explores first-pass/second-pass penalized matrices and prints best route found.

## ⚠️ Coupling you must keep in mind

- Always call `runHungarianMethod()` before `solveProblemTsp(...)`.
- Matrix utilities assume square matrices.
- `LineTool` has static mutable state and is not concurrency-safe.
