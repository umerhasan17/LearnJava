package aeroplane;

public abstract class Passenger {

	private final String firstName;
	private final String lastName;

  public Passenger(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public boolean isAdult() {
    return true;
  }

  @Override
  public String toString() {
    return "First Name: " + firstName + ", Last Name: " + lastName;
  }
}
