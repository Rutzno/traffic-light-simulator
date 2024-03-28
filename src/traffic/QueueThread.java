package traffic;

import java.util.Scanner;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.5
 */

public class QueueThread implements Runnable{
    private int passedTime = 0;
    private int numberOfRoads;
    private int intervals;
    private CircularQueue circularQueue;

    public QueueThread(int numberOfRoads,
                       int intervals) {
        this.numberOfRoads = numberOfRoads;
        this.intervals = intervals;
        circularQueue = new CircularQueue(numberOfRoads);
    }

    @Override
    public void run() {
        do {
            ++passedTime;
            if (TrafficLights.modeSystem) {
                System.out.println("! " + passedTime + "s. have passed since system startup !");
                System.out.printf("! Number of roads: %d !%n", numberOfRoads);
                System.out.printf("! Interval: %d !%n", intervals);
                circularQueue.printRoads();
                System.out.println("! Press \"Enter\" to open menu !");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (TrafficLights.modeSystem) {
                TrafficLights.clearConsole(false);
            }
        } while (!TrafficLights.finished);
    }

    public CircularQueue getCircularQueue() {
        return circularQueue;
    }
}