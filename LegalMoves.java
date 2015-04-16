import java.util.ArrayList;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 */
public class LegalMoves 
{
	static int flag = 1;
	private static String[][] cB;
	static String opponent = null;
	static String curr_p = null;
	static ArrayList<String> notCheck = new ArrayList<String>();
	static ArrayList<Position> positions;

	protected static ArrayList<Position> getLegalMoves(String[][] board,String currentPlayer) 
	{
		Board b = new Board();
		b.copyBoard(board);
        
		positions = new ArrayList<Position>();
		curr_p = currentPlayer;
		cB = b.board; 
		
		// current board configuration copy
		int i = 0, j = 0;

// for general position finding separate our player and opponent
		if (currentPlayer.equals(ReversiGame.me)) //x
		{opponent = ReversiGame.you;} 
		else if (currentPlayer.equals(ReversiGame.you)) 
		{opponent = ReversiGame.me;}

		for (i = 0; i < cB.length; i++) 
		{
			flag = 0;
			for (j = 0; j < cB[i].length; j++) 
			{
				Position pos = null;
				notCheck.clear();
				/**
				 * Finding empty positions on the board
				 */
				if (cB[i][j].equals("*")) // check for only blank positions i.e. with "*"
				{
					pos = new Position(i,j);

					if (j <= 1) 
					{
						notCheck.add("UL");
						notCheck.add("L");
						notCheck.add("BL");
					} 
					else if (j >= 7) 
					{
						notCheck.add("UR");
						notCheck.add("R");
						notCheck.add("BR");
					}
					if (i <= 1) 
					{
						notCheck.add("UL");
						notCheck.add("U");
						notCheck.add("UR");
					} 
					else if (i >= 7) 
					{
						notCheck.add("BL");
						notCheck.add("B");
						notCheck.add("BR");
					}
					if (!notCheck.contains("L")) 
					{
						checkLeft(i, j,pos); // check left side of current cell for legal move
					}

					if (!notCheck.contains("R")) 
					{
						checkRight(i, j,pos); //check right side
					}

					if (!notCheck.contains("B")) 
					{
						checkBottom(i, j,pos);// check below	
					}

					if (!notCheck.contains("BL")) 
					{
						checkBottomLeft(i, j,pos);// check left of bottom
					}

					if (!notCheck.contains("BR")) 
					{
						checkBottomRight(i, j,pos);// check right of bottom
					}

					if (!notCheck.contains("U")) 
					{
						checkUp(i, j,pos);
					}

					if (!notCheck.contains("UL")) 
					{
						checkUpperLeft(i, j,pos);
					}

					if (!notCheck.contains("UR")) 
					{
						checkUpperRight(i, j,pos);
					}
					
					if(pos.direction.isEmpty())
					{
						continue;// if not position found ..continue search with next cell
					}
					else
					{
						positions.add(pos);// positions found
					}
				}
			}
		}

		// print board//////////////////////////////////////////////////////////////
		
		return positions;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected static boolean checkLeft(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*"))
		{
			return false;  //*****  itself blank position, return false
		}
		
		j = j - 1; //*****    decrease column to go to left for position checking
		
		if (cB[i][j].equals(opponent)) //*****  Its opponent, +ve:NEED TO CHECK for more opp disk
		{
			j = j - 1; //*****     decrease column to go to left for position checking
			while (j >= 0) 
			{
				if (cB[i][j].equals("*"))  //*****  blank position
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) //***** Its opponent, NEED TO CHECK for more opp disk
				{
					--j; //*****  go to the left to check the number of opp disk
					continue; 
				} 
				else if (cB[i][j].equals(curr_p)) //***** Its our/current player disk
				{
					
					pos.direction.add("L"); 
					//System.out.println("Legal move -" + pos.direction); //***** where we are going to flip the opp disks
					return true;
				}
				--j;
			}
			return false;//***** only opponent disk found, OUR disk is not on the left line
		} 
		else//***** if its NOT opponent, return false
		{
			return false;
		}
	}

// similarly now we check as per the checking direction the legal moves we have as of now!!!
	
