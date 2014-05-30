package game;

import java.util.ArrayList;

public class Game
{
  private Table t;
  private Deck d;
  
  public Game()
  {
    t = new Table();
    d = new Deck();
    
    // There are always 81 cards in a new Deck, so this can't fail
    for(int i = 0; i < 12; i++)
      t.add(d.getNext());
  }

  public Game(String filename)
  {
    t = new Table();
    d = new Deck(filename);
    
    // There might not be 12 cards
    while(d.hasNext() && t.numCards() < 12)
      t.add(d.getNext());
  }
  
  public int numSets()
  {
    return t.numSets();
  }
  
  public int numCards()
  {
    return t.numCards();
  }
  
  public boolean isGameOver()
  {
    if(d.hasNext())
      return false;
    
    // if we get here, there are no more cards in the deck
    // the game continues until there are no sets on the table
    return t.numSets() == 0;
  }
  
  public void playRound()
  {
    // If the game is over, they shouldn't have called this...
    if(isGameOver())
      return;

    // If there are no sets, we have to add cards
    if(t.numSets() == 0)
    {
      // There must be at least one card
      t.add(d.getNext());
      
      // If we have a custom deck, it might not have a multiple
      // of three cards.
      if(d.hasNext())
        t.add(d.getNext());
      if(d.hasNext())
        t.add(d.getNext());
      return;
    }
    
    // There is a set on the table...
    for(int i = 0; i < t.numCards(); i++)
    {
      for(int j = i + 1; j < t.numCards(); j++)
      {
        for(int k = j + 1; k < t.numCards(); k++)
        {
          Card c1 = t.getCard(i);
          Card c2 = t.getCard(j);
          Card c3 = t.getCard(k);
          
          if(Card.isSet(c1, c2, c3))
          {
        	ArrayList<Card> nextCards = new ArrayList<Card>(3);
        	for(int goddamntoomanyloops=0; goddamntoomanyloops<3;goddamntoomanyloops++){
        		if(d.hasNext()){
        			nextCards.add(d.getNext());
        		}
        	}
        	
            nextCards = t.removeSet(c1, c2, c3,nextCards);
            for(@SuppressWarnings("unused") Card c: nextCards){
            	d.backUp();
            }
            return;
          }
        }
      }
    }
  }
}