import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*Kyle Fram
*Glossary.java
*Dictionary
*2017-03-15
*/

public class Glossary {
	
	private AVLTree tree = new AVLTree();
	private ArrayList<String> words = new ArrayList<>();
	int count = 0;
	
	/**
	 * Constructor, finds the length of the array and then puts 
	 * words in the array
	 * @param fileName
	 * @throws IOException
	 */
	public Glossary(String fileName) throws IOException
	{
		
			File inFile = new File(fileName);
			Scanner input = new Scanner(inFile);
									
			input.close();
			input = new Scanner(inFile);
			
			for(int i = 0; i<count; i++)
			{
				words.add(input.nextLine());
			}
			input.close();
	}
	
	public void insert(ArrayList wordsIn)
	{
		
	}

}

