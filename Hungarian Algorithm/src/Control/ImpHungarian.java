package Control;
import Implementations.AddZeroTool;
import Implementations.ColTool;
import Implementations.LineTool;
import Implementations.RowTool;
import Interfaces.Hungarian;

public class ImpHungarian implements Hungarian{
	int [][]mapLines;
	int [][]m;
	
	public ImpHungarian(int[][] m) {
		this.m = m;
		mapLines = new int[m.length][m.length];
	}

	
	@Override
	public void useMethod() {
		RowTool.SubtractRowMin(m);
		ColTool.SubtractColumnMin(m);
		int zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		
		while(zeros < m.length ){
		AddZeroTool.CreateAdditionalZeros(m, mapLines);
		zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		}
		
		System.out.println(zeros);
		Imprime();
		
		
	}
	
	public void Imprime(){
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(mapLines[row][col] + "\t");
            }
            System.out.println();
        }
	}

	@Override
	public void SolveProblemTSP() {
		// TODO Auto-generated method stub
		
	}
	
	 
}
