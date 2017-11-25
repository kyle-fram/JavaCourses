import java.lang.Math;
import java.util.ArrayList;

public class Deck {
	
	private static final int INTCARDS = 52;
	private static ArrayList<Card> deckTemp = new ArrayList<Card>();
	//deck is public so cards can be added back when the hand is done
	public static ArrayList<Card> deck = new ArrayList<Card>();
	private static Card topCard;

	
	public Deck()
	{
		initDeck();
		shuffle();
	}
	
	/**
	 * Creates a new deck of cards in ascending order
	 */
	public void initDeck()
	{
		deck.clear();
		/*
		 * Makes an array of numbers counting up to the 
		 * number of cards in the deck
		 */
		for(int i=0; i<INTCARDS; i++)
		{
			//removes any existing values in deck
			deck.add(new Card(i));
		}
	}

	/**
	 * Shuffles a deck by randomly assigning each current value
	 * to a cell of sequentially ordered numbers
	 * and assigns it to a temporary array
	 * Then, makes new deck = to temporary array 
	 */
	public void shuffle()
	{	
		ArrayList<Integer> random = new ArrayList<Integer>();
		
		/*
		 * Makes an array of numbers counting up to the 
		 * number of cards in the deck
		 */
		for(int i=0; i<deck.size(); i++)
		{
			random.add(i);
		}
		
		//Makes sure that the temporary shuffle deck is empty
		deckTemp.clear();
		
		/*
		 * This while loop randomizes the existing cards in 
		 * the Random
		 * By removing the value from random, no repetitions occur
		 */
		while(random.size()>0)
		{
			int iter = (int) (Math.random()*random.size());
			deckTemp.add(new Card(random.get(iter)));
			random.remove(iter);			
		}
		//Sets the deck equal to the temporary deck
		deck = deckTemp;				
	}
	
	/**
	 * Gets the top card and takes it out of the deck
	 * Useful for drawing a card
	 * @return
	 */
	public Card getCard()
	{
		topCard = deck.get(0);
		deck.remove(0);		
		return topCard;
	}
	
	/**
	 * Gets a specified card and takes it out of the deck
	 * @param cardIn
	 * @return
	 */
	public Card getCard(int cardIn)
	{
		topCard = deck.get(cardIn);
		deck.remove(cardIn);		
		return topCard;
	}
	
	public void addCards(ArrayList<Card> cardIn)
	{
		deck.addAll(cardIn);
	}
	
	public void addCard(Card cardIn)
	{
		deck.add(cardIn);
	}
	
	public int getINTCARDS()
	{
		return INTCARDS;
	}
	
	public ArrayList<Card> getDeck()
	{
		return deck;
	}
	
}
