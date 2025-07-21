package practice.three;

import java.util.Scanner;

public class SystemMain {
    public static void main(String[] args) {
        VehicleManagementSystem system = new VehicleManagementSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== VEHICLE MANAGEMENT SYSTEM ===");
        System.out.println("Welcome to the Police Vehicle Management System!");

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Add new vehicle");
            System.out.println("2. Search vehicle by vehicle number");
            System.out.println("3. Find vehicles by owner's CMND number");
            System.out.println("4. Delete all vehicles of a manufacturer");
            System.out.println("5. Find manufacturer with most vehicles");
            System.out.println("6. Sort vehicles by manufacturer (descending)");
            System.out.println("7. Show vehicle type statistics");
            System.out.println("8. Display all vehicles");
            System.out.println("9. Exit");
            System.out.print("Please select an option (1-9): ");

            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                switch (option) {
                    case 1:
                        system.addVehicle();
                        break;
                    case 2:
                        system.searchVehicleByNumber();
                        break;
                    case 3:
                        system.findVehiclesByOwnerCmnd();
                        break;
                    case 4:
                        system.deleteVehiclesByManufacturer();
                        break;
                    case 5:
                        system.findManufacturerWithMostVehicles();
                        break;
                    case 6:
                        system.sortVehiclesByManufacturer();
                        break;
                    case 7:
                        system.showVehicleTypeStatistics();
                        break;
                    case 8:
                        system.displayAllVehicles();
                        break;
                    case 9:
                        System.out.println("Thank you for using Vehicle Management System!");
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option! Please enter a number between 1-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}