package events;

import cardgamelibrary.Card;
import cardgamelibrary.Event;
import cardgamelibrary.EventType;
import game.Player;

public class PlayerDamagedEvent extends Event {

	private Card		src;
	private Player	p;
	private int			dmg;

	public PlayerDamagedEvent(Card src, Player p, int dmg) {
		super();
		this.src = src;
		this.p = p;
		this.dmg = dmg;
	}

	@Override
	public EventType getType() {
		// TODO Auto-generated method stub
		return EventType.PLAYER_DAMAGED;
	}

	public Card getSrc() {
		return src;
	}

	public Player getPlayer() {
		return p;
	}

	public int getDmg() {
		return dmg;
	}

}
