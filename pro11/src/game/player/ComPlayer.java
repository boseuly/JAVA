package game.player;

import java.util.Random;

import game.card.Bawi;
import game.card.Bo;
import game.card.Gawi;
import game.card.Hand;
import game.record.Record;

public class ComPlayer implements Player {
	
	Random rd = new Random();
	private String name = "Computer";
	private Hand hand;
	private Record record = new Record();
	
	@Override
	public void randomCardHand() {
		int rand = rd.nextInt(3);
		switch(rand) {
			case 0:
				hand = new Gawi(); break;
			case 1:
				hand = new Bawi(); break;
			case 2:
				hand = new Bo(); break;
		}
	}

	@Override
	public String versus(Hand h) {
		String result = "";
		switch(hand.compare(h)) {
			case -1:
				record.addLose();
				result = "패"; break;
			case 0:
				record.addDraw();
				result = "무"; break;
			case 1:
				record.addWin();
				result = "승"; break;
		}
		return result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Hand getHand() {
		return this.hand;
	}

}
