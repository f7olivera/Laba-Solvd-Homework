package com.solvd.reflection.vehicle;

public class Car extends Vehicle {
    private int capacity;

    public Car(String model, int year) {
        super(model, year);
    }

    @Override
    public void moveForward() {
        System.out.println("Car is moving forward.");
    }

    @Override
    public void moveBackward() {
        System.out.println("Car is moving backward.");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping.");
    }

    @Override
    public void steer(int wheelPosition) {
        System.out.println("Car is steering " + (wheelPosition < 0 ? "left." : (wheelPosition > 0 ? "right." : "center.")));
    }

    public static String honk() {
        return "pii-piip";
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
