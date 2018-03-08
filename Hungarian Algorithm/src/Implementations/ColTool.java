package Implementations;
import Interfaces.Step2;

public class ColTool implements Step2{
	
	public static void SubtractColumnMin(int [][]m) {
		int min;
		
		for(int j=0; j<m.length; j++){
		min = m[0][j];
			
			for(int i=0; i<m.length; i++){
				if(m[i][j] < min)
					min= m[i][j];
			}
			
			for(int k=0; k<m.length; k++){
				m[k][j] -= min;
			}
		}
		
	}
}
