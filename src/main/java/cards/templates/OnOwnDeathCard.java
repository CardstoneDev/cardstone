package cards.templates;

import cardgamelibrary.Board;
import cardgamelibrary.Card;
import cardgamelibrary.Creature;
import cardgamelibrary.Effect;
import cardgamelibrary.Zone;
import effects.EmptyEffect;

public interface OnOwnDeathCard extends Card{

	default public Effect onCreatureDeath(Creature cr, Zone z) {
		if(cr != this){
			return EmptyEffect.create();
		}
		else{
			return this.onDeathEffect(z);
		}
	}
	
	public Effect onDeathEffect(Zone z);
	
}
