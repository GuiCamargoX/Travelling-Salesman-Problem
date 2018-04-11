package Control;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	public static final int INFINITY = 100000;
	boolean work=false;
	
	public ImpHungarian(int[][] m) {
		this.m = m;                                  // variavel m vai ser utilizada para manipulacao
		mapLines = new int[m.length][m.length];
		orig =new int[m.length][m.length];           //backup matrix

		ProblemTSPTool.arrayCopy(m, orig);           //backup matrix orig = m

	}
	

	
	@Override
	public void useMethod() {
		Imprime();
		
		RowTool.SubtractRowMin(m);
		Imprime1();
		ColTool.SubtractColumnMin(m);
		
		int zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		Imprime2(zeros);
		
		while(zeros < m.length ){
		AddZeroTool.CreateAdditionalZeros(m, mapLines);
		zeros = LineTool.FindMinimumNumberLines(m , mapLines);
		Imprime3(zeros);
		}
		
		SolutionTool ans = new SolutionTool(m);
		
		
		if( ans.isViavel() ){
			 path= ans.getSolution();
			 System.out.println("A solução acima é viável!     Caminho encontrado: "+ ImprimePath(path)+ "   Z*= "+ getZ() );
			 work= true;
		}else{
			ToPenalth = ans.getLast();
			System.out.println("A solução acima NÃO é viável! Penalizando $C_{"+ (int)ToPenalth.getX()+" , "+ (int)ToPenalth.getY() +"}$");
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
	public void SolveProblemTSP(int it) { //it representa o numero de iteracoes(tabelas) a serem resolvidas, normalmente dado em potencia 2
		Integer bestZ = null ;
		int z = 0;
		List<Point> bestPath = null;
		int temp[][], temp2[][], matriz[][];
		ImpHungarian h1;
		Queue<int [][]> fila = new LinkedList<int [][]>();
		
		if( ItWorked() ){
			bestZ= getZ();
			bestPath= getPath();
			return;
		}
		
		temp = getMatrix1();
		temp2 = getMatrix2();
		fila.add(temp);
		fila.add(temp2);
		
		for(int i=0; i< it && !fila.isEmpty() ; i++ ){
			
			if( !fila.isEmpty() ){
				matriz = fila.poll();
				
	        	h1 = new ImpHungarian(matriz);
	        	h1.useMethod();
	        	
	        	if( h1.ItWorked() ){
	        		z = h1.getZ();
	        		
        			if( bestZ == null || z < bestZ ){
        				bestZ=z;
        				bestPath = h1.getPath();
        			}
	        		
	        	}else{
	        		temp = h1.getMatrix1();
	        		temp2 = h1.getMatrix2();
	        		fila.add(temp);
	        		fila.add(temp2);	        		
	        	}
						
			}
            
    		
        }
		
		if(bestZ!=null){
			System.out.println("\\\\\\\\\\textbf{Melhor Percurso, com Z*="+bestZ);
			System.out.println("\\\\Caminho encontrado: "+ ImprimePath(bestPath)+ "}");
		}

		
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
	
	public List<Point> getPath(){
		return path;
	}
	
	
	public String ImprimePath(List<Point> pat){
		String caminho="";
		
		for(Point a: pat){
			caminho+= "\\\\"+(int)a.getX()+" $\\rightarrow$ "+ (int)a.getY() + " ;";
		}
		return caminho;
		
	}
	
	
	
	//IMPRESOS
	public void Imprime(){
		System.out.println("\\begin{table}[H]\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(m[row][col]);
                if(col<m.length-1)
                	System.out.print(" & ");
            }
            System.out.println("\\\\ \\hline");
        }
        System.out.println("\n\\end{tabular}"
        		+ "\n\n\\end{table}\n");
      
	}
	
	public void Imprime1(){
		System.out.println("\\begin{table}[H]\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(m[row][col]);
                if(col<m.length-1)
                	System.out.print(" & ");
            }
            System.out.println("\\\\ \\hline");
        }
        System.out.println("\n\\end{tabular}"
        		+ "\n\\\\Subtrai Menor de cada Linha\n\\end{table}\n");
      
	}
	
	public void Imprime2(int num){
		
		System.out.println("\\begin{table}[H]\n\\centering\n\n"
				+ "\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(m[row][col]);
                if(col<m.length-1)
                	System.out.print(" & ");
            }
            System.out.println("\\\\ \\hline");
        }
        System.out.println("\n\\end{tabular}"
        		+ "\n\\\\Subtrai Menor de cada Coluna\n"
        		+ "\\end{minipage}");
        
		
        System.out.println("\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                if(mapLines[row][col] == 1 )
                	System.out.print("\\textbf{"+mapLines[row][col]+"}");
                else
                	System.out.print(mapLines[row][col]);
                
                if(col<m.length-1)
                	System.out.print(" & ");
            }
            System.out.println("\\\\ \\hline");
        }
        System.out.println("\n\\end{tabular}"
        		+ "\n\\\\Mapa de Linhas: "
        		+ num 
        		+ "\n\\end{minipage}\n"
        		+ "\n\\end{table}\n");
		
	}

	public void Imprime3(int num){
		
		System.out.println("\\begin{table}[H]\n\\centering\n\n"
				+ "\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                System.out.print(m[row][col]);
                if(col<m.length-1)
                	System.out.print(" & ");
            }
            System.out.println("\\\\ \\hline");
        }
        System.out.println("\n\\end{tabular}"
        		+ "\n\\\\Subtract this number from all uncovered elements and add it to all elements that are covered twice. \n"
        		+ "\\end{minipage}");
        
		
        System.out.println("\\begin{minipage}{0.4\\textwidth}\n\\centering\n\n"
				+ "\\begin{tabular}{|c|c|c|c|c|}\n\n\\hline");
		
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m.length; col++) {
                if(mapLines[row][col] == 1 )
                	System.out.print("\\textbf{"+mapLines[row][col]+"}");
                else
                	System.out.print(mapLines[row][col]);
                
                if(col<m.length-1)
                	System.out.print(" & ");
            }
            System.out.println("\\\\ \\hline");
        }
        System.out.println("\n\\end{tabular}"
        		+ "\n\\\\Mapa de Linhas: "
        		+ num 
        		+ "\n\\end{minipage}\n"
        		+ "\n\\end{table}\n");	
	}
	
}
