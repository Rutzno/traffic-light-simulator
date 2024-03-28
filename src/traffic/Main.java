package traffic;

import java.util.Scanner;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.4
 */

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to the traffic management system!");
        System.out.print("Input the number of roads: ");
        int numberOfRoads = getInput();
        System.out.print("Input the interval: ");
        int intervals = getInput();
        TrafficLights.clearConsole(sc, false);

        TrafficLights traffic = new TrafficLights(numberOfRoads, intervals);
        traffic.run(sc);
    }

    private static int getInput() {
        int result = 0;
        do {
            try {
                result = Integer.parseInt(sc.nextLine());
                if (result <= 0) {
                    System.out.print("Error! Incorrect Input. Try again: ");
                }
            } catch (Exception e) {
                System.out.print("Error! Incorrect Input. Try again: ");
            }
        } while (result <= 0);
        return result;
    }
}