
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 * @class  Write output file for the results of each algorithm for Reversi Game.
 */

public class OutputAndLogShow
{
	private static File logFile = new File("output.txt");
	protected static FileWriter log = null;
	
	public static void initOutput() 
	{
		try     //create the file 
		{
		logFile.createNewFile();} 
		catch (IOException e) 
		{e.printStackTrace();}

		try  //FileWriter objects
		{
		log = new FileWriter(logFile);} 
		catch (IOException e) 
		{e.printStackTrace();}
	}
	
	protected static void closeLog()
	{
		try 
		{log.close();}
		catch (IOException e) 
		{e.printStackTrace();}
	}

	protected static String checkInfi(String value)
	{
		if(Float.parseFloat(value) == (Integer.MAX_VALUE))
		{
			value = "Infinity";
			return value;
		}
		else if(Float.parseFloat(value) == (-(Integer.MAX_VALUE)))
		{
			value = "-Infinity";
			return value;

		}
		else
		{
			return value;
		}
	}

	public static void greedyLog(String nodeName,int nodeDepth,String nodeBestValue)
	{
		{
			try 
			{
				OutputAndLogShow.log.write(nodeName+","+nodeDepth+","+nodeBestValue+"\r\n");
			} 
			catch (IOException e) 
			{e.printStackTrace();}
		}
	}
	
	protected static void miniMaxLog(String nodeName,int nodeDepth,String nodeBestValue)
	{
		{
			try 
			{
				OutputAndLogShow.log.write(nodeName+","+nodeDepth+","+nodeBestValue+"\r\n");
			} 
			catch (IOException e) 
			{e.printStackTrace();}
		}
	}
	
	public static void alphaBetaLog(String nodeName,int nodeDepth,String nodeBestValue,String nodeAlpha,String nodeBeta) {
		try 
		{
			nodeBestValue = OutputAndLogShow.checkInfi(nodeBestValue);
			nodeAlpha = OutputAndLogShow.checkInfi(nodeAlpha);
			nodeBeta = OutputAndLogShow.checkInfi(nodeBeta);
			
			OutputAndLogShow.log.write(nodeName+","+nodeDepth+","+nodeBestValue+","+nodeAlpha+","+nodeBeta+"\n");
		} 
		catch (IOException e) 
		{e.printStackTrace();}
	}
}
