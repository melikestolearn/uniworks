package entities;

import entities.constants.Constants;
//Weapons in the game hold by the player
public class Weapon extends Entity{
	private int hitPower;
		public Weapon(int weaponnumber){	
				super(20,20);
				hitPower = Constants.SWORD_HIT_DAMAGE;
		}
}

