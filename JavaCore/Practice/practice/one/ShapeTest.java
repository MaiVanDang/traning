package practice.one;

class Shape {
    protected double width;
    protected double height;

    public Shape(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Shape() {
        this.width = 0;
        this.height = 0;
    }

    public void displayInfo() {
        System.out.println("Shape - Width: " + width + ", Height: " + height);
    }
}

class Rectangle extends Shape {
    public Rectangle(double width, double height) {
        super(width, height);
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public void displayInfo() {
        System.out.println("Rectangle - Width: " + width + ", Height: " + height);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super(radius * 2, radius * 2);
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    public void displayInfo() {
        System.out.println("Circle - Radius: " + radius);
        System.out.println("Area: " + getArea());
        System.out.println("Circumference: " + getCircumference());
    }
}

class Triangle extends Shape {
    private double base;
    private double height;
    private double side1, side2, side3;

    public Triangle(double base, double height, double side1, double side2, double side3) {
        super(base, height);
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getArea() {
        return 0.5 * base * height;
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public void displayInfo() {
        System.out.println("Triangle - Base: " + base + ", Height: " + height);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        System.out.println("=== SHAPE INHERITANCE DEMO ===\n");

        Shape shape = new Shape(3, 4);
        System.out.println("1. Base Shape:");
        shape.displayInfo();
        System.out.println();

        Rectangle rectangle = new Rectangle(5, 10);
        System.out.println("2. Rectangle:");
        rectangle.displayInfo();
        System.out.println();

        Circle circle = new Circle(7);
        System.out.println("3. Circle:");
        circle.displayInfo();
        System.out.println();

        Triangle triangle = new Triangle(6, 8, 6, 8, 10);
        System.out.println("4. Triangle:");
        triangle.displayInfo();
        System.out.println();

        // Demonstrate polymorphism
        System.out.println("=== POLYMORPHISM DEMO ===");
        Shape[] shapes = { shape, rectangle, circle, triangle };

        for (int i = 0; i < shapes.length; i++) {
            System.out.println("Shape " + (i + 1) + ":");
            shapes[i].displayInfo();
            System.out.println();
        }
    }
}