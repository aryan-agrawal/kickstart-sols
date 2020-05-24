package c2020;

import java.util.Scanner;

/**
 * My solution to the 2020 Google Kickstart, Round C, Perfect Subarray Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ff43/00000000003381cb
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class PerfectSub {
    /**
     * Main method. Deals with I/O. Also determines the number of perfect
     * subarrays in the input, as defined in the problem description.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            int numVals = Integer.parseInt(input.nextLine());
            int[] nums = new int[numVals];
            Scanner line = new Scanner(input.nextLine());
            for (int check = 1; check <= numVals; check++) {
                nums[check - 1] = line.nextInt();
            }
            int subtotal = 0;
            int numPerf = 0;
            for (int x = 0; x < nums.length; x++) {
                for (int y = x; y < nums.length; y++) {
                    if (x == y) {
                        subtotal = 0;
                    }
                    subtotal += nums[y];
                    if (Math.sqrt(subtotal) % 1 == 0) {
                        numPerf++;
                    }
                }
            }
            System.out.println("Case #" + test + ": " + numPerf);
        }
    }
}
