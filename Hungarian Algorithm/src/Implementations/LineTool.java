package Implementations;

import Interfaces.Step3;

public class LineTool implements Step3 {
	private static int count;
	private static int[][] coverageScoreMap;
	private static int[][] lineMap;

	public static int findMinimumNumberOfLines(int[][] matrix, int[][] mapLines) {
		count = 0;
		coverageScoreMap = new int[matrix.length][matrix.length];
		lineMap = mapLines;
		clearLineMap();

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (matrix[row][col] == 0) {
					coverageScoreMap[row][col] = getMaxZeroDirectionScore(matrix, row, col);
				}
			}
		}

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (Math.abs(coverageScoreMap[row][col]) > 0) {
					clearNeighbors(coverageScoreMap, lineMap, row, col);
				}
			}
		}

		return count;
	}

	private static int getMaxZeroDirectionScore(int[][] matrix, int row, int col) {
		int vertical = 0;
		int horizontal = 0;

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] == 0) {
				horizontal++;
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][col] == 0) {
				vertical++;
			}
		}

		return vertical > horizontal ? vertical : horizontal * -1;
	}

	private static void clearNeighbors(int[][] coverageScoreMap, int[][] lineMap, int row, int col) {
		if (coverageScoreMap[row][col] != 0) {
			count++;
		}

		if (coverageScoreMap[row][col] > 0) {
			for (int i = 0; i < coverageScoreMap.length; i++) {
				if (coverageScoreMap[i][col] > 0) {
					coverageScoreMap[i][col] = 0;
				}
				lineMap[i][col] = 1;
			}
		} else {
			for (int i = 0; i < coverageScoreMap.length; i++) {
				if (coverageScoreMap[row][i] < 0) {
					coverageScoreMap[row][i] = 0;
				}
				lineMap[row][i] = 1;
			}
		}
	}

	private static void clearLineMap() {
		for (int row = 0; row < lineMap.length; row++) {
			for (int col = 0; col < lineMap.length; col++) {
				lineMap[row][col] = 0;
			}
		}
	}
}
