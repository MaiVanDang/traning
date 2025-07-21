package practice.three;

public class Car extends Vehicle {

    private final TypeVehicle type = TypeVehicle.Car;
    public int numberOfSeats;
    public String engineType;

    public Car(String vehicleNumber, TypeManufacturer manufacturer, int yearOfManufacture, String color, Owner owner,
            int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType;
    }

    @Override
    public TypeVehicle getType() {
        return type;
    }

    @Override
    public void display() {
        System.out.println("Car{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", engineType='" + engineType + '\'' +
                ", owner=" + owner +
                ", type=" + type +
                '}');
    }

}
