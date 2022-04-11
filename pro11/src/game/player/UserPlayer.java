package game.player;

import java.util.Random;

import game.card.Bawi;
import game.card.Bo;
import game.card.Gawi;
import game.card.Hand;

public class UserPlayer implements Player {
	
	Random rd = new Random(12345);
	private String name;
	private Hand hand;
	
	public UserPlayer(String name) {
		this.name = name;
	}
	
	public void setCardHand(String name) {
		if(name.equals("가위")) {
			hand = new Gawi();
		} else if(name.equals("바위")) {
			hand = new Bawi();
		} else if(name.equals("보")) {
			hand = new Bo();
		} else {
			this.randomCardHand();
		}
	}
	
	@Override
	public void randomCardHand() {
		Hand card = null;
		int rand = rd.nextInt(3);
		switch(rand) {
			case 0:
				card = new Gawi(); break;
			case 1:
				card = new Bawi(); break;
			case 2:
				card = new Bo(); break;
		}
	}

	@Override
	public int versus(Hand h) {
		
		return hand.compare(h);
	}
	public Hand getHand() {
		return this.hand;
	}
	
	public String getName() {
		return this.name;
		
	}
}
