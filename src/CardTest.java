import junit.framework.TestCase;

public class CardTest extends TestCase {
  
  /* 
   * This helper method makes sure a card has the values specified.
   */
  public void assertCardEqual(Card c, int quantity, int color,
                              int shading, int shape)
  {
    assertEquals(quantity, c.getQuantity());
    assertEquals(color, c.getColor());
    assertEquals(shading, c.getShading());
    assertEquals(shape, c.getShape());
  }

  public void testInRangeCards() 
  {
    for(int q = 1; q < 4; q++)
    {
      for(int c = 1; c < 4; c++)
      {
        for(int d = 1; d < 4; d++)
        {
          for(int s = 1; s < 4; s++)
          {
            Card card = new Card(q, c, d, s);
            assertCardEqual(card, q, c, d, s);
          }
        }
      }
    }
  }
  
  public void testQuantityHigh()
  {
    Card c = new Card(5, 1, 1, 1);
    assertCardEqual(c, 2, 1, 1, 1);
  }
  
  public void testColorHigh()
  {
    Card c = new Card(1, 5, 1, 1);
    assertCardEqual(c, 1, 2, 1, 1);
  }
  
  public void testShadingHigh()
  {
    Card c = new Card(1, 1, 5, 1);
    assertCardEqual(c, 1, 1, 2, 1);
  }
  
  public void testShapeHigh()
  {
    Card c = new Card(1, 1, 1, 5);
    assertCardEqual(c, 1, 1, 1, 2);
  }
  
