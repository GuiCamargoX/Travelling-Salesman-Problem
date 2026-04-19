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
	private final HungarianLatexReporter reporter;
	public static final int INFINITY = 100000;
	private boolean worked = false;

	public ImpHungarian(int[][] matrix) {
		this.workingMatrix = matrix;
		lineMap = new int[matrix.length][matrix.length];
		originalMatrix = new int[matrix.length][matrix.length];
		reporter = new HungarianLatexReporter();

		ProblemTSPTool.arrayCopy(matrix, originalMatrix);
	}

	@Override
	public void runHungarianMethod() {
		reporter.printInitialMatrix(workingMatrix);

		RowTool.subtractRowMin(workingMatrix);
		reporter.printAfterRowReduction(workingMatrix);
		ColTool.subtractColumnMin(workingMatrix);

		int zeroLines = LineTool.findMinimumNumberOfLines(workingMatrix, lineMap);
		reporter.printAfterColumnReductionAndLineMap(workingMatrix, lineMap, zeroLines);

		while (zeroLines < workingMatrix.length) {
			AddZeroTool.createAdditionalZeros(workingMatrix, lineMap);
			zeroLines = LineTool.findMinimumNumberOfLines(workingMatrix, lineMap);
			reporter.printAfterAdditionalZeroStep(workingMatrix, lineMap, zeroLines);
		}

		SolutionTool solutionTool = new SolutionTool(workingMatrix);

		if (solutionTool.isFeasible()) {
			path = solutionTool.getSolution();
			reporter.printFeasibleSolution(path, getObjectiveCost());
			worked = true;
		} else {
			pointToPenalize = solutionTool.getLastRejectedPoint();
			reporter.printInfeasibleSolution(pointToPenalize);
			worked = false;
		}
	}

	public int[][] getFirstPenaltyMatrix() {
		return ProblemTSPTool.firstPass(pointToPenalize, originalMatrix);
	}

	public int[][] getSecondPenaltyMatrix() {
		return ProblemTSPTool.secondPass(pointToPenalize, originalMatrix);
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

		if (bestZ != null) {
			reporter.printBestRoute(bestZ, bestPath);
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
}
