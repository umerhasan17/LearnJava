package aeroplane;

public class BusinessPassenger extends Passenger {

  private final int age;
  private final Luxury luxury;

  public BusinessPassenger(String firstName, String lastName, int age, Luxury luxury) {
    super(firstName, lastName);
    this.luxury = luxury;
    assert age > -1;
    this.age = age;
  }

  @Override
  public boolean isAdult() {
    return age > 17;
  }

  @Override
  public String toString() {
    return "Type: business class, " + super.toString() + ", Age: " +
            age + ", Luxury: " + luxury.toString() + "\n";
  }
}
