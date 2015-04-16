import java.util.ArrayList;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 */

public class Board 
{
	String [][] board = new String[8][8];
	int bestValue;
	int depth;
	int alpha;
	int beta;
	String name;
	
	ArrayList<Board> children = new ArrayList<Board>();
	ArrayList<String> ancestor = new ArrayList<String>();
	
	protected void copyBoard(String[][] from)
	{
		for(int i=0;i<8;i++)
		{
			for(int j =0;j<8;j++)
			{
				board[i][j] = from[i][j];
			}
		}
	}
}
