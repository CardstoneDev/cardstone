package templates.decorators;

import cardgamelibrary.Creature;
import cardgamelibrary.CreatureInterface;
import cardgamelibrary.Effect;
import cardgamelibrary.Event;
import cardgamelibrary.EventType;
import cardgamelibrary.Zone;
import events.CreatureAttackEvent;
import events.PlayerAttackEvent;
import game.Player;
import templates.CantAttackCreature;


//Proof of concept for the wrappers (of joy)
public class CantAttackForTurnsCreature extends CreatureWrapper{

	private int turns;

	public CantAttackForTurnsCreature(CreatureInterface c, int turns){
		super(c);
		this.turns = turns;
	}
	
	public Effect onTurnEnd(Player p, Zone z) {
		if(turns >= 0){
			turns--;
		}
		return super.onTurnEnd(p, z);
	}
	
	public boolean onProposedLegalityEvent(Event e, Zone z){
		if(turns > 0){
			if(e.getType() == EventType.CREATURE_ATTACKED){
				CreatureAttackEvent eve = (CreatureAttackEvent)e;
				return getAllowedAttack(eve.getAttacker(),eve.getTarget());
			}
			else if(e.getType() == EventType.PLAYER_ATTACKED){
				PlayerAttackEvent eve = (PlayerAttackEvent)e;
				return getAllowedAttack(eve.getAttacker());
			}
		}
		return false || internal.onProposedEvent(e, z);
	}
	
	public String getComplaint(Event e, Zone z){
		return "This creature may not attack";
	}

	public boolean getAllowedAttack(Creature attacker, Creature target){
		if(attacker == this.internal){
			return true;
		}
		return false;
	}

	public boolean getAllowedAttack(Creature attacker){
		if(attacker == this.internal){
			return true;
		}
		return false;
	}
}
