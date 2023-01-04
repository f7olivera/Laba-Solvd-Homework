package com.solvd.reflection.vehicle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Car extends Vehicle {
    private final static Logger LOGGER = LogManager.getLogger(Car.class);
    private int capacity;

    public Car(String model, int year) {
        super(model, year);
    }

    @Override
    public void moveForward() {
        LOGGER.info("Car is moving forward.");
    }

    @Override
    public void moveBackward() {
        LOGGER.info("Car is moving backward.");
    }

    @Override
    public void stop() {
        LOGGER.info("Car is stopping.");
    }

    @Override
    public void steer(int wheelPosition) {
        LOGGER.info("Car is steering " + (wheelPosition < 0 ? "left." : (wheelPosition > 0 ? "right." : "center.")));
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
