package practice.two;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Supermarket {

    public abstract class Goods {
        protected int code;
        protected String name;
        protected int inventoryQuantity;
        protected double unitPrice;
        protected final int VAT;

        public Goods(int code, String name, int inventoryQuantity, double unitPrice, int VAT) {

            if (inventoryQuantity < 0) {
                throw new IllegalArgumentException("Inventory quantity must be >= 0.");
            }

            this.code = code;
            this.name = name;
            this.inventoryQuantity = inventoryQuantity;
            this.unitPrice = unitPrice;
            this.VAT = VAT; // %
        }

        public abstract String evaluateConsumption();

        @Override
        public String toString() {
            return String.format("Code: %s, Name: %s, Quantity: %d, Price: %.2f",
                    code, name, inventoryQuantity, unitPrice);
        }

        public int getGoodsCode() {
            return code;
        }

        public int getInventoryQuantity() {
            return inventoryQuantity;
        }

        public int getVAT() {
            return VAT;
        }

        public String getName() {
            return name;
        }
    }

    public abstract class Owner {
        String name;
        String address;
        String phoneNumber;

        public Owner(String name, String address, String phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }
    }

    public class Supplier extends Owner {

        public Supplier(String name, String address, String phoneNumber) {
            super(name, address, phoneNumber);
        }
    }

    public class Manufacturer extends Owner {

        public Manufacturer(String name, String address, String phoneNumber) {
            super(name, address, phoneNumber);
        }

    }

    public class Food extends Goods {
        LocalDate dateOfManufacture;
        LocalDate dateOfExpiry;
        Supplier supplier;

        public Food(int code, String name, int inventoryQuantity, double unitPrice, LocalDate dateOfManufacture,
                LocalDate dateOfExpiry, Supplier supplier) {
            super(code, name, inventoryQuantity, unitPrice, 5);

            if (dateOfExpiry.isBefore(dateOfManufacture)) {
                throw new IllegalArgumentException("Date of expiry must be after date of manufacture.");
            }

            this.dateOfManufacture = dateOfManufacture;
            this.dateOfExpiry = dateOfExpiry;
            this.supplier = supplier;
        }

        @Override
        public String evaluateConsumption() {
            if (inventoryQuantity > 0 && dateOfExpiry.isBefore(LocalDate.now())) {
                return "Hard to sell";
            }
            return "No evaluation";
        }

        @Override
        public String toString() {
            return super.toString()
                    + String.format(", Supplier name: %s, Supplier address: %s, Supplier phone: %s, Mfg: %s, Exp: %s",
                            supplier.name, supplier.address, supplier.phoneNumber, dateOfManufacture, dateOfExpiry);
        }
    }

    public class Crockery extends Goods {

        LocalDate dateOfArrival;
        Manufacturer manufacturer;

        public Crockery(int code, String name, int inventoryQuantity, double unitPrice, LocalDate dateOfArrival,
                Manufacturer manufacturer) {
            super(code, name, inventoryQuantity, unitPrice, 10);
            if (dateOfArrival.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date of arrival cannot be in the future.");
            }
            this.dateOfArrival = dateOfArrival;
            this.manufacturer = manufacturer;
        }

        @Override
        public String evaluateConsumption() {
            long daysInStorage = ChronoUnit.DAYS.between(dateOfArrival, LocalDate.now());
            if (inventoryQuantity > 50 && daysInStorage > 10) {
                return "Slow sale";
            }
            return "No evaluation";
        }

        @Override
        public String toString() {
            return super.toString() + String.format(
                    ", Manufacturer Name: %s, Manufacturer Adress: %s, Manufacturer Phone: %s, Arrival: %s",
                    manufacturer.name, manufacturer.address, manufacturer.phoneNumber, dateOfArrival);
        }
    }

    public class Electronics extends Goods {

        int warrantyPeriod; // in months
        double capacity; // KW

        public Electronics(int code, String name, int inventoryQuantity, double unitPrice, int warrantyPeriod,
                double capacity) {
            super(code, name, inventoryQuantity, unitPrice, 10);

            if (warrantyPeriod < 0) {
                throw new IllegalArgumentException("Warranty period must be >= 0.");
            }
            if (capacity < 0) {
                throw new IllegalArgumentException("Capacity must be >= 0.");
            }

            this.warrantyPeriod = warrantyPeriod;
            this.capacity = capacity;
        }

        @Override
        public String evaluateConsumption() {
            if (inventoryQuantity < 3) {
                return "Sold";
            }
            return "No evaluation";
        }

        @Override
        public String toString() {
            return super.toString() + String.format(", Warranty: %d months, Capacity: %.2f KW",
                    warrantyPeriod, capacity);
        }
    }

    public class InventoryManager {

        // Thêm một mặt hàng
        private ArrayList<Goods> goods;

        public InventoryManager() {
            this.goods = new ArrayList<>();
        }

        public boolean addProduct(Goods good) {
            for (Goods g : goods) {
                if (g.getGoodsCode() == good.getGoodsCode()) {
                    System.out.println("Error: Product code already exists!");
                    return false;
                }
            }

            goods.add(good);
            System.out.println("Product added successfully!");
            return true;
        }

        public void displayInventoryByType() {
            int foodCount = 0, electronicsCount = 0, crockeryCount = 0;
            double foodVAT = 0, electronicsVAT = 0, crockeryVAT = 0;

            for (Goods g : goods) {
                if (g instanceof Food) {
                    foodCount += g.getInventoryQuantity();
                    foodVAT += g.getVAT() * g.getInventoryQuantity() * g.unitPrice / 100;
                } else if (g instanceof Electronics) {
                    electronicsCount += g.getInventoryQuantity();
                    electronicsVAT += g.getVAT() * g.getInventoryQuantity() * g.unitPrice / 100;
                } else if (g instanceof Crockery) {
                    crockeryCount += g.getInventoryQuantity();
                    crockeryVAT += g.getVAT() * g.getInventoryQuantity() * g.unitPrice / 100;
                }
            }

            System.out.println("\n=== INVENTORY SUMMARY ===");
            System.out.printf("Food Products: %d items, VAT: %.2f\n", foodCount, foodVAT);
            System.out.printf("Electronics: %d items, VAT: %.2f\n", electronicsCount, electronicsVAT);
            System.out.printf("Crockery: %d items, VAT: %.2f\n", crockeryCount, crockeryVAT);
            System.out.printf("Total VAT: %.2f\n", foodVAT + electronicsVAT + crockeryVAT);
        }

        // Display all products
        public void displayAllProducts() {
            System.out.println("\n=== ALL PRODUCTS ===");
            for (Goods g : goods) {
                System.out.println(g.toString());
                System.out.println("Consumption Status: " + g.evaluateConsumption());
                System.out.println("VAT: " + g.getVAT() + "%");
                System.out.println("---");
            }
        }

        // Display consumption evaluation
        public void displayConsumptionEvaluation() {
            System.out.println("\n=== CONSUMPTION EVALUATION ===");
            for (Goods g : goods) {
                String status = g.evaluateConsumption();
                if (!status.equals("No evaluation")) {
                    System.out.println(g.getGoodsCode() + " - " + g.getName() + ": " + status);
                }
            }
        }
    }

}