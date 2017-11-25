import java.util.ArrayList;

public class Dealer {
	
	public ArrayList<Card> hand = new ArrayList<Card>();
	int totalValue;
	boolean bust = false;

	//Makes a new deck for the dealer
	public Deck deck1 = new Deck();
	
	//Keeps track of how many aces are in the hand
	private int aceNum;
	
	public Dealer()
	{
		initDealer();
	}
	
	public void initDealer()
	{
		hand.clear();
		hand.add(deck1.getCard());
		hand.add(deck1.getCard());
		fillHand();
	}
	
	/**
	 * Fills the hand with cards until it is greater than 17
	 */
	public void fillHand()
	{
		//Makes sure that the bust defaults to false and aceNum defaults to 0
		bust=false;
		aceNum = 0;

		//These two check if the initial given cards are aces
		if(hand.get(0).getAce())
		{
			aceNum++;
		}
		if(hand.get(1).getAce())
		{
			aceNum++;
		}
		
		//Starts on the third card as the first 2 are made already
		int count=2;
		
		totalValue = hand.get(0).getValue() + hand.get(1).getValue();
		
		while(totalValue < 17)
		{
			hand.add(deck1.getCard());
			
			//Now checks if each new card is an ace
			if(hand.get(count).getAce())
			{
				aceNum++;
			}
			
			totalValue+= hand.get(count).getValue();
			count++;
			
			/*
			 *Establishes ace functionality
			 */
			
			while(totalValue > 21 && totalValue > 10 && aceNum >= 1)
			{
				totalValue -= 10;
				aceNum--;
			}
			
			if(aceNum==0 && totalValue > 21)
			{
				bust=true;
			}	
		}
	}
	
	public int getDealerValue()
	{
		return totalValue;
	}
	
	public String getTopCard()
	{
		return hand.get(0).toString();
	}
	
	public String toString()
	 {
		 String handString = hand.get(0).toString();
		 
		 return handString;
	 }
	
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	public boolean getBust()
	{
		return bust;
	}
	
	
	
}
