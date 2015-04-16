import java.io.*;
import java.util.Scanner;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 */
class ParserInputFile 
{
	public boolean parseInputFile() 
	{
		String readLine = null;
		try 
		{
			int i = 0, j = 0;
			FileReader inputFile = new FileReader("input.txt");
			Scanner inputScanner = new Scanner(inputFile);
			
			ReversiGame.task = Integer.parseInt(inputScanner.nextLine());
			ReversiGame.me = inputScanner.nextLine(); // your player ... interchanging X and O!!!!!!!!!			
			ReversiGame.cutOffDepth = Integer.parseInt(inputScanner.nextLine());
			
			if(ReversiGame.me.equals("X"))
				ReversiGame.you = "O";
			else if (ReversiGame.me.equals("O"))
				ReversiGame.you= "X";
			
			
			
			//System.out.println("Option : " + ReversiGame.task);
			//System.out.println("Max Depth : " +ReversiGame.cutOffDepth);
			
			for (i = 0; i < ReversiGame.root.board.length; i++) 
			{
				if (inputScanner.hasNextLine()) 
				{
					readLine = inputScanner.nextLine();
				}
			
				//System.out.println(readLine);
				char[] eachCell = readLine.toCharArray();
				for (j = 0; j < ReversiGame.root.board[i].length; j++) 
				{
					//String boardCell = Character.toString(eachCell[j]);
					ReversiGame.root.board[i][j] = Character.toString(eachCell[j]);
				}
			}
			inputScanner.close();
		}
		catch (IOException e) 
		{e.printStackTrace();return false;}
		
		return true;
	}
}