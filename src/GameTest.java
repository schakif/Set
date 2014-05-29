import junit.framework.TestCase;

public class GameTest extends TestCase 
{
  public void testNewGame()
  {
    // When a game is created from a random deck, 
    // there are 12 cards on the table and the game
    // is not over.  Can't say much more...
    Game g = new Game();
    
    assertEquals(12, g.numCards());
    assertFalse(g.isGameOver());
  }
  
  public void testNoSetsFirstTwelve()
  {
    Game g = new Game("GameTests/15cards0setsin12.dat");
    
    assertEquals(0, g.numSets());
    assertEquals(12, g.numCards());
    
    g.playRound();
    
    assertEquals(15, g.numCards());
  }
  
  public void testNoSetsFirstFifteen()
  {
    Game g = new Game("GameTests/18cards0setsin15.dat");
    
    assertEquals(0, g.numSets());
    assertEquals(12, g.numCards());
    
    g.playRound();
    
    assertEquals(0, g.numSets());
    assertEquals(15, g.numCards());
    
    g.playRound();
    
    assertEquals(9, g.numSets());
    assertEquals(18, g.numCards());
  }
  
  public void testBackToTwelveAfterFifteen()
  {
    Game g = new Game("GameTests/15cards0setsin12.dat");
    // this takes us to 15
    g.playRound();
    
    assertEquals(15, g.numCards());
    
    // When we play another round, we wont' add more cards.
    g.playRound();
    assertEquals(12, g.numCards());
  }
  
  public void testBackToFifteenAfterEighteen()
  {
    Game g = new Game("GameTests/18cards0setsin15.dat");
    g.playRound();
    g.playRound();

    // now we have 18 cards on the table.
    assertEquals(18, g.numCards());

    // When we play another round, we won't add more cards.
    g.playRound();
    assertEquals(15, g.numCards());
  }
  
  public void testDeckNotMultipleOfThree()
  {
    Game g = new Game("GameTests/14cards.dat");
    assertEquals(12, g.numCards());
    // when we play a round, there is a set, but there are 
    // only 2 more cards available.
    g.playRound();
    
    assertEquals(11, g.numCards());
  }
  
  public void testDeckLessThanTwelve()
  {
    Game g = new Game("GameTests/7cards.dat");
    
    assertEquals(7, g.numCards());
    g.playRound();
    
    assertEquals(4, g.numCards());
  }
  
  public void testGameOverEmptyTable()
  {
    Game g = new Game("GameTests/12cards4sets.dat");
    
    assertEquals(12, g.numCards());
    assertEquals(4, g.numSets());
    
    g.playRound();
    assertEquals(9, g.numCards());
    assertEquals(3, g.numSets());
    
    g.playRound();
    assertEquals(6, g.numCards());
    assertEquals(2, g.numSets());
    
    g.playRound();
    assertEquals(3, g.numCards());
    assertEquals(1, g.numSets());
    
    g.playRound();
    assertEquals(0, g.numCards());
    assertTrue(g.isGameOver());
  }
  
  public void test14Sets()
  {
    Game g = new Game("GameTests/14in12.dat");
    
    assertEquals(12, g.numCards());
    assertEquals(14, g.numSets());
    assertFalse(g.isGameOver());
    
    g.playRound();
    assertEquals(9, g.numCards());
    assertFalse(g.isGameOver());
    
    g.playRound();
    assertEquals(6, g.numCards());
    assertFalse(g.isGameOver());

    g.playRound();
    assertEquals(3, g.numCards());
    assertTrue(g.isGameOver());
  }
  
  public void testRemoveAdd()
  {
    Game g = new Game("GameTests/15cards.dat");
    
    assertEquals(12, g.numCards());
    assertEquals(3, g.numSets());
    assertFalse(g.isGameOver());
    
    g.playRound();
    
    assertEquals(12, g.numCards());
    assertEquals(3, g.numSets());
    assertFalse(g.isGameOver());
    
    g.playRound();
    assertEquals(9, g.numCards());
    assertEquals(1, g.numSets());
    assertFalse(g.isGameOver());
    
    g.playRound();
    assertEquals(6, g.numCards());
    assertEquals(0, g.numSets());
    assertTrue(g.isGameOver());
  }
}
