package AirlineReservation;

import java.util.*;

public class AirlineReservation {
  ArrayList<Flight> flights = new ArrayList<>();
  ArrayList<Reservation> reservations = new ArrayList<>();
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    AirlineReservation airlineReservation = new AirlineReservation();

    Flight flight1 = new Flight("123", "mumbai", 2);
    Flight flight2 = new Flight("456", "chennai", 1);
    Flight flight3 = new Flight("789", "dubai", 2);

    airlineReservation.flights.add(flight1);
    airlineReservation.flights.add(flight2);
    airlineReservation.flights.add(flight3);

    while (true) {
      System.out.println("1. View Flights");
      System.out.println("2. Make Reservation");
      System.out.println("3. View Reservation details");
      System.out.println("4. Cancel Reservation");
      System.out.println("5. Exit");
      System.out.println("------------------------------");

      System.out.print("Enter any option to proceed: ");
      int option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          airlineReservation.viewFlights();
          break;
        case 2:
          airlineReservation.makeReservation();
          break;
        case 3:
          airlineReservation.viewReservationDetails();
          break;
        case 4:
          airlineReservation.cancelTicket();
          break;
        case 5:
          System.out.println("Exiting...");
          System.exit(0);

        default:
          System.out.println("Invalid choice given");
      }
    }
  }

  public void viewFlights() {
    for (Flight flight : flights) {
      System.out.println("\n1. Flight Number: " + flight.getFlightNum());
      System.out.println("2. Flight Destination: " + flight.getDestination());
      System.out.println("3. Flight Capacity: " + flight.getCapacity());
      System.out.println("4. Flight Available seats: " + flight.getAvailableSeat());
      System.out.println("---------------------------------------------------");
    }
  }

  public void makeReservation() {
    System.out.print("\nEnter your Name: ");
    String name = sc.nextLine();

    System.out.print("\nEnter your Age: ");
    int age = sc.nextInt();
    sc.nextLine();

    System.out.print("\nEnter your PassportNumber: ");
    String passportNum = sc.nextLine();

    System.out.print("\nEnter the Flight Number you want to reserve: ");
    String flightNum = sc.nextLine();

    Passenger passenger = new Passenger(name, age, passportNum);

    if (isAvailable(flightNum, passenger)) {
      System.out.println("Reservation Made Successfully");
      System.out.println("-----------------------");
    } else {
      System.out.println("Reservation Unsuccessfull.");
      System.out.println("-----------------------");
    }
  }

  public boolean isAvailable(String flightNum, Passenger passenger) {
    for (Flight flight : flights) {
      if (flight.getFlightNum().equals(flightNum)) {
        if (flight.seatAvailable()) {
          reservations.add(new Reservation(flight, passenger));
          return true;
        } else {
          System.out.println("All tickets are booked in this flight");
          return false;
        }
      }
    }
    return false;
  }

  public void viewReservationDetails() {
    char ch = 'a';
    int i = 1;
    for (Reservation reservation : reservations) {
      System.out.println("\n" + ch + ". Reservation " + i);
      System.out.println("1. Passenger Name: " + reservation.getPassenger().getName());
      System.out.println("2. Passenger Age: " + reservation.getPassenger().getAge());
      System.out.println("3. Passenger Passport number: " + reservation.getPassenger().getPassportNumber());
      System.out.println("4. Journey towards: " + reservation.getFlight().getDestination());
      System.out.println("5. Flight Number: " + reservation.getFlight().getFlightNum());
      System.out.println("----------------------------------------");
      ch++;
      i++;
    }
  }

  public void cancelTicket() {
    System.out.print("\nEnter flight num: ");
    String flightNum = sc.nextLine();

    System.out.print("\nEnter Passenger name: ");
    String passengerName = sc.nextLine();

    Reservation cancelPassenger = null;

    for (Reservation reservation : reservations) {
      if (reservation.getFlight().getFlightNum().equals(flightNum)) {
        if (reservation.getPassenger().getName().equals(passengerName)) {
          cancelPassenger = reservation;
          reservation.getFlight().cancelling();
          break;
        }
      }
    }

    if (cancelPassenger == null) {
      System.out.println("\nNo flight or Passenger Name is available. Please check reservation detail once.");
      System.out.println("---------------------------------");

    } else {
      reservations.remove(cancelPassenger);
      System.out.println("\nTicket cancelled Successfully.");
      System.out.println("-------------------------------");
    }
  }
}
