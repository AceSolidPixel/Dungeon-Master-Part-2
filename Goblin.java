/**
* Base enemy class for Goblin that can get decorated with either warrior or warlock stats and abilities
*/
public class Goblin extends Enemy
{
    /**
    * Constructor for this class, Sets the initial HP to 2, sets the base attack to 2 and name to Goblin
    *
    * @param i Item for Goblin to hold
    */
    public Goblin(Item i)
    {
      super("Goblin", 2, 2, i);
    }
}