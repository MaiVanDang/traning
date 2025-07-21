package practice.three;

public class Truck extends Vehicle {

    private final TypeVehicle type = TypeVehicle.Truck;
    public double tonnage;

    public Truck(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture, String color, Owner owner,
            double tonnage) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
        this.tonnage = tonnage;
    }

    @Override
    public TypeVehicle getType() {
        return type;
    }

    @Override
    public void display() {
        System.out.println("Truck{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", tonnage=" + tonnage +
                ", owner=" + owner +
                ", type=" + type +
                '}');
    }
}
