package com.collegeboard.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.collegeboard.game.NumberGuessing;
public class GuessTheNumberTest {

	NumberGuessing objGuessNumber = null;

	@Before
	public void setUp() throws Exception {
		objGuessNumber = new NumberGuessing(1, 100);
	}

	@Test
	public void guessed75() {
		int answer = objGuessNumber.process("h",50);
		Assert.assertEquals(75, answer);
	}
	
	@Test
	public void guessed25() {
		int answer = objGuessNumber.process("l",50);
		Assert.assertEquals(25, answer);
	}
}
