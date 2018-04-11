package Implementations;
import Interfaces.Step4;

public class AddZeroTool implements Step4{

	public static final int INFINITY  = 100000;
	
	
	public static void CreateAdditionalZeros(int[][] m, int[][] mapLines) {
	
		int small = FindSmallNumber(m, mapLines);
		SubtractOrAdd(m, mapLines, small);
		
	}

	
	private static int FindSmallNumber(int[][] m, int[][] mapLines) {
		int small=INFINITY;
		
	    for (int row = 0; row < m.length; row++) {
	        for (int col = 0; col < m.length; col++) {
	            if (mapLines[row][col] == 0 &&  m[row][col] < small )
	            	small= m[row][col];
	                
	        }
	    }
	    
		return small;
	}

	
	private static void SubtractOrAdd(int[][] m, int[][] mapLines, int small) {
	
		for (int row = 0; row < m.length; row++) {
	        for (int col = 0; col < m.length; col++) {
	            if ( mapLines[row][col] == 0 )
	            	m[row][col]= m[row][col] - small;	                
	            else{
	            	if( IsCross(mapLines,row,col) )
	            		m[row][col]= m[row][col] + small;	
	            }
	            
	        }
	    }
		
	
	}
	
	private static boolean IsCross(int[][]mapLines, int row, int col){		
		
		for (int i = 0; i < row; i++) {
	        if(mapLines[i][col] == 0)
	        	return false;
		}		
		

		for (int i = row+1 ; i < mapLines.length; i++) {
			if(mapLines[i][col] == 0)
				return false;
		}

		for (int j = 0 ; j < col; j++) {
			if(mapLines[row][j] == 0)
				return false;
		}
		
		
		for (int j = col+1 ; j < mapLines.length; j++) {
			if(mapLines[row][j] == 0)
				return false;
		}
		
		return true;
		
	}
	

}