  public void testQuantityVeryHigh()
  {
    Card c = new Card(49, 1, 1, 1);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testColorVeryHigh()
  {
    Card c = new Card(1, 49, 1, 1);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testShadingVeryHigh()
  {
    Card c = new Card(1, 1, 49, 1);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testShapeVeryHigh()
  {
    Card c = new Card(1, 1, 1, 49);
    assertCardEqual(c, 1, 1, 1, 1);
  } 

  public void testQuantityThree()
  {
    Card c = new Card(51, 1, 1, 1);
    assertCardEqual(c, 3, 1, 1, 1);
  }

  public void testColorThree()
  {
    Card c = new Card(1, 51, 1, 1);
    assertCardEqual(c, 1, 3, 1, 1);
  }
  
  public void testShadingThree()
  {
    Card c = new Card(1, 1, 51, 1);
    assertCardEqual(c, 1, 1, 3, 1);
  }
  
  public void testShapeThree()
  {
    Card c = new Card(1, 1, 1, 51);
    assertCardEqual(c, 1, 1, 1, 3);
  }

  public void testQuantityNeg()
  {
    Card c = new Card(-1, 1, 1, 1);
    assertCardEqual(c, 2, 1, 1, 1);
  }
  
  public void testColorNeg()
  {
    Card c = new Card(1, -1, 1, 1);
    assertCardEqual(c, 1, 2, 1, 1);
  }
  
  public void testShadingNeg()
  {
    Card c = new Card(1, 1, -1, 1);
    assertCardEqual(c, 1, 1, 2, 1);
  }
  
  public void testShapeNeg()
  {    
    Card c = new Card(1, 1, 1, -1);
    assertCardEqual(c, 1, 1, 1, 2);
  }
  
  public void testQuantitytVeryNeg()
  {
    Card c = new Card(-11, 1, 1, 1);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testColorVeryNeg()
  {    
    Card c = new Card(1, -11, 1, 1);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testShadingVeryNeg()
  {    
    Card c = new Card(1, 1, -11, 1);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testShapeVeryNeg()
  {
    Card c = new Card(1, 1, 1, -11);
    assertCardEqual(c, 1, 1, 1, 1);
  }
  
  public void testQuantity3Neg()
  {
    Card c = new Card(-12, 1, 1, 1);
    assertCardEqual(c, 3, 1, 1, 1);
  }
  
  public void testColor3Neg()
  {    
    Card c = new Card(1, -12, 1, 1);
    assertCardEqual(c, 1, 3, 1, 1);
  }
  
  public void testShading3Neg()
  {    
    Card c = new Card(1, 1, -12, 1);
    assertCardEqual(c, 1, 1, 3, 1);
  }
  
  public void testShape3Neg()
  {
    Card c = new Card(1, 1, 1, -12);
    assertCardEqual(c, 1, 1, 1, 3);
  }
  
  public void testIsSetSameSameSameDifferent()
  {
    // same, same, same, different
    Card c1 = new Card(1, 2, 3, 1);
    Card c2 = new Card(1, 2, 3, 3);
    Card c3 = new Card(1, 2, 3, 2);
    assertTrue(c1.isSet(c2, c3));
    assertTrue(c2.isSet(c1, c3));
    assertTrue(c3.isSet(c2, c1));
  }
  
  public void testIsSetDiffSameSameDiff()
  {
    // different, same, same, different
    Card c1 = new Card(1, 2, 3, 1);
    Card c2 = new Card(2, 2, 3, 3);
    Card c3 = new Card(3, 2, 3, 2);
    assertTrue(c1.isSet(c2, c3));
    assertTrue(c2.isSet(c1, c3));
    assertTrue(c3.isSet(c2, c1));
  }
  
  public void testIsSetDiffDiffSameDiff()
  {
    // different, different, same, different
    Card c1 = new Card(1, 3, 3, 1);
    Card c2 = new Card(3, 2, 3, 3);
    Card c3 = new Card(2, 1, 3, 2);
    assertTrue(c1.isSet(c2, c3));
    assertTrue(c2.isSet(c1, c3));
    assertTrue(c3.isSet(c2, c1));
  }
  
  public void testSetDiffDiffDiffDiff()
  {
    // all different
    Card c1 = new Card(1, 2, 3, 1);
    Card c2 = new Card(2, 3, 2, 3);
    Card c3 = new Card(3, 1, 1, 2);
    assertTrue(c1.isSet(c2, c3));
    assertTrue(c2.isSet(c1, c3));
    assertTrue(c3.isSet(c2, c1));
  }
  
  public void testNoSetQuantity()
  {
    // not a set in quantity
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 2, 1, 1);
    Card c3 = new Card(2, 3, 1, 1);
    assertFalse(c1.isSet(c2, c3));
    assertFalse(c2.isSet(c3, c1));
    assertFalse(c3.isSet(c2, c1));
  }
  
  public void testNoSetColor()
  {
    // not a set in color
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 2, 1, 1);
    Card c3 = new Card(1, 2, 1, 1);
    assertFalse(c1.isSet(c2, c3));
    assertFalse(c2.isSet(c3, c1));
    assertFalse(c3.isSet(c2, c1));
  }
  
  public void testNoSetShading()
  {
    // not a set in shading
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 2, 1, 1);
    Card c3 = new Card(1, 3, 2, 1);
    assertFalse(c1.isSet(c2, c3));
    assertFalse(c2.isSet(c3, c1));
    assertFalse(c3.isSet(c2, c1));
  }
  
  public void testNoSetShape()
  {
    // not a set in shape
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 2, 1, 1);
    Card c3 = new Card(1, 3, 1, 2);
    assertFalse(c1.isSet(c2, c3));
    assertFalse(c2.isSet(c3, c1));
    assertFalse(c3.isSet(c2, c1));
  }
  
  public void testNoSetAllAttribs()
  {
    // not a set in all 4
    Card c1 = new Card(2, 2, 2, 2);
    Card c2 = new Card(1, 2, 1, 1);
    Card c3 = new Card(1, 3, 1, 2);
    assertFalse(c1.isSet(c2, c3));
    assertFalse(c2.isSet(c3, c1));
    assertFalse(c3.isSet(c2, c1));
  }
  
  public void testToString()
  {
    Card c = new Card(1, 1, 1, 1);
    assertEquals("1ROO", c.toString());
    
    c = new Card(2, 2, 2, 2);
    assertEquals("2GTD", c.toString());
    
    c = new Card(3, 3, 3, 3);
    assertEquals("3PSS", c.toString());
    
    c = new Card(1, 2, 3, 1);
    assertEquals("1GSO", c.toString());
  }
  
  public void testEquals()
  {
    Card c1 = new Card(1, 1, 1, 1);
    Card c2 = new Card(1, 1, 1, 1);
    
    assertTrue(c1 != c2);
    assertTrue(c1.equals(c2));
    assertTrue(c2.equals(c1));
    assertEquals(c1, c2);
    assertEquals(c2, c1);
    
    Card c3 = new Card(2, 2, 2, 2);
    
    assertTrue(c1 != c3);
    assertFalse(c1.equals(c3));
    assertFalse(c3.equals(c1));                
  }
}
