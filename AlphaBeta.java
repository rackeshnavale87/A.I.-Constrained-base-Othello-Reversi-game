import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 * 
 */
public class AlphaBeta 
{
	static int count = 0, visited=0;
//	static boolean pass =false;
	static boolean printOutput;
	static ArrayList<String> bufflog = new ArrayList<String>(1000);
	static ArrayList<String> bufflog2 = new ArrayList<String>(1000);
	static boolean wEval = true;
	/**
	 * Boolean variable which is set to 
	 * "true" -> if the user wants to use weighted evaluation function
	 * "false" -> if want to use simple evaluation function (#Black - #White)
	 */
	/**
	 * 
	 * @param b: the Board to be check.
	 * @return true: if board is full, false otherwise.
	 * 
	 */
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
	
	/**
	 * 
	 * @param b: Board for which the evaluation function has to be calculated.
	 * @return   weighted evaluation function of the board.  
	 */

	protected static int genWtEval(Board b) 
	{
		int numBlack = 0, Blkval=0;
		int numWhite = 0, Whtval=0;
		int eval = 0;

		for (int i = 0; i < b.board.length; i++) 
		{
			for (int j = 0; j < b.board[i].length; j++) 
			{
				if (b.board[i][j].equals(ReversiGame.me)) 
				{
					numBlack += ReversiGame.wtBoard[i][j];
					Blkval = Blkval + ReversiGame.wtBoard[i][j];
				} 
				else if (b.board[i][j].equals(ReversiGame.you)) 
				{
					numWhite += ReversiGame.wtBoard[i][j];
					Whtval = Whtval + ReversiGame.wtBoard[i][j];
				}
			}
		}
		eval = Blkval - Whtval;
		return eval;
	}

	/**
	 * @param b:  reference to the board for which we need to generate the evaluation function value
	 * 
	 * @return 	  returns the evaluation value = (#black_pieces - #white_pieces)
	 */
///////////////////???????????????????????????????????????????????????/ NEEDED>>??????????????????????????	
	protected static int genEval(Board b) 
	{
		int numBlack = 0;
		int numWhite = 0;
		int eval = 0;

		for (int i = 0; i < b.board.length; i++) 
		{
			for (int j = 0; j < b.board[i].length; j++) 
			{
				if (b.board[i][j].equals(ReversiGame.me)) 
				{
					numBlack++;
				} 
				else if (b.board[i][j].equals(ReversiGame.you)) 
				{
					numWhite++;
				}
			}
		}
		eval = numBlack - numWhite;
		return eval;
	}
///////////////////???????????????????????????????????????????????????/ NEEDED>>??????????????????????????	
	
