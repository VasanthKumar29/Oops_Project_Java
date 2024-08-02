package AirlineReservation;

public class Passenger {
  private String name;
  private int age;
  private String passportNumber;

  public Passenger(String name, int age, String passportNumber) {
    this.name = name;
    this.age = age;
    this.passportNumber = passportNumber;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

}
