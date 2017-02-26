public class Card {

	private int rank;
	private int suit;
	private int value;
	private static String[] ranks = {"Joker","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private static String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
	
	public static void main(String[] args) {
		
	}

	public Card(int suit, int values) {
		this.rank = values;
		this.suit = suit;
	}

	public String toString() {
		return ranks[rank] + " " + suits[suit];
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		if(rank > 10) {
			value = 10;
		}
		else if(rank == 1) {
			value = 11;
		}
		else {
			value = rank;
		}
		return value;
	}

}