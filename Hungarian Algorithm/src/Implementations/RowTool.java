package Implementations;
import Interfaces.Step1;

public class RowTool implements Step1{
	public static void SubtractRowMin(int m[][]) { //each row
		int min;
		
		for(int i=0; i<m.length; i++){
		min=m[i][0];
			
			for(int j=0; j<m.length; j++){
				if(m[i][j] < min)
					min= m[i][j];
			}
			
			for(int k=0; k<m.length; k++){
				m[i][k] -= min;
			}
		}
		
	}
	
}
