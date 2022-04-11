package game.player;

import game.card.Hand;

public interface Player {
	// 임의의 가위,바위,보가 반환되도록 한다.
	public void randomCardHand();
	
	// 가위, 바위, 보 대결을 진행한다.
	public int versus(Hand h);
}
