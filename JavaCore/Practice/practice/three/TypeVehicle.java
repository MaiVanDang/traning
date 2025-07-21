package practice.three;

public enum TypeVehicle {
    Car("Car"),
    Truck("Truck"),
    Motorbike("Motorbike");

    private final String type;

    TypeVehicle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    // Tuỳ chọn: hỗ trợ chuyển từ String về enum
    public static TypeVehicle fromString(String text) {
        for (TypeVehicle v : TypeVehicle.values()) {
            if (v.type.equalsIgnoreCase(text)) {
                return v;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle type: " + text);
    }
}
