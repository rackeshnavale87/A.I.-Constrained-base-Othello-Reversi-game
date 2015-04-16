import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 * @class This class plays the Reversi Game according to MiniMax Algorithm.
 */
public class MiniMaxAlgorithm
{
	static int count = 0,visited=0;
	static boolean printOutput;
	static ArrayList<String> bufflog = new ArrayList<String>(1000);
	static ArrayList<String> bufflog2 = new ArrayList<String>(1000);

	protected static boolean isFull(Board b)
	{
		for(int i=0;i< b.board.length;i++)
		{
			for(int j =0 ; j < b.board[i].length;j++)
			{
				if(b.board[i][j].equals("*"))
				{
					return false;
				}
			}
		}
		return true;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected static int genEval(Board b)
	{
		int numBlack = 0,Blkval=0;
		int numWhite = 0,Whtval=0;
		int eval = 0;

		for (int i = 0; i < b.board.length; i++) 
		{
			for (int j = 0; j < b.board[i].length; j++) 
			{
				if (b.board[i][j].equals(ReversiGame.me)) 
				{
					numBlack++;
					Blkval = Blkval + ReversiGame.wtBoard[i][j];
				} 
				else if (b.board[i][j].equals(ReversiGame.you)) 
				{
					numWhite++;
					Whtval = Whtval + ReversiGame.wtBoard[i][j];
				}
			}
		}
//		eval = numBlack - numWhite;
		eval = Blkval - Whtval;
		return eval;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// this is MINIMAX-VALUE(state,game) as per class algorithm

	static int m=0;
	static int n=0;
	protected static int play(Board node, boolean max) throws IOException 
	{
		ArrayList<Position> moves = null;

		if (max)
		{
			moves = genLegalMoves(node, ReversiGame.me);
		} 
		else 
		{
			moves = genLegalMoves(node, ReversiGame.you);
		}

		// TERMINATE ///////////////////////////////////////////////////////////////
		if ((node.depth == (ReversiGame.cutOffDepth)) || (moves.isEmpty())) //if depth = 0 or node is a terminal node
		{
			int eval = genEval(node);// evaluation function heuristic value of node
			node.bestValue = eval;
			
			if(moves.isEmpty() && wp == 0 && bp ==0)
			{
				if(isFull(node))
				{
					//wp = 0;
				}
				else
				{
					//wp = 1;
					bufflog.add("pass");
					bufflog.add(Integer.toString(node.depth));
					bufflog.add(Integer.toString(node.bestValue));
					visited++;
				}
			}
			if(MiniMaxAlgorithm.printOutput)//// printing last
			{
				bufflog.add(node.name);
				bufflog.add(Integer.toString(node.depth));
				bufflog.add(Integer.toString(node.bestValue));
				visited++;
				//OutputAndLogShow.miniMaxLog(node.name,node.depth,Float.toString(node.bestValue));
				//System.out.println(node.name+", "+node.depth+", "+Float.toString(node.bestValue));
			}
			return eval;
		}

		
		if (max) //if MAXImizingPlayer  <-----------------------------------------
		{
			node.bestValue = -(Integer.MAX_VALUE); //bestValue = -Infinity
			MiniMaxAlgorithm.genChildStates(node, ReversiGame.me, moves);
			
			if(moves.isEmpty())
			{
				if(isFull(node))
				{
					bp = 0;
				}
				else
				{
					bp = 1;
					bufflog.add("pass");
					bufflog.add(Integer.toString(node.depth));
					bufflog.add(Integer.toString(node.bestValue));
					visited++;
				}
			}
			if(MiniMaxAlgorithm.printOutput)
			{	
				bufflog.add(node.name);
				bufflog.add(Integer.toString(node.depth));
				bufflog.add(ReversiGame.mI);
				visited++;
				//OutputAndLogShow.miniMaxLog(node.name,node.depth,ReversiGame.mI);
				//System.out.println(node.name+", "+node.depth+", "+ReversiGame.mI);
			}	
			for (Board child : node.children) // for each child of node
			{
				child.depth = node.depth + 1;//node depth increment	
				node.bestValue = Math.max(node.bestValue, MiniMaxAlgorithm.play(child, false));// bestValue := max(bestValue, val);				

				// print children having best value "node.children"
				if(MiniMaxAlgorithm.printOutput)
				{
					bufflog.add(node.name);
					bufflog.add(Integer.toString(node.depth));
					bufflog.add(Integer.toString(node.bestValue));
					visited++;
					//OutputAndLogShow.miniMaxLog(node.name,node.depth,Float.toString(node.bestValue));
					//System.out.println(node.name+", "+node.depth+", "+Float.toString(node.bestValue));
				}	
			}			
			return node.bestValue;
		}
		
		else //if MINImizingPlayer  <-----------------------------------------
		{
			node.bestValue = Integer.MAX_VALUE; // bestValue = +Infinity
			MiniMaxAlgorithm.genChildStates(node, ReversiGame.you, moves);
			
			if(moves.isEmpty())
			{
				if(isFull(node))
				{
					wp = 0;
				}
				else
				{
					wp = 1;
					bufflog.add("pass");
					bufflog.add(Integer.toString(node.depth));
					bufflog.add(Integer.toString(node.bestValue));
					visited++;
				}
			}
			if(MiniMaxAlgorithm.printOutput)
			{
				bufflog.add(node.name);
				bufflog.add(Integer.toString(node.depth));
				bufflog.add(ReversiGame.I);
				visited++;
				//OutputAndLogShow.miniMaxLog(node.name,node.depth,ReversiGame.I);
				//System.out.println(node.name+", "+node.depth+", "+ReversiGame.I);
			}
			for (Board child : node.children)  // for each child of node
			{
				child.depth = node.depth + 1;// node depth increment
				node.bestValue = Math.min(node.bestValue,MiniMaxAlgorithm.play(child, true));

				if(MiniMaxAlgorithm.printOutput)
				{
					bufflog.add(node.name);
					bufflog.add(Integer.toString(node.depth));
					bufflog.add(Integer.toString(node.bestValue));
					visited++;
				//	OutputAndLogShow.miniMaxLog(node.name,node.depth,Float.toString(node.bestValue));
					//System.out.println(node.name+", "+node.depth+", "+Float.toString(node.bestValue));
				}
			}	
		return node.bestValue;
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected static ArrayList<Position> genLegalMoves(Board b, String player) 
	{
		ArrayList<Position> moves = null;
		moves = LegalMoves.getLegalMoves(b.board, player); //player either X or O depending on chance
		return moves; // return Position ArrayList
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected static void genChildStates(Board b, String player,ArrayList<Position> moves) throws IOException 
	{  										// node and its possible                moves
		if (!moves.isEmpty()) 
		{
			for (Position po : moves) 
			{
				Board brd = null;
				if (po.direction.size() == 1) 
				{
					brd = Flip.makeMove(b.board, po.xpos, po.ypos,po.direction.get(0), player);
					brd.name = Flip.createChildName(po.xpos, po.ypos);
					for (String s : b.ancestor) 
					{
						brd.ancestor.add(s); // b is ancestor of brd 
					}
					
					// <<< FLIP >>> 
					
					
					brd.ancestor.add(b.name);
					b.children.add(brd);
				} 
				else 
				{
					Board brd2 = new Board();
					Board b1 = new Board();
					b1.copyBoard(b.board);
					
					for (String s : po.direction) 
					{
						brd2 = Flip.makeMove(b1.board, po.xpos, po.ypos, s,player);
						b1.copyBoard(brd2.board);
					}
					
					// <<< FLIP >>> 
					brd2.name = Flip.createChildName(po.xpos, po.ypos);
					// <<< FLIP >>> 
					
					for (String s : b.ancestor) 
					{
						brd2.ancestor.add(s);
					}
					
					brd2.ancestor.add(b.name);
					b.children.add(brd2);
				}
			}
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean once = false;
	static int bp = 0;
	static int wp = 0;

	static void minimax_reversi(Board node, boolean max) 
	{
		try 
		{
			ArrayList<Position> moves = null;
			++MiniMaxAlgorithm.count;
			MiniMaxAlgorithm.printOutput = true;
			
			if(MiniMaxAlgorithm.count > 64)
			{
				MiniMaxAlgorithm.printOutput =false; // does it affect anything????
				OutputAndLogShow.closeLog();
			}
			
			if (max) //====================================== my move ====================================
			{
				moves = MiniMaxAlgorithm.genLegalMoves(node, ReversiGame.me);
				
				if(moves.isEmpty())
				{
					if(isFull(node))
					{
						//OutputAndLogShow.opt.write("\r\nME");
						bp = 0;
					}
					else
					{
						//OutputAndLogShow.opt.write("\r\nME PASS");
						bp = 1;////////////////////////////////////////////////last change						
					}
				}
				else
				{
					//OutputAndLogShow.opt.write("\r\nBLACK");
					bp = 0;
				}
			} 
			else//====================================== your move ====================================
			{
				moves = MiniMaxAlgorithm.genLegalMoves(node, ReversiGame.you);
				
				if(moves.isEmpty())
				{
					if(isFull(node))
					{
						//OutputAndLogShow.opt.write("\r\nyou");
						wp = 0;
					}
					else
					{
						//OutputAndLogShow.opt.write("\r\nyou PASS");
						wp = 1;
					}
				}
				else
				{
					//OutputAndLogShow.opt.write("\r\nWHITE");
					wp = 0;
				}
			}
			
			
//no valid moves at start		
			if(!MiniMaxAlgorithm.isFull(node) && MiniMaxAlgorithm.count == 2 && (wp ==1 && bp ==1) )
			{
				if(MiniMaxAlgorithm.count>2)
					OutputAndLogShow.log.write("\r\n");
				for (int i = 0; i < node.board.length; i++) 
				{
					for (int j = 0; j < node.board[i].length; j++) 
					{
						OutputAndLogShow.log.write(node.board[i][j]); //// print the board to file
						//System.out.print(node.board[i][j]);
					}
					OutputAndLogShow.log.write("\r\n");
					//System.out.println();
				}
				OutputAndLogShow.log.write("Node,"+"Depth,"+"Value"+"\r\n");
				//System.out.print("Node,"+"Depth,"+"Value");
				
				int evalf = genEval(node);
				OutputAndLogShow.log.write(node.name+","+node.depth+","+Integer.toString(evalf));
				//System.out.println();
			//	System.out.print(node.name+", "+node.depth+", "+Integer.toString(evalf));
				//System.out.println();
				bufflog.clear();
				//System.out.println("\nGame End");
				OutputAndLogShow.closeLog();
                return;  
			}
			

			if(MiniMaxAlgorithm.isFull(node) || (wp ==1 && bp ==1))
			{
				if(MiniMaxAlgorithm.isFull(node))
				{if(MiniMaxAlgorithm.count>2)
					OutputAndLogShow.log.write("\r\n");
					for (int i = 0; i < node.board.length; i++) 
					{
						for (int j = 0; j < node.board[i].length; j++) 
						{
							OutputAndLogShow.log.write(node.board[i][j]); //// print the board to file
							//System.out.print(node.board[i][j]);
						}
						OutputAndLogShow.log.write("\r\n");
						//System.out.println();
					}
					if(bufflog2.size()!=0)
					{
					OutputAndLogShow.log.write("Node,"+"Depth,"+"Value"+"\r\n");
					//System.out.print("Node,"+"Depth,"+"Value");
					}
					for (int i = 0; i < bufflog2.size(); i=i+3) 
					{
						//System.out.println();
						{
							OutputAndLogShow.log.write(bufflog2.get(i)+","+bufflog2.get(i+1)+","+bufflog2.get(i+2));
							//System.out.print(bufflog2.get(i)+", "+bufflog2.get(i+1)+", "+bufflog2.get(i+2));
							m++;
						}
						OutputAndLogShow.log.write("\r\n");
					}
				}
				
				//System.out.println();
				bufflog.clear();
				
				//OutputAndLogShow.log.write("\r\nGame End");
				//System.out.println("\nGame End");
				OutputAndLogShow.closeLog();
                return;                
			}
//////////////////////////////////////////////////////////////////////////////////////			
			if(MiniMaxAlgorithm.count>1)
			{
				if(MiniMaxAlgorithm.count>2)
				OutputAndLogShow.log.write("\r\n");
				
				for (int i = 0; i < node.board.length; i++) 
				{
					
					for (int j = 0; j < node.board[i].length; j++) 
					{
						OutputAndLogShow.log.write(node.board[i][j]); //// print the board to file
						//System.out.print(node.board[i][j]);
					}
					OutputAndLogShow.log.write("\r\n");
					//System.out.println();
				}
				////////////////////////////////////////////////////
				OutputAndLogShow.log.write("Node,"+"Depth,"+"Value"+"\r\n");
				//System.out.print("Node,"+"Depth,"+"Value");
				//////////////////////////////////////////////////////
				for (int i = 0; i < bufflog2.size(); i=i+3) 
				{
					//System.out.println();
					{
						OutputAndLogShow.log.write(bufflog2.get(i)+","+bufflog2.get(i+1)+","+bufflog2.get(i+2));
						//System.out.print(bufflog2.get(i)+", "+bufflog2.get(i+1)+", "+bufflog2.get(i+2));
						m++;
					}
					OutputAndLogShow.log.write("\r\n");
				}
				
				if(!moves.isEmpty() && bufflog2.size()==0)
				{
					//OutputAndLogShow.log.write("pass"+",");
					//System.out.println("pass");
					OutputAndLogShow.miniMaxLog("pass",node.depth,Integer.toString(node.bestValue));
					//System.out.println("\npass,"+node.depth+","+Integer.toString(node.bestValue));
				}
				//System.out.println();
				bufflog.clear();
			}	

///////////////////////////////////////////////////////////////////////////////////////

			if (!moves.isEmpty()) // Legal and valid moves are available------------------------------
			{
				MiniMaxAlgorithm.play(node, max);//<--------------------------------------------------
				bufflog2 = bufflog;
				
				if (max) // finding max between children to explore more
				{				
					Board childMax = null;
					for(Board b:node.children)
					{					
						if(b.bestValue == node.bestValue)
						{
							childMax = b;
							break;
						}
					}
					childMax.depth = 0;
					childMax.children.clear();
					MiniMaxAlgorithm.minimax_reversi(childMax, false);
				} 
				else // finding minimum of children to explore more----------------------------------
				{	
					Board childMin = null;
					for(Board b:node.children)
					{
						if(b.bestValue == node.bestValue)
						{
							childMin = b;						
							break;
						}
					}
					childMin.depth = 0;
					childMin.children.clear();
					MiniMaxAlgorithm.minimax_reversi(childMin,true);
				}
			}
			else//------------------------------------------------------------------------------------
			{
				node.depth = 0;
				node.children.clear();
				if(max)
				{
					MiniMaxAlgorithm.minimax_reversi(node,false);
				}
				else
				{
					MiniMaxAlgorithm.minimax_reversi(node,true);
				}			
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
}