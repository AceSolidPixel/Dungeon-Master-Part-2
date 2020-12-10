/**
* Decoration class that gives an enemy a stat boost only available to warriors
*/
public class WarriorDecoration extends EnemyDecorator
{
  /**
  * Adds Warrior adjective and bonus health stats to passed in monster.
  * Warriors get bonus 2 hp and attack.
  * @param newEnemy Enemy to which the warrior bonus is granted
  */
  public WarriorDecoration(Enemy newEnemy)
  {
    super(newEnemy, newEnemy.getName() + " Warrior", newEnemy.getHP() + 2, newEnemy.getAttack() + 2, newEnemy.getItem());
  }
}