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
		
	        ImpHungarian h= new ImpHungarian(m1);
	        h.useMethod();
	        
	 }
	
}
