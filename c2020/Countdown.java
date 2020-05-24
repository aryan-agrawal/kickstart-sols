package c2020;

import java.util.Scanner;

/**
 * My solution to the 2020 Google Kickstart, Round C, Countdown Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ff43/00000000003380d2
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class Countdown {
    /**
     * Main method. Deals with I/O. Also determines number of countdowns
     * in the input as defined in the problem description.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            Scanner sets = new Scanner(input.nextLine());
            int N = sets.nextInt();
            int K = sets.nextInt();
            int[] nums = new int[N];
            Scanner line = new Scanner(input.nextLine());
            for (int i = 1; i <= N; i++) {
                nums[i - 1] = line.nextInt();
            }
            int numRuns = 0;
            int needed = K;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == needed) {
                    if (nums[i] == 1) {
                        needed = K;
                        numRuns++;
                    } else {
                        needed--;
                    }
                } else {
                    needed = K;
                    if (nums[i] == needed) {
                        needed--;
                    }
                }
            }
            System.out.println("Case #" + test + ": " + numRuns);
        }
    }
}
