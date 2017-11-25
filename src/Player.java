import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private double money;
	private double betTotal;
	private int totalValue;
	//Keeps track of whether the player has busted
	private boolean bust = false;
	//Keeps track of how many aces are in the hand
	private int aceNum;

	
	//Makes a new deck for this player
	public Deck deck1 = new Deck();
	
	/**
	 * Creates a player with a custom $ input
	 */
	public Player(double moneyIn)
	{				
		
		money = moneyIn;
		
		initPlayer();
	}
	
	public void initPlayer()
	{
		hand.clear();
		drawCard();
		if(hand.get(0).getAce())
		{
			aceNum++;
		}
		drawCard();
		if(hand.get(1).getAce())
		{
			aceNum++;
		}
	}

	/**
	 * Gets a card from the deck and adds it to the hand
	 */
	public void drawCard()
	{
		hand.add(deck1.getCard());
		
		makePlayerValue();
	}
	
	public void makePlayerValue()
	{
		//Makes sure that the bust defaults to false and aceNum defaults to 0
		bust=false;
		aceNum = 0;
		
		totalValue=0;
		
		for(int i = 0; i < hand.size(); i++)
		{
			totalValue += hand.get(i).getValue();	
			
			if(hand.get(i).getAce())
			{
				aceNum++;
			}
		}
		
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
	
	/**
	 * Get the final value of the player's cards
	 * @return
	 */
	public int getPlayerValue()
	{		
		makePlayerValue();
		
		return totalValue;
	}
	
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	public boolean getBust()
	{
		return bust;
	}
	
	
	/*
	 * BETTING BELOW
	 */
	
	/**
	 * Adds the initial money to the card
	 */
	public void addStartingMoney(double moneyIn)
	{
		money = moneyIn;
	}
	
	/**
	 * Allows the player to bet money
	 */
	public void bet(double betIn)
	{
		betTotal = betIn;
		//Subtracts money so it can be added back later if win
		money = money - betIn;
	}
	
	/**
	 * Gives the current amount of money on the table
	 * @return
	 */
	public double getBet()
	{
		return betTotal;
	}
	
	/**
	 * At the end of the turn, combines the funds
	 * Also sets the current bet value to 0
	 * 
	 * In a win, adds 2 times the bet
	 */
	public void combineFundsWin()
	{
		money += 2*betTotal;
	}
	
	/**
	 * At the end of the turn, combines the funds
	 * Also sets the current bet value to 0
	 * 
	 * In a blackjack, player recieves 1.5 times their bet
	 */
	public void combineFundsBlackjack()
	{
		money += 2.5*betTotal;
	}
	
	/**
	 * At the end of the turn, combines the funds
	 * Also sets the current bet value to 0
	 * 
	 * In a tie, adds the bet back
	 */
	public void combineFundsPush()
	{
		money += betTotal;
	}
	
	/**
	 * At the end of the turn, combines the funds
	 * Also sets the current bet value to 0
	 * 
	 * In a loss, player does not recieve any money
	 */
	public void combineFundsLose()
	{
	}
	
	/**
	 * Gives the amount of money left 
	 * @return
	 */
	public double getMoney()
	{
		return money;
	}
	
	 public String toString()
	 {
		 String handString = "You have $" +String.valueOf(money) + "and \n";
		 
		 for(int i=0; i<hand.size(); i++)
		 {
			 handString += "a " + hand.get(i).toString() + "\n";
		 }
		 
		 return handString;
	 }

}
