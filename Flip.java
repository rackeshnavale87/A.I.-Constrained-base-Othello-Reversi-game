
/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 */
public class Flip 
{
	static String curr_p = null;
	static String[][] currentBoard = null;

	protected static Board makeMove(String[][] board, int i, int j, String pos,	String currentPlayer) 
	{
		Flip.curr_p = currentPlayer;
		Board child = new Board();

		child.copyBoard(board);

		Flip.currentBoard = child.board;

		if (pos.equals("L")) 
		{

			checkInitPos(i, j);
			j = j - 1;
			while (j >= 0) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				--j;
			}
		} 
		else if (pos.equals("R")) 
		{
			
			checkInitPos(i, j);
			j = j + 1;
			while (j <= 7) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				++j;
			}
		} 
		else if (pos.equals("U")) 
		{
			
			checkInitPos(i, j);
			i = i - 1;
			while (i >= 0) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				--i;
			}
		} 
		else if (pos.equals("UL")) 
		{
			
			checkInitPos(i, j);
			j = j - 1;
			i = i - 1;
			while (j >= 0 && i >= 0) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				--j;
				--i;
			}
		} 
		else if (pos.equals("UR")) 
		{
			
			checkInitPos(i, j);
			j = j + 1;
			i = i - 1;
			while (i >= 0 && j <= 7) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				++j;
				--i;
			}
		} 
		else if (pos.equals("B")) 
		{
			
			checkInitPos(i, j);
			i = i + 1;
			while (i <= 7) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				++i;
			}
		} 
		else if (pos.equals("BL")) 
		{
			
			checkInitPos(i, j);
			i = i + 1;
			j = j - 1;
			while (i <= 7 && j >= 0) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				++i;
				--j;
			}
		} 
		else if (pos.equals("BR")) 
		{
			
			checkInitPos(i, j);
			i = i + 1;
			j = j + 1;
			while (i <= 7 && j <= 7) 
			{
				if (!flip(i, j)) 
				{
					return child;
				}
				++j;
				++i;
			}
		}
		return child;
	}

	protected static void checkInitPos(int i, int j) 
	{
		if (Flip.currentBoard[i][j].equals("*")) 
		{
			Flip.currentBoard[i][j] = Flip.curr_p;	
		} 
		else if (currentBoard[i][j].equals(Flip.curr_p)) 
		{

		} 
		else 
		{
			System.err.println("Invalid Move! at position" + i + j);
			System.exit(0);
		}
		return;
	}

	protected static boolean flip(int i, int j) 
	{

		if (Flip.currentBoard[i][j].equals(Flip.curr_p))  // already that player == cell
		{
			return false;
		} 
		else 
		{	
			Flip.currentBoard[i][j] = Flip.curr_p; // that cell == current player
			return true;
		}
	}

	protected static String createChildName(int i, int j) 
	{
		String name = null;
		name = ReversiGame.forChildName.get(Integer.toString(j));
		name = name + (i + 1);
		return name;
	}

}