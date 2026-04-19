# 🎓 Hungarian Method Theory

## What problem does it solve?

The Hungarian Method solves the **assignment problem**:

- You have `n` workers and `n` tasks.
- Each worker-task pair has a cost.
- You must assign exactly one task to each worker (and one worker to each task) with minimum total cost.

In this repository, the same idea is used as a core step inside a TSP-oriented workflow.

## Core concept (the important intuition)

The method transforms the matrix until good assignments appear as zeros.

Why this is valid:

- If you subtract a constant from an entire row, every complete assignment loses the same amount.
- If you subtract a constant from an entire column, every complete assignment also loses the same amount.
- So the best assignment does not change; only the matrix representation changes.

That is why row and column reductions are mathematically safe.

## Standard solving steps and why each exists

1. **Row reduction**: subtract the smallest value in each row.
   - Why: guarantees at least one zero per row.
2. **Column reduction**: subtract the smallest value in each column.
   - Why: increases zero structure without changing optimality.
3. **Cover all zeros with minimum number of lines**.
   - Why: tells us whether current zero structure is rich enough for a full assignment.
4. **If number of lines is less than `n`**, create additional zeros:
   - subtract smallest uncovered value from all uncovered entries;
   - add it to entries covered twice.
   - Why: creates new zeros while preserving assignment equivalence.
5. **Extract a feasible independent-zero assignment**.
   - If feasible, you have an optimal assignment for the transformed matrix and thus for the original cost matrix.

## Tradeoffs

- Strong for square assignment matrices and exact for that problem.
- Requires careful bookkeeping of zeros and covers.
- In this repo, high values are used as forbidden-edge sentinels (`100000`, `10000`) instead of true infinity.

## How this maps to the code

- Row reduction: `Hungarian Algorithm/src/Implementations/RowTool.java`
- Column reduction: `Hungarian Algorithm/src/Implementations/ColTool.java`
- Minimum line cover: `Hungarian Algorithm/src/Implementations/LineTool.java`
- Additional zero creation: `Hungarian Algorithm/src/Implementations/AddZeroTool.java`
- Feasibility and route extraction: `Hungarian Algorithm/src/Implementations/SolutionTool.java`
- Orchestration: `Hungarian Algorithm/src/Control/ImpHungarian.java`

## Beginner checklist before coding changes

- Keep matrices square.
- Keep call order: `runHungarianMethod()` before `solveProblemTsp(...)`.
- Do not casually change sentinel constants.
