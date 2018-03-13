package Implementations;

import java.awt.Point;

import Control.ImpHungarian;
import Interfaces.FinalStep;

public class ProblemTSPTool implements FinalStep{
	
	public static final int INFINITY = 10000;
	
	public static int [][] FirstPass(Point ToPenalth, int orig[][]){
		int row= (int) ToPenalth.getX();
		int col= (int) ToPenalth.getY();
		int aux[][] = new int[orig.length][orig.length];
		arrayCopy(orig, aux);
		
		//Cross
		for (int i = 0; i < row; i++) {
	        aux[i][col]= INFINITY;
		}		
		

		for (int i = row+1 ; i < aux.length; i++) {
			aux[i][col]= INFINITY;
		}

		for (int j = 0 ; j < col; j++) {
			aux[row][j]= INFINITY;
		}
		
		
		for (int j = col+1 ; j < aux.length; j++) {
			aux[row][j]= INFINITY;
		}
		
		aux[col][row]= INFINITY; //Transposta
		
		return aux;
	}
	
	public static int [][] SecondPass(Point ToPenalth, int orig[][]){
			
			int row= (int) ToPenalth.getX();
			int col= (int) ToPenalth.getY();
			int aux[][] = new int[orig.length][orig.length];
			arrayCopy(orig, aux);
			
			aux[row][col]= INFINITY;
			
			return aux;
	}
	
	public static void arrayCopy(int[][] aSource, int[][] aDestination) {
	    for (int i = 0; i < aSource.length; i++) {
	        System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
	    }
	}
	
	
}
