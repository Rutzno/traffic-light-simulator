package traffic;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.5
 */

public class CircularQueue {
    private int front;
    private int rear;
    private final String[] roads;

    public CircularQueue(int size) {
        front = 0;
        rear = 0;
        this.roads = new String[size];
    }

    public void addRoad(String name) { // enqueue
        if (isEmpty()) {
            roads[front] = name;
            System.out.println(name + " Added!");
            return;
        }
        int next = (rear + 1) % roads.length;
        if (roads[next] != null) {
            System.out.println("Queue is full");
        } else {
            roads[next] = name;
            rear = next;
            System.out.println(name + " Added!");
        }
    }

    public void deleteRoad() { // dequeue
        int next = (front + 1) % roads.length;
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            String name = roads[front];
            roads[front] = null;
            front = next;
            System.out.println(name + " deleted!");
        }
    }

    public boolean isEmpty() {
        return roads[front] == null;
    }

    public void printRoads() {
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
}