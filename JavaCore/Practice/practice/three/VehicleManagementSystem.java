package practice.three;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VehicleManagementSystem {

    private List<Vehicle> vehicles;
    private Scanner scanner;

    public VehicleManagementSystem() {
        this.vehicles = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Validation methods
    private boolean isValidVehicleNumber(String vehicleNumber) {
        return vehicleNumber != null && vehicleNumber.length() == 5 &&
                vehicleNumber.matches("[A-Za-z0-9]{5}");
    }

    private boolean isValidCmndNumber(String cmndNumber) {
        return cmndNumber != null && cmndNumber.length() == 12 &&
                cmndNumber.matches("\\d{12}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    private boolean isValidYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year > 2000 && year <= currentYear;
    }

    private boolean isVehicleNumberUnique(String vehicleNumber) {
        return vehicles.stream().noneMatch(v -> v.getVehicleNumber().equals(vehicleNumber));
    }

    private boolean isCmndNumberUnique(String cmndNumber) {
        return vehicles.stream().noneMatch(v -> v.getOwner().getCmnd().equals(cmndNumber));
    }

    // Add vehicle method
    public void addVehicle() {
        System.out.println("=== ADD NEW VEHICLE ===");

        // Get vehicle number
        String vehicleNumber;
        do {
            System.out.print("Enter vehicle number (5 characters): ");
            vehicleNumber = scanner.nextLine().trim();
            if (!isValidVehicleNumber(vehicleNumber)) {
                System.out.println("Invalid vehicle number! Must be exactly 5 characters.");
                continue;
            }
            if (!isVehicleNumberUnique(vehicleNumber)) {
                System.out.println("Vehicle number already exists! Please enter a unique number.");
                continue;
            }
            break;
        } while (true);

        // Get manufacturer
        TypeManufacturer manufacturer;
        do {
            System.out.println("Select manufacturer:");
            System.out.println("1. Honda");
            System.out.println("2. Yamaha");
            System.out.println("3. Toyota");
            System.out.println("4. Suzuki");
            System.out.print("Enter choice (1-4): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        manufacturer = TypeManufacturer.Honda;
                        break;
                    case 2:
                        manufacturer = TypeManufacturer.Yamaha;
                        break;
                    case 3:
                        manufacturer = TypeManufacturer.Toyota;
                        break;
                    case 4:
                        manufacturer = TypeManufacturer.Suzuki;
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-4.");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        // Get year of manufacture
        int yearOfManufacture;
        do {
            System.out.print("Enter year of manufacture (2001-" + Calendar.getInstance().get(Calendar.YEAR) + "): ");
            try {
                yearOfManufacture = Integer.parseInt(scanner.nextLine().trim());
                if (!isValidYear(yearOfManufacture)) {
                    System.out.println(
                            "Invalid year! Must be between 2001 and " + Calendar.getInstance().get(Calendar.YEAR));
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid year.");
            }
        } while (true);

        // Get color
        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine().trim();

        // Get owner information
        Owner owner = createOwner();

        // Get vehicle type
        TypeVehicle vehicleType;
        do {
            System.out.println("Select vehicle type:");
            System.out.println("1. Car");
            System.out.println("2. Motorbike");
            System.out.println("3. Truck");
            System.out.print("Enter choice (1-3): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        vehicleType = TypeVehicle.Car;
                        break;
                    case 2:
                        vehicleType = TypeVehicle.Motorbike;
                        break;
                    case 3:
                        vehicleType = TypeVehicle.Truck;
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-3.");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        // Create specific vehicle based on type
        Vehicle vehicle = null;
        switch (vehicleType) {
            case Car:
                vehicle = createCar(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
                break;
            case Motorbike:
                vehicle = createMotorbike(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
                break;
            case Truck:
                vehicle = createTruck(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
                break;
        }

        if (vehicle != null) {
            vehicles.add(vehicle);
            System.out.println("Vehicle added successfully!");
        }
    }

    private Owner createOwner() {
        System.out.println("=== OWNER INFORMATION ===");

        // Get CMND number
        String cmndNumber;
        do {
            System.out.print("Enter CMND number (12 digits): ");
            cmndNumber = scanner.nextLine().trim();
            if (!isValidCmndNumber(cmndNumber)) {
                System.out.println("Invalid CMND number! Must be exactly 12 digits.");
                continue;
            }
            if (!isCmndNumberUnique(cmndNumber)) {
                System.out.println("CMND number already exists! Please enter a unique number.");
                continue;
            }
            break;
        } while (true);

        // Get full name
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine().trim();

        // Get email
        String email;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine().trim();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format! Please enter a valid email.");
                continue;
            }
            break;
        } while (true);

        return new Owner(cmndNumber, fullName, email);
    }

    private Car createCar(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture,
            String color, Owner owner) {
        System.out.println("=== CAR SPECIFIC INFORMATION ===");

        // Get number of seats
        int numberOfSeats;
        do {
            System.out.print("Enter number of seats: ");
            try {
                numberOfSeats = Integer.parseInt(scanner.nextLine().trim());
                if (numberOfSeats <= 0) {
                    System.out.println("Number of seats must be positive!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        } while (true);

        // Get engine type
        System.out.print("Enter engine type: ");
        String engineType = scanner.nextLine().trim();

        return new Car(vehicleNumber, manufacturer, yearOfManufacture, color, owner, numberOfSeats, engineType);
    }

    private Motorbike createMotorbike(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture,
            String color, Owner owner) {
        System.out.println("=== MOTORBIKE SPECIFIC INFORMATION ===");

        // Get engine capacity
        int engineCapacity;
        do {
            System.out.print("Enter engine capacity (cc): ");
            try {
                engineCapacity = Integer.parseInt(scanner.nextLine().trim());
                if (engineCapacity <= 0) {
                    System.out.println("Engine capacity must be positive!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        } while (true);

        return new Motorbike(vehicleNumber, manufacturer, yearOfManufacture, color, owner, engineCapacity);
    }

    private Truck createTruck(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture,
            String color, Owner owner) {
        System.out.println("=== TRUCK SPECIFIC INFORMATION ===");

        // Get tonnage
        double tonnage;
        do {
            System.out.print("Enter tonnage: ");
            try {
                tonnage = Double.parseDouble(scanner.nextLine().trim());
                if (tonnage <= 0) {
                    System.out.println("Tonnage must be positive!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        } while (true);

        return new Truck(vehicleNumber, manufacturer, yearOfManufacture, color, owner, tonnage);
    }

    // Search vehicle by vehicle number
    public void searchVehicleByNumber() {
        System.out.println("=== SEARCH VEHICLE BY NUMBER ===");
        System.out.print("Enter vehicle number to search: ");
        String vehicleNumber = scanner.nextLine().trim();

        Vehicle foundVehicle = vehicles.stream()
                .filter(v -> v.getVehicleNumber().equals(vehicleNumber))
                .findFirst()
                .orElse(null);

        if (foundVehicle != null) {
            System.out.println("Vehicle found:");
            foundVehicle.display();
        } else {
            System.out.println("No vehicle found with number: " + vehicleNumber);
        }
    }

    // Find vehicles by owner's CMND number
    public void findVehiclesByOwnerCmnd() {
        System.out.println("=== FIND VEHICLES BY OWNER CMND ===");
        System.out.print("Enter CMND number to search: ");
        String cmndNumber = scanner.nextLine().trim();

        List<Vehicle> foundVehicles = vehicles.stream()
                .filter(v -> v.getOwner().getCmnd().equals(cmndNumber))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        if (!foundVehicles.isEmpty()) {
            System.out.println("Vehicles found for CMND " + cmndNumber + ":");
            foundVehicles.forEach(Vehicle::display);
        } else {
            System.out.println("No vehicles found for CMND: " + cmndNumber);
        }
    }

    // Delete vehicles by manufacturer
    public void deleteVehiclesByManufacturer() {
        System.out.println("=== DELETE VEHICLES BY MANUFACTURER ===");
        System.out.println("Select manufacturer to delete:");
        System.out.println("1. Honda");
        System.out.println("2. Yamaha");
        System.out.println("3. Toyota");
        System.out.println("4. Suzuki");
        System.out.print("Enter choice (1-4): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            TypeManufacturer manufacturerToDelete;
            switch (choice) {
                case 1:
                    manufacturerToDelete = TypeManufacturer.Honda;
                    break;
                case 2:
                    manufacturerToDelete = TypeManufacturer.Yamaha;
                    break;
                case 3:
                    manufacturerToDelete = TypeManufacturer.Toyota;
                    break;
                case 4:
                    manufacturerToDelete = TypeManufacturer.Suzuki;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            boolean deletedCount = vehicles.removeIf(v -> v.getManufacturer() == manufacturerToDelete);
            System.out.println("Deleted " + deletedCount + " vehicles of manufacturer: " + manufacturerToDelete);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }

    // Find manufacturer with most vehicles
    public void findManufacturerWithMostVehicles() {
        System.out.println("=== MANUFACTURER WITH MOST VEHICLES ===");

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Map<TypeManufacturer, Long> manufacturerCount = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getManufacturer, Collectors.counting()));

        TypeManufacturer topManufacturer = manufacturerCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (topManufacturer != null) {
            long count = manufacturerCount.get(topManufacturer);
            System.out.println("Manufacturer with most vehicles: " + topManufacturer + " (" + count + " vehicles)");
        }
    }

    // Sort vehicles by manufacturer (descending order by count)
    public void sortVehiclesByManufacturer() {
        System.out.println("=== VEHICLES SORTED BY MANUFACTURER (DESCENDING) ===");

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Map<TypeManufacturer, List<Vehicle>> vehiclesByManufacturer = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getManufacturer));

        vehiclesByManufacturer.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .forEach(entry -> {
                    System.out.println("\n" + entry.getKey() + " (" + entry.getValue().size() + " vehicles):");
                    entry.getValue().forEach(Vehicle::display);
                });
    }

    // Statistics of each vehicle type
    public void showVehicleTypeStatistics() {
        System.out.println("=== VEHICLE TYPE STATISTICS ===");

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Map<TypeVehicle, Long> typeCount = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getType, Collectors.<Vehicle>counting()));

        System.out.println("Vehicle statistics:");
        for (TypeVehicle type : TypeVehicle.values()) {
            long count = typeCount.getOrDefault(type, 0L);
            System.out.println(type + ": " + count + " vehicles");
        }

        System.out.println("Total vehicles: " + vehicles.size());
    }

    // Display all vehicles
    public void displayAllVehicles() {
        System.out.println("=== ALL VEHICLES ===");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        vehicles.forEach(Vehicle::display);
    }
}
