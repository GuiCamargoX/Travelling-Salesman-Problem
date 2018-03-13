package Control;
import java.awt.Point;
import java.util.List;

import Implementations.AddZeroTool;
import Implementations.ColTool;
import Implementations.LineTool;
import Implementations.ProblemTSPTool;
import Implementations.RowTool;
import Implementations.SolutionTool;
import Interfaces.Hungarian;

public class ImpHungarian implements Hungarian{
	List<Point> path;
	Point ToPenalth;
	int [][]mapLines;
	int [][]m;
	int [][] orig;
	public static final int INFINITY = 10000;
	boolean work=false;
	
	public ImpHungarian(int[][] m) {
		this.m = m;                                  // variavel m vai ser utilizada para manipulacao
		mapLines = new int[m.length][m.length];
		orig =new int[m.length][m.length];           //backup matrix

		ProblemTSPTool.arrayCopy(m, orig);           //backup matrix orig = m

	}
	

	
	@Override
	public void useMethod() {
		RowTool.SubtractRowMin(m);
		//Imprime();
		ColTool.SubtractColumnMin(m);
		//Imprime();
		
		int zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		//ImprimeMap();
		
		while(zeros < m.length ){
		AddZeroTool.CreateAdditionalZeros(m, mapLines);
		//Imprime();
		zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		}
		
		SolutionTool ans = new SolutionTool(m);
		
		
		if( ans.isViavel() ){
			 path= ans.getSolution();
			 //System.out.println(path);
			 work= true;
		}else{
			ToPenalth = ans.getLast();
			//System.out.println(ToPenalth);
			work= false;
		}
		
		
	}
	
	public int[][] getMatrix1(){
		return ProblemTSPTool.FirstPass(ToPenalth, orig);
	}
	
	public int[][] getMatrix2(){
		return ProblemTSPTool.SecondPass(ToPenalth, orig);
	}
	
	@Override
	public void SolveProblemTSP(int it) { //it representa o numero de iteracoes total caso nao encontre uma respota viavel otima 
		Integer bestZ = null ;
		int temp[][], temp2[][];
		ImpHungarian h1,h2;
		
		if( ItWorked() ){
			bestZ= getZ();
			return;
		}
		
		temp = getMatrix1();
		temp2 = getMatrix2();
		
		for(int i=0; i< it && bestZ==null ; i++ ){
            
        	h1 = new ImpHungarian(temp);
        	h1.useMethod();
        	if( h1.ItWorked() ){
        		bestZ= h1.getZ();
        	}else{
        		temp = h1.getMatrix1();
        	}
        	
        	
        	h2 = new ImpHungarian(temp2);
        	h2.useMethod();
        	Imprime();
        	if( h2.ItWorked() ){
        		int z = h2.getZ();
        		
        			if( bestZ == null || z < bestZ )
        				bestZ=z;
			}else{
        		temp2 = h2.getMatrix2();
        	}
        	
    		
        }
		
			
		System.out.println("\n"+bestZ);
		
	}

	

	@Override
	public boolean ItWorked() {
		return work;
	}
	
	public int getZ(){
		int z=0;

		for(Point a: path){
			z+= orig[(int) a.getX()][ (int) a.getY()];
		}
		
		return z;
	}
	
	
	
	//IMPRESOS
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
	
	 
}
