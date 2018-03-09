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
	List<Point> path;
	Point ToPenalth;
	int [][]mapLines;
	int [][]m;
	int [][] orig1, orig2;
	public static final int INFINITY = 10000;
	boolean work=false;
	
	public ImpHungarian(int[][] m) {
		this.m = m;
		mapLines = new int[m.length][m.length];
		orig1 =new int[m.length][m.length];
		orig2 =new int[m.length][m.length];
		
		ImpHungarian.arrayCopy(m, orig1);
		ImpHungarian.arrayCopy(m, orig2);
	}
	
	public static void arrayCopy(int[][] aSource, int[][] aDestination) {
	    for (int i = 0; i < aSource.length; i++) {
	        System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
	    }
	}

	
	@Override
	public void useMethod() {
		RowTool.SubtractRowMin(m);
		Imprime();
		ColTool.SubtractColumnMin(m);
		Imprime();
		
		int zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		ImprimeMap();
		
		while(zeros < m.length ){/*bug aqui*/
		AddZeroTool.CreateAdditionalZeros(m, mapLines);
		Imprime();
		zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		}
		
		SolutionTool ans = new SolutionTool(m);
		
		
		if( ans.isViavel() ){
			 path= ans.getSolution();
			 //System.out.println(path);
			 work= true;
		}else{
			ToPenalth = ans.getLast();
			System.out.println(ToPenalth);
			work= false;
		}
		
		
	}
	
	public void Imprime(){
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(m[row][col] + "\t");
            }
            System.out.println();
        }
        System.out.println();
	}

	public void ImprimeMap(){
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(mapLines[row][col] + "\t");
            }
            System.out.println();
        }
        System.out.println();
	}	
	
	@Override
	public void SolveProblemTSP() {
		// two steps
		
	}
	
	public void FirstPass(){
		int row= (int) ToPenalth.getX();
		int col= (int) ToPenalth.getY();
		
		//Cross
		for (int i = 0; i < row; i++) {
	        orig1[i][col]= INFINITY;
		}		
		

		for (int i = row+1 ; i < m.length; i++) {
			orig1[i][col]= INFINITY;
		}

		for (int j = 0 ; j < col; j++) {
			orig1[row][j]= INFINITY;
		}
		
		
		for (int j = col+1 ; j < m.length; j++) {
			orig1[row][j]= INFINITY;
		}
		
		orig1[col][row]= INFINITY; //Transposta
		
	}
	
	public void SecondPass(){
		int row= (int) ToPenalth.getX();
		int col= (int) ToPenalth.getY();
		
		orig2[row][col]= INFINITY;
	}
	
	public int [][] getMatrix2(){
		return orig2;
	}


	@Override
	public boolean ItWorked() {
		return work;
	}
	
	public int getZ(){
		int z=0;

		for(Point a: path){
			z+= orig1[(int) a.getX()][ (int) a.getY()];
		}
		
		return z;
	}
	
	public int [][] getMatrix(){
		return orig1;
	}
	
	 
}
