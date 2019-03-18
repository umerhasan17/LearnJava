package aeroplane;

public class EconomyPassenger extends Passenger {

  private final int age;

  public EconomyPassenger(String firstName, String lastName, int age) {
    super(firstName, lastName);
    assert age > -1;
    this.age = age;
  }

  @Override
  public boolean isAdult() {
    return age > 17;
  }

  @Override
  public String toString() {
    return "Type: economy class, " + super.toString() + ", Age: " + age + "\n";
  }
}
