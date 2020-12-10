/**
* Decoration that gives an enemy the stat bonuses and title of a warlock while also providing
* the enemy an attack method that can choose from various spells
*/
public class WarlockDecoration extends EnemyDecorator implements Magical
{

  /**
  * Adds Warlock adjective and bonus health stats to passed in monster.
  * Warlocks get bonus 1 hp and bonus 1 attack.
  * @param newEnemy Enemy to which the warlock bonus is granted
  */
  public WarlockDecoration(Enemy newEnemy)
  {
    super(newEnemy,  newEnemy.getName() + " Warlock", newEnemy.getHP() + 1, newEnemy.getAttack() + 1, newEnemy.getItem());
  }
  /**
	* attack - Overriden method that the magical enemy uses to attack the hero, can use any of the magical attacks depending on the random number generated
	* @param e - Entity passed in
	* return - Returns a string containing the result of the attack
	*/
	@Override
	public String attack(Entity e)
	{
    final int NUM_ATTACKS = 3;
		// Random integer between 1 and hero's max hp
		int randomAttack = (int) ((Math.random() * (NUM_ATTACKS)) + 1);
		switch(randomAttack)
		{
			case 1:
				return magicMissile(e);
			
			case 2:
				return fireball(e);
			
			case 3:
				return thunderclap(e);
		}
		// If invalid random num
		return magicMissile(e);
	}
	/**
	* magicMissile - Overriden string function magicMissile from Magical that allows the magical enemy to attack the hero using a magical attack called magic missile. The functions returns a string with the attack made and how much damage the enemy incurred with this type of attack.
	* @param e - Passes in an entity such as the hero
	* @return -  Returns a string
	*/
	@Override
	public String magicMissile(Entity e)
	{
		final int MAX_DAMAGE = getAttack();

		//Random integer between 1 and what the attack stat 
		int randomDamage = (int) ((Math.random() * (MAX_DAMAGE)) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " shoots " + e.getName() + " with a magic missile.\n");
		returnString.append(e.getName() + " takes " + randomDamage + " damage.");
		
		return returnString.toString();
	}
	/**
	* fireball - Overriden string function fireball from Magical that allows the magical enemy to attack the hero using a magical attack called fireball. The functions returns a string with the attack made and how much damage the enemy incurred with this type of attack.
	* @param e - Passes in an entity such as the hero
	* @return - Returns a string
	*/
	@Override
	public String fireball(Entity e)
	{
		final int MAX_DAMAGE = getAttack() * 2;
		//Random integer between 1 and double the attack stat of the enemy
		int randomDamage = (int) ((Math.random() * (MAX_DAMAGE)) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " hits " + e.getName() + " with a fireball for " + randomDamage);
		returnString.append("\ndamage.");
		
		return returnString.toString();
	}
  
	/**
	* thunderclap - Overriden string function thunderclap from Magical that allows the magical enemy to attack the hero using a magical attack called Thunderclap. The functions returns a string with the attack made and how much damage the enemy incurred with this type of attack.
	* @param e - Passes in an entity such as the hero
	* @return - Returns a string
	*/
	@Override
	public String thunderclap(Entity e)
	{
		final int MAX_DAMAGE = getAttack() * 3;
		//Random integer between 1 and triple the enemy's attack stat
		int randomDamage = (int) ((Math.random() * (MAX_DAMAGE)) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " hits " + e.getName() + " with Thunderclap for " + randomDamage);
		returnString.append("\ndamage.");
		
		return returnString.toString();
	}

}
