package tests;
import java.util.ArrayList;

import game.Card;
import game.Deck;
import game.Table;
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
             new Card(3, 3, 3, 3),null);
    
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
    t.removeSet(c2, c3, c1,null);
    
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
    t.removeSet(c1, c2, c3,null);
    
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
    t.removeSet(c1, c2, c3,null);
        
    assertEquals(3, t.numCards());
  }
   
  public void testNotAllOnTable()
  {
    Table t = makeTable("3cards0sets.dat");
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 1, 1, 2);
    Card c3 = new Card(1, 1, 1, 3);

    // c1 and c2 are on the table, but c3 is not.  The cards from a set
    t.removeSet(c1, c2, c3,null);
    
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
                new Card(1, 2, 1, 2),null);
    
    assertEquals(9, t.numCards());
    // that removed 6 of the 14 sets.
    assertEquals(8, t.numSets());
    
    // 1 1 1 1 (last card)
    // 1 1 2 1 (middle)
    // 1 1 3 1 (middle)
    t.removeSet(new Card(1, 1, 1, 1),
                new Card(1, 1, 2, 1),
                new Card(1, 1, 3, 1),null);
    
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
  
  public void test12Cards3FromEnd() {
	  Table t = makeTable("GameTests/OurTestData.dat");
	  
	  assertEquals(12, t.numCards());
	  
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), null);
	  assertTrue(t.getCard(0).equals(new Card(2,2,1,3)));
	  assertTrue(t.getCard(1).equals(new Card(2,2,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(2,2,1,1)));
	  
	  assertEquals(9, t.numCards());
	  
  }
  
  public void test12Cards3FromMiddle() {
	  Table t = makeTable("GameTests/OurTestData.dat");
	  
	  assertEquals(12, t.numCards());
	  
	  t.removeSet(new Card(1,2,1,1), new Card(1,2,1,2), new Card(1,2,1,3), null);
	  assertTrue(t.getCard(6).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(7).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(8).equals(new Card(1,1,1,3)));
	  
	  assertEquals(9, t.numCards());
	  
  }
  
  public void testSlidesDownProperly(){
	  Table t = new Table();
	  //avoid the shuffling
	  t.addLast(new Card(1,1,1,1));
	  t.addLast(new Card(1,1,1,2));
	  t.addLast(new Card(1,1,1,3));
	  t.addLast(new Card(1,2,1,1));
	  t.addLast(new Card(1,2,1,2));
	  t.addLast(new Card(1,2,1,3));
	  t.addLast(new Card(2,1,1,1));
	  t.addLast(new Card(2,1,1,2));
	  t.addLast(new Card(2,1,1,3));
	  t.addLast(new Card(2,2,1,1));
	  t.addLast(new Card(2,2,1,2));
	  t.addLast(new Card(2,2,1,3));
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), null);
	  assertTrue(t.getCard(0).equals(new Card(2,2,1,3)));
	  assertTrue(t.getCard(1).equals(new Card(2,2,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(2,2,1,1)));
  }
  
  
  public void test12CardsPut3AtEnd() {
	  Table t = makeTable("GameTests/OurTestData.dat");
	  
	  assertEquals(12, t.numCards());
	  
	  ArrayList<Card> newCards = new ArrayList<Card>(3);
	  newCards.add(new Card(3,3,3,3));
	  newCards.add(new Card(2,2,2,2));
	  newCards.add(new Card(1,1,1,1));
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), newCards);
	  
	  assertTrue(t.getCard(9).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(10).equals(new Card(2,2,2,2)));
	  assertTrue(t.getCard(11).equals(new Card(3,3,3,3)));
	  
	  assertEquals(12, t.numCards());
	  
  }
  
  public void test12CardsPut3AtMiddle() {
	  Table t = makeTable("GameTests/OurTestData.dat");
	  
	  assertEquals(12, t.numCards());
	  
	  ArrayList<Card> newCards = new ArrayList<Card>(3);
	  newCards.add(new Card(3,3,3,3));
	  newCards.add(new Card(2,2,2,2));
	  newCards.add(new Card(1,1,1,1));
	  t.removeSet(new Card(1,2,1,1), new Card(1,2,1,2), new Card(1,2,1,3), newCards);
	  
	  assertTrue(t.getCard(6).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(7).equals(new Card(2,2,2,2)));
	  assertTrue(t.getCard(8).equals(new Card(3,3,3,3)));
	  
	  assertEquals(12, t.numCards());
	  
  }
  
  public void testRemovingAndAddingProperly(){
	  Table t = new Table();
	  //avoid shuffling
	  t.addLast(new Card(1,1,1,1));
	  t.addLast(new Card(1,1,1,2));
	  t.addLast(new Card(1,1,1,3));
	  t.addLast(new Card(1,2,1,1));
	  t.addLast(new Card(1,2,1,2));
	  t.addLast(new Card(1,2,1,3));
	  t.addLast(new Card(2,1,1,1));
	  t.addLast(new Card(2,1,1,2));
	  t.addLast(new Card(2,1,1,3));
	  t.addLast(new Card(2,2,1,1));
	  t.addLast(new Card(2,2,1,2));
	  t.addLast(new Card(2,2,1,3));
	  ArrayList<Card> newCards = new ArrayList<Card>(3);
	  newCards.add(new Card(3,3,3,3));
	  newCards.add(new Card(2,2,2,2));
	  newCards.add(new Card(1,1,1,1));
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), newCards);
	  assertEquals(t.getCard(0),(new Card(3,3,3,3)));
	  assertEquals(t.getCard(1),(new Card(2,2,2,2)));
	  assertEquals(t.getCard(2),(new Card(1,1,1,1)));
  }
  
  public void test15CardsTakeFromFront() {
	  Table t = makeTable("GameTests/OurBigTestData.dat");
	  
	  assertEquals(15, t.numCards());
	  
	  t.removeSet(new Card(3,1,1,1), new Card(3,2,2,2), new Card(3,3,3,3), null);
	  
	  assertTrue(t.getCard(0).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(1).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(1,1,1,3)));
	  
	  assertEquals(12, t.numCards());
  }
  public void test15CardsTakeFromEnd() {
	  
	  Table t = makeTable("GameTests/OurBigTestData.dat");
	  
	  assertEquals(15, t.numCards());
	  
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), null);
	  
	  assertTrue(t.getCard(0).equals(new Card(3,3,3,3)));
	  assertTrue(t.getCard(1).equals(new Card(3,2,2,2)));
	  assertTrue(t.getCard(2).equals(new Card(3,1,1,1)));
	  
	  assertEquals(12, t.numCards());
	  
  }
  public void test15CardsTakeFromMiddle() {
	  Table t = makeTable("GameTests/OurBigTestData.dat");
	  
	  assertEquals(15, t.numCards());
	  
	  t.removeSet(new Card(1,2,1,1), new Card(1,2,1,2), new Card(1,2,1,3), null);
	  
	  assertTrue(t.getCard(9).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(10).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(11).equals(new Card(1,1,1,3)));
	  
	  assertEquals(12, t.numCards());
	  
  }
  
  public void test18CardsTakeFromFront() {
	  Table t = makeTable("GameTests/OurBiggerTestData.dat");
	  
	  assertEquals(18, t.numCards());
	  
	  t.removeSet(new Card(3,2,1,1), new Card(3,2,1,2), new Card(3,2,1,3), null);
	  
	  assertTrue(t.getCard(0).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(1).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(1,1,1,3)));
	  
	  assertEquals(15, t.numCards());
  }
  public void test18CardsTakeFromEnd() {
	  
	  Table t = makeTable("GameTests/OurBiggerTestData.dat");
	  
	  assertEquals(18, t.numCards());
	  
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), null);
	  
	  assertTrue(t.getCard(0).equals(new Card(3,2,1,3)));
	  assertTrue(t.getCard(1).equals(new Card(3,2,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(3,2,1,1)));
	  
	  assertEquals(15, t.numCards());
	  
  }
  public void test18CardsTakeFromMiddle() {
	  Table t = makeTable("GameTests/OurBiggerTestData.dat");
	  
	  assertEquals(18, t.numCards());
	  
	  t.removeSet(new Card(1,2,1,1), new Card(1,2,1,2), new Card(1,2,1,3), null);
	  
	  assertTrue(t.getCard(12).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(13).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(14).equals(new Card(1,1,1,3)));
	  
	  assertEquals(15, t.numCards());
	  
  }
  
  public void test9CardsTakeFromFront() {
	  Table t = makeTable("GameTests/OurSmallerTestData.dat");
	  
	  assertEquals(9, t.numCards());
	  
	  t.removeSet(new Card(2,1,1,1), new Card(2,1,1,2), new Card(2,1,1,3), null);
	  
	  assertTrue(t.getCard(0).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(1).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(1,1,1,3)));
	  
	  assertEquals(6, t.numCards());
  }
  public void test9CardsTakeFromEnd() {
	  
	  Table t = makeTable("GameTests/OurSmallerTestData.dat");
	  
	  assertEquals(9, t.numCards());
	  
	  t.removeSet(new Card(1,1,1,1), new Card(1,1,1,2), new Card(1,1,1,3), null);
	  
	  assertTrue(t.getCard(0).equals(new Card(2,1,1,3)));
	  assertTrue(t.getCard(1).equals(new Card(2,1,1,2)));
	  assertTrue(t.getCard(2).equals(new Card(2,1,1,1)));
	  
	  assertEquals(6, t.numCards());
	  
  }
  public void test9CardsTakeFromMiddle() {
	  Table t = makeTable("GameTests/OurSmallerTestData.dat");
	  
	  assertEquals(9, t.numCards());
	  
	  t.removeSet(new Card(1,2,1,1), new Card(1,2,1,2), new Card(1,2,1,3), null);
	  
	  assertTrue(t.getCard(3).equals(new Card(1,1,1,1)));
	  assertTrue(t.getCard(4).equals(new Card(1,1,1,2)));
	  assertTrue(t.getCard(5).equals(new Card(1,1,1,3)));
	  
	  assertEquals(6, t.numCards());
	  
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
