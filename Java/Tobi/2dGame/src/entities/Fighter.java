package entities;

public class Fighter extends Entity{
	private int health;
	private int power;
	
	public Fighter(){
		super(100,100);
		health = 100;
		power = 100;
	}
	public void hit(final int damage){
		health = health-damage;
	}public void heal(final int recovery){
		if(health+recovery > 100)
			health = 100;
		else
			health += recovery;
	}
	
	public void powerup(final int increase){
		if(power + increase > 100)
			power = 100;
		else
			power = power + increase;
	}
	public void powerdown(final int decrease){
		
		if(power - decrease < 0)
			power = 0;
		else
			power -= decrease;
	}
} 
