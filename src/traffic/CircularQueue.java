package traffic;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.6
 */

public class CircularQueue {
    private int front; // head
    private int rear;  // tail
    private final Road[] roads;
    private int countRoads = 0;

    public CircularQueue(int size) {
        front = 0;
        rear = 0;
        this.roads = new Road[size];
    }

    public void addRoad(String name) { // enqueue
        Road road;
        if (isEmpty()) {
            road = new Road(name, true, TrafficLights.intervals);
            countRoads++;
            roads[front] = road;
            System.out.println(name + " Added!");
            return;
        }
        int next = (rear + 1) % roads.length;
        if (roads[next] != null) {
            System.out.println("Queue is full");
        } else {
            int previousTime = roads[rear].getRemainingTime();
            if (!roads[rear].isOpen()) {
                road = new Road(name, false, previousTime + TrafficLights.intervals);
            } else {
                if (countRoads >= 2) {
                    roads[front].setRemainingTime(roads[front].getRemainingTime() + TrafficLights.intervals);
                }
                road = new Road(name, false, roads[rear].getRemainingTime());
            }
            countRoads++;
            roads[next] = road;
            rear = next;
            System.out.println(name + " Added!");
        }
    }

    public void deleteRoad() { // dequeue
        int next = (front + 1) % roads.length;
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            Road road = roads[front];
            String name = road.getName();
            roads[front] = null;
            countRoads--;
            front = next;
            if (roads[front] != null && !roads[front].isOpen()) {
                roads[front].setRemainingTime(road.getRemainingTime()); // update remaining time
            }
            System.out.println(name + " deleted!");
        }
    }

    public boolean isEmpty() {
        return roads[front] == null;
    }

    public void printRoads0() {
        if (roads[front] == null) {
            return;
        }
        System.out.println("\n"+roads[front]);
        int i = (front+1)%roads.length;
        while (i != front) {
            if (roads[i] == null) {
                break;
            }
            System.out.println(roads[i]);
            i = (i+1)%roads.length;
        }
        System.out.println();
    }

    public void printRoads() {
        if (roads[front] == null) {
            return;
        }
        System.out.println();
        int i = (front - 1) % roads.length; // to get previous index of 'front'
        do {
            i = (i + 1) % roads.length;
            if (roads[i] == null) {
                break;
            }
            System.out.println(roads[i]);
        } while (i != rear);
        System.out.println();
    }

    public void computeRemainingTimes() {
        if (roads[front] == null) {
            return;
        }
        int i = (front - 1) % roads.length;
        do {
            i = (i + 1) % roads.length;
            if (roads[i] == null) {
                break;
            }
            int newRemainingTime = roads[i].getRemainingTime() - 1;
            if (newRemainingTime == 0) {
                if (!roads[i].isOpen()) {
                    roads[i].setRemainingTime(TrafficLights.intervals);
                } else {
                    roads[i].setRemainingTime(countRoads > 2 ?
                            (countRoads - 1) * TrafficLights.intervals : TrafficLights.intervals);
                }
                if (countRoads == 1) { //  let the road open
                    roads[i].setOpen(true);
                    break;
                }
                roads[i].setOpen(!roads[i].isOpen()); // change the road's state
            } else {
                roads[i].setRemainingTime(newRemainingTime);
            }
        } while (i != rear);
    }
}