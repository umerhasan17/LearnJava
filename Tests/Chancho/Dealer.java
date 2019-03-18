import java.util.Set;

/**
 * The class Dealer encapsulates the actions of a Chancho game-dealer. The game
 * dealer is responsible for dealing cards from the provided game-deck to each
 * player, and scheduling rounds of the game until a player has won the game.
 * All players who have declared themselves as a winner should be congratulated.
 * 
 * Developers should provide the constructor,
 * 
 * public Dealer(Set<Player> players, Deck gameDeck);
 * 
 */
public final class Dealer {

  public static final int HAND_SIZE = 4;
  private final Set<Player> playerList;

  public Dealer(Set<Player> playerList, Deck gameDeck) {
    this.playerList = playerList;
    for (int i = 0; i < HAND_SIZE; i ++) {
      this.playerList.iterator()
                .forEachRemaining(player -> player.addToHand(gameDeck.removeFromTop()));
    }
  }

  public void playGame() {
    boolean hasWinner = false;
    while(!hasWinner) {
      for (Player player : playerList) {
        if (player.hasWon()) {
          hasWinner = true;
          break;
        }
      }
      for (Player player : playerList) {
        player.discard();
      }
      for (Player player : playerList) {
        player.pickup();
      }
    }
    congratulateWinners();
  }

  public void congratulateWinners() {
    System.out.println("The game has been won! Congratulations to:");
    for (Player player : playerList) {
      if (player.hasWon()) {
        System.out.println(player);
      }
    }
  }
}