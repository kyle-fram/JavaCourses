import java.util.ArrayList;


public class Card {

	private final String[] SUITS = {"Clubs", "Hearts", "Diamonds", "Spades"};
	private final int VALUES = 13;
	
	//defines values for face cards
	private final int KING = 13;
	private final int QUEEN = 12;
	private final int JACK = 11;
	private final int ACE = 1;
	private int cardNum;
	private String suit;
	private int num;
	private int value;
	private boolean isAce;
	
	public Card(int num)
	{
		cardNum = num;
	}
	
	public String getSuit()
	{
		suit = SUITS[(cardNum/VALUES)];
		
		return suit;
	}
	
	public int getValue()
	{
		value = 1+(cardNum%VALUES);
		
		if(value == 11 || value == 12 || value == 13)
		{
			value = 10;
		}
		if(value == 1)
		{
			value = 11;
		}
		
		return value;
	}
	
	public boolean getAce()
	{
		if(this.getNum() == ACE)
		{
			isAce= true;
		}
		else
		{
			isAce= false;
		}
		return isAce;
	}
	
	public int getNum()
	{
		num = 1+(cardNum%VALUES);
		
		return num;
	}
	
	public String toString()
	{
		String cardValue = String.valueOf(this.getNum());
		
		//Checks if any card is a face card
		if(this.getNum()==KING)
			cardValue = "King";
		if(this.getNum()==QUEEN)
			cardValue = "Queen";
		if(this.getNum()==JACK)
			cardValue = "Jack";
		if(this.getNum()==ACE)
			cardValue = "Ace";
		
		return cardValue+" of " + this.getSuit();
	}
	
}
