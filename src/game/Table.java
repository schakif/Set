/**
 * This class makes an ArrayList of Cards.
 */

package game;

import java.util.ArrayList;
import java.util.LinkedList;

public class Table {

	private LinkedList<Card> table;

	public Table() {
		table = new LinkedList<Card>();
	}

	/**
	 * This method adds a Card to the table.
	 * 
	 * @param card
	 *            that will be added: card
	 */
	public void add(Card card) {
		table.addFirst(card);
	}

	/**
	 * Removes a set and refills the board if given new cards. Puts new cards
	 * into old cards place if cards are added, else slides old cards into new
	 * cards spot.
	 * 
	 * @param c1
	 *            The first card in the set.
	 * @param c2
	 *            The second card in the set.
	 * @param c3
	 *            The third card in the set.
	 * @param newCards
	 *            The new cards to be added into the deck, in the old cards
	 *            place. Can be null.
	 * @return The leftover cards from newCards, all of the cards not added onto
	 *         the table.
	 */
	public ArrayList<Card> removeSet(Card c1, Card c2, Card c3,
			ArrayList<Card> newCards) {
		if (newCards == null) {// prevent NullPointerException
			newCards = new ArrayList<Card>();
		}
		if (!table.contains(c1) || !table.contains(c2) || !table.contains(c3))
			return newCards;
		if (!Card.isSet(c1, c2, c3))
			return newCards;
		int[] indexes = new int[3];
		indexes[0] = table.indexOf(c1);
		indexes[1] = table.indexOf(c2);
		indexes[2] = table.indexOf(c3);

		table.set(indexes[0], null);
		table.set(indexes[1], null);
		table.set(indexes[2], null);

		// If there are 12 or more cards on the table,
		// we do not add cards this round.
		// we also do not add cards if we cannot replace the entire set
		if (table.size() < 15) {
			// there may or may not be cards left in the deck
			if (!newCards.isEmpty())
				table.set(indexes[0], newCards.remove(0));

			if (!newCards.isEmpty())
				table.set(indexes[1], newCards.remove(0));

			if (!newCards.isEmpty())
				table.set(indexes[2], newCards.remove(0));
		}
		slideDownCards();// always make sure we are slid down
		return newCards;
	}

	private void slideDownCards() {
		// put the last card in the first empty space
		for (int j = 0; j < table.size() - 1; j++) {
			if (table.get(j) == null) {
				table.set(j, table.removeLast());
				j--;// make sure that space isn't still null
			}
		}
		if (table.getLast() == null) {
			table.removeLast();
		}
	}

	/**
	 * This is a query method that returns the number of cards on the table.
	 * 
	 * @return
	 */
	public int numCards() {
		return table.size();
	}

	/**
	 * This method returns a card at a specific index of the table.
	 * 
	 * @param the
	 *            index of the card that needs to be removed
	 * @return
	 */

	public Card getCard(int index) {
		return table.get(index);
	}

	/**
	 * This is a query method that returns the number of sets on a given table.
	 * 
	 * @return
	 */

	public int numSets() {
		LinkedList<Set> l1 = returnSets();
		return l1.size();

	}

	/**
	 * This method returns a LinkedList of arrays of sets. It will return all of
	 * the sets on the table.
	 * 
	 * @return
	 */

	public LinkedList<Set> returnSets() {
		int index1 = 0;
		int index2 = 1;
		int index3 = 2;

		Card n1 = null;

		LinkedList<Set> setList = new LinkedList<Set>();

		if (table.size() > 0) {
			n1 = table.get(index1);
		}

		while (index1 < table.size() - 2) {
			Card n2 = table.get(index2);

			while (index2 < table.size() - 1) {
				Card n3 = table.get(index3);

				while (index3 < table.size()) {

					if (Card.isSet(n1, n2, n3)) {

						Set s = new Set(n1, n2, n3);
						setList.add(s);

					}

					index3++;
					if (index3 < table.size()) {
						n3 = table.get(index3);
					}

				}

				index2++;
				if (index2 < table.size()) {
					n2 = table.get(index2);
				}

				index3 = index2 + 1;
			}

			index1++;
			if (index1 < table.size()) {
				n1 = table.get(index1);
			}

			index2 = index1 + 1;
			index3 = index2 + 1;

		}

		return setList;
	}
}