package videogame;

public class Magician extends Entity implements SpellCaster {

  public Magician(String name, int lifePoints) {
    super(name, lifePoints);
  }

  @Override
  public int getStrength() {
    return lifePoints * 2;
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    int oldLifePoints = lifePoints;
    lifePoints -= damageAmount;
    if (lifePoints < 0) {
      lifePoints = 0;
    }
    return oldLifePoints - lifePoints;
  }

  @Override
  public int minimumStrikeToDestroy() {
    return lifePoints;
  }

  @Override
  public String toString() {
    return name + " (" + lifePoints + ")";
  }
}