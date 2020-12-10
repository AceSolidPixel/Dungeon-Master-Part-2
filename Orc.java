/**
* Base class for the orc enemy that can be decorated with either warrior or warlock stats and abilities
*/
class Orc extends Enemy
{
    /**
    * Constructor for this class, Sets the initial HP to 4, sets the base attack to 4, and name to Orc
    * @param i - Item for Orc to hold
    */
    public Orc(Item i)
    {
      super("Orc", 4, 4, i);
    }
}