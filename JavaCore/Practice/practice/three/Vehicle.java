package practice.three;

public abstract class Vehicle {
    String vehicleNumber;
    TypeManufacturer manufacturer;
    int yearOfManufacture;
    String color;
    Owner owner;

    public Vehicle(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture, String color,
            Owner owner) {
        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.owner = owner;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public TypeManufacturer getManufacturer() {
        return manufacturer;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public Owner getOwner() {
        return owner;
    }

    public abstract void display();

    public abstract TypeVehicle getType();
}