	/**
	 * 
	 * @param node Board on which the current move is going to be played.
	 * @param max: true if the player "X" false if player is "O" (Opponent)
	 * @return the max or min value.
	 * @throws IOException 
	 */

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
		if ((node.depth == (ReversiGame.cutOffDepth)) || (moves.isEmpty())) 
		{
			////////////// check last time..............
			int eval;
			//if (wEval) 
			//{
				eval = genWtEval(node);
			//} 
			//else 
			//{
			//	eval = genEval(node);
			//}
			node.bestValue = eval;
			
			
			if(moves.isEmpty() )
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
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
					visited++;
				}
			}
			
			
			
			if (AlphaBeta.printOutput) 
			{
				bufflog.add(node.name);
				bufflog.add(Integer.toString(node.depth));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
				visited++;
				//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha), Float.toString(node.beta));
			}
			
			
			
			return eval;
		}

	
		if (max)  //if MAXImizingPlayer  <-----------------------------------------
		{
			node.bestValue = -Integer.MAX_VALUE; //bestValue = -Infinity
			AlphaBeta.genChildStates(node, ReversiGame.me, moves);
			
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
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
					visited++;
				}
			}
			
			if (AlphaBeta.printOutput) 
			{
				bufflog.add(node.name);
				bufflog.add(Integer.toString(node.depth));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
				visited++;
				//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha), Float.toString(node.beta));
			}

			
			for (Board child : node.children) // for each child of node
			{
				child.depth = node.depth + 1;//node depth increment

				child.alpha = node.alpha;
				child.beta = node.beta;
				int temp = AlphaBeta.play(child, false);
				if(temp > node.bestValue)
				{
					node.bestValue = temp;
				}
				
				node.alpha = Math.max(node.alpha,node.bestValue ); // bestValue := max(bestValue, val);
				//node.bestValue = node.alpha;
				if (node.beta <= node.alpha) 
				{
					if (AlphaBeta.printOutput) 
					{
						bufflog.add(node.name);
						bufflog.add(Integer.toString(node.depth));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
						visited++;
						//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha),Float.toString(node.beta),"CUT-OFF");
					}
					break;
				} 
				else 
				{
					if (AlphaBeta.printOutput) 
					{
						bufflog.add(node.name);
						bufflog.add(Integer.toString(node.depth));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
						visited++;
						//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha),Float.toString(node.beta));
					}
				}
			}
			return node.bestValue;
		} 
		
		else //if MINImizingPlayer  <-----------------------------------------
		{
			node.bestValue = Integer.MAX_VALUE;
			AlphaBeta.genChildStates(node, ReversiGame.you, moves);
			// for each child of node
			
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
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
					bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
					visited++;
				}
			}
			
			if (AlphaBeta.printOutput) 
			{
				bufflog.add(node.name);
				bufflog.add(Integer.toString(node.depth));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
				bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
				visited++;
				//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha), Float.toString(node.beta));
			}
			for (Board child : node.children) 
			{
				child.depth = node.depth + 1;
				child.alpha = node.alpha;
				child.beta = node.beta;
				
				Integer temp = AlphaBeta.play(child, true);
				if(temp < node.bestValue)
				{
					node.bestValue = temp;
				}

				node.beta = Math.min(node.beta, node.bestValue);
			//	node.bestValue = node.beta;
				if (node.beta <= node.alpha) 
				{
					if (AlphaBeta.printOutput) 
					{
						bufflog.add(node.name);
						bufflog.add(Integer.toString(node.depth));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
						visited++;
						//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha),Float.toString(node.beta), "CUT-OFF");
					}
					break;
				} 
				else 
				{
					if (AlphaBeta.printOutput) 
					{
						bufflog.add(node.name);
						bufflog.add(Integer.toString(node.depth));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.bestValue)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.alpha)));
						bufflog.add(OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
						visited++;
						//OutputAndLogShow.alphaBetaLog("\r\n"+node.name, node.depth,Float.toString(node.bestValue),Float.toString(node.alpha),Float.toString(node.beta));
					}
				}
			}
			return node.bestValue;
		}
	}
	/**
	 * 
	 * @param b Board for which the legal moves have to be generated.
	 * @param player: player can be "X" or "O"
	 * @return The possible positions on the board along 
	 * 		   with directions in which legal move could be made.
	 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected static ArrayList<Position> genLegalMoves(Board b, String player) 
	{
		ArrayList<Position> moves = null;
		moves = LegalMoves.getLegalMoves(b.board, player);
		return moves;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected static void genChildStates(Board b, String player,ArrayList<Position> moves) throws IOException 
	{
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
						brd.ancestor.add(s);
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
	
	/**
	 * 
	 * @param node node Board on which the best move has to be computed 
	 * 				using Alpha Beta Pruning.
	 * 				Game will end if the board
	 * 			   	is full or there are no possible moves for both player 
	 * @param max is true for Player "X" and false for Player "O"
	 */
	static boolean once = false;
	static int wp = 0;
	static int bp = 0;
	static void alphabeta_reversi(Board node, boolean max) 
	{
		
		try 
		{
			ArrayList<Position> moves = null;
			++AlphaBeta.count;
			AlphaBeta.printOutput = true;
			
			if (AlphaBeta.count > 64) 
			{
				AlphaBeta.printOutput = false;
				OutputAndLogShow.closeLog();
			}
			
			if (max) //====================================== my move ====================================
			{
				moves = AlphaBeta.genLegalMoves(node, ReversiGame.me);	
				
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
					//OutputAndLogShow.opt.write("\r\nME");
					bp = 0;
				}
			} 
			else //====================================== your move ====================================
			{
				moves = AlphaBeta.genLegalMoves(node, ReversiGame.you);
				
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
			
			if(!AlphaBeta.isFull(node) && AlphaBeta.count == 2 && (wp ==1 && bp ==1) )
			{
				if(AlphaBeta.count>2)
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
				OutputAndLogShow.log.write("Node,Depth,Value,Alpha,Beta"+"\r\n");
				//System.out.print("Node,Depth,Value,Alpha,Beta");
				
				int evalf = genWtEval(node);
				OutputAndLogShow.log.write(node.name+","+node.depth+","+Integer.toString(evalf)+","+OutputAndLogShow.checkInfi(Integer.toString(node.alpha))+","+OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
				//System.out.println();
				//System.out.print(node.name+", "+node.depth+", "+Integer.toString(evalf)+","+OutputAndLogShow.checkInfi(Integer.toString(node.alpha))+","+OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
				//System.out.println();
				bufflog.clear();
				//System.out.println("\nGame End");
				OutputAndLogShow.closeLog();
                return;  
			}
			

			if(AlphaBeta.isFull(node) || (wp ==1 && bp ==1))
			{
				if(AlphaBeta.isFull(node))
				{if(AlphaBeta.count>2)
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
					OutputAndLogShow.log.write("Node,Depth,Value,Alpha,Beta"+"\r\n");
					//System.out.print("Node,"+"Depth,"+"Value");
					}
					for (int i = 0; i < bufflog2.size(); i=i+5) 
					{
						//System.out.println();
						{
							OutputAndLogShow.log.write(bufflog2.get(i)+","+bufflog2.get(i+1)+","+OutputAndLogShow.checkInfi(bufflog2.get(i+2))+","+OutputAndLogShow.checkInfi(bufflog2.get(i+3))+","+OutputAndLogShow.checkInfi(bufflog2.get(i+4)));
							//System.out.print(bufflog2.get(i)+", "+bufflog2.get(i+1)+", "+OutputAndLogShow.checkInfi(bufflog2.get(i+2))+", "+OutputAndLogShow.checkInfi(bufflog2.get(i+3))+", "+OutputAndLogShow.checkInfi(bufflog2.get(i+4)));
							//m++;
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
			
////////////////////////////////////////////////////////////////////////////////////
			if(AlphaBeta.count>1)
			{	
				if(AlphaBeta.count>2)
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
				OutputAndLogShow.log.write("Node,Depth,Value,Alpha,Beta"+"\r\n");
				//System.out.print("Node,Depth,Value,Alpha,Beta"+"\n");
				////////////////////////////////////////////////////				
				for (int i = 0; i < bufflog2.size(); i=i+5) 
				{
					//System.out.println();
					{
						OutputAndLogShow.log.write(bufflog2.get(i)+","+bufflog2.get(i+1)+","+OutputAndLogShow.checkInfi(bufflog2.get(i+2))+","+OutputAndLogShow.checkInfi(bufflog2.get(i+3))+","+OutputAndLogShow.checkInfi(bufflog2.get(i+4)));
						//System.out.print(bufflog2.get(i)+", "+bufflog2.get(i+1)+", "+OutputAndLogShow.checkInfi(bufflog2.get(i+2))+", "+OutputAndLogShow.checkInfi(bufflog2.get(i+3))+", "+OutputAndLogShow.checkInfi(bufflog2.get(i+4)));
						m++;
					}
					OutputAndLogShow.log.write("\r\n");
				}
				
				
				if(!moves.isEmpty() && bufflog2.size()==0)
				{
					//OutputAndLogShow.log.write("pass"+",");
					//System.out.println("pass");
					OutputAndLogShow.log.write("pass"+","+node.depth+","+Integer.toString(node.bestValue)+","+OutputAndLogShow.checkInfi(Integer.toString(node.bestValue))+","+OutputAndLogShow.checkInfi(Integer.toString(node.alpha))+","+OutputAndLogShow.checkInfi(Integer.toString(node.beta)));
					OutputAndLogShow.log.write("\r\n");
					//System.out.println("\npass,"+node.depth+","+Integer.toString(node.bestValue));
				}
				//System.out.println();
				bufflog.clear();
				
			}	
			
			
///////////////////////////////////////////////////////////////////////////////////////
//			if(AlphaBeta.isFull(node) || (wp ==1 && bp ==1))
//			{
//				//opt to log
//				//OutputAndLogShow.log.write("\r\nGame End");
//				//closeOutput to log
//				OutputAndLogShow.closeLog();
//                return;
//			}
////////////////////////////////////////////////////////////////////////////////////////
			if (!moves.isEmpty())  // Legal and valid moves are available------------------------------
			{
				AlphaBeta.play(node, max);
				bufflog2 = bufflog;
				
				if (max) // finding max between children to explore more
				{
					Board childMax = null;
					for (Board b : node.children) 
					{
						if (b.bestValue == node.bestValue) 
						{
							childMax = b;
							break;
						}
					}
					childMax.depth = 0;
					childMax.children.clear();
					childMax.alpha = -Integer.MAX_VALUE;
					childMax.beta = Integer.MAX_VALUE;
					AlphaBeta.alphabeta_reversi(childMax, false);
				} 
				else 
				{
					Board childMin = null;
					for (Board b : node.children) 
					{
						if (b.bestValue == node.bestValue) 
						{
							childMin = b;
							break;
						}
					}
					childMin.depth = 0;
					childMin.children.clear();
					childMin.alpha = -Integer.MAX_VALUE;
					childMin.beta = Integer.MAX_VALUE;
					AlphaBeta.alphabeta_reversi(childMin, true);
				}
			} 
			else //------------------------------------------------------------------------------------
			{
				node.depth = 0;
				node.children.clear();
				node.alpha = -Integer.MAX_VALUE;
				node.beta = Integer.MAX_VALUE;
				if(max)
				{
					AlphaBeta.alphabeta_reversi(node, false);
				}
				else
				{
					AlphaBeta.alphabeta_reversi(node, true);
				}
				//output to log
				OutputAndLogShow.closeLog();
			}
		} catch (IOException e) 
		{e.printStackTrace();}
	}

}
