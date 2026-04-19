# AGENTS.md

## Maintainer intent

- Keep behavior stable unless the user explicitly approves behavior changes.
- Keep changes small, reviewable, and beginner-friendly.
- Prefer executable truth (code/config/scripts) over prose when they conflict.

## Project shape

- Single Eclipse Java 8 project rooted at `Hungarian Algorithm/`.
- Entrypoint: `Hungarian Algorithm/src/Main/Main.java`.
- Orchestration: `Hungarian Algorithm/src/Control/ImpHungarian.java`.
- Core algorithm steps: `Hungarian Algorithm/src/Implementations/*.java`.

## Exact compile/run commands

From repo root:

```bash
cd "Hungarian Algorithm"
mkdir -p bin
javac -encoding ISO-8859-1 -d bin src/Interfaces/*.java src/Implementations/*.java src/Control/*.java src/Main/*.java
java -cp bin Main.Main
```

## Critical coupling (easy to break)

- Call order matters: `useMethod()` must run before `SolveProblemTSP(...)`.
- Branching in `SolveProblemTSP` depends on state set by `useMethod()` (`path`, `ToPenalth`, `work`).
- Sentinel "infinity" values are behavior-critical:
  - `ImpHungarian.INFINITY = 100000`
  - `ProblemTSPTool.INFINITY = 10000`
- Matrix operations assume square matrices (`m.length` used for row and column loops).
- `LineTool` uses static mutable state (`count`, `m2`, `m3`) and is not concurrency-safe.

## Verification (smoke level)

- Compile with `javac` command above.
- Run `java -cp bin Main.Main`.
- Confirm it finishes and prints a feasible/non-feasible status and best-route section.

## Repo gotchas

- Repository path includes spaces; quote paths in shell commands.
- `Hungarian Algorithm/bin/` is generated output and must not be committed.
- Eclipse project metadata is tracked (`.project`, `.classpath`, `.settings/...`); avoid unrelated IDE churn.
