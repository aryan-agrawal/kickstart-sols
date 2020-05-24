package b2020;

import java.util.*;

/**
 * My solution to the 2020 Google Kickstart, Round B, Wandering Robot Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc8/00000000002d8565
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class WanderingRobot {

    /**
     * Main method, deals primarily with I/O. Also defines a new cache for each test case.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            Scanner line = new Scanner(input.nextLine());
            int w = line.nextInt();
            int h = line.nextInt();
            int l = line.nextInt();
            int u = line.nextInt();
            int r = line.nextInt();
            int d = line.nextInt();
            Board board = new Board(w, h, l, r, u, d);
            double[][][] cache = new double[w][h][2];
            double probSuccess;
            if (w == 1 || h == 1) {
                probSuccess = 0;
            } else {
                probSuccess = getProbSuccess(1, 1, board, cache);
            }
            System.out.println("Case #" + test + ": " + probSuccess);
        }
    }

    /**
     * Recursive helper method that determines the probability of the robot
     * successfully reaching the bottom right corner of the given board.
     * Reads from the cache to avoid repeated operations.
     */
    private static double getProbSuccess(int xPos, int yPos, Board b, double[][][] cache) {
        if (cache[xPos - 1][yPos - 1][1] == 1) {
            return cache[xPos - 1][yPos - 1][0];
        } else if (b.inHole(xPos, yPos)) {
            addToCache(xPos, yPos, 0, cache);
            return 0;
        } else if (xPos > b.getHoleRight() || yPos > b.getHoleDown()) {
            addToCache(xPos, yPos, 1, cache);
            return 1;
        } else if (xPos == b.getWidth()) {
            double prob = getProbSuccess(xPos, yPos + 1, b, cache);
            addToCache(xPos, yPos, prob, cache);
            return prob;
        } else if (yPos == b.getHeight()) {
            double prob = getProbSuccess(xPos + 1, yPos, b, cache);
            addToCache(xPos, yPos, prob, cache);
            return prob;
        } else {
            double probRight = getProbSuccess(xPos + 1, yPos, b, cache);
            double probDown = getProbSuccess(xPos, yPos + 1, b, cache);
            double probHere = (probRight + probDown) / 2;
            addToCache(xPos, yPos, probHere, cache);
            return probHere;
        }
    }

    /**
     * Helper method that writes to the cache.
     */
    private static void addToCache(int xPos, int yPos, double d, double[][][] cache) {
        cache[xPos - 1][yPos - 1][1] = 1;
        cache[xPos - 1][yPos - 1][0] = d;
    }

    /**
     * This class serves as a representation of a Board with a hole, as defined
     * in the problem description.
     */
    private static class Board {
        private int width, height;
        private int left, right, up, down;

        /**
         * Board constructor, sets the size of the board and the location
         * of the hole.
         */
        private Board(int w, int h, int l, int r, int u, int d) {
            width = w;
            height = h;
            left = l;
            right = r;
            up = u;
            down = d;
        }

        /**
         * Returns the width of the board.
         */
        private int getWidth() {
            return width;
        }

        /**
         * Returns the height of the board.
         */
        private int getHeight() {
            return height;
        }

        /**
         * Returns the position representing the right-most edge of the hole.
         */
        private int getHoleRight() {
            return right;
        }

        /**
         * Returns the position representing the bottom-most edge of the hole.
         */
        private int getHoleDown() {
            return down;
        }

        /**
         * Returns whether or not a certain position on the board falls
         * within the hole.
         */
        private boolean inHole(int xPos, int yPos) {
            return xPos >= left && xPos <= right && yPos >= up && yPos <= down;
        }
    }
}
