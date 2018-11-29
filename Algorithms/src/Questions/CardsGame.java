package Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardsGame {

	public CardsGame() {
		for(int i = 1; i <= 53; ++i) {
			// populate all the cards
			allCards.add(i);
		}
	}
	public void pick() {
		Random random = new Random();
		int pick = random.nextInt(max - min + 1);
		System.out.println("Picked up card : " + pick);
		pickedCards.add(pick);
		allCards.remove(pick-1);
	}
	
	public void status() {
		System.out.println(allCards.toString());
		System.out.println(pickedCards.toString());
	}
	
	public void reset() {
		allCards.clear();
		for(int i = 1; i <= 53; ++i) {
			// populate all the cards
			allCards.add(i);
		}
		pickedCards.clear();
	}
	
	public void shuffle() {
		// Shuffle the allCards
		Collections.shuffle(allCards);
	}
	
	private int min = 0;
	private int max = 52;
	private ArrayList<Integer> allCards = new ArrayList<Integer>();
	private ArrayList<Integer> pickedCards = new ArrayList<Integer>();
	
}

class MainClass {
	public static void main(String[] args) {
		CardsGame c = new CardsGame();
		c.pick();
		c.pick();
		c.pick();
		c.status();
		c.shuffle();
		c.status();
	}
}
