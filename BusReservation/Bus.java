package BusReservation;

public class Bus {
  private int busNo;
  private boolean ac;
  private int capacity;

  Bus(int busNo, boolean ac, int capacity) {
    this.busNo = busNo;
    this.ac = ac;
    this.capacity = capacity;
  }

  public int getBusNo() {
    return busNo;
  }

  public int getCapacity() {
    return capacity;
  }

  public void displayBusInfo() {
    System.out.println("Bus Number: " + busNo);
    System.out.println("AC: " + ac);
    System.out.println("Capacity : " + capacity);
    System.out.println("-------------------------");
  }

}
