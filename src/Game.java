import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
 * To make a multi-person game, must make a bunch of arraylists
 */

public class Game {
	
	//Makes a new deck for the game
	private Deck deck1 = new Deck();
	
	//Makes a discard pile
	private ArrayList<Card> discard = new ArrayList<Card>();

	//Makes player 1
	//If one wants multiple players, then make a loop
	// and array for players, modify code to then
	//Go in order
	public Player player1 ;
	private String playerHand = "Your hand is: \n \n";
	private boolean playerContinue = true;
	
	private double moneyIn;
	private double betIn;
	
	private final double MINMONEYSTART = 100.0;
	private final double MINBET = 10.0;
	private final double MAXBET = 1000.0;

	//Makes the Dealer		
	public Dealer dealer = new Dealer();
	private String dealerHandTop = "The dealer's top card is a ";
	
	//Bust Value
	private final int TWENTYONE = 21;
	//Number of cards left to shuffle
	private final int SHUFFLENUM = 40;
	//Initial number of cards in a deck
	private final int INTCARDS = deck1.getINTCARDS();
	
	//The scanner for inputs
	Scanner input = new Scanner(System.in);

	/**
	 * The constructor- does not immediately set anything
	 */
	public Game()
	{		
	}
	
	/**
	 * Runs through the order of the game
	 */
	public void play()
	{
		printStartOfGame();
		
		player1 = new Player(moneyIn);
				
		while(player1.getMoney() > 0 && playerContinue == true)
		{
			player1.initPlayer();
			
			dealer.initDealer();
			
			playerMove();
			
			dealerMove();
			
			endRound();
			
			continuePlayer();
		}
		
		endGame();
	}
	
	/**
	 * Recieves the buy-in
	 */
	private void printStartOfGame()
	{
		System.out.println("Enter how much money you want to start with");
		
		//Try-catch loop to make sure input is double 100-1000
		try
        {
			moneyIn = input.nextDouble();
			while(moneyIn < MINMONEYSTART)
			{
				System.out.println("This is not a valid input- please enter "
					+ "a number greater than " 
					+String.valueOf(MINMONEYSTART));
				
				moneyIn = input.nextDouble();

			}
				
        }
		catch(InputMismatchException exception)
        {
			System.out.println("This is not a valid input- please enter "
				+ "a number greater than " 
				+String.valueOf(MINMONEYSTART));        
        }
		
	}
	
	/**
	 * Recieves the bet and outputs the cards in hand, the dealer's cards
	 */
	private void printStartOfRound()
	{
		
		System.out.println(dealerHandTop +getDealerTopCard().toString());
		
		//Creates the string for the player's hand
		for(int i = 0; i<player1.getHand().size(); i++)
		{
			playerHand += player1.getHand().get(i).toString() +"\n" ;
		}
		
		System.out.println(playerHand);
		
		if(player1.getMoney()>= MINBET)
		{
		
			System.out.println("Enter how much money you want to bet \n you"
					+ " have $" + String.valueOf(player1.getMoney())
					+ " left.");
		
			//Try-catch loop to make sure bet is number 100-1000
			try
			{
				betIn = input.nextDouble();
				while(betIn < MINBET || betIn >MAXBET)
				{
					System.out.println("This is not a valid input- "
						+ "please enter "
						+ "a number between " +String.valueOf(MINBET)
						+ " and " + String.valueOf(MAXBET));
					
					//Converts the input to an increment of $1.00
					betIn = input.nextDouble()/1;
				}
						
			}
			catch(InputMismatchException exception)
			{
				System.out.println("This is not a valid input- please enter "
					+ "a number between " +String.valueOf(MINBET)
					+ " and " + String.valueOf(MAXBET));
			}		
			
			//Makes sure that the player has enough money to bet
			// If not, then automatically goes all in
			if(betIn>player1.getMoney())
			{
				player1.bet(player1.getMoney());
				System.out.println("You went all in because you "
						+ "bet more than the minimum bet left!");
			}
			else
			{	
			player1.bet(betIn);
			}
			
		}
		else
		{
			player1.bet(player1.getMoney());
			
			System.out.println("You went all in because you "
					+ "have less than the minimum bet left!");
		}
	}
	
	/**
	 * Has the player do its choices
	 */
	private void playerMove()
	{
		playerContinue = true;
		boolean drawCard = true;
		
		playerHand = "Your hand is: \n \n";
		
		printStartOfRound();
		
		while(drawCard == true)
		{
			
			System.out.println("Do you want to draw another card?"
					+ " If yes, type 'yes'");
			try
			{
				String drawCardIn = input.next();
			
				if(drawCardIn.equals("y") || drawCardIn.equals("yes")
					|| drawCardIn.equals("Yes"))
				{
					drawCard = true;
					drawCard();
					
					//If the card brings the player over 21, they bust
					if(player1.getBust()==true)
					{
						drawCard = false;
					}
				}
				else
				{
					drawCard = false;
				}
			}
			catch(InputMismatchException exception)
			{
				
			}
		}
		
	}

