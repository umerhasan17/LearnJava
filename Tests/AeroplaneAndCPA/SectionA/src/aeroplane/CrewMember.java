package aeroplane;

public class CrewMember extends Passenger {

  public CrewMember(String firstName, String lastName) {
    super(firstName, lastName);
  }

  @Override
  public String toString() {
    return "Type: crew, " + super.toString() + "\n";
  }
}
