package traffic;

/**
 * @author Mack_TB
 * @since 23/03/2024
 * @version 1.0.6
 */

public class Road {
    private String name;
    private boolean isOpen; // state
    private int remainingTime;

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Road(String name, boolean isOpen, int remainingTime) {
        this.name = name;
        this.isOpen = isOpen;
        this.remainingTime = remainingTime;
    }

    @Override
    public String toString() {
        return isOpen ? "Road \"" + name + "\" will be "+ANSI_GREEN+"open for " + remainingTime + "s."+ANSI_RESET :
                        "Road \"" + name + "\" will be "+ANSI_RED+"closed for " + remainingTime + "s."+ANSI_RESET;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
}
