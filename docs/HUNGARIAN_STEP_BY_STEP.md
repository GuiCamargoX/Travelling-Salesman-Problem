# 🧮 Hungarian Method Step-by-Step Example

This example focuses on the assignment logic first, then connects it to implementation.

## Example matrix

We want the minimum-cost one-to-one assignment:

|     | C1 | C2 | C3 | C4 |
|-----|----|----|----|----|
| R1  | 9  | 2  | 7  | 8  |
| R2  | 6  | 4  | 3  | 7  |
| R3  | 5  | 8  | 1  | 8  |
| R4  | 7  | 6  | 9  | 4  |

## Step 1: Row reduction

Subtract row minima `[2, 3, 1, 4]`:

|     | C1 | C2 | C3 | C4 |
|-----|----|----|----|----|
| R1  | 7  | 0  | 5  | 6  |
| R2  | 3  | 1  | 0  | 4  |
| R3  | 4  | 7  | 0  | 7  |
| R4  | 3  | 2  | 5  | 0  |

Why: now every row has at least one zero candidate.

## Step 2: Column reduction

Column minima are `[3, 0, 0, 0]`. Subtract from each column:

|     | C1 | C2 | C3 | C4 |
|-----|----|----|----|----|
| R1  | 4  | 0  | 5  | 6  |
| R2  | 0  | 1  | 0  | 4  |
| R3  | 1  | 7  | 0  | 7  |
| R4  | 0  | 2  | 5  | 0  |

Why: this creates more zero structure while preserving optimal assignment.

## Step 3: Cover zeros with minimum lines

At this point we can select independent zeros for all rows and columns, so a full assignment is available.

One valid independent-zero selection is:

- `R1 -> C2`
- `R2 -> C1`
- `R3 -> C3`
- `R4 -> C4`

No additional-zero step is required in this case.

## Step 4: Read the assignment in the original matrix

Use the selected row-column pairs on the original costs:

- `R1 -> C2 = 2`
- `R2 -> C1 = 6`
- `R3 -> C3 = 1`
- `R4 -> C4 = 4`

Total cost: `2 + 6 + 1 + 4 = 13`.

## How this appears in this repository

- Step 1: `RowTool.subtractRowMin(...)`
- Step 2: `ColTool.subtractColumnMin(...)`
- Step 3: `LineTool.findMinimumNumberOfLines(...)`
- Step 4 (if needed): `AddZeroTool.createAdditionalZeros(...)`
- Feasibility extraction: `SolutionTool.isFeasible()`

## If your matrix is not solved after Step 3

If minimum line count is `< n`, apply the additional-zero rule and repeat from line-cover step until a feasible assignment emerges.
