import java.util.ArrayList;

/**
 * @author Rakesh Sharad Navale.
 * 		   (navale@usc.edu) | A.I. Assignment | Fall 2014.
 */
public class Position 
{
	int xpos, ypos;
	ArrayList<String> direction = new ArrayList<String>();
	
	protected Position(int xpos, int ypos)
	{
		this.xpos = xpos;
		this.ypos = ypos;
	}
}
