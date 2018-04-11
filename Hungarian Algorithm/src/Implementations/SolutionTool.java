package Implementations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Interfaces.Step5;

public class SolutionTool implements Step5{
	
	List< List<Integer> >col = new ArrayList< List<Integer> >(); //criando vetor de listas,
	int MarkMap[][];
	int m[][];
	Point last = new Point();
	
	public SolutionTool(int m[][]){
		MarkMap= new int[m.length][m.length];
		this.m=m;
				
		FindPossibleSol();
		StartMapMark();
		
	}
	
	
	public void FindPossibleSol(){//Summering find zeros
		List<Integer> row;
		
	    for (int j = 0; j < m.length; j++) {
	    	row = new ArrayList<Integer>();
	    	
	        for (int i = 0; i < m.length; i++) {
	            if( m[i][j] == 0 ){
	            	row.add(i);
	            	
	            }
	        }
	        
	        col.add( row );
	    }
	    		
	}
	
	private void fillCross(int row, int col) {
	    
		for (int i = 0; i < row; i++) {
	        MarkMap[i][col]= -1;
		}		
		

		for (int i = row+1 ; i < m.length; i++) {
	        MarkMap[i][col]= -1;
		}

		for (int j = 0 ; j < col; j++) {
	        MarkMap[row][j]= -1;
		}
		
		
		for (int j = col+1 ; j < m.length; j++) {
	        MarkMap[row][j]= -1;
		}
	
	}
	
	public void StartMapMark(){
	    
		for (int i = 0; i < m.length; i++) {
	        for (int j = 0; j < m.length; j++) {
	            if(i==j){
	            	MarkMap[i][j]=-1;
	            }else
	            	MarkMap[i][j]=0;
	        }	        
	    }
	
	}

	@Override
	public boolean isViavel() {
		
		
	    for(int k=1; k<=m.length ; k++){ //utilizar Iterator 
	    	int IndexCol=0;
	    	
	    	for( Iterator< List<Integer> > Itcol = col.iterator() ; Itcol.hasNext(); IndexCol++){
	    		List<Integer> colun = Itcol.next();
	    		
	    		if(colun.size() == k){
	    			
	    			for( Iterator< Integer > ItRow = colun.iterator() ; ItRow.hasNext(); ){
	    				Integer linha = ItRow.next();
	    				
    					int i=linha;
    					int j= IndexCol;
    					
	    				if(MarkMap[i][j] == 0){
	    					//System.out.println(i+ " " +j);
	    					MarkMap[i][j] = 1;
	    					MarkMap[j][i] = -1;//transp
	    					fillCross(i,j);
	    				}else{
	    					//System.out.println(i+ " " +j);
	    					last.setLocation(i, j);
	    					ItRow.remove();
	    					
	    					if( colun.isEmpty() )
	    						return false;
	    				}
	    				
	    			}
	    		}
	    	}
	    
	    }
	    

		return true;
	}
	
	public List<Point> getSolution(){
		//convert List<List<Integer>> to List<Point>
		List<Point> ans= new ArrayList<Point>();
		
		Point p = null;
		
		for(List<Integer> colun: col ){
			
			for(Integer linha : colun){
			p=new Point(linha, col.indexOf(colun) );	
			}
			ans.add(p);
		}			
		return ans;
	}
	
	public Point getLast(){
		return last;
	}
	

}
