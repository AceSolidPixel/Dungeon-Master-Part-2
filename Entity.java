/**
* class Entity - abstract class Entity provides all functions for the child classes Hero and Enemy; along with abstract functions that can be overriden, it provides an overall structure for the child classes interaction. When the child classes call super, any parameters passed will pass directly through the constructor
*/
public abstract class Entity 
{
	/** Name of the entity	*/
	private String name;
	/** Max hit points	*/
	private int maxHP;
	/** Current health points value	*/
	private int hp;
	/**
	* Constructor - passes in name and max health points of entity Health points and sets them
	* @param n -  Name of Entity
	* @param mHP - Max Health Points
	*/
	public Entity(String n, int mHP) 
	{
		name = n;
		maxHP = mHP;
		hp = maxHP;
	}
	/**
	* Entity's attack move to damage other entities (removing their HP)
  * @param Entity e - The entity that will be attacked
  * @return - returns a String containing the result of the attack
	*/
	public abstract String attack(Entity e);
	/**
	* Gets name of the Entity and returns it
	* @return - Name of Entity
	*/
	public String getName() 
  {
		return name;
	}
	/**
	* Gets current health points and returns them
	* @return - Current health points
	*/
	public int getHP() 
  {
		return hp;
	}
	/**
	* Returns max health points
	* @return - Max Health Points
	*/
	public int getMaxHP() 
  {
		return maxHP;
	}

	/**
	* Increases current health points of entity up to a maximum of maxHP
  * @param int h - The amount by which the entity will be healed by
	*/
	public void heal(int h) 
  {
		int newHealth = hp + h;
		if (newHealth > maxHP) //HP cannot exceed maxHP
    {
			newHealth = maxHP;
		}
		hp = newHealth;
	}
	/**
	* Decreases hp of entity to minimum value of 0
  * @param int h - The amount of damage the entity will take
	*/
	public void takeDamage(int h) 
  {
		int newHealth = hp - h;

		if (newHealth < 0) 
    {
			newHealth = 0;
		}

		hp = newHealth;
	}
	/**
	* toString - Creates a general toString function and returns it
	* @return Returns a string that displays the entity's HP/maxHP
	*/
	public String toString() 
  {
		StringBuilder returnString = new StringBuilder();
    	returnString.append(this.getName());
    	returnString.append("\n");
    	returnString.append("HP: " + this.getHP() + "/" + this.getMaxHP());
		return returnString.toString();
	}
}