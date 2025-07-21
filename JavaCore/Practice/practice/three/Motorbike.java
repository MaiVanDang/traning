package practice.three;

public class Motorbike extends Vehicle {

    private final TypeVehicle type = TypeVehicle.Motorbike;
    public int engineCapacity; // in cc

    public Motorbike(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture, String color,
            Owner owner,
            int engineCapacity) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public TypeVehicle getType() {
        return type;
    }

    @Override
    public void display() {
        System.out.println("Motobike{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", owner=" + owner +
                ", type=" + type +
                '}');
    }

}
