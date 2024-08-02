package AirlineReservation;

public class Flight {
  private String flightNum;
  private String Destination;
  private int capacity;
  private int bookedTickets;

  public Flight(String flightNum, String destination, int capacity) {
    this.flightNum = flightNum;
    Destination = destination;
    this.capacity = capacity;
    bookedTickets = 0;
  }

  public String getFlightNum() {
    return flightNum;
  }

  public String getDestination() {
    return Destination;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getAvailableSeat() {
    return capacity - bookedTickets;
  }

  public boolean seatAvailable() {
    if (bookedTickets < capacity) {
      bookedTickets++;
      return true;
    }
    return false;
  }

  public void cancelling() {
    bookedTickets--;
  }
}
