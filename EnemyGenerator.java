import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;

/**
* This class generates enemies using a factory design pattern. This class is a singleton. 
*/
public class EnemyGenerator extends Factory
{
  /** Single instance of enemy generator to be accessed from anywhere in the class  */
  private static EnemyGenerator instance = null;

  /**
  * This method chooses what kind of enemy to create. The default enemy is a troll.
  * @return - returns a randomly chosen enemy type
  */
  @Override
  public Enemy createBaseEnemy()
  {
    final int MIN_ENEMY = 0;
    final int MAX_ENEMY = 4;

    //Generate random value between [MIN_ENEMY, MAX_ENEMY)
    int randomEnemy = (int)(Math.random() * (MAX_ENEMY - MIN_ENEMY + 1) + MIN_ENEMY);

    switch(randomEnemy)
    {
      case 0:
        return new Troll(ItemGenerator.getInstance().generateItem());
      
      case 1:
        return new Froglock(ItemGenerator.getInstance().generateItem());
              
      case 2:
        return new Orc(ItemGenerator.getInstance().generateItem());
        
      
      case 3:
        return new Goblin(ItemGenerator.getInstance().generateItem());
        

      default:
        return new Troll(ItemGenerator.getInstance().generateItem());
        
    }
  }  

  /**
  * Returns the single instance of enemy generator. If none exists, one is created
  * @return - The single instance of enemy generator
  */
  public static EnemyGenerator getInstance()
  {
    if (instance == null)
    {
      instance = new EnemyGenerator();
    }
    return instance;
  }
}