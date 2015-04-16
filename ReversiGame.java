import java.util.HashMap;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 * @class ReversiGame
 * 		  Takes input from file in format 
 * 			<task#> 
 * 				1 = Greedy/
 * 				2 = MiniMax/ 
 * 				3 = Alpha-beta/ 
 * 				4 = Competition
 *			<your player: X or O>
 *				X/
 *				O
 *			<cutting off depth >
 *			<current state as follows:>
 *				e.g.
 *				********
 *				********
 *				********
 *				***OX***
 *				***XO***
 *				********
 *				********
 *				********
 * 			*: blank cell
 *			X: Black player
 *			O: White Player
 *
 * 		  It plays the game on the Board supplied by the input file using MiniMax Algorithm.
 * 		  There are 3 possible variations
 * 			1. Greedy (MiniMax) Algorithm 
 * 			2. MiniMAx Algorithm
 * 			3. Alpha Beta Algorithm
 * 		  Finally writes an output to file depending on the Algorithm is uses
 * 			1 = Greedy/
 * 			<next state>
 * 			2 = MiniMax/ 
 * 			<next state>
 *			<traverse log>	: Node,Depth,Value
 * 			3 = Alpha-beta/ 
 * 			<next state>
 *			<traverse log> : Node,Depth,Value,Alpha,Beta.
 */


public class ReversiGame
{
	protected static HashMap<String, String> forChildName = new HashMap<String, String>();
	public static void initChildNames() 
	{
		forChildName.put(Integer.toString(0), "a");
		forChildName.put(Integer.toString(1), "b");
		forChildName.put(Integer.toString(2), "c");
		forChildName.put(Integer.toString(3), "d");
		forChildName.put(Integer.toString(4), "e");
		forChildName.put(Integer.toString(5), "f");
		forChildName.put(Integer.toString(6), "g");
		forChildName.put(Integer.toString(7), "h");
	}
	
	/**
	 * Consider the below 2D array for playing board with weights for each cell,
	 * which will be used to calculate evaluation function.
	 */
	static int[][] wtBoard = 
	{
			{ 99, -8, 8, 6, 6, 8, -8, 99 },
			{ -8, -24, -4, -3, -3, -4, -24, -8 }, 
			{ 8, -4, 7, 4, 4, 7, -4, 8 },
			{ 6, -3, 4, 0, 0, 4, -3, 6 }, 
			{ 6, -3, 4, 0, 0, 4, -3, 6 },
			{ 8, -4, 7, 4, 4, 7, -4, 8 },
			{ -8, -24, -4, -3, -3, -4, -24, -8 },
			{ 99, -8, 8, 6, 6, 8, -8, 99 } 
	};
	
	/**
	 * Initialize the board with root as depth "0" and 
	 * alpha beta value with "-Float.MAX_VALUE/Float.MAX_VALUE" respectively
	 */
	public static String me, you;
	protected static Board root;
	protected static int cutOffDepth;
	protected static String I = "Infinity";
	protected static String mI = "-Infinity";

	protected static void rootInit() 
	{
		ReversiGame.root = new Board();
		ReversiGame.root.name = "root";
		ReversiGame.root.depth = 0;
		ReversiGame.root.alpha = -Integer.MAX_VALUE;
		ReversiGame.root.beta = Integer.MAX_VALUE;
		ReversiGame.initChildNames();
	}
	
	public static int task;

	
	public static void main(String[] args) 
	{
		/**
		 * Initializing the root board
		 */
		ReversiGame.rootInit();
		ParserInputFile p = new ParserInputFile();
		p.parseInputFile(); 

		/**
		 * Initializing the log and output file writers
		 */
		OutputAndLogShow.initOutput();
		if (ReversiGame.task == 1)
		{
			GreedyAlgorithm.greedy_reversi(ReversiGame.root, true);
			//System.out.println("Completed Greedy - MiniMax Algorithm on the input");
		}		
		if (ReversiGame.task == 2)
		{
			MiniMaxAlgorithm.minimax_reversi(ReversiGame.root, true);
			//System.out.println("Completed MiniMax Algorithm on the input");
		}
		else if (ReversiGame.task == 3) 
		{
			AlphaBeta.wEval = true;
			AlphaBeta.alphabeta_reversi(ReversiGame.root, true);
			//System.out.println("Completed MiniMax Algorithm with Alpha Beta Pruning on the weighted board input");
		}
		else
		{
			// competition??????????
		}

	}
}
