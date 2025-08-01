package main.java.com.eastnets.bank.ui;

import java.util.Scanner;

public class InputHandler {

    //read an index for the list and check if its in the constraints
    public static int readIndex(Scanner scanner, String message, int maxSize) {
        System.out.print(message);
        try {
            int index = Integer.parseInt(scanner.nextLine().trim());
            if (index >= 0 && index < maxSize)
                return index;

            System.out.println("Invalid choice. Please select a valid option.");
            return -1;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    //read a double from user and check if its negative
    public static double readPositiveDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0)
                    return value;
                System.out.println("Value must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a numeric value.");
            }
        }
    }

    //read an integer from user
    public static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer.");
            }
        }
    }

    //read any string
    public static String readString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
