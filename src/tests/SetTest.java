package tests;

import static org.junit.Assert.*;
import game.Card;
import game.Set;

import org.junit.Test;

public class SetTest {

	@Test
	public void createStringTest() {
		
		Card c1 = new Card(1,1,1,1);
		Card c2 = new Card(2,2,2,2);
		Card c3 = new Card(3,3,3,3);
		
		Set set1 = new Set(c1, c2, c3);
		
		
		
		assertEquals("1ROO 2GTD 3PSS", set1.toString());		
	}
	public void createListTest() {
		Card c1 = new Card(1,1,1,1);
		Card c2 = new Card(2,2,2,2);
		Card c3 = new Card(3,3,3,3);
		
		Set set1 = new Set(c1, c2, c3);
		Card[] array = new Card[3];
		array = set1.returnSet();
		assertEquals("1ROO", array[0]);
		assertEquals("2GTD", array[1]);
		assertEquals("3PSS", array[2]);
		
	}
	
	public void testNotSet(){
		Card c1 = new Card(1,1,1,1);
		Card c2 = new Card(1,2,1,3);
		Card c3 = new Card(2,1,3,3);
		
		try{
			Set set1 = new Set(c1, c2, c3);
			fail();
		}
		
		catch(RuntimeException e){
		}
		fail();

	}

}
