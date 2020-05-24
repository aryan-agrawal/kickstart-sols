package c2020;

import java.util.Scanner;

/**
 * My solution to the 2020 Google Kickstart, Round C, Candies Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ff43/0000000000337b4d
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class Candies {
    /**
     * Main method. Deals with I/O and also handles query/update operations
     * as defined in the problem description.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            Scanner sets = new Scanner(input.nextLine());
            int len = sets.nextInt();
            int numOps = sets.nextInt();
            Scanner line = new Scanner(input.nextLine());
            int[] vals = new int[len];
            for (int i = 0; i < len; i++) {
                vals[i] = line.nextInt();
            }
            int result = 0;
            for (int i = 1; i <= numOps; i++) {
                Scanner thisOp = new Scanner(input.nextLine());
                String type = thisOp.next();
                int val1 = thisOp.nextInt();
                int val2 = thisOp.nextInt();
                if (type.equals("U")) {
                    vals[val1 - 1] = val2;
                } else {
                    boolean adding = true;
                    int total = 0;
                    int multiplier = 1;
                    for (int index = val1 - 1; index < val2; index++) {
                        int sub = (multiplier) * vals[index];
                        multiplier++;
                        if (adding) {
                            total += sub;
                        } else {
                            total -= sub;
                        }
                        adding = !adding;
                    }
                    result += total;
                }
            }
            System.out.println("Case #" + test + ": " + result);
        }
    }
}
