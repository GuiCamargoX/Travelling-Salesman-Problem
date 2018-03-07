import Interfaces.Step3;

public class LineTool implements Step3{
	static int count=0;
	static int[][] m2;
	static int[][] m3;
	
	// Cover all zeros in the resulting matrix using a minimum number of horizontal and vertical lines.
	public static int FindMinimumNumberLines(int[][] m1, int [][]mapLines) {
    	
    // m2 max(horizontal,vertical) values, with negative number for
    // horizontal, positive for vertical
    m2 = new int[m1.length][m1.length];

    // m3 where the line are drawen
    m3 = new int[m1.length][m1.length];

    // loop on zeroes from the input array, and store the max num of zeroes
    // in the m2 array
    for (int row = 0; row < m1.length; row++) {
        for (int col = 0; col < m1.length; col++) {
            if (m1[row][col] == 0)
                m2[row][col] = hvMax(m1, row, col);
        }
    }


    // Loop on m2 elements, clear neighbours and draw the lines
    for (int row = 0; row < m1.length; row++) {
        for (int col = 0; col < m1.length; col++) {
            if (Math.abs(m2[row][col]) > 0) {
                clearNeighbours(m2, m3, row, col);
            }
        }
    }
    
    return count;
	}

// max of vertical vs horizontal at index row col
    private static int hvMax(int[][] m1, int row, int col) {
    int vertical = 0;
    int horizontal = 0;

    // check horizontal
    for (int i = 0; i < m1.length; i++) {
        if (m1[row][i] == 0)
            horizontal++;
    }

    // check vertical
    for (int i = 0; i < m1.length; i++) {
        if (m1[i][col] == 0)
            vertical++;
    }

    // negative for horizontal, positive for vertical
    return vertical > horizontal ? vertical : horizontal * -1;
    }

// clear the neighbors of the picked largest value, the sign will let the
// app decide which direction to clear
    private static void clearNeighbours(int[][] m2, int[][] m3, int row, int col) {
    
	if(m2[row][col] != 0){
          count++;
    }	        
	
	// if vertical
    if (m2[row][col] > 0) {
        for (int i = 0; i < m2.length; i++) {
            if (m2[i][col] > 0)
                m2[i][col] = 0; // clear neigbor
            m3[i][col] = 1; // draw line
        }
    } else {
        for (int i = 0; i < m2.length; i++) {
            if (m2[row][i] < 0)
                m2[row][i] = 0; // clear neigbor
            m3[row][i] = 1; // draw line
        }
       }
    
    }


}
