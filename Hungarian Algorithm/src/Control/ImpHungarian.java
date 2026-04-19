package Control;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Implementations.AddZeroTool;
import Implementations.ColTool;
import Implementations.LineTool;
import Implementations.ProblemTSPTool;
import Implementations.RowTool;
import Implementations.SolutionTool;
import Interfaces.Hungarian;

public class ImpHungarian implements Hungarian {
	private List<Point> path;
	private Point pointToPenalize;
	private final int[][] lineMap;
	private final int[][] workingMatrix;
	private final int[][] originalMatrix;
	public static final int INFINITY = 100000;
	private boolean worked = false;

	public ImpHungarian(int[][] matrix) {
		this.workingMatrix = matrix;
		lineMap = new int[matrix.length][matrix.length];
		originalMatrix = new int[matrix.length][matrix.length];

		ProblemTSPTool.arrayCopy(matrix, originalMatrix);
	}

	@Override
	public void runHungarianMethod() {
		printInitialMatrix();

		RowTool.SubtractRowMin(workingMatrix);
		printAfterRowReduction();
		ColTool.SubtractColumnMin(workingMatrix);

		int zeroLines = LineTool.FindMinimumNumberLines(workingMatrix, lineMap);
		printAfterColumnReductionAndLineMap(zeroLines);

		while (zeroLines < workingMatrix.length) {
			AddZeroTool.CreateAdditionalZeros(workingMatrix, lineMap);
			zeroLines = LineTool.FindMinimumNumberLines(workingMatrix, lineMap);
			printAfterAdditionalZeroStep(zeroLines);
		}

		SolutionTool solutionTool = new SolutionTool(workingMatrix);

		if (solutionTool.isFeasible()) {
			path = solutionTool.getSolution();
			System.out.println("The solution above is feasible!     Route found: " + formatPath(path) + "   Z*= " + getObjectiveCost());
			worked = true;
		} else {
			pointToPenalize = solutionTool.getLastRejectedPoint();
			System.out.println("The solution above is NOT feasible! Penalizing $C_{" + (int) pointToPenalize.getX() + " , "
					+ (int) pointToPenalize.getY() + "}$");
			worked = false;
		}
	}

	public int[][] getFirstPenaltyMatrix() {
		return ProblemTSPTool.FirstPass(pointToPenalize, originalMatrix);
	}

	public int[][] getSecondPenaltyMatrix() {
		return ProblemTSPTool.SecondPass(pointToPenalize, originalMatrix);
	}

	@Override
	public void solveProblemTsp(int iterations) {
		Integer bestZ = null;
		int z = 0;
		List<Point> bestPath = null;
		int[][] temp;
		int[][] temp2;
		int[][] matrix;
		ImpHungarian solver;
		Queue<int[][]> queue = new LinkedList<int[][]>();

		if (itWorked()) {
			bestZ = getObjectiveCost();
			bestPath = getPath();
			return;
		}

		temp = getFirstPenaltyMatrix();
		temp2 = getSecondPenaltyMatrix();
		queue.add(temp);
		queue.add(temp2);

		for (int i = 0; i < iterations && !queue.isEmpty(); i++) {
			if (!queue.isEmpty()) {
				matrix = queue.poll();

				solver = new ImpHungarian(matrix);
				solver.runHungarianMethod();

				if (solver.itWorked()) {
					z = solver.getObjectiveCost();

					if (bestZ == null || z < bestZ) {
						bestZ = z;
						bestPath = solver.getPath();
					}

				} else {
					temp = solver.getFirstPenaltyMatrix();
					temp2 = solver.getSecondPenaltyMatrix();
					queue.add(temp);
					queue.add(temp2);
				}
			}
		}

		if (bestZ != null) {
			System.out.println("\\\\\\\\textbf{Best Route, with Z*=" + bestZ);
			System.out.println("\\\\Route found: " + formatPath(bestPath) + "}");
		}
	}

	@Override
	public boolean itWorked() {
		return worked;
	}

	public int getObjectiveCost() {
		int z = 0;

		for (Point point : path) {
			z += originalMatrix[(int) point.getX()][(int) point.getY()];
		}

		return z;
	}

	public List<Point> getPath() {
		return path;
	}

	public String formatPath(List<Point> points) {
		String route = "";

		for (Point point : points) {
			route += "\\\\" + (int) point.getX() + " $\\rightarrow$ " + (int) point.getY() + " ;";
		}

		return route;
	}

	private void printInitialMatrix() {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");

		for (int row = 0; row < workingMatrix.length; row++) {
			for (int col = 0; col < workingMatrix.length; col++) {
				System.out.print(workingMatrix[row][col]);
				if (col < workingMatrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}

		System.out.println("\n\\end{tabular}" + "\n\n\\end{table}\n");
	}

	private void printAfterRowReduction() {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");

		for (int row = 0; row < workingMatrix.length; row++) {
			for (int col = 0; col < workingMatrix.length; col++) {
				System.out.print(workingMatrix[row][col]);
				if (col < workingMatrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}

		System.out.println("\n\\end{tabular}" + "\n\\\\Subtract row minimum\n\\end{table}\n");
	}

	private void printAfterColumnReductionAndLineMap(int lineCount) {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");

		for (int row = 0; row < workingMatrix.length; row++) {
			for (int col = 0; col < workingMatrix.length; col++) {
				System.out.print(workingMatrix[row][col]);
				if (col < workingMatrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}

		System.out.println("\n\\end{tabular}" + "\n\\\\Subtract column minimum\n" + "\\end{minipage}");

		System.out.println("\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");

		for (int row = 0; row < workingMatrix.length; row++) {
			for (int col = 0; col < workingMatrix.length; col++) {
				if (lineMap[row][col] == 1) {
					System.out.print("\\textbf{" + lineMap[row][col] + "}");
				} else {
					System.out.print(lineMap[row][col]);
				}

				if (col < workingMatrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}

		System.out.println("\n\\end{tabular}" + "\n\\\\Line map: " + lineCount + "\n\\end{minipage}\n" + "\n\\end{table}\n");
	}

	private void printAfterAdditionalZeroStep(int lineCount) {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");

		for (int row = 0; row < workingMatrix.length; row++) {
			for (int col = 0; col < workingMatrix.length; col++) {
				System.out.print(workingMatrix[row][col]);
				if (col < workingMatrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}

		System.out.println("\n\\end{tabular}"
				+ "\n\\\\Subtract this number from all uncovered elements and add it to all elements that are covered twice. \n"
				+ "\\end{minipage}");

		System.out.println("\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");

		for (int row = 0; row < workingMatrix.length; row++) {
			for (int col = 0; col < workingMatrix.length; col++) {
				if (lineMap[row][col] == 1) {
					System.out.print("\\textbf{" + lineMap[row][col] + "}");
				} else {
					System.out.print(lineMap[row][col]);
				}

				if (col < workingMatrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}

		System.out.println("\n\\end{tabular}" + "\n\\\\Line map: " + lineCount + "\n\\end{minipage}\n" + "\n\\end{table}\n");
	}
}
