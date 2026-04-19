package Control;

import java.awt.Point;
import java.util.List;

public class HungarianLatexReporter {

	public void printInitialMatrix(int[][] matrix) {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		printMatrix(matrix);
		System.out.println("\n\\end{tabular}" + "\n\n\\end{table}\n");
	}

	public void printAfterRowReduction(int[][] matrix) {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		printMatrix(matrix);
		System.out.println("\n\\end{tabular}" + "\n\\\\Subtract row minimum\n\\end{table}\n");
	}

	public void printAfterColumnReductionAndLineMap(int[][] matrix, int[][] lineMap, int lineCount) {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		printMatrix(matrix);
		System.out.println("\n\\end{tabular}" + "\n\\\\Subtract column minimum\n" + "\\end{minipage}");

		System.out.println("\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		printLineMap(lineMap);
		System.out.println("\n\\end{tabular}" + "\n\\\\Line map: " + lineCount + "\n\\end{minipage}\n" + "\n\\end{table}\n");
	}

	public void printAfterAdditionalZeroStep(int[][] matrix, int[][] lineMap, int lineCount) {
		System.out.println("\\begin{table}[H]\n\\centering\n\n" + "\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		printMatrix(matrix);
		System.out.println("\n\\end{tabular}"
				+ "\n\\\\Subtract this number from all uncovered elements and add it to all elements that are covered twice. \n"
				+ "\\end{minipage}");

		System.out.println("\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n" + "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		printLineMap(lineMap);
		System.out.println("\n\\end{tabular}" + "\n\\\\Line map: " + lineCount + "\n\\end{minipage}\n" + "\n\\end{table}\n");
	}

	public void printFeasibleSolution(List<Point> path, int objectiveCost) {
		System.out.println("The solution above is feasible!     Route found: " + formatPath(path) + "   Z*= " + objectiveCost);
	}

	public void printInfeasibleSolution(Point pointToPenalize) {
		System.out.println("The solution above is NOT feasible! Penalizing $C_{" + (int) pointToPenalize.getX() + " , "
				+ (int) pointToPenalize.getY() + "}$");
	}

	public void printBestRoute(int bestZ, List<Point> bestPath) {
		System.out.println("\\\\\\\\textbf{Best Route, with Z*=" + bestZ);
		System.out.println("\\\\Route found: " + formatPath(bestPath) + "}");
	}

	private void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				System.out.print(matrix[row][col]);
				if (col < matrix.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}
	}

	private void printLineMap(int[][] lineMap) {
		for (int row = 0; row < lineMap.length; row++) {
			for (int col = 0; col < lineMap.length; col++) {
				if (lineMap[row][col] == 1) {
					System.out.print("\\textbf{" + lineMap[row][col] + "}");
				} else {
					System.out.print(lineMap[row][col]);
				}

				if (col < lineMap.length - 1) {
					System.out.print(" & ");
				}
			}
			System.out.println("\\\\ \\hline");
		}
	}

	private String formatPath(List<Point> points) {
		String route = "";

		for (Point point : points) {
			route += "\\\\" + (int) point.getX() + " $\\rightarrow$ " + (int) point.getY() + " ;";
		}

		return route;
	}
}
