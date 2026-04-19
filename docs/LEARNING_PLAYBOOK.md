# Learning Playbook

## Correct usage examples

### Run the default 5-city example

```bash
./scripts/build.sh
./scripts/run.sh --skip-build
```

Expected outcome:
- multiple LaTeX tables
- feasibility lines (`The solution above is ...`)
- final best route section with `Z*=`

### Try another matrix safely

1. Open `Hungarian Algorithm/src/Main/Main.java`.
2. Replace:

```java
int[][] matrix = ExampleMatrices.fiveCityDemoMatrix();
```

with:

```java
int[][] matrix = ExampleMatrices.sixCityDistanceDemoMatrix();
```

3. Run build and run again.

## Common mistakes

- Calling `solveProblemTsp(...)` before `runHungarianMethod()`.
  - Why it fails: branching depends on state set by the first call.
- Using non-square matrices.
  - Why it fails: helper tools use `m.length` for both rows and columns.
- Changing `INFINITY` constants casually.
  - Why it fails: branch penalties and forbidden-edge behavior shift.
- Committing generated `bin/` classes.
  - Why it fails: noisy diffs and non-source artifacts in history.

## Safe refactor workflow

1. Run baseline smoke test and capture final route/cost.
2. Make one small change only.
3. Run smoke test again.
4. Compare behavior (feasible/not feasible transitions and final `Z*`).
5. Commit only when behavior is unchanged (unless behavior change was intentional).

## Debugging checklist

- Did compilation succeed with `./scripts/build.sh`?
- Does output still include feasibility messages?
- Does output still include final best route with `Z*=`?
- Did you keep call order (`runHungarianMethod` then `solveProblemTsp`)?
- Are `ImpHungarian.INFINITY` and `ProblemTSPTool.INFINITY` unchanged?
- If refactoring output code, is route formatting still LaTeX-friendly (`\\` and `$\\rightarrow$`)?
