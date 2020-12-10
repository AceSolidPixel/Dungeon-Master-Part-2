import java.util.*;
/**
* class Hero - The Hero class aggregates to the Entity class and implements the Magical interface. With these two additional classes, the Hero object can play the game properly. The Hero's class functions aid the hero object in movement, attacking, and interaction with enemies and items. The implementation of the Magical interface allows the hero object to use magical attacks when called for.
*/
public class Hero extends Entity implements Magical
{
	/** Items hero has in inventory	*/
	private ArrayList<Item> items;
  
  /** Hero's map of the dungeon */
  private Map map;

	/** Hero's Location in dungeon */
	private Point location;

  /** Currency hero can spend in shops  */
  private int gold;

	/** 
	* Hero - Constructor for the hero object
	* @param n - Passes in name of hero
	* @param m - Passes in the current map the hero is in
	*/
	public Hero(String n, Map m)
	{
		super(n, 25);
		map = m;
		items = new ArrayList<Item>();
		location = map.findStart();
	}
	/**
	* toString - Overriden toString function from abstract class Entity that returns a String
	* @return Returns a string
	*/
	@Override
	public String toString()
	{
		StringBuilder returnString = new StringBuilder();
		returnString.append(super.toString());
		returnString.append("\n");
		returnString.append("Inventory:\n");
		if (this.getNumItems() != 0)
		{
			returnString.append(itemsToString());
		}
			return returnString.toString();
	}
	/**
	* itemsToString - a toString function that prints the hero's items
	* @return Returns a string 
	*/
	public String itemsToString()
	{
		StringBuilder returnString = new StringBuilder();
		for (int itemIt = 0; itemIt < getNumItems(); ++itemIt)
		{
			returnString.append(itemIt + 1 + ". " + items.get(itemIt).getName() 
                          + " ($" + items.get(itemIt).getValue() + ")\n");
		}
		return returnString.toString();
	}
	/**
	* Gets number of items in inventory and returns it
	* @return Number of items in inventory
	*/
	public int getNumItems()
	{
		return items.size();
	}
	/**
	* If inventory is not full, an item is added to items and true is returned, otherwise no item is added and false is returned.
	* @param i - Item to be picked up
	* @return -  Whether or not item was picked up
	*/
	public boolean pickUpItem(Item i)
	{
		final int MAX_ITEMS = 5;
		if (items.size() == MAX_ITEMS)
		{
			return false;
		}
		items.add(i);
		return true;
	}
	/**
	* If the Hero has a potion in their inventory, consume adding a maximum of 25 to their hp and removing the potion from their inventory
	*/
	public void drinkPotion()
	{
		final String POTION_NAME = "Health Potion";
		final int HEAL_AMOUNT = 25;
		for (int invIt = 0; invIt < items.size(); invIt++)
		{
			if (items.get(invIt).getName().equals(POTION_NAME))
			{
				super.heal(HEAL_AMOUNT);
				items.remove(invIt);
				break;
			}
		}
	}
	/**
	* Removes an element from the items array at the given index
	* @param index Index of element to be removed from items array 
	*/
	public Item dropItem(int index)
	{
    Item droppedItem = items.get(index);
		items.remove(index);
    return droppedItem;
	}
	/**
	* Returns true if hero has a potion, false if they do not.
	* @return Whether or not hero has a potion.
	*/
	public boolean hasPotion()
	{
		final String POTION_NAME = "Health Potion";
		for (int invIt = 0; invIt < items.size(); invIt++)
		{
			if (POTION_NAME.equals(items.get(invIt).getName()))
			{
				return true;
			}
		}
		return false;
	}
	/**
	* Returns location of hero
	* @return location of hero 
	*/
	public Point getLocation()
	{
		return location;
	}
	/**
	* Moves hero north if 1 space above is not out of bounds
	* @return - Value of spot hero was standing on before moving
	*/
	public char goNorth()
	{
		final int HIGHEST_Y = 0;
		char returnVal = map.getCharAtLocation(location);
		if (location.getY() > HIGHEST_Y)
		{
			location.translate(0, -1);
		}
		else
		{
			System.out.println("Cannot go any farther North!");
		}
		return returnVal;
	} 
	/**
	* Moves hero south if 1 space below is not out of bounds
	* @return - Value of spot hero was standing on before moving
	*/
	public char goSouth()
	{
		final int LOWEST_Y = 4;
		char returnVal = map.getCharAtLocation(location);

		if (location.getY() < LOWEST_Y)
		{
			location.translate(0, 1);
		}
		else
		{
			System.out.println("Cannot go any farther South!");
		}
		return returnVal;
	} 
	/**
	* Moves hero east if 1 space to the right is not out of bounds
	* @return - Value of spot hero was standing on before moving
	*/
	public char goEast()
	{
		final int RIGHTEST_X = 4;
		char returnVal = map.getCharAtLocation(location);

		if (location.getX() < RIGHTEST_X)
		{
			location.translate(1, 0);
		}
		else
		{
			System.out.println("Cannot go any farther East!");
		}
		return returnVal;
	} 
	/**
	* Moves hero west if 1 space to the left is not out of bounds
	* @return - Value of spot hero was standing on before moving
	*/
	public char goWest()
	{
		final int LEFTEST_X = 0;
		char returnVal = map.getCharAtLocation(location);

		if (location.getX() > LEFTEST_X)
		{
			location.translate(-1, 0);
		}
		else
		{
			System.out.println("Cannot go any farther West!");
		}
		return returnVal;
	}
	/**
	* attack - Overriden string function attack from Entity will allow the hero to attack an enemy. It will do some damage and return what attack it did and how much damage the hero incurred
	* @returns Returns a string 
	*/ 
	@Override 
	public String attack(Entity e)
	{
		//Random integer between 1 and enemy's max hp
		int randomDamage = (int) ((Math.random() * (e.getMaxHP())) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " attacks " + e.getName() + " for " + randomDamage + " damage.");
		
		return returnString.toString();
	}
	/**
	* magicMissile - Overriden string function magicMissile from Magical that allows the hero to attack an enemy using a magical attack called magic missile. The functions returns a string with the attack made and how much damage the hero incurred with this type of attack.
	* @param e - Passes in an entity such as an enemy or magical enemy
	* @return Returns a string
	*/
	@Override
	public String magicMissile(Entity e)
	{
		//Random integer between 1 and enemy's max hp
		int randomDamage = (int) ((Math.random() * (e.getMaxHP())) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " shoots " + e.getName() + " with a magic missile.\n");
		returnString.append(e.getName() + " takes " + randomDamage + " damage.");
		
		return returnString.toString();
	}
	/**
	* fireball - Overriden string function fireball from Magical that allows the hero to attack an enemy using a magical attack called fireball. The functions returns a string with the attack made and how much damage the hero incurred with this type of attack.
	* @param e - Passes in an entity such as an enemy or magical enemy
	* @return - Returns a string
	*/
	@Override
	public String fireball(Entity e)
	{
		//Random integer between 1 and enemy's max hp
		int randomDamage = (int) ((Math.random() * (e.getMaxHP())) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " hits " + e.getName() + " with a fireball for " + randomDamage);
		returnString.append("\ndamage.");
		
		return returnString.toString();
	}
	/**
	* thunderclap - Overriden string function thunderclap from Magical that allows the hero to attack an enemy using a magical attack called Thunderclap. The functions returns a string with the attack made and how much damage the hero incurred with this type of attack.
	* @param e - Passes in an entity such as an enemy or magical enemy
	* @return - Returns a string
	*/
	@Override
	public String thunderclap(Entity e)
	{
		//Random integer between 1 and enemy's max hp
		int randomDamage = (int) ((Math.random() * (e.getMaxHP())) + 1);
		e.takeDamage(randomDamage);

		StringBuilder returnString = new StringBuilder();
		returnString.append(this.getName() + " hits " + e.getName() + " with Thunderclap for " + randomDamage);
		returnString.append("\ndamage.");
		
		return returnString.toString();
	}

