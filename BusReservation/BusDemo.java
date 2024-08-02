package BusReservation;

import java.util.*;

public class BusDemo {
  public static void main(String[] args) {

    ArrayList<Bus> buses = new ArrayList<Bus>();
    ArrayList<Booking> bookings = new ArrayList<Booking>();

    buses.add(new Bus(1, true, 2));
    buses.add(new Bus(2, false, 60));
    buses.add(new Bus(3, true, 48));

    int userOpt = 1;
    Scanner sc = new Scanner(System.in);

    for (Bus bus : buses) {
      bus.displayBusInfo();
    }

    while (userOpt == 1) {
      System.out.println("Enter 1 to book ticker and 2 to exit :");
      userOpt = sc.nextInt();
      sc.nextLine();
      if (userOpt == 1) {
        System.out.println("Booking...");
        Booking booking = new Booking();
        if (isAvailable(booking, bookings, buses)) {
          bookings.add(booking);
          System.out.println("Booking confirmed");
        } else {
          System.out.println("Please try another Bus or date");
        }
        System.out.println("-------------------------");
      }
    }
  }

  public static boolean isAvailable(Booking booking, ArrayList<Booking> bookings, ArrayList<Bus> buses) {
    int capacity = 0;
    for (Bus bus : buses) {
      if (bus.getBusNo() == booking.busNo) {
        capacity = bus.getCapacity();
        break;
      }
    }

    int booked = 0;
    for (Booking b : bookings) {
      if (b.busNo == booking.busNo && b.date.equals(booking.date)) {
        booked++;
      }
    }

    return booked < capacity;
  }
}
