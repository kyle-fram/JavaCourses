import java.io.IOException;
import java.util.Scanner;

public class WordListTest {

	/**
	 * Main method to run the program
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		//Input scanner for user inputs
		Scanner input = new Scanner(System.in);
		
		try
		{
			//Creates a wordLists object for the dictionary file
			WordLists dictionary = new WordLists(args[0]);
			
			/*
			 * All of these take user input for the specifications for 
			 * word characteristics
			 */
			System.out.println("Input the length of the"
					+ " word you want to test \n [NUMBER]");
			
			String len = input.nextLine();
			
			System.out.println("Input the character at the start of "
					+ "the string that you want to check for "
					+ "\n [LOWERCASE CHARACTER]");
			
			char st = input.nextLine().charAt(0);
			
			System.out.println("Input the character anywhere"
					+ " besides the start of the string "
					+ "that you want to check for"
					+ "\n [LOWERCASE CHARACTER]");
			
			char in = input.nextLine().charAt(0);
			
			System.out.println("Input how many vowels that"
					+ " you want to check for\n [NUMBER]");
			
			String vow = input.nextLine();
			
			System.out.println("Input how many of a letter you "
					+ "want to check for\n [NUMBER]");
			
			String num = input.nextLine();
			
			System.out.println("Input the letter "
					+ "that you want to check for"
					+ "\n [LOWERCASE CHARACTER]");
			
			char mult = input.nextLine().charAt(0);
						
			/*
			 * Runs all the necessary methods
			 */
			dictionary.length(Integer.valueOf(len));
			dictionary.startsWith(Integer.valueOf(len), st);
			dictionary.containsLetter(Integer.valueOf(len), in);
			dictionary.vowelHeavy(Integer.valueOf(len),Integer.valueOf(vow));
			dictionary.multiLetter(Integer.valueOf(num), mult);

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