	/**
	 * Sees if the player wants to keep playing
	 */
	private void continuePlayer()
	{
		/*
		 * At the end of the playerMove class, asks whether player
		 * wants to play another round
		 */
		System.out.println("Do you want to continue? If yes, type 'yes'");
		
		String playerContinueIn = input.next();
		if(playerContinueIn.equals("y") || playerContinueIn.equals("yes")
				|| playerContinueIn.equals("Yes"))
		{
			playerContinue = true;
		}
		
		else
		{
			playerContinue = false;
		}
	}
	
	/**
	 * If the player chooses, it will draw another card
	 * @param draw
	 */
	private void drawCard()
	{
		player1.drawCard();
		
		playerHand += 
				player1.getHand().get(player1.getHand().size()-1).toString()
				+"\n";
		
		System.out.println(playerHand);
	}
	

	/**
	 * Has the player do its choices
	 */
	private void dealerMove()
	{
		
		String dealerHand = "The dealer's hand is:  \n" ;
		
		//Creates the string for the dealer's hand
		for(int i = 0; i<dealer.getHand().size(); i++)
		{
			
			dealerHand += dealer.getHand().get(i).toString() +"\n" ;
			
		}
		System.out.println(dealerHand);
		
		String dealerValue = "The dealer's total hand value is "
				+ String.valueOf(dealer.getDealerValue());
		
		System.out.println(dealerValue);
		
		String playerValue = "Your total hand value is "
				+String.valueOf(player1.getPlayerValue());
		
		System.out.println(playerValue);	
	}
	
	/**
	 * Gets the dealer's top card to show the player
	 * @return
	 */
	private Card getDealerTopCard()
	{		
		Card topCard = dealer.getHand().get(0);
		
		return topCard;
	}
	
	/**
	 * Puts the cards in a discard pile
	 */
	private void returnCards()
	{
		/*
		 * Both of these add the cards back into the deck
		 * Adds cards back at the end of each round
		 */
		for(int i = 0; i< player1.getHand().size(); i++)
		{
			discard.add(player1.getHand().get(i));

		}
		
		for(int i = 0; i< dealer.getHand().size(); i++)
		{
			discard.add(dealer.getHand().get(i));
			
		}
		
		/*
		 * If there are less than 12 cards in the deck,
		 * or in other words if the discard pile has 40 or more cards 
		 */
		
		System.out.println(String.valueOf(discard.size())+ "     " +String.valueOf(INTCARDS - SHUFFLENUM));

		
		//deck1.addCards(discard);
		// discard.clear();
		

		if(discard.size() > INTCARDS - SHUFFLENUM)
		{
			deck1.addCards(discard);
			discard.clear();
		}

				
		//Shuffles the deck at the end of the hand
		deck1.shuffle();
	}
	
	/**
	 * Ends the round
	 *  If the player doesn't bust, and the dealer doesn't bust
	 *  Then it does the normal round end calculation
	 */
	private void endRound()
	{
		if(player1.getBust()==true)
		{
			endRoundPlayerBust();
		}
		else
		{
			if(dealer.getBust()==true)
			{
				endRoundDealerBust();
			}
			else
			{
				endRoundCalculation();
			}
		}
		
		returnCards();
		
		System.out.println("There are " 
				+ String.valueOf(INTCARDS- discard.size())
				+ " cards left in the deck"); 
		
	}
	
	/**
	 * Ends the round traditionally with hand calculations
	 * Gives the player back different amounts in different cases
	 */
	private void endRoundCalculation()
	{
		player1.makePlayerValue();
		
		double compare = Double.compare(player1.getPlayerValue(),
				dealer.getDealerValue());
		
		if(compare < 0)
		{
			player1.combineFundsLose();
			System.out.println("You Lost!");
		}
		if(compare == 0)
		{
			player1.combineFundsPush();
			System.out.println("You Pushed!");

		}
		if(compare > 0)
		{
			if(player1.getHand().size()==2 
				&& player1.getPlayerValue()==TWENTYONE)
			{
				player1.combineFundsBlackjack();
				System.out.println("You got BlackJack!");
			}
			else
			{
				player1.combineFundsWin();
				System.out.println("You Won!");
			}

		}
			
	}
	
	/**
	 * If the player busts, return that you busted
	 */
	private void endRoundPlayerBust()
	{
		player1.combineFundsLose();
		System.out.println("You Lost- you busted!");
		
	}
	
	/**
	 * If the dealer busts, determines whether there was blackjack and ends
	 * the round
	 */
	private void endRoundDealerBust()
	{
		if(player1.getHand().size()==2 
				&& player1.getPlayerValue()==TWENTYONE)
		{
			player1.combineFundsBlackjack();
			System.out.println("You got BlackJack- the dealer busted!");
		}
		else
		{
			player1.combineFundsWin();
			System.out.println("You Won- the dealer busted!");

		}
		
	}
	
	/**
	 * When the game ends, says how much money you have
	 */
	private void endGame()
	{
		System.out.println("You ended this game with $" +player1.getMoney()
				+ "\n If you have a gambling problem please go to "
				+ "http://www.ncpgambling.org/");
	}
		

}
