package Control;
import java.awt.Point;
import java.util.List;

import Implementations.AddZeroTool;
import Implementations.ColTool;
import Implementations.LineTool;
import Implementations.RowTool;
import Implementations.SolutionTool;
import Interfaces.Hungarian;

public class ImpHungarian implements Hungarian{
	int [][]mapLines;
	int [][]m;
	public static final int ININITY = 10000;
	
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
		
		SolutionTool ans = new SolutionTool(m);
		
		if( ans.isViavel() ){
			 List<Point> path= ans.getSolution();
			 
			 for(Point p: path){
				 System.out.println(p);
			 }
			 
		}else{
			Point last = ans.getLast();
			SolveProblemTSP(last);
		}
		
		Imprime();
		
		
	}
	
	public void Imprime(){
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(m[row][col] + "\t");
            }
            System.out.println();
        }
	}

	@Override
	public void SolveProblemTSP(Point Topenalth) {
		// TODO Auto-generated method stub
		
	}
	
	 
}
