package BusReservation;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Booking {
  String passengerName;
  int busNo;
  Date date;

  Booking() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Passenger Name : ");
    passengerName = sc.nextLine();
    System.out.println();

    System.out.print("Enter your Bus Number: ");
    busNo = sc.nextInt();
    sc.nextLine();
    System.out.println();

    System.out.print("Enter which date to book Ticket as dd-mm-yyyy: ");
    String dateInput = sc.nextLine();
    System.out.println();
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

    try {
      date = dateformat.parse(dateInput);
    } catch (ParseException e) {
      System.out.println("Please Enter the date in Correct formate...");
    }

  }

  public String getPassengerName() {
    return passengerName;
  }

  public void setPassengerName(String passengerName) {
    this.passengerName = passengerName;
  }

  public int getBusNo() {
    return busNo;
  }

  public void setBusNo(int busNo) {
    this.busNo = busNo;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
