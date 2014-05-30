package game;

/**
 * This class is meant to be able to make a containment of a set on a table, as well as print out the set itself. It checks to make sure 
 * the 3 cards are a set, and then puts and returns them in a array of 3 indices as a collection of the 3 cards. 
 */

public class Set {
	
	Card c1;
	Card c2;
	Card c3;
	Card[] cards = new Card[3];
	
	public Set(Card c1, Card c2, Card c3) {
		
		// check to make sure it is a set
		// Card.isSet(c1,c2,c3)
		// if it is not a set throw an exception
		
		if (!Card.isSet(c1,c2, c3)) {
			throw new RuntimeException("Bad Set");
		}
		
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		
		cards[0] = this.c1;
		cards[1] = this.c2;
		cards[2] = this.c3;
	}
	
	public Card[] returnSet() {
		return cards;
	}
	
	public String toString() {
		return c1 + " " + c2 + " " + c3;
	}

}
