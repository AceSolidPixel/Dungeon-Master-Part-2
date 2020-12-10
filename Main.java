import java.util.ArrayList;

/*
 Name: Blake Del Rey and Prince Angulo
 Due Date: 12 - 8 - 2020
 Purpose: The purpose of this Project is to expand upon our dungeon crawler game to
          make the gameplay more complex while at the same time implementing various
          design patterns to enhance the flexibility of our code.
*/


class Main 
{
	public static void main(String[] args) 
	{
		final char FINISH_CHAR = 'f';
		final char MONSTER_CHAR = 'm';
		final char ITEM_CHAR = 'i';
    final char START_CHAR = 's';
    boolean justRun = false;

		ItemGenerator itemMaker = ItemGenerator.getInstance();
		EnemyGenerator enemyMaker = EnemyGenerator.getInstance();

		int levelNum = 1;
		Map currentMap = Map.getInstance();

    System.out.print("What is your name, traveler? ");
    String heroName = CheckInput.getString();
		Hero protag = new Hero(heroName, currentMap);

		int userChoice = 0;

    //Since the hero starts on the starting space, give option to enter the shop
    System.out.println("You are on the start space for this map!");
    System.out.println("Would you like to enter Celine's Item Shop?");
    System.out.println("1. Enter");
    System.out.println("2. Continue on with Journey");
    int enterChoice = CheckInput.getIntRange(1,2);
    if (enterChoice == 1)
    {
      store(protag);
    }

		while (userChoice != 5 && protag.getHP() != 0)
		{
      System.out.print(protag.toString());
      currentMap.displayMap(protag.getLocation());

      System.out.println("1. Go North");
      System.out.println("2. Go South");
      System.out.println("3. Go East");
      System.out.println("4. Go West");
      System.out.println("5. Quit");
      
      userChoice = CheckInput.getIntRange(1,5);
      switch (userChoice)
      {
        case 1:
        currentMap.reveal(protag.getLocation());
        protag.goNorth();
        break;
        
        case 2:
        currentMap.reveal(protag.getLocation());
        protag.goSouth();
        break;
        
        case 3:
        currentMap.reveal(protag.getLocation());
        protag.goEast();
        break;
        
        case 4:
        currentMap.reveal(protag.getLocation());
        protag.goWest();
        break;
        
        case 5:
        break;
        
        default:
        System.out.println("Invalid Input! Try Again!");
      }
      
			if (currentMap.getCharAtLocation(protag.getLocation()) == FINISH_CHAR)
			{
        System.out.println("You have found the end of the level!");
        if (protag.hasKey() == -1)
        {
          System.out.println("Sadly you don't have any keys to open the door"
                            +" to the next level:(");
          System.out.println("Come back when you have attained a key.");
        }
        else
        {
          System.out.println("Would you like to use a key to advance to the next level?");
          System.out.println("Or would you like to explore this level more?");
          System.out.println("1. Advance to next level (use key)");
          System.out.println("2. Explore this level some more");
          int advanceChoice = CheckInput.getIntRange(1,2);
          if (advanceChoice == 1)
          {
            protag.useKey();
            System.out.println("Congrats you passed level " + levelNum + "!");
            ++levelNum;

            System.out.println("Level " + levelNum + " will now be loaded...");

            currentMap.loadMap(levelNum);

            protag.heal(protag.getMaxHP());
            System.out.println(protag.getName() + "\'s HP has been restored!");
          }
          else
          {
            System.out.println("Have fun exploring!");
          }
        }
				
			}
			if (currentMap.getCharAtLocation(protag.getLocation()) == MONSTER_CHAR)
			{
				monsterRoom(protag, levelNum);
			}
			if (currentMap.getCharAtLocation(protag.getLocation()) == ITEM_CHAR)
			{
				itemRoom(protag);
			}
      if (currentMap.getCharAtLocation(protag.getLocation()) == START_CHAR 
          && userChoice != 5)
      {
        System.out.println("You are on the start space for this map!");
        System.out.println("Would you like to enter Celine's Item Shop?");
        System.out.println("1. Enter");
        System.out.println("2. Continue on with Journey");
        enterChoice = CheckInput.getIntRange(1,2);
        if (enterChoice == 1)
        {
          store(protag);
        }
      }
	  }
    System.out.println("Game Over");
  }

