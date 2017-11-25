import java.io.*;
import java.util.Scanner;

public class WordLists {
	
	private String[] words;
	int count = 0;
	
	/**
	 * Constructor, finds the length of the array and then puts 
	 * words in the array
	 * @param fileName
	 * @throws IOException
	 */
	public WordLists(String fileName) throws IOException
	{
		
			File inFile = new File(fileName);
			Scanner input = new Scanner(inFile);
			
			/*
			 *Finds needed length of array 
			 */
			while(input.hasNext())
			{
				  input.nextLine();
				  count++;
			}
						
			//Initializes array of words
			words = new String[count];
			
			input.close();
			input = new Scanner(inFile);
			
			for(int i = 0; i<count; i++)
			{
				words[i] = input.nextLine();
			}
			input.close();
		
	}
	
	/**
	 * returns an array of words of length n
	 * @param n
	 * @return
	 */
	public String[] length(int n)
	{

		String[] array = new String[count];
		
		try 
		{
			PrintWriter output = new PrintWriter("length.txt");
			
			int outputArrayLength=0;
			
			/*
			 * Goes through entire dictionary array
			 */
			for(int i=0; i<count; i++)
			{
				/*
				 * Checks if the length of the word is correct
				 *and adds it to the array if so
				 */
				if(words[i].length()==n)
				{
					array[outputArrayLength]=words[i];
					output.println(words[i]+"\n");
					outputArrayLength++;
				}
			}
			
			output.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("This should never trigger.");
		}
		return array;
	}
	
	/**
	 * returns an array of words of length n beginning 
	 * with the letter firstLetter
	 * @param n
	 * @param firstLetter
	 * @return
	 */
	public String[] startsWith(int n, char firstLetter)
	{
		
		String[] array = new String[count];
		
		try 
		{
			PrintWriter output = new PrintWriter("firstLetter.txt");
			
			int outputArrayLength=0;
			
			/*
			 * Goes through entire dictionary array
			 */
			for(int i=0; i<count; i++)
			{
				/*
				 * Checks if the length of the word is correct and 
				 * if the first letter is correct
				 */
				if(words[i].length()==n && words[i].charAt(0)==firstLetter)
				{
					array[outputArrayLength]=words[i];
					output.println(words[i]+"\n");
					outputArrayLength++;
				}
			}
			
			output.close();

		}
		catch (FileNotFoundException e) 
		{
			System.out.println("This should never trigger.");
		}
	
		return array;
	}
	
	/**
	 * returns an array of words of length n 
	 * containing the letter included but not beginning with it.
	 * @param n
	 * @param included
	 * @return
	 */
	public String[] containsLetter(int n, char included)
	{
		
		String[] array = new String[count];
		
		try 
		{
			PrintWriter output = new PrintWriter("containsLetter.txt");
			
			int outputArrayLength=0;
			
			boolean isIncluded = false;
			
			/*
			 * Goes through entire dictionary array
			 */
			for(int i=0; i<count; i++)
			{
				/*Goes through word and checks if the letter is there
				 * (leaves out the first letter, as per instructions)
				 */
				for(int j = 1; j< words[i].length(); j++)
				{
					if(words[i].charAt(j)==included)
					{
						isIncluded=true;
					}
				}
				
				/*
				 * Checks if the length of the word is correct and 
				 * if the letter is included
				 */
				if(words[i].length()==n && isIncluded)
				{
					array[outputArrayLength]=words[i];
					output.println(words[i]+"\n");
					outputArrayLength++;
				}
			}
			
			output.close();

		}
		catch (FileNotFoundException e) 
		{
			System.out.println("This should never trigger.");
		}
		

		return array;
	}
	
	/**
	 * returns an array of words of
	 *  length n containing at least m vowels
	 * @param n
	 * @param m
	 * @return
	 */
	public String[] vowelHeavy(int n, int m)
	{
		
		String[] array = new String[count];
		
		try 
		{
			PrintWriter output = new PrintWriter("vowelHeavy.txt");
			
			int outputArrayLength=0;
			int minVerifier=0;
			
			char letter;
			
			boolean isIncluded = false;
			
			/*
			 * Goes through entire dictionary array
			 */
			for(int i=0; i < count; i++)
			{
				//Goes through each letter in the word and checks for vowels
				for(int j = 0; j< words[i].length(); j++)
				{
					letter = words[i].charAt(j);
					
					//Checks if the letter is a vowel
					if(letter == 'a' || letter == 'e' || letter == 'i' ||
							letter == 'o' || letter == 'u')
					{
						minVerifier++;
					}
				}
				
				//Notes that the correct number of vowels is included
				if(minVerifier>=m)
				{
					isIncluded = true;
				}
				
				/*
				 * Checks if the length of the word is correct and 
				 * if the correct number of vowels is included
				 */
				if(words[i].length()==n && isIncluded )
				{
					array[outputArrayLength]=words[i];
					output.println(words[i]+"\n");
					outputArrayLength++;
				}
			}
			
			output.close();

		}
		catch (FileNotFoundException e) 
		{
			System.out.println("This should never trigger.");
		}
			
		return array;
	}
	
	/**
	 * returns an array of words with at least m occurrences 
	 * of the letter included
	 * @param m
	 * @param included
	 * @return
	 */
	public String[] multiLetter(int m, char included)
	{
		String[] array = new String[count];
		
		try 
		{
			PrintWriter output = new PrintWriter("multiLetter.txt");
			
			int outputArrayLength=0;
			int minVerifier=0;
			
			char letter;
			
			boolean isIncluded = false;
			
			/*
			 * Goes through entire dictionary array
			 */
			for(int i=0; i<count; i++)
			{
				/*
				 * Goes through the word and adds all letters equal to input
				 * letter to a counter
				 */
				for(int j = 0; j< words[i].length(); j++)
				{
					letter = words[i].charAt(j);
					
					//Increments count if it finds the correct letter
					if(letter == included)
					{
						minVerifier++;
					}
				}
				/*
				 * Checks if there are more instances of a letter than m
				 */
				if(minVerifier>=m)
				{
					isIncluded = true;
				}
				
				/*
				 * Checks if the number of letters is included and 
				 * sets an entry to the next array element and 
				 * increments array  position
				 */
				if(isIncluded)
				{
					array[outputArrayLength]=words[i];
					output.println(words[i]+"\n");
					outputArrayLength++;
				}
				
			}
			output.close();

		}
		catch (FileNotFoundException e) 
		{
			System.out.println("This should never trigger.");
		}
				
		return array;
	}

}
