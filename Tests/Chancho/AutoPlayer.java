import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoPlayer extends AbstractPlayer {

  public static final int HAND_SIZE = 4;

  AutoPlayer(CardPile left, CardPile right, String name) {
    super(left, right, name);
  }

  @Override
  protected int selectCard() {
    Random r = new Random();
    List<Integer> chooseFrom = new ArrayList<>();

    for (int i = 0; i < cards.length; i ++) {
      chooseFrom.add(i);
      for (int j = 0; j < i; j ++) {
        if (cards[i].getRank() == cards[j].getRank()) {
          if (chooseFrom.contains(i)) {
            chooseFrom.remove(i);
          }
          if (chooseFrom.contains(j)) {
            chooseFrom.remove(j);
          }
        }
      }
    }

    if (chooseFrom.isEmpty()) {
      return r.nextInt(HAND_SIZE);
    } else {
      return chooseFrom.get(r.nextInt(chooseFrom.size()));
    }
  }

}