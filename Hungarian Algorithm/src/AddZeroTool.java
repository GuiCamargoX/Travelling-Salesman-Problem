import Interfaces.Step4;

public class AddZeroTool implements Step4{

	public static final int INFINITY  = 10000;
	
	@Override
	public void CreateAdditionalZeros(int[][] m, int[][] mapLines) {
	
		int small = FindSmallNumber(m, mapLines);
	
	}

	
	private int FindSmallNumber(int[][] m, int[][] mapLines) {
		int small=INFINITY;
		
	    for (int row = 0; row < m.length; row++) {
	        for (int col = 0; col < m.length; col++) {
	            if (mapLines[row][col] == 0 &&  m[row][col] < small )
	            	small= m[row][col];
	                
	        }
	    }
	    
		return small;
	}

	
	public void SubtractOrAdd(int[][] m, int[][] mapLines, int small) {
	
	//SUBTRACT
		for (int row = 0; row < m.length; row++) {
	        for (int col = 0; col < m.length; col++) {
	            if ( mapLines[row][col] == 0 )
	            	m[row][col]= m[row][col] - small;	                
	        }
	    }
		
	//ADD
		//SEE INSIDE - CROSS
		for (int row = 1; row < m.length-1; row++) {
	        for (int col = 1; col < m.length-1; col++) {
	            if ( mapLines[row][col] == 1 && mapLines[row-1][col]==1 && mapLines[row+1][col]==1 && mapLines[row][col-1]==1 && mapLines[row][col+1]==1 )
	            	m[row][col]= m[row][col] + small;         
	        }
	    }
		
		//SEE borders left - rigth
		int last=m.length-1;
		for (int row = 1; row < m.length-1; row++) {
	            if ( mapLines[row][0] == 1 && mapLines[row-1][0]==1 && mapLines[row+1][0]==1 && mapLines[row][1]==1 )
	            	m[row][0]= m[row][0] + small;
	            
	            if ( mapLines[row][last] == 1 && mapLines[row-1][last]==1 && mapLines[row+1][last]==1 && mapLines[row][last-1]==1 )
	            	m[row][last]= m[row][last] + small;
	    }		

		//SEE borders up - down
	    for (int col = 1; col < m.length-1; col++) {
	            if ( mapLines[0][col] == 1 && mapLines[0][col-1]==1 && mapLines[0][col+1]==1 && mapLines[1][col]==1 )
	            	m[0][col]= m[0][col] + small;
	            
	            if ( mapLines[last][col] == 1 && mapLines[last][col-1]==1 && mapLines[last][col+1]==1 && mapLines[last-1][col]==1 )
	            	m[last][col]= m[last][col] + small;
	            
	    }
	    
	    //SEE 4 vertex
	    
	    if(mapLines[0][0]==1 && mapLines[0][1]==1 && mapLines[1][0]==1)
	    	mapLines[0][0]= mapLines[0][0] + small;
	    
	    if(mapLines[last][0]==1 && mapLines[last][1]==1 && mapLines[last-1][0]==1)
	    	mapLines[last][0]= mapLines[last][0] + small;
	    
	    if(mapLines[0][last]==1 && mapLines[0][last-1]==1 && mapLines[1][last]==1)
	    	mapLines[0][last]= mapLines[0][last] + small;	    
	
	    if(mapLines[last][last]==1 && mapLines[last][last-1]==1 && mapLines[last-1][last]==1)
	    	mapLines[last][last]= mapLines[last][last] + small;
	
	}
	
	

	@Override
	public void FindOptimalSolution() {
		// TODO Auto-generated method stub
		teste
	}

}
