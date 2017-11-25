import java.io.IOException;
import java.util.Scanner;

/*Kyle Fram
*Problem2.java
*Dictionary
*2017-03-15
*/

public class Problem2 {

	public static void main(String[] args) 
	{
		
		//Input scanner for user inputs
		Scanner input = new Scanner(System.in);
		
		try
		{
			//Creates a wordLists object for the dictionary file
			WordLists dictionary = new WordLists(args[0]);
		} 
		/*
		 * Catches IOExceptions, 
		 * Then catches incorrect formats,
		 * Then catches anything else it may have found
		 */
		catch(IOException e)
		{
			System.out.println("Please try again "
					+ "with correct input file name");
		}
		catch(NumberFormatException | StringIndexOutOfBoundsException e)
		{
			System.out.println("Please try again "
					+ "with a reasonable answer");
		}
		catch(Exception e)
		{
			System.out.println("Please try again");
		}
		
		input.close();
	}
}

