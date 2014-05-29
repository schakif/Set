
public class TableNode
{
  private Card theCard;
  private TableNode next;
  
  public TableNode(Card card)
  {
    theCard = card;
    next = null;
  }
  
  public void setNext(TableNode newNext)
  {
    next = newNext;
  }
  
  public TableNode getNext()
  {
    return next;
  }
  
  public Card getCard()
  {
    return theCard;
  }
}