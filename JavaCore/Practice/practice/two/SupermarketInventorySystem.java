package practice.two;

import java.time.LocalDate;
import java.util.Scanner;

import practice.two.Supermarket.Crockery;
import practice.two.Supermarket.Electronics;
import practice.two.Supermarket.Food;
import practice.two.Supermarket.InventoryManager;
import practice.two.Supermarket.Manufacturer;
import practice.two.Supermarket.Supplier;

// Made SupermarketInventorySystem a static nested class
public class SupermarketInventorySystem {
    private Supermarket supermarket;
    private InventoryManager manager;
    private Scanner scanner = new Scanner(System.in);

    public SupermarketInventorySystem() {
        this.supermarket = new Supermarket();
        this.manager = supermarket.new InventoryManager();
    }

    public static void main(String[] args) {
        SupermarketInventorySystem system = new SupermarketInventorySystem();
        system.initializeSampleData();
        system.run();
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addProductMenu();
                    break;
                case 2:
                    manager.displayAllProducts();
                    break;
                case 3:
                    manager.displayInventoryByType();
                    break;
                case 4:
                    manager.displayConsumptionEvaluation();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== SUPERMARKET INVENTORY MANAGEMENT ===");
        System.out.println("1. Add Product");
        System.out.println("2. Display All Products");
        System.out.println("3. Display Inventory Summary");
        System.out.println("4. Display Consumption Evaluation");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private void addProductMenu() {
        System.out.println("\nChoose product type:");
        System.out.println("1. Food Product");
        System.out.println("2. Electronics Product");
        System.out.println("3. Crockery Product");
        System.out.print("Choose type: ");

        int type = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (type) {
            case 1:
                addFoodProduct();
                break;
            case 2:
                addElectronicsProduct();
                break;
            case 3:
                addCrockeryProduct();
                break;
            default:
                System.out.println("Invalid product type!");
        }
    }

    private void addFoodProduct() {
        try {
            System.out.print("Product Code: ");
            int code = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Unit Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("Manufacture Date (YYYY-MM-DD): ");
            LocalDate mfgDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Expiration Date (YYYY-MM-DD): ");
            LocalDate expDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Supplier Name: ");
            String supplierName = scanner.nextLine();

            System.out.print("Supplier Address: ");
            String supplierAddress = scanner.nextLine();

            System.out.print("Supplier Phone: ");
            String supplierPhone = scanner.nextLine();

            Supplier supplier = supermarket.new Supplier(supplierName, supplierAddress, supplierPhone);
            Food product = supermarket.new Food(code, name, quantity, price, mfgDate, expDate, supplier);
            manager.addProduct(product);

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void addElectronicsProduct() {
        try {
            System.out.print("Product Code: ");
            int code = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Unit Price: ");
            double price = scanner.nextDouble();

            System.out.print("Warranty (months): ");
            int warranty = scanner.nextInt();

            System.out.print("Capacity (KW): ");
            double capacity = scanner.nextDouble();

            Electronics product = supermarket.new Electronics(code, name, quantity, price, warranty, capacity);
            manager.addProduct(product);

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void addCrockeryProduct() {
        try {
            System.out.print("Product Code: ");
            int code = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Unit Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("Manufacturer Name: ");
            String mfgName = scanner.nextLine();

            System.out.print("Manufacturer Address: ");
            String mfgAddress = scanner.nextLine();

            System.out.print("Manufacturer Phone: ");
            String mfgPhone = scanner.nextLine();

            System.out.print("Arrival Date (YYYY-MM-DD): ");
            LocalDate arrivalDate = LocalDate.parse(scanner.nextLine());

            Manufacturer manufacturer = supermarket.new Manufacturer(mfgName, mfgAddress, mfgPhone);
            Crockery product = supermarket.new Crockery(code, name, quantity, price, arrivalDate, manufacturer);
            manager.addProduct(product);

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void initializeSampleData() {
        // Sample suppliers and manufacturers
        Supplier vinmart = supermarket.new Supplier("VinMart", "123 Nguyen Hue, HCMC", "0123456789");
        Supplier thTrueMilk = supermarket.new Supplier("TH True Milk", "456 Le Loi, Hanoi", "0987654321");
        Manufacturer minhLong = supermarket.new Manufacturer("Minh Long", "789 Dong Khoi, HCMC", "0111222333");
        Manufacturer batTrang = supermarket.new Manufacturer("Bat Trang", "101 Hang Bac, Hanoi", "0444555666");

        // Sample food products
        manager.addProduct(supermarket.new Food(1001, "Rice", 100, 25000,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), vinmart));
        manager.addProduct(supermarket.new Food(1002, "Expired Milk", 5, 15000,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 1), thTrueMilk));

        // Sample electronics products
        manager.addProduct(supermarket.new Electronics(2001, "TV", 2, 5000000, 24, 0.15));
        manager.addProduct(supermarket.new Electronics(2002, "Refrigerator", 10, 8000000, 36, 0.25));

        // Sample crockery products
        manager.addProduct(supermarket.new Crockery(3001, "Dinner Plates", 60, 50000,
                LocalDate.of(2024, 6, 1), minhLong));
        manager.addProduct(supermarket.new Crockery(3002, "Bowls", 30, 25000,
                LocalDate.of(2024, 7, 1), batTrang));
    }
}
