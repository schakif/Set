import junit.framework.TestCase;

public class DeckTest extends TestCase {
  
  public void testNewDeck() 
  {
    Deck d = new Deck();
    
    int quantitySum = 0;
    int colorSum = 0;
    int shadingSum = 0;
    int shapeSum = 0;
    
    Card theCards[] = new Card[81];
    int position = 0;
    
    while(d.hasNext())
    {
      Card c = d.getNext();
      
      theCards[position] = c;
      position++;
      
      quantitySum += c.getQuantity();
      colorSum += c.getColor();
      shadingSum += c.getShading();
      shapeSum += c.getShape();
    }
    
    assertEquals(0, quantitySum % 3);
    assertEquals(0, colorSum % 3);
    assertEquals(0, shadingSum % 3);
    assertEquals(0, shapeSum % 3);
    
    for(int p1 = 0; p1 < 81; p1++)
    {
      for(int p2 = p1 + 1; p2 < 81; p2++)
      {
        assertFalse(theCards[p1].equals(theCards[p2]));
      }
    }
  }
  
  public void testEmptyDeck()
  {
    Deck d = new Deck("empty.dat");
    
    assertFalse(d.hasNext());
  }
  
  public void testDeckFromFile()
  {
    Deck d = new Deck("deck.dat");
    
    assertTrue(d.hasNext());
    assertEquals(new Card(1, 1, 1, 1), d.getNext());
    
    assertTrue(d.hasNext());
    assertEquals(new Card(1, 1, 1, 2), d.getNext());
    
    assertTrue(d.hasNext());
    assertEquals(new Card(1, 1, 1, 3), d.getNext());
    
    assertFalse(d.hasNext());
  }
}
