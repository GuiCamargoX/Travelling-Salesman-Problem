package Implementations;

import Interfaces.Step4;

public class AddZeroTool implements Step4 {

	public static final int INFINITY = 100000;

	public static void createAdditionalZeros(int[][] matrix, int[][] lineMap) {
		int smallestUncovered = findSmallestUncoveredValue(matrix, lineMap);
		subtractOrAdd(matrix, lineMap, smallestUncovered);
	}

	private static int findSmallestUncoveredValue(int[][] matrix, int[][] lineMap) {
		int small = INFINITY;

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (lineMap[row][col] == 0 && matrix[row][col] < small) {
					small = matrix[row][col];
				}
			}
		}

		return small;
	}

	private static void subtractOrAdd(int[][] matrix, int[][] lineMap, int small) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (lineMap[row][col] == 0) {
					matrix[row][col] = matrix[row][col] - small;
				} else if (isCross(lineMap, row, col)) {
					matrix[row][col] = matrix[row][col] + small;
				}
			}
		}
	}

	private static boolean isCross(int[][] lineMap, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (lineMap[i][col] == 0) {
				return false;
			}
		}

		for (int i = row + 1; i < lineMap.length; i++) {
			if (lineMap[i][col] == 0) {
				return false;
			}
		}

		for (int j = 0; j < col; j++) {
			if (lineMap[row][j] == 0) {
				return false;
			}
		}

		for (int j = col + 1; j < lineMap.length; j++) {
			if (lineMap[row][j] == 0) {
				return false;
			}
		}

		return true;
	}
}
