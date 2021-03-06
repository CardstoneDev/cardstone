package cards;

import cardgamelibrary.Board;
import cardgamelibrary.Card;
import cardgamelibrary.CardType;
import cardgamelibrary.Effect;
import cardgamelibrary.ManaPool;
import cardgamelibrary.SpellCard;
import cardgamelibrary.Zone;
import effects.AddToOccEffect;
import game.Player;
import templates.TargetsOtherCard;

public class FellTitan extends SpellCard implements TargetsOtherCard{

	private static final String		defaultImage	= "images/FellTitan.jpg";
	private static final String		defaultName		= "Fell Titan";
	private static final String		defaultText		= "Destroy target creature that costs 50 or more gold.";
	private static final CardType	defaultType		= CardType.SPELL;
	
	public FellTitan(Player owner) {
		super(new ManaPool(10, 0, 0, 0, 0, 2), defaultImage, owner, defaultName, defaultText, defaultType);
	}

	@Override
	public boolean cardValidTarget(Card card, Zone targetIn) {
		if(targetIn == Zone.CREATURE_BOARD){
			if(card.getCost().getResources() >= 50){
				return true;
			}
		}
		return false;
	}

	@Override
	public Effect impactCardTarget(Card target, Zone targetIn) {
		assert cardValidTarget(target,targetIn);
		return new AddToOccEffect(target,target.getOwner(),Zone.CREATURE_BOARD,Zone.GRAVE,this);
	}

	
	
}
