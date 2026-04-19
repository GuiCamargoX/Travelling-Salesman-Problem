package Main;

public final class ExampleMatrices {

	private ExampleMatrices() {
	}

	public static int[][] fiveCityDemoMatrix() {
		return new int[][] {
				{ 1000, 185, 119, 152, 133 },
				{ 185, 1000, 121, 150, 200 },
				{ 119, 121, 1000, 174, 120 },
				{ 152, 150, 174, 1000, 199 },
				{ 133, 200, 120, 199, 1000 },
		};
	}

	public static int[][] fourCitySparseDemoMatrix() {
		return new int[][] {
				{ 1000, 65, 53, 37 },
				{ 65, 1000, 95, 1000 },
				{ 53, 95, 1000, 81 },
				{ 37, 1000, 81, 100 },
		};
	}

	public static int[][] sixCityDistanceDemoMatrix() {
		return new int[][] {
				{ 100000, 702, 454, 842, 2396, 1196 },
				{ 702, 100000, 324, 1093, 2136, 764 },
				{ 454, 324, 100000, 1137, 2180, 798 },
				{ 842, 1093, 1137, 100000, 1616, 1857 },
				{ 2396, 2136, 2180, 1616, 100000, 2900 },
				{ 1196, 764, 798, 1857, 2900, 100000 },
		};
	}
}
