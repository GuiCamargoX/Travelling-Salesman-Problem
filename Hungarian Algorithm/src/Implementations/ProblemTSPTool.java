package Implementations;

import java.awt.Point;

import Interfaces.FinalStep;

public class ProblemTSPTool implements FinalStep {

	public static final int INFINITY = 10000;

	public static int[][] firstPass(Point pointToPenalize, int[][] originalMatrix) {
		int row = (int) pointToPenalize.getX();
		int col = (int) pointToPenalize.getY();
		int[][] aux = new int[originalMatrix.length][originalMatrix.length];
		arrayCopy(originalMatrix, aux);

		for (int i = 0; i < row; i++) {
			aux[i][col] = INFINITY;
		}

		for (int i = row + 1; i < aux.length; i++) {
			aux[i][col] = INFINITY;
		}

		for (int j = 0; j < col; j++) {
			aux[row][j] = INFINITY;
		}

		for (int j = col + 1; j < aux.length; j++) {
			aux[row][j] = INFINITY;
		}

		aux[col][row] = INFINITY;

		return aux;
	}

	public static int[][] secondPass(Point pointToPenalize, int[][] originalMatrix) {
		int row = (int) pointToPenalize.getX();
		int col = (int) pointToPenalize.getY();
		int[][] aux = new int[originalMatrix.length][originalMatrix.length];
		arrayCopy(originalMatrix, aux);

		aux[row][col] = INFINITY;

		return aux;
	}

	public static void arrayCopy(int[][] source, int[][] destination) {
		for (int i = 0; i < source.length; i++) {
			System.arraycopy(source[i], 0, destination[i], 0, source[i].length);
		}
	}
}
