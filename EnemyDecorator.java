/**
* Class that allows base enemies to get decorated with a new name and new health stat
*/
public abstract class EnemyDecorator extends Enemy
{
  /** Enemy to be decorated  */
  private Enemy enemy;

  /**
  * Constructor for EnemyDecorator - passes in an enemy to be decorated, a string for their name, their new maxHP, new attack stat, and the item that they will carry
  * @param Enemy newEnemy - The enemy object that will be decorated
  * @param String newName - The enemy's new name which contains their class name as well as race of monster
  * @param int newHp - The increased HP from getting assigned a class
  * @param int newAttack - The increased attack from getting assigned a class.
  * @param Item I - Item that the enemy is going to carry
  */
  public EnemyDecorator(Enemy newEnemy, String newName, int newHp, int newAttack, Item i)
  {
    super(newName, newHp,newAttack, i);
    enemy = newEnemy;
  }
}