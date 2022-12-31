package com.solvd.reflection;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> carClass = Class.forName("com.solvd.reflection.vehicle.Car");
        Object car = carClass.getConstructor(String.class, int.class).newInstance("Toyota Corolla", 2006);

        // Print information about class
        System.out.printf("Parent class of %s is %s\n", carClass.getSimpleName(), carClass.getSuperclass().getSimpleName());

        // Print interfaces
        Class<?>[] interfaces = {};
        Class<?> currentClass = carClass;
        while (currentClass != null) {
            interfaces = ArrayUtils.addAll(interfaces, currentClass.getInterfaces());
            currentClass = currentClass.getSuperclass();
        }
        System.out.printf("%s implements %s\n\n",
                carClass.getSimpleName(),
                Arrays.toString(Arrays.stream(interfaces).map(Class::getSimpleName).toArray()));

        // Print fields
        printFields(carClass);
        printFields(carClass.getSuperclass());

        // Print methods
        printMethods(carClass);
        printMethods(carClass.getSuperclass());

        // Call methods with parameters
        System.out.println("Calling methods using reflection.");
        carClass.getMethod("moveForward").invoke(car);
        carClass.getMethod("steer", int.class).invoke(car, 5);
    }

    public static void printFields(Class<?> objectClass) {
        System.out.printf("Declared fields of %s: \n", objectClass.getSimpleName());
        for (Field field : objectClass.getDeclaredFields()) {
            System.out.println(field.getName() + ":");
            System.out.println("- modifiers: " + Modifier.toString(field.getModifiers()));
            System.out.println("- type: " + field.getType() + "\n");
        }
    }

    public static void printMethods(Class<?> objectClass) {
        System.out.printf("Declared methods of %s: \n", objectClass.getSimpleName());
        for (Method method : objectClass.getDeclaredMethods()) {
            System.out.println(method.getName() + ":");
            System.out.println("- modifiers: " + Modifier.toString(method.getModifiers()));
            System.out.println("- parameter types: " + Arrays.toString(method.getParameterTypes()) + "\n");
        }
    }
}
