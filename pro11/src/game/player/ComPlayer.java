package game.player;

import java.util.Random;

import game.card.Bawi;
import game.card.Bo;
import game.card.Gawi;
import game.card.Hand;

public class ComPlayer implements Player {
	
	Random rd = new Random(67890);
	private String name = "Computer";
	private Hand hand;

	@Override
	public void randomCardHand() {
		int rand = rd.nextInt(3);
		switch(rand) {
			case 0:
				hand= new Gawi(); break;
			case 1:
				hand = new Bawi(); break;
			case 2:
				hand = new Bo(); break;
		}
	}

	@Override
	public int versus(Hand h) {
		return hand.compare(h);
	}
	public String getName() {
		return this.name;
	}

}
