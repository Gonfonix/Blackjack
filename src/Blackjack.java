import java.util.Scanner;
//import java.awt.event.KeyEvent;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PittProject3 {

	ArrayList<Card> hand;
	private static ArrayList<Card> printHand;

	private static int handvalue = 0;
	private static int handvalue2 = handvalue;
	private static boolean dealerBusted = true;
	private static boolean showFirstCard = true;
	private Card[] aHand;
	private static int AceCounter;
	private static int AceCounter2 = AceCounter;
	private static int dealerhandvalue = 0;
	private static String resetDealer;
	private static int cash = 0;
	private static int bet;
	private static int x = 0;
	private static String name = "";
	private static boolean run;
	private static boolean runBet;
	private static boolean askName = true;
	//private static KeyEvent catchException;

	public PittProject3(Deck deck) {
		hand = new ArrayList<>();
		aHand = new Card[]{};
		int AceCounter=0;
		for(int i = 0; i < 2; i++) {
			hand.add(deck.drawCard());
		}
		aHand = hand.toArray(aHand);
		for(int i = 0; i < aHand.length; i++)
		{
			handvalue += aHand[i].getValue();
			if(aHand[i].getValue() == 11)
			{
				AceCounter ++;
			}
			while(AceCounter > 0 && handvalue > 21)
			{
				handvalue -= 10;
				AceCounter --;
			}
		}
	}

	public void showFirstCard() {
		Card[] firstCard = new Card[]{};
		firstCard = hand.toArray(firstCard);
		System.out.println("["+firstCard[0]+"]");
		dealerhandvalue = firstCard[0].getValue();
	}

	public void Hit(Deck deck) {
		hand.add(deck.drawCard());
		aHand = hand.toArray(aHand);
		handvalue = 0;
		for (int i = 0; i < aHand.length; i++) {
			handvalue += aHand[i].getValue();
			if(aHand[i].getValue() == 11) {
				AceCounter++;
			}
			while (AceCounter > 0 && handvalue > 21) {
				handvalue -= 10;
				AceCounter --;
			}
		}
	}

	public boolean wantsToHit() {
		if (handvalue < 17) {
			return true;
		}
		return false;
	}

	public void showHand() {
		System.out.println(hand);
	}

	public static int getHandValue() {
		return handvalue;
	}

	/*public boolean busted(int handvalue) {
		if (handvalue > 21)
		{
			return true;
		}
		return false;
	} */

	public int takeTurn(Deck deck) {
		while(wantsToHit()) {
			Hit(deck);
			/*if (dealerBusted) {
				break;
			} */
		}
		if (handvalue <= 21) {
		}
		return handvalue;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Scanner money = new Scanner(System.in);

		while (askName == true) {
			System.out.println("Welcome to Mr. Gill's Casino! Name please?");
			name = scan.nextLine();
			askName = false;
		}
		if (name.equalsIgnoreCase("Gill")) {
			System.out.println("");
			System.out.println("EASTER EGG: You have found the name Easter egg!");
			System.out.println("");
			System.out.println("You have been given $1,500 cash instead of just $1,000!");
			System.out.println("");
			cash = 1500;
		}
		if (cash == 0) {
			cash = 1000;
			System.out.println(name + " has $1000");
			System.out.println();
		}

		while(cash > 0) {


			Scanner bet2 = new Scanner(System.in);
			Deck deck = new Deck();
			deck.shuffle();
			AceCounter = 0;
			PittProject3 dealer = new PittProject3(deck);
			List<Card> hand = new ArrayList<>();
			hand.add(deck.drawCard());
			hand.add(deck.drawCard());
			String catchEnter = "";
			if (bet == 0) {
				bet = 25;
				System.out.println("You have $" + cash + ". " + "Bet? (0 to quit. Hit the 'Enter Key' to bet $" + bet + ")");
				catchEnter = bet2.nextLine();
			}
			else {
				System.out.println("You have $" + cash + ". " + "Bet? (0 to quit. Hit the 'Enter Key' to bet your last bet of $" + bet + ")");
				catchEnter = bet2.nextLine();
			}
			runBet = true;
			System.out.println();

			if (catchEnter.length() > 0) {
				while (runBet) {
					try {
						bet = Integer.parseInt(catchEnter);
						runBet = false;
					}
					catch (NumberFormatException e) {
						System.out.println("Error: Please enter a number; not a letter / word");
						System.out.println();
						catchEnter = bet2.nextLine();
						System.out.println();
					}
				}

				if (catchEnter.equals("0")) {
					if (cash > 0) {
						System.out.println("You have won against the dealer with $" + cash + " to spare! The dealer has lost!");
					}
					break;
				}
			}

			while (bet > cash || bet == 0) {
				System.out.println();
				if (bet == 0) {
					System.out.println("Error: $0 is NOT a bet!");
				}
				else {
					System.out.println("Error: You cannot bet more cash than you have");
				}
				System.out.println();
				System.out.println("You have $" + cash);
				System.out.println();
				System.out.println("Bet?");
				System.out.println();
				bet = Bet(cash);
			}

			/*if (cash < 25) {
				System.out.println();
				System.out.println("You have under 25 dollars left. You have been drawn extra money on the table so your bet is equivelant to $25.");
				bet = 25;
				System.out.println();
			} */

			System.out.println("Bet: $" + bet);
			System.out.println("Dealer's hand: ");
			dealer.showFirstCard();
			System.out.println("Value: " + dealerhandvalue);
			System.out.println(name + "'s hand: ");
			System.out.println(hand);
			int handvalue = calcHandValue(hand);
			System.out.println("Value: " + handvalue);

			/*if(hasBlackJack(handvalue) && dealer.hasBlackJack())
	        {
	            Push();
	        } */

			System.out.println("Move? (hit/stay)");
			Scanner hitorstand = new Scanner(System.in);
			String hitter = hitorstand.nextLine();
			if (!isHitorStand(hitter)) {
				System.out.println("Please enter 'hit' or 'stay'");
				hitter = hitorstand.nextLine();
			}
			while (hitter.equalsIgnoreCase("hit") || hitter.equalsIgnoreCase("stay")) {
				if (hitter.equalsIgnoreCase("hit")) {
					int deal = 0;
					Hit(deck, hand);
					System.out.println("Bet: $" + bet);
					System.out.println("Dealer's hand: ");
					if (showFirstCard == true) {
						dealer.showFirstCard();
						System.out.println("Value: " + dealerhandvalue);
						deal = dealerhandvalue;
					}
					else {
						dealer.showHand();

						System.out.println("Value: " + getHandValue());
						deal = getHandValue();
					}
					System.out.println(name + "'s hand:");
					System.out.println(hand);
					handvalue = calcHandValue(hand);
					System.out.println("Value: " + handvalue);

					int you = handvalue;
					if (you == deal)
					{
						Push();
						hitter = "";
						break;
					}

					if (handvalue > 21) {
						Lose();
						break;
					}
					else {

						System.out.println("Move? (hit/stay)");
						hitter = hitorstand.nextLine();
					}

				}
				if (hitter.equalsIgnoreCase("stay")) {
					showFirstCard = false;
					int dealerhand = getHandValue();
					System.out.println("");
					System.out.println("Bet: $" + bet);
					System.out.println("Dealer's hand: ");
					/*if (dealerhand > 21) {
						dealer.showFirstCard();
						System.out.println("Value: " + dealerhandvalue);
					} */
					//else {
					dealer.showHand();

					System.out.println("Value: " + dealerhand);
					//}
					System.out.println(name + "'s hand:");
					System.out.println(hand);
					System.out.println("Value: " + handvalue);

					int you = handvalue;
					int deal = dealerhand;
					if (you == deal)
					{
						Push();
						hitter = "";
						break;
					}

					if (dealerhand > 21) {
						Win();
						showFirstCard = true;
						break;
					}

					else {
						if (dealerhand >= 17) {
							System.out.println();
							System.out.println("The dealer stands");
							System.out.println();
							hitter = "hit";

						}
						else {
							System.out.println("Move? (hit/stay)");
							hitter = hitorstand.nextLine();
						}

					}
				}
			}




			if(cash == 0) {
				System.out.println("You ran out of cash. The dealer has won; you have lost!");
			}

		}

		while(cash == 0) {
			Scanner runAgain = new Scanner(System.in);
			String str = "";
			System.out.println("Game over! Do you want to play again (Yes or No)?");
			str = runAgain.nextLine();
			if (str.equalsIgnoreCase("Yes")) {
				run = true;
				askName = false;
				main(args);
			}
			else if (str.equalsIgnoreCase("No")) {
				run = false;
				break;
			}
		}
	}

	public static boolean hasBlackJack(int handValue) {
		if(handValue == 21) {
			return true;
		}
		return false;
	}

	public static int calcHandValue(List<Card> hand) {
		Card[] aHand = new Card[]{};
		aHand = hand.toArray(aHand);
		int handvalue = 0;
		for(int i = 0; i < aHand.length; i++) {
			handvalue += aHand[i].getValue();
			if(aHand[i].getValue() == 11) {
				AceCounter++;
			}
			while(AceCounter > 0 && handvalue > 21) {
				handvalue-=10;
				AceCounter--;
			}
		}
		return handvalue;
	}

	public static int Bet(int cash) {
		Scanner sc = new Scanner(System.in);
		int bet = sc.nextInt();
		while(bet > cash || bet == 0) {
			System.out.println();
			if (bet == 0) {
				System.out.println("Error: $0 is NOT a bet!");
			}
			else {
				System.out.println("Error: You cannot bet more cash than you have");
			}
			System.out.println();
			System.out.println("You have $" + cash);
			System.out.println();
			System.out.println("Bet?");
			System.out.println();
			bet=sc.nextInt();
		}
		return bet;
	}

	public static void Hit(Deck deck, List<Card> hand) {
		hand.add(deck.drawCard());
		Card[] aHand = new Card[]{};
		aHand = hand.toArray(aHand);
		handvalue = 0;
		for(int i=0; i<aHand.length; i++) {
			handvalue += aHand[i].getValue();
			if(aHand[i].getValue()==11) {
				AceCounter++;
			}
			while(AceCounter > 0 && handvalue > 21) {
				handvalue -= 10;
				AceCounter--;
			}
		}
	}

	public static boolean isHitorStand(String hitter) {
		if(hitter.equalsIgnoreCase("hit") || hitter.equalsIgnoreCase("stay")) {
			return true;
		}
		return false;
	}

	public static boolean Win() {
		System.out.println("Dealer bust");
		cash = cash + bet;
		System.out.println(name + " has $" + cash);
		System.out.println();
		return dealerBusted = true;
	}

	public static boolean Lose() {
		System.out.println(name + " bust");
		cash = cash - bet;
		System.out.println(name + " has $" + cash);
		System.out.println();
		return true;
	}

	public static void Push() {
		System.out.println("");
		System.out.println("Push (tie): You have recieved your money back");
		System.out.println();
	}

	/*public static boolean checkBust(int handvalue) {
	if(handvalue>21)
	{
		System.out.println("You have busted!");
		return true;
	}
	return false;
} */

}
