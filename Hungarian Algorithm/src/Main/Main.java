package Main;

import Control.ImpHungarian;

public class Main {

	public static void main(String[] args) {
		int[][] matrix = ExampleMatrices.fiveCityDemoMatrix();

		ImpHungarian solver = new ImpHungarian(matrix);
		solver.runHungarianMethod();
		solver.solveProblemTsp(6); // second and third level => 2^1 + 2^2
	}
}
