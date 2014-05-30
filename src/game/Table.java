package game;

import java.util.ArrayList;

public class Table
{
  
  private ArrayList<Card> table;
  
  public Table()
  {
    table = new ArrayList<Card>();
  }
  
  public void add(Card card)
  {	  
	  table.add(0, card);
  }
  
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

  public int numCards()
  {  
	  return table.size();
  }
  
  public Card getCard(int index)
  {
	  return table.get(index);
  }
  
  public int numSets()
  {    
    int count = 0;
    
    int index1 = 0;
    int index2 = 1;
    int index3 = 2;
    
    Card n1 = null;
    
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

          
          if(Card.isSet(n1, n2, n3))
            count++;
          
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
    
    return count;
  }
        
      
}