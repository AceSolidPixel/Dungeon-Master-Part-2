/**
* interface Magical - interface that constructs abstract methods to use when magical attacks are used
*/
public interface Magical 
{
	/** MAGIC_MENU - Menu that displays when hero encounters a Magical Enemy	*/
	public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap";
	/**
	* magicMissile - Method that uses a magicMissile against the Entity passed in 
	* @param e - Entity passed in such as hero object
  * @return - String containing a message of the result of the attack
	*/
	public String magicMissile(Entity e);
	/**
	* fireball - Method that uses a fireball against the Entity passed in
	* @param e - Entity passed in such as hero object
  * @return - String containing a message of the result of the attack
	*/
	public String fireball(Entity e);
	/**
	* thunderclap - Method that uses a thunderclap against the Entity passed in
	* @param e - Entity passed in such as hero object
  * @return - String containing a message of the result of the attack
	*/
	public String thunderclap(Entity e);
}