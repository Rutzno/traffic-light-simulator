package traffic;

import java.util.Scanner;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.2
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the traffic management system!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number of roads: ");
        int numberOfRoads = sc.nextInt();
        System.out.print("Input the interval: ");
        int intervals = sc.nextInt();

        int option;
        do {
            printMenu();
            option = sc.nextInt();
            switch (option) {
                case 1 -> addRoad();
                case 2 -> deleteRoad();
                case 3 -> openSystem();
                case 0 -> quit();
                default -> System.out.println("Invalid option");
            }
        } while (option != 0);

    }

    private static void quit() {
        System.out.println("Bye!");
    }

    private static void printMenu() {
        System.out.println("""
            Menu:
            1. Add
            2. Delete
            3. System
            0. Quit""");
    }

    public static void addRoad() {
        System.out.println("Road added");
    }

    public static void deleteRoad() {
        System.out.println("Road deleted");
    }

    public static void openSystem() {
        System.out.println("System opened");
    }
}