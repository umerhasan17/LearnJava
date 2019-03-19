package videogame;

import java.util.HashSet;
import java.util.Set;

public class TransportUnit extends Entity {

  private final Set<Entity> entities;

  public TransportUnit(String name, int lifePoints) {
    super(name, lifePoints);
    this.entities = new HashSet<>();
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    int oldLifePoints = lifePoints;
    lifePoints -= damageAmount;
    if (lifePoints < 0) {
      lifePoints = 0;
    }
    int sumDamage = oldLifePoints - lifePoints;
    for (Entity entity : entities) {
      sumDamage += entity.propagateDamage(damageAmount / 2);
    }

    return sumDamage;
  }

  @Override
  public int minimumStrikeToDestroy() {
    int minimumStrike = lifePoints;
    for (Entity entity : entities) {
      minimumStrike = Math.max(minimumStrike,
              entity.minimumStrikeToDestroy() * 2);
    }
    return minimumStrike;
  }

  public void add(Entity entity) {
    entities.add(entity);
  }

  @Override
  public String toString() {
    String result = name + " (" + lifePoints + ") transporting: [";
    for (Entity entity : entities) {
      result += entity.toString() + ", ";
    }
    result = result.substring(0, result.length() - 2);
    result += "]";
    return result;
  }
}