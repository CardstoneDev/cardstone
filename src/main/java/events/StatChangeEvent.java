package events;



import cardgamelibrary.Creature;
import cardgamelibrary.Event;
import cardgamelibrary.EventType;

public class StatChangeEvent implements Event{

	private EventType eventType;
	private Creature target;
	private int amount;

	public StatChangeEvent(EventType type, Creature target, int amount) {
		this.eventType = type;
		this.target = target;
		this.amount = amount;
	}
	
	public EventType getType(){
		return eventType;
	}
	
	public Creature getTarget(){
		return target;
	}
	
	public int getAmount(){
		return amount;
	}

}
