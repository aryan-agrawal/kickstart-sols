package b2020;

import java.util.*;

/**
 * My solution to the 2020 Google Kickstart, Round B, Bus Routes Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc8/00000000002d83bf
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class Buses {

    /**
     * Main method. Deals primarily with I/O operations.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            Scanner line = new Scanner(input.nextLine());
            int numBuses = line.nextInt();
            long lastDay = line.nextLong();
            line = new Scanner(input.nextLine());
            LinkedList<Long> busDays = new LinkedList<>();
            for (int bus = 1; bus <= numBuses; bus++) {
                busDays.add(line.nextLong());
            }
            long leaveBy = getLeaveBy(busDays, lastDay);
            System.out.println("Case #" + test + ": " + leaveBy);
        }
    }

    /**
     * Helper method that returns the last possible day the traveller can leave,
     * while still arriving on time.
     */
    private static long getLeaveBy(LinkedList<Long> busDays, long lastDay) {
        if (busDays.size() == 0) {
            return lastDay;
        } else if (lastDay % busDays.getLast() == 0) {
            busDays.removeLast();
            return getLeaveBy(busDays, lastDay);
        } else {
            return getLeaveBy(busDays, lastDay - (lastDay % busDays.getLast()));
        }
    }
}
