class animationsMaker{	
	static getAttackAnimation(attacker,defender){
		let anim = new linearAnimation();
		let attackerCard = cardCache.getByIID(attacker);
        console.log(attacker);
		anim.setColorSet(attackerCard.cost.manaPool.getColors());
		anim.setShape("circle");
		anim.setRadius(2);
		anim.setCount(200);
		anim.setSpeed(.6);
		anim.setRandom(true);
		anim.setStartDivById(attacker);
		anim.setEndDivById(defender);
		return anim;
	}
	
	static getDamagedAnimation(damaged){
		let anim = new singleRadial();
		anim.setColor("red");
		anim.setShape("circle");
		anim.setRadius(130);
		anim.setSpeed(.4);
		anim.setCenterDivById(damaged);
		anim.setCount(3);
		return anim;
	}
}