package traffic;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.6
 */

public class TrafficLights {
    private int numberOfRoads;
    static int intervals;
    private final QueueThread queueThread;
    static boolean finished = false;
    static boolean modeSystem = false;
    static final Scanner scanner = new Scanner(System.in);

    public TrafficLights(int numberOfRoads, int intervals) {
        this.numberOfRoads = numberOfRoads;
        TrafficLights.intervals = intervals;

        queueThread = new QueueThread(numberOfRoads, intervals);
        Thread thread = new Thread(queueThread, "QueueThread");
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
                clearConsole(true);
                continue;
            }
            switch (option) {
                case 1 -> {
                    addRoad();
                    clearConsole(true);
                }
                case 2 -> {
                    deleteRoad();
                    clearConsole(true);
                }
                case 3 -> {
                    openSystem();
                    clearConsole(false);
                }
                case 0 -> quit();
                default -> {
                    System.out.println("Incorrect option");
                    clearConsole(true);
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
        System.out.print("Input road name: ");
        String roadName = scanner.nextLine();
        queueThread.getCircularQueue().addRoad(roadName);
    }

    public void deleteRoad() {
        queueThread.getCircularQueue().deleteRoad();
    }

    public void openSystem() {
//        System.out.println("System opened");
        modeSystem = true;
        scanner.nextLine();
        modeSystem = false;
    }

    public static void clearConsole(boolean newLine) {
        if (newLine) scanner.nextLine();
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