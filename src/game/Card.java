package game;

/**
 * This class is meant to hold the attributes of a card in the game set.
 * These attributes are quantity, color, shading and shape.
 *
 */
public class Card
{
  private int quantity;
  private int color;
  private int shading;
  private int shape;
  
  // This is a comment
  // This is also a comment
  // This is a third comment
  
  /**
   * <p>
   * This is the public constructor that allows the developer to create
   * an instance of the class.<p>
   * @param theQuantity
   * @param theColor
   * @param theShading
   * @param theShape
   */
  public Card(int theQuantity, int theColor, int theShading, int theShape)
  {
    quantity = fixValue(theQuantity);
    color = fixValue(theColor);
    shading = fixValue(theShading);
    shape = fixValue(theShape);
  }
  
  /**
   * This method takes an integer parameter and returns it mod 3.
   * @param value
   * @return
   */
  private int fixValue(int value)
  {
    value = value % 3;
    
    if(value <= 0)
      value += 3;
    
    return value;
  }
  /**
   * Returns the quantity of the current instance of Card
   * @return
   */
  public int getQuantity()
  {
    return quantity;
  }
  /**
   * Returns the color of the current instance of Card
   * @return
   */
  public int getColor()
  {
    return color;
  }
  /**
   * Returns the shading of the current instance of Card
   * @return
   */
  public int getShading()
  {
    return shading;
  }
  /**
   * Returns the shape of the current instance of Card
   * @return
   */
  public int getShape()
  {
    return shape;
  }
  /**
   * <p>
   * This method takes three cards and determines if they are a set based on the rules
   * of the game set.<p>
   * @param c1
   * @param c2
   * @param c3
   * @return
   * @throws NullPointerException
   */
  public static boolean isSet(Card c1, Card c2, Card c3) throws NullPointerException
  {
	  int quantitySum = c1.getQuantity() + c2.getQuantity() + c3.getQuantity();
	  int colorSum = c1.getColor() + c2.getColor() + c3.getColor();
	  int shadingSum = c1.getShading() + c2.getShading() + c3.getShading();
	  int shapeSum = c1.getShape() + c2.getShape() + c3.getShape();
	    
	    return quantitySum % 3 == 0 && colorSum % 3 == 0 &&
	      shadingSum % 3 == 0 && shapeSum % 3 == 0;
  }
  /**
   * <p>
   *  Based on the attributes of the Card, this method returns a string value to
   *  to help differentiate between cards. <p>
   */
  public String toString()
  {
    char colorVal;
    char shadingVal;
    char shapeVal;
    
    if(color == 1)
      colorVal = 'R';
    else if(color == 2)
      colorVal = 'G';
    else
      colorVal = 'P';
    
    if(shading == 1)
      shadingVal = 'O';
    else if(shading == 2)
      shadingVal = 'T';
    else
      shadingVal = 'S';
    
    if(shape == 1)
      shapeVal = 'O';
    else if(shape == 2)
      shapeVal = 'D';
    else
      shapeVal = 'S';
    
    return "" + quantity + colorVal + shadingVal + shapeVal;
  }
  /**
   * <p>
   * This method compares one card to another to determine whether or not they have the same
   * attributes and returns true if they are. Else it returns false. <p>
   * @param obj
   * @return 
   */
  public boolean equals(Object obj)
  {
    Card that = (Card)obj;
    
    return quantity == that.getQuantity() &&
      color == that.getColor() &&
      shading == that.getShading() &&
      shape == that.getShape();
  }
}