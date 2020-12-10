/**
* Base enemy for the froglock monster. Can get decorated with either warlock or warrior stats and abilities
*/
public class Froglock extends Enemy
{
    /**
    * Constructor for this class, Sets the initial HP to 2, sets the base attack to 5, and name to Froglock
    *
    * @param i Item for Froglock to hold
    */
    public Froglock(Item i)
    {
      super("Froglock", 2, 5, i);
    }
}