import junit.framework.TestCase;

public class TableTest extends TestCase 
{
  public void testNewTable() 
  {
    Table t = new Table();
    
    assertEquals(0, t.numCards());
    assertEquals(0, t.numSets());

    // Removing a valid set form an empty table shouldn't
    // do anything.
    t.removeSet(new Card(1, 1, 1, 1),
             new Card(2, 2, 2, 2),
             new Card(3, 3, 3, 3));
    
    assertEquals(0, t.numCards());
    assertEquals(0, t.numSets());
  }
  
  public void testSmallTableNoSets()
  {
    Table t = makeTable("3cards0sets.dat");
    
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 1, 1, 2);
    Card c3 = new Card(2, 2, 2, 3);

    assertEquals(3, t.numCards());
    assertEquals(0, t.numSets());
    
    // Cards will be added in reverse
    assertEquals(c3, t.getCard(0));
    assertEquals(c2, t.getCard(1));
    assertEquals(c1, t.getCard(2));
  }
  
  public void testSmallTable1Set()
  {
    Deck d = new Deck("small.dat");
    Table t = new Table();
    
    while(d.hasNext())
      t.add(d.getNext());
    
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(2, 2, 2, 2);
    Card c3 = new Card(3, 3, 3, 3);
    
    // This will result in a middle remove, a head remove and a tail remove
    t.removeSet(c2, c3, c1);
    
    assertEquals(0, t.numCards());
    assertEquals(0, t.numSets());
  } 
  
  public void testSetNotOnTable()
  {
    Table t = makeTable("3cards0sets.dat");
    
    Card c1 = new Card(2, 2, 2, 2);
    Card c2 = new Card(2, 2, 2, 3);
    Card c3 = new Card(3, 3, 3, 1);

    // These cards form a set, but are not on the table
    t.removeSet(c1, c2, c3);
    
    assertEquals(3, t.numCards());
  }
  
  public void testNotSet()
  {
    Table t = makeTable("3cards0sets.dat");

    // These cards are on the table, but they do not form a set.
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 1, 1, 2);
    Card c3 = new Card(2, 2, 2, 3);

    // these cards are all on the table, but they do not form a set.
    t.removeSet(c1, c2, c3);
        
    assertEquals(3, t.numCards());
  }
   
  public void testNotAllOnTable()
  {
    Table t = makeTable("3cards0sets.dat");
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 1, 1, 2);
    Card c3 = new Card(1, 1, 1, 3);

    // c1 and c2 are on the table, but c3 is not.  The cards from a set
    t.removeSet(c1, c2, c3);
    
    assertEquals(3, t.numCards());
  }
  
  public void testFourteenInTwelve()
  {
    Table t = makeTable("fourteen.dat");
    
    assertEquals(14, t.numSets());
    assertEquals(12, t.numCards());
    
    // 1 1 1 3  (3rd to last card)
    // 1 3 1 1  (second card)
    // 1 2 1 2  (first card)
    t.removeSet(new Card(1, 1, 1, 3),
                new Card(1, 3, 1, 1),
                new Card(1, 2, 1, 2));
    
    assertEquals(9, t.numCards());
    // that removed 6 of the 14 sets.
    assertEquals(8, t.numSets());
    
    // 1 1 1 1 (last card)
    // 1 1 2 1 (middle)
    // 1 1 3 1 (middle)
    t.removeSet(new Card(1, 1, 1, 1),
                new Card(1, 1, 2, 1),
                new Card(1, 1, 3, 1));
    
    assertEquals(6, t.numCards());
    // only one set left.
    assertEquals(1, t.numSets());
  }
  
  public void testNoSetsinTwelve()
  {
    Table t = makeTable("12cards0sets.dat");
    
    assertEquals(12, t.numCards());
    assertEquals(0, t.numSets());
  }
  
  private Table makeTable(String filename)
  {
    Deck d = new Deck(filename);
    Table t = new Table();
    
    while(d.hasNext())
      t.add(d.getNext());
    
    return t;
  }
}
