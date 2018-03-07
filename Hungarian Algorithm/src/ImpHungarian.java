import Interfaces.Hungarian;

public class ImpHungarian implements Hungarian{
	int [][]mapLines;
	int [][]m;
	
	public void Hungarian(int [][]m ){		
		this.m = m;
		mapLines = new int[m.length][m.length];
		
	};
	
	@Override
	public void useMethod() {
		
		LineTool.FindMinimumNumberLines(m , mapLines);
		
	}

	@Override
	public void SolveProblemTSP() {
		// TODO Auto-generated method stub
		
	}
	
	 
}
