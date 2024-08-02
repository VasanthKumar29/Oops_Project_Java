package AssestManagement;

import java.text.SimpleDateFormat;
import java.util.*;

public class AssetManagementSystem {

  private List<Software> softwares;
  private List<Device> devices;
  private List<Employee> employees;
  private List<Vendor> vendors;
  private Scanner sc;

  public AssetManagementSystem() {
    this.softwares = new ArrayList<>();
    this.devices = new ArrayList<>();
    this.employees = new ArrayList<>();
    this.vendors = new ArrayList<>();
    this.sc = new Scanner(System.in);
  }

  public void run() {
    while (true) {
      System.out.println("\n---Asset Management System Menu---");
      System.out.println("1. Add Vendor");
      System.out.println("2. Add Software");
      System.out.println("3. Add Employee");
      System.out.println("4. Add Device");
      System.out.println("5. Install Software on Device");
      System.out.println("6. Generate Report");
      System.out.println("7. Exit");
      System.out.print("Choose any option to continue: ");

      int option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          addVendor();
          break;
        case 2:
          addSoftware();
          break;
        case 3:
          addEmployee();
          break;
        case 4:
          addDevice();
          break;
        case 5:
          installSoftware();
          break;
        case 6:
          generateReport();
          break;
        case 7:
          System.out.println("Exiting...");
          System.exit(0);
        default:
          System.out.println("Invalid option. please try again.");
      }
    }
  }

  private void addVendor() {
    System.out.print("Enter Vendor ID: ");
    String vendorId = sc.nextLine();
    System.out.print("\nEnter Vendor Name: ");
    String name = sc.nextLine();
    Vendor vendor = new Vendor(vendorId, name);
    vendors.add(vendor);
    System.out.println("\nVendor added successfully.");
  }

  private void addSoftware() {
    System.out.print("\nEnter Software Name: ");
    String name = sc.nextLine();
    System.out.print("\nEnter Vendor ID: ");
    String vendorId = sc.nextLine();
    Vendor vendor = findVendorById(vendorId);
    if (vendor == null) {
      System.out.print("\nVendor not found. Please add the vendor first.");
      return;
    }
    System.out.print("\nEnter Cost Per Device: ");
    double costPerDevice = sc.nextDouble();
    sc.nextLine();

    System.out.print("\nEnter Expiry Date(yyyy-mm-dd): ");
    String expiryDateString = sc.nextLine();
    Date expiryDate = parseDate(expiryDateString);
    if (expiryDate == null) {
      System.out.print("\nInvalid date format.");
      return;
    }

    Software software = new Software(name, vendor, costPerDevice, expiryDate);
    softwares.add(software);

    System.out.println("\nSoftware added Succesfully.");
  }

  private void addEmployee() {
    System.out.print("\nEnter Employee ID: ");
    String employeeId = sc.nextLine();
    System.out.print("\nEnter EMployee Name: ");
    String name = sc.nextLine();
    Employee employee = new Employee(employeeId, name);
    employees.add(employee);
    System.out.println("\nEmployee added successfully. ");
  }

  private void addDevice() {
    System.out.print("\nEnter Device ID: ");
    String deviceId = sc.nextLine();
    System.out.print("\nEnter Employee ID: ");
    String employeeId = sc.nextLine();
    Employee employee = findEmployeeById(employeeId);
    if (employee == null) {
      System.out.print("\nEmployee not found. Please add the employee first. ");
      return;
    }
    Device device = new Device(deviceId, employee);
    employee.addDevice(device);
    devices.add(device);
    System.out.print("\nDevice Added succesfully ");
  }

  private void installSoftware() {
    System.out.print("\nEnter Device Id: ");
    String deviceId = sc.nextLine();
    Device device = findDeviceById(deviceId);
    if (device == null) {
      System.out.println("Device not found. Please add the device first.");
      return;
    }
    System.out.print("\nEnter Software Name: ");
    String softwareName = sc.nextLine();
    Software software = findSoftwareByName(softwareName);
    if (software == null) {
      System.out.println("\nSoftware not found.Please add the software first.");
      return;
    }
    System.out.println("Enter Installation Date (yyyy-MM-dd): ");
    String installationDateString = sc.nextLine();
    Date installationDate = parseDate(installationDateString);
    if (installationDate == null) {
      System.out.println("Invalid date formate");
      return;
    }
    installSoftwareOnDevice(device, software, installationDate);
    System.out.println("Software Installed successfully on device.");
  }

  private void generateReport() {
    System.out.println("\n---Report Menu---");
    System.out.println("1. Number of installation of a particular software");
    System.out.println("2. Number of software installed on a device");
    System.out.println("3. Number of Software installed for an employee");
    System.out.println("4. Amount spent on a software");
    System.out.println("5. Amount spent for an employee");
    System.out.println("6. Number of Installation from a vendor");
    System.out.println("7. Devices with expired software");

    System.out.print("Choose a report Option: ");
    int reportOption = sc.nextInt();
    sc.nextLine();

    switch (reportOption) {
      case 1:
        System.out.print("Enter Software Name: ");
        String softwareName = sc.nextLine();
        System.out.println("\nNumber of installation of " + softwareName + ":" + numberOfInstallations(softwareName));
        break;
      case 2:
        System.out.print("Enter Device ID: ");
        String deviceId = sc.nextLine();
        System.out.println(
            "\nNumber of software installed on device " + deviceId + ":" + numberOfSoftwareInstalledOnDevice(deviceId));
        break;
      case 3:
        System.out.print("\nEnter Employee ID");
        String employeeId = sc.nextLine();
        System.out.println("Number of Software installed for Employee " + employeeId + ":"
            + numberOfSoftwareInstalledByEmployee(employeeId));
        break;
      case 4:
        System.out.println("\nEnter Software Name: ");
        String softwareNameForAmount = sc.nextLine();
        System.out
            .println("Amount spent on" + softwareNameForAmount + ":" + amountSpentOnSoftware(softwareNameForAmount));
        break;
      case 5:
        System.out.print("\nEnter Employee ID: ");
        String employeeIdForAmount = sc.nextLine();

        System.out.println(
            "\nAmount spent for employee: " + employeeIdForAmount + ":"
                + amountSpentForEmployeeById(employeeIdForAmount));
        break;
      case 6:
        System.out.print("\nEnter Vendor ID: ");
        String vendorId = sc.nextLine();
        System.out.println(
            "\nNumber of installation for vendor: " + vendorId + ":"
                + noOfInstallationForVendor(vendorId));
        break;
      default:
        System.out.print("\nInvalid Option ");

    }
  }

  public int noOfInstallationForVendor(String vendorId) {
    int count = 0;
    for (Device device : devices) {
      for (Installation installation : device.getInstallations()) {
        if (installation.getSoftware().getVendor().getVendorId().equals(vendorId)) {
          count = 0;
        }
      }
    }
    return count;
  }

  public double amountSpentForEmployeeById(String employeeId) {
    double amount = 0;
    for (Employee employee : employees) {
      if (employee.getEmployeeId().equals(employeeId)) {
        for (Device device : employee.getDevices()) {
          for (Installation installation : device.getInstallations()) {
            amount += installation.getSoftware().getCostPerDevice();
          }
        }
      }
    }
    return amount;
  }

  public double amountSpentOnSoftware(String softwareNameForAmount) {
    double total = 0;
    for (Device device : devices) {
      for (Installation installation : device.getInstallations()) {
        if (installation.getSoftware().getName().equals(softwareNameForAmount)) {
          total = installation.getSoftware().getCostPerDevice();
        }
      }
    }
    return total;
  }

  public int numberOfSoftwareInstalledByEmployee(String employeeId) {
    int count = 0;
    for (Employee employee : employees) {
      if (employee.getEmployeeId().equals(employeeId)) {
        for (Device device : employee.getDevices()) {
          count += device.getInstallations().size();
        }
      }
    }
    return count;
  }

  public int numberOfSoftwareInstalledOnDevice(String deviceId) {
    for (Device device : devices) {
      if (device.getDeviceId().equals(deviceId)) {
        return device.getInstallations().size();
      }
    }
    return 0;
  }

  public int numberOfInstallations(String softwareName) {
    int count = 0;
    for (Device device : devices) {
      for (Installation installation : device.getInstallations()) {
        if (installation.getSoftware().getName().equals(softwareName)) {
          count++;
        }
      }
    }
    return count;
  }

  public void installSoftwareOnDevice(Device device, Software software, Date installatioDate) {
    Installation installation = new Installation(software, installatioDate);
    device.addInstallation(installation);
  }

  private Device findDeviceById(String deviceId) {
    for (Device device : devices) {
      if (device.getDeviceId().equals(deviceId)) {
        return device;
      }
    }
    return null;
  }

  private Software findSoftwareByName(String softwareName) {
    for (Software software : softwares) {
      if (software.getName().equals(softwareName)) {
        return software;
      }
    }
    return null;
  }

  private Employee findEmployeeById(String employeeId) {
    for (Employee employee : employees) {
      if (employee.getEmployeeId().equals(employeeId)) {
        return employee;
      }
    }
    return null;
  }

  private Vendor findVendorById(String vendorId) {
    for (Vendor vendor : vendors) {
      if (vendor.getVendorId().equals(vendorId)) {
        return vendor;
      }
    }
    return null;
  }

  private Date parseDate(String dateStr) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    } catch (Exception e) {
      return null;
    }
  }

  public static void main(String[] args) {
    AssetManagementSystem system = new AssetManagementSystem();
    system.run();
  }
}
