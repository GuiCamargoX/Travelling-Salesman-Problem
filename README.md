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

- Theory and key ideas first: `docs/HUNGARIAN_THEORY.md`
- Step-by-step worked example: `docs/HUNGARIAN_STEP_BY_STEP.md`
- Why TSP is famous and how this repo approaches it: `docs/TSP_INTRO.md`
- Concepts and algorithm flow: `docs/HOW_IT_WORKS.md`
- Hands-on examples and checklists: `docs/LEARNING_PLAYBOOK.md`

## 🧑‍🏫 Lesson plan

- **30 minutes (quick intuition):** read `docs/TSP_INTRO.md` and `docs/HUNGARIAN_THEORY.md`, then run `./scripts/build.sh` and `./scripts/run.sh --skip-build`.
- **2 hours (guided practice):** complete `docs/HUNGARIAN_STEP_BY_STEP.md`, then switch matrix in `Hungarian Algorithm/src/Main/Main.java` using `ExampleMatrices` and compare final `Z*`.
- **Deep dive (implementation focus):** trace `runHungarianMethod()` in `Hungarian Algorithm/src/Control/ImpHungarian.java` and map each stage to the corresponding tool class in `Hungarian Algorithm/src/Implementations/`.

## ✅ Smoke verification

- Build must succeed.
- Run must finish and print feasibility messages and a final best-route section (`Z*=` output).

## ⚠️ Important notes

- Call order is required: `runHungarianMethod()` before `solveProblemTsp(...)`.
- Matrix operations assume square matrices.
- Sentinel penalty values are behavior-critical (`100000` and `10000`).
