import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
/**
* Generates Items from the file ItemList.txt, this class is a singleton. Uses prototype desing pattern to clone
* items.
*/
public class ItemGenerator
{
  /** Single instance of ItemGenerator that is shared throughout the program  */
  private static ItemGenerator instance = null;


	/** itemList - ArrayList that contains items that can be generated	*/
	private ArrayList<Item> itemList = new ArrayList<Item>();
	
  /**
	* Constructor ItemGenerator - Generates Items from the file, created when enemy is created, either magical or regular
	*/
	public ItemGenerator() 
	{
		File ItemList = new File("ItemList.txt");
		try
		{
			Scanner sn = new Scanner(ItemList);
      sn.useDelimiter("[,\n]");
			do 
			{
				String name = sn.next();
        int value = Integer.parseInt(sn.next());
        char type = sn.next().charAt(0);
				Item item = new Item(name, value, type);
				this.itemList.add(item);
			} while (sn.hasNextLine());
			sn.close();
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("File Not Found.");
		}	
	}	
	/**
	* Copies an item from the item list using prototype cloning
	* @return - Returns the item
	*/
	public Item generateItem() 
	{
		Random rand = new Random();
		int ran = rand.nextInt(this.itemList.size());
    Prototype newItem = itemList.get(ran).clone();
		return (Item) newItem;
	}

  /**
  * Generates a potion using prototype cloning 
  * @return - Potion item
  */
  public Item getPotion()
  {
    Prototype newPotion = null;
    for (int itemListIt = 0; itemListIt < itemList.size(); ++itemListIt)
    {
      
      if (itemList.get(itemListIt).getType() == 'p')
      {
        newPotion = itemList.get(itemListIt).clone();
      }
    }
    return (Item) newPotion;
  }

  /**
  * Generates a key using the prototype design pattern
  * @return - Key item
  */
  public Item getKey()
  {
    Prototype newKey = null;
    for (int itemListIt = 0; itemListIt < itemList.size(); ++itemListIt)
    {
      
      if (itemList.get(itemListIt).getType() == 'k')
      {
        newKey = itemList.get(itemListIt).clone();
      }
    }
    return (Item) newKey;
  }

  /**
  * Gets the single instance of the item generator. If there is none,
  * one is created.
  * @return - Single instance of item generator 
  */
  public static ItemGenerator getInstance()
  {
    if (instance == null)
    {
      instance = new ItemGenerator();
    }
    return instance;
  }

}