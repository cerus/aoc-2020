package de.cerus.aoc2020.third;

import de.cerus.aoc2020.Challenge;
import de.cerus.aoc2020.Day;
import java.util.List;

public class Day3 implements Day {

    static final String REGEX = "[.#]+";
    static final char TREE_CHAR = '#';

    private final Challenge firstChallenge = new ChallengeOne();
    private final Challenge secondChallenge = new ChallengeTwo();

    @Override
    public void solveChallenges(final String input) {
        System.out.println("=== Day 3 ===");

        System.out.println("--- Challenge 1 ---");
        this.firstChallenge.solve(input);

        System.out.println("--- Challenge 2 ---");
        this.secondChallenge.solve(input);

        System.out.println();
    }

    static class Grid {

        private final List<String> patterns;

        Grid(final List<String> patterns) {
            this.patterns = patterns;
        }

        public char getCharAt(int x, final int y) {
            final String pattern = this.patterns.get(y);
            if (x >= pattern.length()) {
                x = x % pattern.length();
            }

            return pattern.charAt(x);
        }

    }

}
