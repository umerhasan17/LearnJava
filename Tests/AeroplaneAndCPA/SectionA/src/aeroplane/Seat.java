package aeroplane;

import java.util.NoSuchElementException;

import static aeroplane.SeatAllocator.*;

public class Seat {


	private final int row;
	private final char letter;

  public Seat(int row, char letter) {
    this.row = row;
    this.letter = Character.toUpperCase(letter);
  }

  public boolean isEmergencyExit() {
    boolean result = false;
    for (int i = 0; i < EXIT_ROWS.length; i ++) {
      result = result || EXIT_ROWS[i] == row;
    }
    return result;
  }

  public boolean hasNext() {
    return !(row == NUM_ROWS && (int) letter ==  ASCII_A + NUM_SEATS - 1);
  }

  public int getRow() {
    return row;
  }

  public char getLetter() {
    return letter;
  }

  public Seat next() {
    if (hasNext()) {
      if ((int) letter >= ASCII_A + NUM_SEATS - 1) {
        return new Seat(row + 1, (char) ASCII_A);
      } else {
        return new Seat(row, (char) (letter + 1));
      }
    } else {
      throw new NoSuchElementException();
    }
  }

  @Override
  public String toString() {
    return String.valueOf(row) + letter;
  }

  @Override
  public boolean equals(Object obj) {
    Seat seat = (Seat) obj;
    return seat.getRow() == this.getRow() &&
            seat.getLetter() == this.getLetter();
  }

  @Override
  public int hashCode() {
    return row * NUM_SEATS + letter;
  }
}
