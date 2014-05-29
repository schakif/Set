
public class Card
{
  private int quantity;
  private int color;
  private int shading;
  private int shape;
  
  // This is a comment
  
  public Card(int theQuantity, int theColor, int theShading, int theShape)
  {
    quantity = fixValue(theQuantity);
    color = fixValue(theColor);
    shading = fixValue(theShading);
    shape = fixValue(theShape);
  }
  
  private int fixValue(int value)
  {
    value = value % 3;
    
    if(value <= 0)
      value += 3;
    
    return value;
  }
  
  public int getQuantity()
  {
    return quantity;
  }
  
  public int getColor()
  {
    return color;
  }
  
  public int getShading()
  {
    return shading;
  }
  
  public int getShape()
  {
    return shape;
  }
  
  public boolean isSet(Card c2, Card c3)
  {
    int quantitySum = quantity + c2.getQuantity() + c3.getQuantity();
    int colorSum = color + c2.getColor() + c3.getColor();
    int shadingSum = shading + c2.getShading() + c3.getShading();
    int shapeSum = shape + c2.getShape() + c3.getShape();
    
    return quantitySum % 3 == 0 && colorSum % 3 == 0 &&
      shadingSum % 3 == 0 && shapeSum % 3 == 0;
  }
  
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
  
  public boolean equals(Object obj)
  {
    Card that = (Card)obj;
    
    return quantity == that.getQuantity() &&
      color == that.getColor() &&
      shading == that.getShading() &&
      shape == that.getShape();
  }
}