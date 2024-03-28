package traffic;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.4
 */

public class TrafficLights {
    private int numberOfRoads;
    private int intervals;
    private final Thread thread;
    static boolean finished = false;
    static boolean modeSystem = false;

    public TrafficLights(int numberOfRoads, int intervals) {
        this.numberOfRoads = numberOfRoads;
        this.intervals = intervals;

        thread = new Thread(new QueueThread(numberOfRoads, intervals));
        thread.setName("QueueThread");
        thread.start();

    }

    public void run(Scanner sc) {
        int option = -1;
        do {
            printMenu();
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect option");
                clearConsole(sc, true);
                continue;
            }
            switch (option) {
                case 1 -> {
                    addRoad();
                    clearConsole(sc, true);
                }
                case 2 -> {
                    deleteRoad();
                    clearConsole(sc, true);
                }
                case 3 -> {
                    openSystem(sc);
                    clearConsole(sc, false);
                }
                case 0 -> quit();
                default -> {
                    System.out.println("Incorrect option");
                    clearConsole(sc, true);
                }
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("""
            Menu:
            1. Add
            2. Delete
            3. System
            0. Quit""");
    }

    private void quit() {
        System.out.println("Bye!");
        finished = true;
        System.exit(0);
    }

    public void addRoad() {
        System.out.println("Road added");
    }

    public void deleteRoad() {
        System.out.println("Road deleted");
    }

    public void openSystem(Scanner sc) {
        modeSystem = true;
        sc.nextLine();
        modeSystem = false;
    }

    public static void clearConsole(Scanner sc, boolean newLine) {
        if (newLine) sc.nextLine();
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}