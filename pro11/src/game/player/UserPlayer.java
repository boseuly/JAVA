package game.player;

import java.util.Random;

import game.card.Bawi;
import game.card.Bo;
import game.card.Gawi;
import game.card.Hand;
import game.record.Record;

public class UserPlayer implements Player {
	
	Random rd = new Random();
	private String name;
	private Hand hand;
	private Record record = new Record();
	private int[] recordArray = new int[3];
	
	public UserPlayer() {}
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
	
	public String getRecord() {
		String result = "";
		result = String.format("%d 승 %d 패 %d 무",
				record.getWin(), record.getLose(), record.getDraw());
		return result;
	}

	public void setRecordArray(int[] record) {	// 이전의 기록을 불러오는
		this.record.setScore(record);
		
	}

	public int[] getRecordArray() {				// 받은 점수의 배열을 만들어야 한다.
		return this.record.getScore();
	}

}
