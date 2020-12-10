public abstract class Factory
{
  /**
  * Generates a base enemy using the createBaseEnemy function and decorates it
  * with either Warlock or Warrier for however many levels above 1 the hero is
  *
  * @param level Current level of the hero
  * @return Decorated enemy of specified type
  */
  public Enemy generateEnemy(int level)
  {
    Enemy returnEnemy = createBaseEnemy();

    boolean isWarrior = true;
    if (Math.random() > .5)
    {
      isWarrior = false;
    }

    for (int decorationLooper = 1; decorationLooper < level; ++decorationLooper)
    {
      if (isWarrior)
      {
        returnEnemy = new WarriorDecoration(returnEnemy);
      }
      else
      {
        returnEnemy = new WarlockDecoration(returnEnemy);
      } 
    }
    
    return returnEnemy;
  }

  /**
  * Generates a random base enemy
  * 
  * @return Random base enemy
  */
  public abstract Enemy createBaseEnemy();
}