	/**
	* Method that executes when hero object encounters a monster room 
	* @param h - Type Hero class that passes in the current Hero object
	* @param level - Passes in the current level the Hero object is in
	* @return - Returns true if monster is defeated, false otherwise
	*/
	public static boolean monsterRoom(Hero h, int level)
	{
    final char MONSTER_CHAR = 'm';
		final int FIGHT_NUM = 1;
		final int RUN_NUM = 2;

		final int PHYSICAL_NUM = 1;
		final int MAGICAL_NUM = 2;

		final int NUM_MAGIC_OPTIONS = 3;

		final int TOTAL_DIRECTIONS = 4;

		final int MAP_SIZE = 5;

		Enemy enemy = EnemyGenerator.getInstance().generateEnemy(level); 
    Map m = Map.getInstance();

		int userChoice = 0;

		boolean runFlag = false;
		int maxOptions = 2;

    System.out.println("You've encountered a " + enemy.getName());
		while(enemy.getHP() != 0 && !runFlag && h.getHP() != 0)
		{
			System.out.println(enemy.toString());
			System.out.println("1. Fight");
			System.out.println("2. Run Away");
			if (h.hasPotion())
			{
				maxOptions = 3;
				System.out.println("3. Drink Potion");
			}
			userChoice = CheckInput.getIntRange(FIGHT_NUM, maxOptions);
			if (userChoice == FIGHT_NUM)
			{
				System.out.println("1. Physical Attack");
				System.out.println("2. Magic Attack");
				userChoice = CheckInput.getIntRange(PHYSICAL_NUM, MAGICAL_NUM);
				if (userChoice == PHYSICAL_NUM)
				{
					System.out.println(h.attack(enemy));
				}
				else
				{
					System.out.println(Magical.MAGIC_MENU);
					userChoice = CheckInput.getIntRange(1, NUM_MAGIC_OPTIONS);
					if (userChoice == 1)
					{
						System.out.println(h.magicMissile(enemy));
					}
					else if (userChoice == 2)
					{
						System.out.println(h.fireball(enemy));
					}
					else
					{
						System.out.println(h.thunderclap(enemy));
					}
				}
			} 
			else if (userChoice == RUN_NUM)
			{
				runFlag = true;
			}
			else
			{
				h.drinkPotion();
			}
			if (enemy.getHP() != 0 && !runFlag)
			{
        if (h.hasArmorItem() == -1)
        {
          System.out.println(enemy.attack(h));
        }
				else
        {
          Item armorItem = h.dropItem(h.hasArmorItem());
          if (enemy instanceof WarlockDecoration)
          {
            System.out.println(h.getName() + " deflects " + enemy.getName() + "\'s spells using "
                            + armorItem.getName() + "\nand takes no damage.");
          }
          else
          {
            System.out.println(h.getName() + " deflects " + enemy.getName() + "\'s attack using "
                            + armorItem.getName() + "\nand takes no damage.");
          }
          System.out.println(armorItem.getName() + " was destroyed in the process.");
        }
        
			}
    }

		// CASE: USER DEFEATS MONSTER
		if (!runFlag && h.getHP() != 0 )
		{
			// Remove monster space 
			System.out.println("You defeated the " + enemy.getName());
			System.out.println("You found a " + enemy.getItem().getName() + " in its corpse.");
			m.removeCharAtLoc(h.getLocation());
			
			// Pickup item / drop item
			if (!h.pickUpItem(enemy.getItem()))
			{
				System.out.println("Inventory Full. Select Item To Drop");
				System.out.print(h.itemsToString());
				System.out.println(h.getNumItems() + 1 + ". (new item): " + enemy.getItem().getName()
                          + " ($" + enemy.getItem().getValue() + ")");
				int itemToDrop = CheckInput.getIntRange(1, h.getNumItems() + 1);
				if (itemToDrop <= h.getNumItems())
				{
					Item droppedItem = h.dropItem(itemToDrop - 1);
          System.out.println(h.getName() + " dropped " + droppedItem.getName());
					h.pickUpItem(enemy.getItem());
					System.out.println(h.getName() + " picked up " + enemy.getItem().getName());
				}
				else
				{
					System.out.println(h.getName() + " did not pick up " + enemy.getItem().getName());
				}
				
			}
			else
			{
				System.out.println(h.getName() + " picked up " + enemy.getItem().getName());
			}
			return true;
		}
		
		// CASE: USER RUNS
		if (h.getHP() != 0)
		{
			boolean validDirection = false;
			while (!validDirection)
			{
				// Random integer between 1 and number of directions player can move
				int randomDirection = (int) ((Math.random() * (TOTAL_DIRECTIONS)) + 1);

				switch(randomDirection)
				{
					case 1:
						if (h.getLocation().getY() - 1 >= 0)
						{
							m.reveal(h.getLocation());
							h.goNorth();
							validDirection = true;
							System.out.println(h.getName() + " successfully ran north!");
						}
						break;
					
					case 2:
						if (h.getLocation().getY() + 1 < MAP_SIZE)
						{
							m.reveal(h.getLocation());
							h.goSouth();
							validDirection = true;
							System.out.println(h.getName() + " successfully ran south!");
						}
						break;
					
					case 3:
						if (h.getLocation().getX() + 1 < MAP_SIZE)
						{
							m.reveal(h.getLocation());
							h.goEast();
							validDirection = true;
							System.out.println(h.getName() + " successfully ran east!");
						}
						break;

					case 4:
						if (h.getLocation().getX() - 1 > MAP_SIZE)
						{
							m.reveal(h.getLocation());
							h.goWest();
							validDirection = true;
							System.out.println(h.getName() + " successfully ran west!");
						}
						break;
				}
			}
      if (m.getCharAtLocation(h.getLocation()) == MONSTER_CHAR)
			{
				monsterRoom(h, level);
			}
		}
		return false;
	}

