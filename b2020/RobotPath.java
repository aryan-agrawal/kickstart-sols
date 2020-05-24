package b2020;

import java.util.*;

/**
 * My solution to the 2020 Google Kickstart, Round B, Robot Path Decoding Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ffc8/00000000002d83dc
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class RobotPath {

    /**
     * Main method, deals primarily with I/O. Defines a Robot object that
     * executes the given program for each test case.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        for (int test = 1; test <= numCases; test++) {
            String program = input.nextLine();
            Robot rob = new Robot();
            rob.execute(program);
            System.out.println("Case #" + test + ": " + rob.getxPos() + " " + rob.getyPos());
        }
    }

    /**
     * Class used to define a robot. Each robot has an x and y coordinate position, on
     * a grid as defined by the problem description. Can be fed a program of movements
     * to execute, as defined in the problem description.
     */
    private static class Robot {
        private long xPos;
        private long yPos;

        /**
         * Constructor for the Robot class. All robots default to starting at
         * position (1, 1).
         */
        private Robot() {
            xPos = 1;
            yPos = 1;
        }

        /**
         * Returns the x position of this robot.
         */
        private long getxPos() {
            return xPos;
        }

        /**
         * Returns the y position of this robot.
         */
        private long getyPos() {
            return yPos;
        }

        /**
         * Recursively reads and executes a given program. Has the capability to
         * read and execute subprograms. The program format is defined in the
         * problem description.
         */
        private void execute(String program) {
            if (program.length() > 0) {
                if (program.charAt(0) == 'N' || program.charAt(0) == 'S'
                        || program.charAt(0) == 'E' || program.charAt(0) == 'W') {
                    move(program.charAt(0));
                    execute(program.substring(1));
                } else {
                    int repeats = Integer.parseInt(program.substring(0, 1));
                    String subProgram = "";
                    int numOpenParen = 1;
                    for (int index = 2; numOpenParen > 0; index++) {
                        char thisPos = program.charAt(index);
                        if (thisPos == '(') {
                            numOpenParen++;
                        } else if (thisPos == ')') {
                            numOpenParen--;
                            if (numOpenParen == 0) {
                                break;
                            }
                        }
                        subProgram += thisPos;
                    }
                    for (int sub = 1; sub <= repeats; sub++) {
                        execute(subProgram);
                    }
                    execute(program.substring(3 + subProgram.length()));
                }
            }
        }

        /**
         * Helper method that moves this robot in one of four directions. Recognizes
         * char args of 'N', 'S', 'E', and 'W'.
         */
        private void move(char dir) {
            if (dir == 'N') {
                if (yPos == 1) {
                    yPos = 1000000000;
                } else {
                    yPos--;
                }
            } else if (dir == 'S') {
                if (yPos == 1000000000) {
                    yPos = 1;
                } else {
                    yPos++;
                }
            } else if (dir == 'E') {
                if (xPos == 1000000000) {
                    xPos = 1;
                } else {
                    xPos++;
                }
            } else if (dir == 'W') {
                if (xPos == 1) {
                    xPos = 1000000000;
                } else {
                    xPos--;
                }
            }
        }
    }
}
