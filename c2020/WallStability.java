package c2020;

import java.util.HashSet;
import java.util.Scanner;

/**
 * My solution to the 2020 Google Kickstart, Round C, Wall Stability Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ff43/00000000003381cb
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class WallStability {

    /**
     * Main method. Deals primarily with I/O.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            Scanner sets = new Scanner(input.nextLine());
            int numRows = sets.nextInt();
            int numCols = sets.nextInt();
            char[][] wall = new char[numRows][numCols];
            for (int row = 0; row < numRows; row++) {
                String level = input.nextLine();
                for (int col = 0; col < numCols; col++) {
                    wall[row][col] = level.charAt(col);
                }
            }
            String order = stableOrder(wall);
            if (order == null) {
                System.out.println("Case #" + test + ": " + -1);
            } else {
                System.out.println("Case #" + test + ": " + order);
            }
        }
    }

    /**
     * Given a char[][] representing the pieces of the wall, returns a String
     * representing a possible stable order in which they were placed. A stable
     * placement is defined in the problem description.
     */
    private static String stableOrder(char[][] wall) {
        boolean[][] traversed = new boolean[wall.length][wall[0].length];
        HashSet<Character> notSet = new HashSet<>();
        for (int r = 0; r < wall.length; r++) {
            for (int c = 0; c < wall[0].length; c++) {
                notSet.add(wall[r][c]);
            }
        }
        String result = "";
        int lastSize = -1;
        while (!notSet.isEmpty()) {
            HashSet<Character> toRemove = new HashSet<>();
            for (Character symb : notSet) {
                boolean[][] polySoFar = new boolean[wall.length][wall[0].length];
                boolean stable = isStable(wall, wall.length - 1, polySoFar, symb, traversed);
                if (stable) {
                    toRemove.add(symb);
                    result += symb;
                }
            }
            for (Character symb : toRemove) {
                notSet.remove(symb);
            }
            if (lastSize == notSet.size()) {
                return null;
            }
            lastSize = notSet.size();
        }
        return result;
    }

    /**
     * Recursive helper method that determines, given a specific wall
     * and previous placements, whether a certain piece can be placed in a stable manner.
     */
    private static boolean isStable(char[][] wall, int row, boolean[][] polySoFar, char symb, boolean[][] traversed) {
        if (row == wall.length - 1) {
            for (int col = 0; col < wall[0].length; col++) {
                if (!traversed[row][col]) {
                    if (wall[row][col] == symb) {
                        polySoFar[row][col] = true;
                    }
                }
            }
            return isStable(wall, row - 1, polySoFar, symb, traversed);
        } else {
            for (int col = 0; col < wall[0].length; col++) {
                if (!traversed[row][col]) {
                    if (wall[row][col] == symb) {
                        if (polySoFar[row + 1][col] || traversed[row + 1][col]) {
                            polySoFar[row][col] = true;
                        } else {
                            return false;
                        }
                    }
                }
            }
            if (row == 0) {
                for (int r = 0; r < traversed.length; r++) {
                    for (int c = 0; c < traversed[0].length; c++) {
                        if (polySoFar[r][c]) {
                            traversed[r][c] = true;
                        }
                    }
                }
                return true;
            } else {
                return isStable(wall, row - 1, polySoFar,symb, traversed);
            }
        }
    }
}
