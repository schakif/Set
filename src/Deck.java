
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
  private ArrayList<Card> theCards;
  private int nextCardIndex;
  
  public Deck()
  {
    theCards = new ArrayList<Card>(81);
        
    for(int quantity = 1; quantity < 4; quantity++)
    {
      for(int color = 1; color < 4; color++)
      {
        for(int shading = 1; shading < 4; shading++)
        {
          for(int shape = 1; shape < 4; shape++)
          {
            theCards.add(new Card(quantity, color, shading, shape));
          }
        }
      }
    }

    Collections.shuffle(theCards);
    
    nextCardIndex = 0;
  }
  
  public Deck(String filename)
  {
    theCards = new ArrayList<Card>(81);
    
    try
    {
      String line;
      BufferedReader infile = new BufferedReader(new FileReader(filename));
      //int position = 0;
  
      while((line = infile.readLine()) != null)
      {
        // Blank lines might contain white space, so trim it off
        line = line.trim();
        
        // ignore blank lines
        if(line.length() == 0)
          continue;
        
        // ignore comments
        if(line.startsWith("#"))
          continue;
            
        // a valid line contains 4 integers
        StringTokenizer tokenizer = new StringTokenizer(line);
        
        int quantity = Integer.parseInt(tokenizer.nextToken());
        int color = Integer.parseInt(tokenizer.nextToken());
        int shading = Integer.parseInt(tokenizer.nextToken());
        int shape = Integer.parseInt(tokenizer.nextToken());
        
        theCards.add(new Card(quantity, color, shading, shape));
        nextCardIndex = 0;
      }
      infile.close();
    }
    catch(Exception e)
    {
      throw new RuntimeException("Error while reading file: " + e.toString());
    }
  }
    
  public boolean hasNext()
  {
    return nextCardIndex < theCards.size();
  }
    
  public Card getNext()
  {
    if(nextCardIndex > 80)
      return null;
    
    Card ret = theCards.get(nextCardIndex);
    nextCardIndex++;
    
    return ret;
  }
}