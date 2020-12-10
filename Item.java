/**
* class Item - class that constructs and returns the name of an item
*/
class Item implements Prototype
{
	/** name - Name of item	*/
	private String name;

  /** Amount of gold the item is worth at the store */
  private int value;

  /** Type of item it is: potion, key, money, or armor */
  private char type;


	/**
	* @method Constructor - Initializes with specific value of name
	* @param n - Passes in the name of the item
	*/
	public Item(String n, int v, char t) 
	{
		name = n;
    value = v;
    type = t;
	}

  /**
  * Copy constructor
  * @param Item i - item to be copied from
  */
  public Item(Item i)
  {
    if (i != null)
    {
      name = i.getName();
      value = i.getValue();
      type = i.getType();
    }
  }


	/**
	* getName gets the name of the item and returns it
	* @return - Returns a string
  */
	public String getName() 
	{
		return name;
	}

  /**
  * Returns the shop value of the item
  * @return - Value of item in the shop
  */
  public int getValue()
  {
    return value;
  }

  /**
  * Returns the item type
  * @return - Item type
  */
  public char getType()
  {
    return type;
  }

  /**
  * Returns a deep copy of the item
  * @return - Deep copy of the item
  */
  @Override
  public Prototype clone()
  {
    return new Item(this);
  }
}