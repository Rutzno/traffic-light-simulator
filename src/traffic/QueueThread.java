package traffic;

import java.util.Scanner;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.4
 */

public class QueueThread implements Runnable{
    Scanner scanner = new Scanner(System.in);
    private int passedTime = 0;
    private int numberOfRoads;
    private int intervals;

    public QueueThread(int numberOfRoads,
                       int intervals) {
        this.numberOfRoads = numberOfRoads;
        this.intervals = intervals;
    }

    @Override
    public void run() {
        do {
            ++passedTime;
            if (TrafficLights.modeSystem) {
                System.out.println("! " + passedTime + "s. have passed since system startup !");
                System.out.printf("! Number of roads: %d !%n", numberOfRoads);
                System.out.printf("! Interval: %d !%n", intervals);
                System.out.println("! Press \"Enter\" to open menu !");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (TrafficLights.modeSystem) {
                TrafficLights.clearConsole(Main.sc, false);
            }
        } while (!TrafficLights.finished);
    }
}