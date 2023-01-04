package com.solvd.reflection;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> carClass = Class.forName("com.solvd.reflection.vehicle.Car");
        Object car = carClass.getConstructor(String.class, int.class).newInstance("Toyota Corolla", 2006);

        // Print information about class
        LOGGER.info(String.format(
                "Parent class of %s is %s", carClass.getSimpleName(), carClass.getSuperclass().getSimpleName()
        ));

        // Print interfaces
        Class<?>[] interfaces = {};
        Class<?> currentClass = carClass;
        while (currentClass != null) {
            interfaces = ArrayUtils.addAll(interfaces, currentClass.getInterfaces());
            currentClass = currentClass.getSuperclass();
        }
        LOGGER.info(String.format(
                "%s implements %s\n",
                carClass.getSimpleName(),
                Arrays.toString(Arrays.stream(interfaces).map(Class::getSimpleName).toArray())
        ));

        // Print fields
        printFields(carClass);
        printFields(carClass.getSuperclass());

        // Print methods
        printMethods(carClass);
        printMethods(carClass.getSuperclass());

        // Call methods with parameters
        LOGGER.info("Calling methods using reflection.");
        carClass.getMethod("moveForward").invoke(car);
        carClass.getMethod("steer", int.class).invoke(car, 5);
    }

    public static void printFields(Class<?> objectClass) {
        LOGGER.info(String.format("Declared fields of %s: ", objectClass.getSimpleName()));
        for (Field field : objectClass.getDeclaredFields()) {
            LOGGER.info(field.getName() + ":");
            LOGGER.info("    modifiers: " + Modifier.toString(field.getModifiers()));
            LOGGER.info("    type: " + field.getType());
        }
        LOGGER.info("\n");
    }

    public static void printMethods(Class<?> objectClass) {
        LOGGER.info(String.format("Declared methods of %s: ", objectClass.getSimpleName()));
        for (Method method : objectClass.getDeclaredMethods()) {
            LOGGER.info(method.getName() + ":");
            LOGGER.info("    modifiers: " + Modifier.toString(method.getModifiers()));
            LOGGER.info("    parameter types: " + Arrays.toString(method.getParameterTypes()));
        }
        LOGGER.info("\n");
    }
}
