package tests;

import static org.junit.Assert.*;
import game.Card;
import game.Table;

import org.junit.Test;

public class TestNumSets {

	@Test
	public void testReturnSets() {
		Card c1 = new Card(1,1,1,1);
		Card c2 = new Card(2,2,2,2);
		Card c3 = new Card(2,1,2,1);
		Card c4 = new Card(3,3,3,3);
		
		Table t1 = new Table();
		
		t1.add(c1);
		t1.add(c2);
		t1.add(c3);
		
		assertEquals(0, t1.numSets());
		
		t1.add(c4);
		
		assertEquals(1, t1.numSets());
		
		
	}
	

}
