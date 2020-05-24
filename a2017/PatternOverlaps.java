package a2017;

import java.util.HashMap;
import java.util.Scanner;

/**
 * My solution to the 2017 Google Kickstart, Round A, Pattern Overlaps Problem.
 * https://codingcompetitions.withgoogle.com/kickstart/round/0000000000201c97/0000000000201b79
 *
 * Disclaimer: These solutions are entirely mine, but the problem is from the Google
 * Kickstart competition. This solution may be imperfect.
 * @author Aryan Agrawal
 */
public class PatternOverlaps {

    /**
     * Main method, deals primarily with I/O operations. Defines a cache
     * that is used for all test cases.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCases = Integer.parseInt(input.nextLine());
        HashMap<String, HashMap<String, Boolean>> cache = new HashMap<>();
        for (int test = 1; test <= numCases; test++) {
            String pattern1 = input.nextLine();
            String pattern2 = input.nextLine();
            boolean overlap = checkOverlap(pattern1, pattern2, cache);
            if (overlap) {
                System.out.println("Case #" + test + ": TRUE");
            } else {
                System.out.println("Case #" + test + ": FALSE");
            }
        }
    }

    /**
     * Called once for each test case, returns whether or not there is an overlap between
     * the given patterns.
     */
    private static boolean checkOverlap(String pattern1, String pattern2, HashMap<String, HashMap<String, Boolean>> cache) {
        if (cache.getOrDefault(pattern1, new HashMap<>()).getOrDefault(pattern2, false)) {
            return cache.get(pattern1).get(pattern2);
        } else if (cache.getOrDefault(pattern2, new HashMap<>()).getOrDefault(pattern1, false)) {
            return cache.get(pattern2).get(pattern1);
        } else if (pattern1.equals("") && pattern2.equals("")) {
            addToCache(pattern1, pattern2, cache, true);
            return true;
        } else if (pattern1.startsWith("*")) {
            if (pattern2.isEmpty()) {
                boolean overlaps = checkOverlap(pattern1.substring(1), pattern2, cache);
                addToCache(pattern1, pattern2, cache, overlaps);
                return overlaps;
            } else {
                int len2 = pattern2.length();
                int skippable = 4;
                for (int skip = 0; skip <= skippable && skip <= len2; skip++) {
                    if (skip != 0 && pattern2.charAt(skip - 1) == '*') {
                        skippable++;
                    } else {
                        boolean overlaps = checkOverlap(pattern1.substring(1), pattern2.substring(skip), cache);
                        if (overlaps) {
                            addToCache(pattern1, pattern2, cache, true);
                            return true;
                        }
                    }
                }
                addToCache(pattern1, pattern2, cache, false);
                return false;
            }
        } else if (pattern2.startsWith("*")) {
            boolean overlaps = checkOverlap(pattern2, pattern1, cache);
            addToCache(pattern1, pattern2, cache, overlaps);
            return overlaps;
        } else if (!pattern1.isEmpty() && !pattern2.isEmpty() && pattern1.charAt(0) == pattern2.charAt(0)) {
            boolean overlaps = checkOverlap(pattern1.substring(1), pattern2.substring(1), cache);
            addToCache(pattern1, pattern2, cache, overlaps);
            return overlaps;
        } else {
            addToCache(pattern1, pattern2, cache,false);
            return false;
        }
    }

    /**
     * Helper method to simplify operations that write to the cache.
     */
    private static void addToCache(String pattern1, String pattern2, HashMap<String, HashMap<String, Boolean>> cache, boolean val) {
        HashMap<String, Boolean> inner = cache.getOrDefault(pattern1, new HashMap<>());
        inner.put(pattern2, val);
        cache.put(pattern1, inner);
    }
}
