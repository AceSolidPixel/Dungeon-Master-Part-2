/**
* Class Enemy - Enemy class extends abstract Entity class, it is also the parent class for MagicalEnemy and overrides super class Entity functions such as attack and toString. 
Allows the enemy to interact with the hero
*/
public abstract class Enemy extends Entity 
{
	/** item - Item enemy has in inventory	*/
	private Item item;
  /* Stat that dictate's an Enemy's attack power. Will be increased as the player progresses through the dungeon */
  private int attack;
	/** 
	* Constructor - Passes in name of the enemy, maxHp, and item to use
  * @param String n - String that is passed containing the Enemy's name
  * @param int mHp - The Enemy's maximum HP
  * @param int a - The Enemy's base attack power
  * @param Item I - The item that the Enemy will carry
	*/
	public Enemy(String n, int mHp, int a, Item i)
  {
		super(n, mHp);
		item = i;
    attack = a;
	}
	/**
	* This method gets the enemy's item
	* @return Returns item
	*/
	public Item getItem() 
  {
		return item;
	}
  /**
  * The getAttack method returns the enemy's attack stat
  * @return - returns the enemy's attack stat
  */
  public int getAttack()
  {
    return attack;
  }
	/**
	* The attack method passes in an entity e which performs an attack a returns a String
	* @param e - Of type entity which is passed in to perform an * attack
	* @return - Returns a String containing the results of the monster's attack
	*/
	@Override
	public String attack(Entity e)
	{
		final int MAX_DAMAGE = attack * 2;

    
		int randomDamage = (int) ((Math.random() * (MAX_DAMAGE)) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " attacks " + e.getName() + " for " + randomDamage + " damage.");
		
		return returnString.toString();
	} 
}