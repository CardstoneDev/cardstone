package events;

import java.util.List;

import cardgamelibrary.Card;
import cardgamelibrary.Event;
import cardgamelibrary.EventType;

public class CardDrawnEvent implements Event {

	private List<Card> drawn;

	public CardDrawnEvent(List<Card> drawn) {
		this.drawn = drawn;
	}

	@Override
	public EventType getType() {
		return EventType.CARD_DRAWN;
	}

	@Override
	public List<Card> getAffected() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Card> getDrawn() {
		return drawn;
	}

}