	/**
	* Method that executes when hero object encounters an item room
	* @param h - Type Hero class that passes in the current Hero object
	*/
	public static void itemRoom(Hero h)
	{
		Item newItem = ItemGenerator.getInstance().generateItem();
    Map m = Map.getInstance();
		System.out.println("You found a " + newItem.getName());
		if (!h.pickUpItem(newItem))
		{
			System.out.println("Inventory Full. Select Item To Drop");
			System.out.print(h.itemsToString());
			System.out.println(h.getNumItems() + 1 + ". (new item): " + newItem.getName()
                        + " ($" + newItem.getValue() + ")");
			int itemToDrop = CheckInput.getIntRange(1, h.getNumItems() + 1);
			if (itemToDrop <= h.getNumItems())
			{
				Item droppedItem = h.dropItem(itemToDrop - 1);
        System.out.println(h.getName() + " dropped " + droppedItem.getName());
        
				h.pickUpItem(newItem);
				System.out.println(h.getName() + " picked up " + newItem.getName());
        		m.removeCharAtLoc(h.getLocation());
			}
			else
			{
				System.out.println(h.getName() + " did not pick up " + newItem.getName());
			}
		}
		else
		{
			System.out.println(h.getName() + " picked up " + newItem.getName());
      		m.removeCharAtLoc(h.getLocation());
		}
	}



  /**
  * Simulates a store that the hero can buy or sell items from
  *
  * @param h Hero shopping at the store
  */
  public static void store(Hero h)
  {
    ItemGenerator ig = ItemGenerator.getInstance();
    int shopChoice = 0;
    int itemChoice = 0;
    Item soldItem;
    boolean isFirstTransaction = true;

    System.out.println("Current gold: $" + h.getGold());
    System.out.println("Welcome to Celine's Potion and Key Shop");
    System.out.println("What would you like to do?");
    
    while(shopChoice != 3)
    {
      //Displays different text on transactions after the first
      if (!isFirstTransaction)
      {
        System.out.println("Current gold: $" + h.getGold());
        System.out.println("Would you like to make any more transactions?");
      }
      
      System.out.println("1. Buy");
      System.out.println("2. Sell");
      System.out.println("3. Exit");
      shopChoice = CheckInput.getIntRange(1,3);
      if (shopChoice == 1)
      {
        System.out.println("Current gold: $" + h.getGold());
        System.out.println("What would you like to buy?");
        System.out.println("1. Potion (" + ig.getPotion().getValue() + ")");
        System.out.println("2. Key (" + ig.getKey().getValue() + ")");
        itemChoice = CheckInput.getIntRange(1, 2);

        //User tries to buy potion
        if (itemChoice == 1)
        {
          if (h.getGold() >= ig.getPotion().getValue())
          {
            if (h.pickUpItem(ig.getPotion()))
            {
              h.spendGold(ig.getPotion().getValue());
            }
            else
            {
              System.out.println("**INVENTORY FULL - PURCHASE COULD NOT BE MADE**");
            }
          }
          else
          {
            System.out.println("**COULD NOT SPEND GOLD - PRICE EXCEEDS CURRENT WEALTH**");
          }
        }
        //User tries to buy Key
        else if (itemChoice == 2)
        {
          if (h.getGold() >= ig.getKey().getValue())
          {
            if (h.pickUpItem(ig.getKey()))
            {
              h.spendGold(ig.getKey().getValue());
            }
            else
            {
              System.out.println("**INVENTORY FULL - PURCHASE COULD NOT BE MADE**");
            }
          }
          else
          {
            System.out.println("**COULD NOT SPEND GOLD - PRICE EXCEEDS CURRENT WEALTH**");
          }
        }
      }
      //User wants to sell item
      else if (shopChoice == 2)
      {
        if (h.getNumItems() > 0)
        {
          System.out.println("Current gold: $" + h.getGold());
          System.out.println("What item would you like to sell?");
          System.out.print(h.itemsToString());
          itemChoice = CheckInput.getIntRange(1, h.getNumItems());
          soldItem = h.dropItem(itemChoice - 1);
          System.out.println(h.getName() + " dropped " + soldItem.getName());
          h.collectGold(soldItem.getValue());
          System.out.println("Celine hands you " + soldItem.getValue() + " gold");
          System.out.println("You now have " + h.getGold() + " gold");
        }
        else
        {
          System.out.println("**NO ITEMS IN INVENTORY TO SELL**");
        }
      }
      isFirstTransaction = false;

    }
    
  }
}