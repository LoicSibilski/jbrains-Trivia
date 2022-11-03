package com.trivia;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

class GameTest {

	@Spy
	@InjectMocks
	Game gameMock;

	@Mock
	ArrayList players = new ArrayList();

	int[] places = new int[6];
	int[] purses = new int[6];
	boolean[] inPenaltyBox = new boolean[6];

	int currentPlayer = 0;
	boolean isGettingOutOfPenaltyBox;

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

	@Test
	void testCreateRockQuestion() {
		String rockQuestion = gameMock.createRockQuestion(32);
		assertEquals(rockQuestion, "Rock Question 32");
	}

	@Test
	void testIsPlayableFailed() {
		Mockito.when(gameMock.howManyPlayers()).thenReturn(1);
		assertEquals(gameMock.isPlayable(), false);
	}

	@Test
	void testIsPlayablePassed() {
		Mockito.when(gameMock.howManyPlayers()).thenReturn(4);
		assertEquals(gameMock.isPlayable(), true);
	}

	@Test
	void testAdd() {
		boolean adding = gameMock.add("lblblb");
		assertTrue(adding);
	}

	@Test
	void testHowManyPlayers() {
		Mockito.when(players.size()).thenReturn(3);
		assertEquals(gameMock.howManyPlayers(), 3);
	}

	@Test
	void testRollIfIf() {
		gameMock.currentPlayer = 0;
		gameMock.inPenaltyBox[0] = true;

		Mockito.when(players.get(Mockito.anyInt())).thenReturn("blblbl");

		gameMock.roll(1);
		System.out.println(outputStreamCaptor.toString()
				.trim());
		assertTrue(outputStreamCaptor.toString()
				.trim().contains("blblbl is getting out of the penalty box"));
	}

	@Test
	void testRollIfElse() {
		gameMock.currentPlayer = 0;
		gameMock.inPenaltyBox[0] = true;

		Mockito.when(players.get(Mockito.anyInt())).thenReturn("blblbl");

		gameMock.roll(2);
		System.out.println(outputStreamCaptor.toString()
				.trim());
		assertTrue(outputStreamCaptor.toString()
				.trim().contains("blblbl is not getting out of the penalty box"));
	}

	@Test
	void testRollElse() {
		int place = 4;
		int roll = 5;
		gameMock.currentPlayer = 0;
		gameMock.inPenaltyBox[0] = false;
		gameMock.places[0] = place;

		Mockito.when(players.get(Mockito.anyInt())).thenReturn("blblbl");

		gameMock.roll(roll);
		System.out.println(outputStreamCaptor.toString()
				.trim());
		
		assertThat(outputStreamCaptor.toString().trim(), containsString("blblbl's new location is "+ (place+roll)));
	}

	@Test
	void testWrongAnswer() {
		Mockito.when(players.get(Mockito.anyInt())).thenReturn("blblbl");

		assertEquals(gameMock.wrongAnswer(), true);
		assertTrue(outputStreamCaptor.toString()
				.trim().contains("blblbl was sent to the penalty box"));
	}

	@Test
	void testWasCorrectlyAnsweredWinElse() {
		gameMock.currentPlayer = 0;
		gameMock.inPenaltyBox[0] = false;
		gameMock.isGettingOutOfPenaltyBox = true;
		gameMock.purses[currentPlayer] = 5;
		Mockito.when(players.get(Mockito.anyInt())).thenReturn("blblbl");

		System.out.println(outputStreamCaptor.toString()
				.trim());
			
		assertEquals(false, gameMock.wasCorrectlyAnswered());

		assertThat(outputStreamCaptor.toString().trim(), containsString("blblbl now has " + gameMock.purses[currentPlayer] + " Gold Coins."));				
	}

	@Test
	void testWasCorrectlyAnsweredNotWinElse() {
		gameMock.currentPlayer = 0;
		gameMock.inPenaltyBox[0] = false;
		gameMock.isGettingOutOfPenaltyBox = true;
		gameMock.purses[currentPlayer] = 3;
		Mockito.when(players.get(Mockito.anyInt())).thenReturn("blblbl");

		System.out.println(outputStreamCaptor.toString()
				.trim());
			
		assertEquals(true, gameMock.wasCorrectlyAnswered());

		assertThat(outputStreamCaptor.toString().trim(), containsString("blblbl now has " + gameMock.purses[currentPlayer] + " Gold Coins."));				
	}
}