  /**
  * Gets the amount of gold the hero currently has
  * @return Current gold value
  */
  public int getGold()
  {
    return gold;
  }

  /**
  * Increases amount of gold the hero has
  * @param g Gold to be collected
  */
  public void collectGold(int g)
  {
    gold += g;
  }

  /**
  * Allows hero to get rid of gold in exchange for an item
  * @param g - Amount of gold to be spent on an item
  */
  public void spendGold(int g)
  {
    if (g <= gold)
    {
      gold -= g;
    }
    else
    {
      System.out.println("**COULD NOT SPEND GOLD - PRICE EXCEEDS CURRENT WEALTH**");
    }
  }

  /**
  * Checks if the hero has any armor items
  * @return - Index of first armor item, returns -1 if not found
  */
  public int hasArmorItem()
  {
    for (int itemIt = 0; itemIt < items.size(); ++itemIt)
    {
      if (items.get(itemIt).getType() == 'a')
      {
        return itemIt;
      }
    }
    return -1;
  }

  /**
  * Checks if the hero has any keys
  * @return - Index of first key in inventory, returns -1 if not found
  */
  public int hasKey()
  {
    for (int itemIt = 0; itemIt < items.size(); ++itemIt)
    {
      if (items.get(itemIt).getType() == 'k')
      {
        return itemIt;
      }
    }
    return -1;
  }


  /**
  * Uses first key in inventory to open the door.
  */
  public boolean useKey()
  {
    if (hasKey() == -1)
    {
      return false;
    }
    dropItem(hasKey());
    return true;
  }


  


}