	protected static boolean checkRight(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*"))
		{
			return false;
		}
		j = j + 1;
		if (cB[i][j].equals(opponent)) // Its opp disk, +ve:NEED TO FIND opp disk (best for current player to find more of these)
		{
			j = j + 1; // shift right(+1) for further check as i am using While loop
			while (j <= 7) 
			{
				if (cB[i][j].equals("*")) 
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) 
				{
					++j;
					continue;
				} 
				else if (cB[i][j].equals(curr_p)) 
				{

					pos.direction.add("R"); // xpos and ypos is the "LEGAL MOVE" and pos.direction is the directn to opp disk
					//System.out.println("Legal move -" + pos.direction);
					return true;
				}
				++j;
			}
			return false;
		} 
		else 
		{
			return false;
		}

	}

	protected static boolean checkBottom(int i, int j,Position pos) 
	{
		
		if(!cB[i][j].equals("*")) // cell itself not blank
		{
			return false;
		}
		
		i = i + 1;
		
		if (cB[i][j].equals(opponent)) // below is opp then only check further below
		{
			i = i + 1;
			while (i <= 7) 
			{

				if (cB[i][j].equals("*")) //no valid position
				{
					return false;
				}
				else if (cB[i][j].equals(opponent))// finding opp disk in between 
				{
					++i;
					continue;

				} 
				else if (cB[i][j].equals(curr_p)) // found opponent disk in between
				{
					
					pos.direction.add("B");
					//System.out.println("Legal move -" + pos.direction);

					return true;
				}
				++i;
			}
			return false;
		} 
		else 
		{
			return false;
		}
	}

	protected static boolean checkBottomRight(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*"))
		{
			return false;
		}
		i = i + 1;
		j = j + 1;
		if (cB[i][j].equals(opponent)) 
		{
			i = i + 1;
			j = j + 1;
			while (i <= 7 && j <= 7) 
			{
				if (cB[i][j].equals("*")) 
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) 
				{
					++i;
					++j;
					continue;
				} 
				else if (cB[i][j].equals(curr_p)) 
				{
					
					pos.direction.add("BR");
					//System.out.println("Legal move -" + pos.direction);

					return true;
				}
				++i;
				++j;
			}
			return false;
		} 
		else 
		{
			return false;
		}
	}

	protected static boolean checkBottomLeft(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*")) // cell itself is not blank
		{
			return false;
		}
		// |?????  | Cur |
		// | check |???? |
		i = i + 1; 
		j = j - 1; 
		if (cB[i][j].equals(opponent)) // found opp now check futher for more opp return if finds same as itself
		{
			j = j - 1; 
			i = i + 1; // go further Bottom n left
			while (j >= 0 && i <= 7) 
			{
				if (cB[i][j].equals("*")) // no closing same disk open end, so return false
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) 
				{
					--j;
					++i;
					continue;
				} 
				else if (cB[i][j].equals(curr_p)) 
				{
					
					pos.direction.add("BL");
					//System.out.println("Legal move -" + pos.direction);

					return true;
				}
				--j;
				++i;
			}
			return false;
		} 
		else 
		{
			return false;
		}

	}

	protected static boolean checkUp(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*"))// it contains position already filled up
		{
			return false;
		}
		i = i - 1;
		if (cB[i][j].equals(opponent)) // U is itself so no check
		{
			i = i - 1;
			while (i >= 0) 
			{
				if (cB[i][j].equals("*")) 
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) 
				{
					--i;
					continue;

				} 
				else if (cB[i][j].equals(curr_p)) 
				{
					
					pos.direction.add("U");
					//System.out.println("Legal move -" + pos.direction);

					return true;
				}
				--i;
			}
			return false;
		} 
		else 
		{
			return false;
		}
	}

	protected static boolean checkUpperLeft(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*"))//itself blank, return false
		{
			return false;
		}
	
		i = i - 1;
		j = j - 1; // go further up n left

		if (cB[i][j].equals(opponent)) //check only if its opp disk else return
		{
			i = i - 1;
			j = j - 1;
			while (i >= 0 && j >= 0) 
			{
				if (cB[i][j].equals("*")) 
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) 
				{
					--i;
					--j;
					continue;
				} 
				else if (cB[i][j].equals(curr_p)) 
				{
					
					pos.direction.add("UL");
					//System.out.println("Legal move -" + pos.direction);

					return true;
				}
				--i;
				--j;
			}
			return false;
		} 
		else 
		{
			return false;
		}
	}

	protected static boolean checkUpperRight(int i, int j,Position pos) 
	{
		if(!cB[i][j].equals("*"))
		{
			return false;
		}
		i = i - 1;
		j = j + 1;
		if (cB[i][j].equals(opponent)) // loop only if its opp disk else return false
		{
			i = i - 1;
			j = j + 1;// go further up and right
			while (i >= 0 && j <= 7) 
			{
				if (cB[i][j].equals("*")) // not valid
				{
					return false;
				} 
				else if (cB[i][j].equals(opponent)) // nice, keep checking for more opp disk
				{
					--i;
					++j;
					continue;
				} 
				else if (cB[i][j].equals(curr_p)) // if finds same disk as itself, great move!
				{
					
					pos.direction.add("UR");
					//System.out.println("Legal move -" + pos.direction);
					return true;
				}
				--i;
				++j;
			}
			return false;
		} 
		else 
		{
			return false;
		}
	}
}
