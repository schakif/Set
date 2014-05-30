/**
 * This class makes an ArrayList of Cards.
 */

package game;

import java.util.ArrayList;
import java.util.LinkedList;

public class Table
{
  
  private ArrayList<Card> table;
  
  public Table()
  {
    table = new ArrayList<Card>();
  }
  
  /**
   * This method adds a Card to the table.
   * @param card that will be added: card
   */
  
  public void add(Card card)
  {	  
	  table.add(0, card);
  }
  
  /**
   * This method take three cards that are a set as parameters, and removes those cards from the table. It checks to make sure that 
   * the cards are in a set.  If they aren't, it returns early.
   * @param first card in set: c1
   * @param second card in set: c2
   * @param third card in set:  c3
   */
  public void removeSet(Card c1, Card c2, Card c3)
  {
    if(!table.contains(c1) || !table.contains(c2) || !table.contains(c3))
      return;
    if(!Card.isSet(c1, c2, c3))
      return;
    
    table.remove(c1);
    table.remove(c2);
    table.remove(c3);
  }  
  
  /**
   * This is a query method that returns the number of cards on the table.
   * @return
   */

  public int numCards()
  {  
	  return table.size();
  }
  
  /**
   * This method returns a card at a specific index of the table.
   * @param the index of the card that needs to be removed
   * @return
   */
  
  public Card getCard(int index)
  {
	  return table.get(index);
  }
  
  /**
   * This method returns a LinkedList of arrays of sets.  It will return all of the sets on the table.
   * @return
   */
  
  public LinkedList<Set> returnSets()
  {        
    int index1 = 0;
    int index2 = 1;
    int index3 = 2;
    
    Card n1 = null;
    
    LinkedList<Set> setList = new LinkedList<Set>();
    
    if (table.size() > 0) {
    	n1 = table.get(index1);
    }
    
    while(index1 < table.size()-2)
    {
      Card n2 = table.get(index2);
      
      while(index2 < table.size()-1)
      {
        Card n3 = table.get(index3);
        
        while(index3 < table.size())
        {
          
          if(Card.isSet(n1, n2, n3)){
            
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
  
  /**
   * This is a query method that returns the number of sets on a given table.
   * @return
   */
  
  public int numSets(){
       LinkedList<Set> l1 = returnSets();
       return l1.size();
       
  }
  
}