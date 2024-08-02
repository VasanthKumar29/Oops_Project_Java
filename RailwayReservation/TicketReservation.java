package RailwayReservation;

import java.util.*;

public class TicketReservation {
  private int totalBerths = 3;
  private int lowerBerths = 1;
  private int middleBerths = 1;
  private int upperBerths = 1;
  private int totalRACBerths = 1;
  private int totalWaitingListTickets = 1;
  private ArrayList<Passenger> bookedTickets = new ArrayList<>();
  private ArrayList<Passenger> racTickets = new ArrayList<>();
  private ArrayList<Passenger> waitingListTickets = new ArrayList<>();

  public static void main(String[] args) {
    TicketReservation reservationSystem = new TicketReservation();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\nRailway Ticket Reservation System");
      System.out.println("1. Book Ticket");
      System.out.println("2. Cancel ticket");
      System.out.println("3. Print Booked Tickets");
      System.out.println("4. Print Available Tickets");
      System.out.println("5. Exit");

      System.out.print("Enter your choice (1-5) : ");
      int choice = sc.nextInt();
      sc.nextLine();
      System.out.println("------------------------------");

      switch (choice) {
        case 1:
          reservationSystem.bookTicket();
          break;
        case 2:
          reservationSystem.cancelTicket();
          break;
        case 3:
          reservationSystem.printBookedTickets();
          break;
        case 4:
          reservationSystem.printAvailableTickets();
          break;
        case 5:
          System.out.println("Exiting the application. Thank You!");
          System.exit(0);
          break;

        default:
          System.out.println("Invalid choice. Please enter a number between 1 to 5");
      }
    }
  }

  public void bookTicket() {
    Scanner sc = new Scanner(System.in);

    if (totalWaitingListTickets == 0) {
      System.out.println("No tickets available.");
      System.out.println("----------------------");
      return;
    }

    System.out.print("\nEnter Passenger name: ");
    String name = sc.nextLine();
    System.out.print("\nEnter Passenger age: ");
    int age = sc.nextInt();
    sc.nextLine();
    System.out.print("\nEnter Passenger gender (M/F): ");
    String gender = sc.nextLine().toUpperCase();
    System.out.print("\nEnter birth preferance (Lower/Middle/Upper): ");
    String berthPreference = sc.nextLine().toLowerCase();

    if (age < 5) {
      System.out.println("Children below 5 years are not allowed to Book tickets");
      return;
    }

    if (berthPreference.equals("lower")) {
      if (lowerBerths > 0) {
        lowerBerths--;
      } else {
        System.out.println("----------------------");
        System.out.println("Lower birth is not available. Suggesting available brths");
        suggestAvailableBerths(name, age, gender);
        return;
      }
    } else if (berthPreference.equals("middle")) {
      if (middleBerths > 0) {
        middleBerths--;
      } else {
        System.out.println("----------------------");
        System.out.println("Middle birth is not available. Suggesting available brths");
        suggestAvailableBerths(name, age, gender);
        return;
      }
    } else if (berthPreference.equals("upper")) {
      if (upperBerths > 0) {
        upperBerths--;
      } else {
        System.out.println("----------------------");
        System.out.println("Upper birth is not available. Suggesting available brths");
        suggestAvailableBerths(name, age, gender);
        return;
      }
    } else {
      System.out.println("Invalid Birth given");
      return;
    }

    Passenger passenger = new Passenger(name, age, gender, berthPreference);

    bookedTickets.add(passenger);
    totalBerths--;
    System.out.println("Ticket Booked Successfully!");
    System.out.println("--------------------------");
  }

  public void suggestAvailableBerths(String name, int age, String gender) {
    System.out.println("-------------------------------");
    if (lowerBerths > 0) {
      System.out.println("1. Lower");
    }
    if (middleBerths > 0) {
      System.out.println("2. Middle");
    }
    if (upperBerths > 0) {
      System.out.println("3. Upper");
    }
    if (lowerBerths == 0 && middleBerths == 0 && upperBerths == 0) {
      System.out.println("4. Book on RAC");
    }

    System.out.println("Enter your choice : ");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
      case 1:
        lowerBerths--;
        bookedTickets.add(new Passenger(name, age, gender, "lower"));
        totalBerths--;
        System.out.println("Ticket Booked successfully!");
        System.out.println("---------------------------");
        break;

      case 2:
        middleBerths--;
        bookedTickets.add(new Passenger(name, age, gender, "middle"));
        totalBerths--;
        System.out.println("Ticket Booked successfully!");
        System.out.println("---------------------------");
        break;

      case 3:
        upperBerths--;
        bookedTickets.add(new Passenger(name, age, gender, "upper"));
        totalBerths--;
        System.out.println("Ticket Booked successfully!");
        System.out.println("---------------------------");
        break;

      case 4:
        if (totalRACBerths > 0) {
          racTickets.add(new Passenger(name, age, gender, "side lower"));
          totalRACBerths--;
          System.out.println("Ticket Booked successfully int RAC!");
          System.out.println("---------------------------");
        } else {
          System.out.println("---------------------------");
          System.out.println("RAC NOT available , you can book on Waiting list");
          System.out.println("Say Yes to book on waiting list");
          String reply = sc.nextLine().toLowerCase();
          if (reply.equals("yes")) {
            waitingListTickets.add(new Passenger(name, age, gender, "WL"));
            totalWaitingListTickets--;
            System.out.println("Ticket Booked in Waiting list!");
            System.out.println("---------------------------");
          } else {
            System.out.println("Thank you!");
            System.exit(0);
          }
        }
        break;

      default:
        System.out.println("Invalid choice");
        break;
    }

  }

  /**
   * 
   */
  public void cancelTicket() {
    Scanner sc = new Scanner(System.in);
    if (bookedTickets.isEmpty()) {
      System.out.println("No tickets are there to cancel");
      return;
    }

    System.out.print("Enter passenger name to cancel the ticket: ");
    String canceledPassengerName = sc.nextLine();

    Passenger canceledTicket = null;
    for (Passenger ticket : bookedTickets) {
      if (ticket.name.equalsIgnoreCase(canceledPassengerName)) {
        canceledTicket = ticket;
        break;
      }
    }
    if (canceledTicket == null) {
      System.out.println("Ticket with provided name not found");
      return;
    }

    bookedTickets.remove(canceledTicket);
    totalBerths++;
    if (canceledTicket.berthPreference.equals("lower")) {
      lowerBerths++;
    } else if (canceledTicket.berthPreference.equals("middle")) {
      middleBerths++;
    } else if (canceledTicket.berthPreference.equals("upper")) {
      upperBerths++;
    }

    if (!racTickets.isEmpty()) {
      Passenger confirmedTicket = racTickets.remove(0);
      totalRACBerths++;
      bookedTickets.add(new Passenger(confirmedTicket.name, confirmedTicket.age, confirmedTicket.gender,
          canceledTicket.berthPreference));
      totalBerths--;
      if (canceledTicket.berthPreference.equals("lower")) {
        lowerBerths--;
      } else if (canceledTicket.berthPreference.equals("middle")) {
        middleBerths--;
      } else if (canceledTicket.berthPreference.equals("upper")) {
        upperBerths--;
      }
    }
    if (!waitingListTickets.isEmpty()) {
      Passenger racTicketMoving = waitingListTickets.remove(0);
      totalWaitingListTickets++;
      racTickets.add(new Passenger(racTicketMoving.name, racTicketMoving.age, racTicketMoving.gender,
          "side lower"));
      totalRACBerths--;
    }
    System.out.println("Ticket canceled successfully for " + canceledPassengerName);
  }

  public void printBookedTickets() {
    if (bookedTickets.isEmpty()) {
      System.out.println("No Booked tickets available.");
      System.out.println("-----------------------------");
    } else {
      System.out.println("Booked tickets");
      System.out.println("-----------------------------");
      for (int i = 0; i < bookedTickets.size(); i++) {
        Passenger ticket = bookedTickets.get(i);
        System.out.println("1. Name: " + ticket.name);
        System.out.println("2. Age: " + ticket.age);
        System.out.println("3. Gender: " + ticket.gender);
        System.out.println("4. Birth Preferance: " + ticket.berthPreference);
        System.out.println("-------------------");
      }
      System.out.println("-------------------");
      if (!racTickets.isEmpty()) {
        System.out.println("RAC tickets");
        System.out.println("----------------------");
        for (int i = 0; i < racTickets.size(); i++) {
          Passenger ticket = racTickets.get(i);
          System.out.println((i + 1) + "1. Name: " + ticket.name);
          System.out.println((i + 1) + "2. Age: " + ticket.age);
          System.out.println((i + 1) + "3. Gender: " + ticket.gender);
          System.out.println((i + 1) + "4. Birth Preferance: side lower");
          System.out.println("----------------------");
        }
        if (!waitingListTickets.isEmpty()) {
          System.out.println("WL tickets");
          System.out.println("----------------------");
          for (int i = 0; i < waitingListTickets.size(); i++) {
            Passenger ticket = waitingListTickets.get(i);
            System.out.println((i + 1) + "1. Name: " + ticket.name);
            System.out.println((i + 1) + "2. Age: " + ticket.age);
            System.out.println((i + 1) + "3. Gender: " + ticket.gender);
            System.out.println((i + 1) + "4. Birth Preferance: N/A");
            System.out.println("----------------------");
          }
        }
      }
    }
  }

  public void printAvailableTickets() {
    System.out.println("1. Total Birth: " + totalBerths);
    System.out.println("2. Total Lower birth: " + lowerBerths);
    System.out.println("3. Total Middle birth: " + middleBerths);
    System.out.println("4. Total Upper birth: " + upperBerths);
    System.out.println("5. Total RAC: " + totalRACBerths);
    System.out.println("6. Total Waiting List: " + totalWaitingListTickets);
  }
}
