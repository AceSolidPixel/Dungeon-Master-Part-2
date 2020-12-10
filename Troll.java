/**
* Base enemy for the troll, this troll can be upgraded with either Warlock or Warrior Decorations
*/
public class Troll extends Enemy
{
    /**
    * Constructor for this class, Sets the initial HP to 5, sets the base attack to 3 and name to Troll
    * @param i Item for troll to hold
    */
    public Troll(Item i)
    {
      super("Troll", 5, 3, i);
    }
}