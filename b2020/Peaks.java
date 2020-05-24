package b2020;

import java.util.Scanner;

/**
 * My solution to the 2020 Google Kickstart, Round B, Bike Tour Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc8/00000000002d82e6
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class Peaks {

    /**
     * Main method, deals with I/O and contains logic checking number of peaks
     * along the path. A peak is defined as in the problem description.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            int numCheckpoints = Integer.parseInt(input.nextLine());
            int[] heights = new int[numCheckpoints];
            Scanner line = new Scanner(input.nextLine());
            for (int check = 1; check <= numCheckpoints; check++) {
                heights[check - 1] = line.nextInt();
            }
            int numPeaks = 0;
            for (int check = 1; check < numCheckpoints - 1; check++) {
                if (heights[check] > heights[check - 1]
                        && heights[check] > heights[check + 1]) {
                    numPeaks++;
                }
            }
            System.out.println("Case #" + test + ": " + numPeaks);
        }
    }
}
