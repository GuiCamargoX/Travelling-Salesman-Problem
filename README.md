# Travelling Salesman Problem (Hungarian Method Study Project)

This repository is a small Java project used to study a Hungarian-method-based approach for assignment and a branching strategy for the Travelling Salesman Problem (TSP).

## What is implemented

- A step-by-step Hungarian reduction flow:
  1. row reduction
  2. column reduction
  3. minimum-line cover over zeros
  4. extra-zero creation when needed
  5. feasibility check and route extraction
- A branching loop (`SolveProblemTSP`) that explores penalized matrices and keeps the best feasible route found.
- LaTeX-friendly console output so each matrix transformation can be copied into reports.

## Why this design

- Each Hungarian step lives in its own utility class under `src/Implementations/`.
- `src/Control/ImpHungarian.java` orchestrates the full flow, so beginners can follow one main class first.
- `src/Main/Main.java` uses a hardcoded matrix to make the project runnable with zero setup.

## Tradeoffs

- Easy to run and inspect, but no automated tests yet.
- Output is verbose (good for learning), but not optimized for production logs.
- Uses integer sentinel values for "infinity" (simple, but must stay consistent when refactoring).

## Project shape

- Entry point: `Hungarian Algorithm/src/Main/Main.java`
- Solver orchestration: `Hungarian Algorithm/src/Control/ImpHungarian.java`
- Algorithm steps: `Hungarian Algorithm/src/Implementations/*.java`
- Contracts/placeholders: `Hungarian Algorithm/src/Interfaces/*.java`

## Quick start

From the repository root:

```bash
cd "Hungarian Algorithm"
mkdir -p bin
javac -d bin src/Interfaces/*.java src/Implementations/*.java src/Control/*.java src/Main/*.java
java -cp bin Main.Main
```

If your default terminal encoding is UTF-8, compile with explicit source encoding:

```bash
javac -encoding ISO-8859-1 -d bin src/Interfaces/*.java src/Implementations/*.java src/Control/*.java src/Main/*.java
```

## Common beginner mistakes

- Running from the wrong directory. Compile and run from `Hungarian Algorithm/`.
- Forgetting quoted paths when working from the repository root (it contains spaces).
- Changing sentinel values (`100000` and `10000`) without understanding branching impact.

## Safe refactor workflow

1. Compile and run once before changes.
2. Make one small refactor.
3. Re-compile and run the same matrix.
4. Compare route/cost behavior, not only text output formatting.
