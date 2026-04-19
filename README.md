# 🧭 Travelling Salesman Problem (Hungarian Method Study Project)

This repository is a beginner-friendly Java project that demonstrates a Hungarian-method workflow and a branching strategy for the Travelling Salesman Problem (TSP).

## 🚀 Quick start

From repository root:

```bash
./scripts/build.sh
./scripts/run.sh --skip-build
./scripts/clean.sh
```

Manual equivalent:

```bash
cd "Hungarian Algorithm"
mkdir -p bin
javac -encoding ISO-8859-1 -d bin src/Interfaces/*.java src/Implementations/*.java src/Control/*.java src/Main/*.java
java -cp bin Main.Main
```

## 🗺️ Project map

- Entry point: `Hungarian Algorithm/src/Main/Main.java`
- Demo matrices: `Hungarian Algorithm/src/Main/ExampleMatrices.java`
- Solver orchestration: `Hungarian Algorithm/src/Control/ImpHungarian.java`
- Output/report formatting: `Hungarian Algorithm/src/Control/HungarianLatexReporter.java`
- Algorithm steps: `Hungarian Algorithm/src/Implementations/*.java`

## 📚 Learning docs

- Concepts and algorithm flow: `docs/HOW_IT_WORKS.md`
- Hands-on examples and checklists: `docs/LEARNING_PLAYBOOK.md`

## ✅ Smoke verification

- Build must succeed.
- Run must finish and print feasibility messages and a final best-route section (`Z*=` output).

## ⚠️ Important notes

- Call order is required: `runHungarianMethod()` before `solveProblemTsp(...)`.
- Matrix operations assume square matrices.
- Sentinel penalty values are behavior-critical (`100000` and `10000`).
