package Main;

import Control.ImpHungarian;

public class Main {
	
	public static void main(String[] args) {
	        /*int[][] m1 = { 	{ 82, 83, 69, 92},
	        			 	{ 77, 37, 49, 92}, 
	        			 	{ 11, 69, 5, 86},
	        			 	{ 8, 9, 98, 23}, };
	        	*/		 	
	        
		int[][] m1 = { 	{ 1000, 65, 53, 37},
							{ 65, 1000, 95, 1000}, 
							{ 53, 95, 1000, 81},
							{ 37, 1000, 81, 1000}, };
	        
		
			Integer bestZ = null ;
		
	        ImpHungarian h= new ImpHungarian(m1);
	        h.useMethod();
	        
	        for(int i=0; i<1 && bestZ==null ; i++ ){
	        	h.FirstPass();
	            m1 = h.getMatrix();
	            
	        	h = new ImpHungarian(m1);
	        	h.useMethod();
	        	
	        	if( h.ItWorked() ){
	        		bestZ= h.getZ();
	        	}
	        	
	        	h.SecondPass();
	        	
	        	m1 = h.getMatrix2();
	        	h = new ImpHungarian(m1);
	        	h.useMethod();
	        	
	        	if( h.ItWorked() ){
	        		int z = h.getZ();
	        		if( bestZ == null || z < bestZ )
	        			bestZ=z;
	        	}
	        	System.out.println("\n"+bestZ);
	        }
	        
	 
	}
	
}
