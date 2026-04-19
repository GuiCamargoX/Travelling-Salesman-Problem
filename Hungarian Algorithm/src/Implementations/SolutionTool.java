package Implementations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Interfaces.Step5;

public class SolutionTool implements Step5 {

	private final List<List<Integer>> zeroRowsByColumn = new ArrayList<List<Integer>>();
	private final int[][] markMap;
	private final int[][] matrix;
	private final Point lastRejectedPoint = new Point();

	public SolutionTool(int[][] matrix) {
		markMap = new int[matrix.length][matrix.length];
		this.matrix = matrix;

		findPossibleSolutions();
		initializeMarkMap();
	}

	private void findPossibleSolutions() {
		for (int col = 0; col < matrix.length; col++) {
			List<Integer> rows = new ArrayList<Integer>();

			for (int row = 0; row < matrix.length; row++) {
				if (matrix[row][col] == 0) {
					rows.add(row);
				}
			}

			zeroRowsByColumn.add(rows);
		}
	}

	private void fillCross(int row, int col) {
		for (int i = 0; i < row; i++) {
			markMap[i][col] = -1;
		}

		for (int i = row + 1; i < matrix.length; i++) {
			markMap[i][col] = -1;
		}

		for (int j = 0; j < col; j++) {
			markMap[row][j] = -1;
		}

		for (int j = col + 1; j < matrix.length; j++) {
			markMap[row][j] = -1;
		}
	}

	private void initializeMarkMap() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i == j) {
					markMap[i][j] = -1;
				} else {
					markMap[i][j] = 0;
				}
			}
		}
	}

	@Override
	public boolean isFeasible() {
		for (int candidateCount = 1; candidateCount <= matrix.length; candidateCount++) {
			int columnIndex = 0;

			for (Iterator<List<Integer>> columnIterator = zeroRowsByColumn.iterator(); columnIterator.hasNext(); columnIndex++) {
				List<Integer> rowsInColumn = columnIterator.next();

				if (rowsInColumn.size() == candidateCount) {
					for (Iterator<Integer> rowIterator = rowsInColumn.iterator(); rowIterator.hasNext();) {
						int row = rowIterator.next();
						int col = columnIndex;

						if (markMap[row][col] == 0) {
							markMap[row][col] = 1;
							markMap[col][row] = -1;
							fillCross(row, col);
						} else {
							lastRejectedPoint.setLocation(row, col);
							rowIterator.remove();

							if (rowsInColumn.isEmpty()) {
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	public List<Point> getSolution() {
		List<Point> solution = new ArrayList<Point>();
		Point candidate = null;

		for (List<Integer> rowsInColumn : zeroRowsByColumn) {
			for (Integer row : rowsInColumn) {
				candidate = new Point(row, zeroRowsByColumn.indexOf(rowsInColumn));
			}
			solution.add(candidate);
		}

		return solution;
	}

	public Point getLastRejectedPoint() {
		return lastRejectedPoint;
	}

}
