public class Main
{
  private static final int NUMSIMS = 100000;
  
  public static void main(String[] args)
  {
    int sum = 0;
    
    for(int i = 0; i < NUMSIMS; i++)
    {
      Deck d = new Deck();
      
      Table t = new Table();
      
      for(int j = 0; j < 12; j++)
        t.add(d.getNext());
      
      sum += t.numSets();
    }
    
    System.out.println(sum / (double)(NUMSIMS));
    
    
    sum = 0;
    
    for(int i = 0; i < NUMSIMS; i++)
    {
      Game g = new Game();
      
      while(!g.isGameOver())
        g.playRound();
      
      sum += g.numCards();
    }
    
    System.out.println(sum / (double)(NUMSIMS));
    
    
  }
}