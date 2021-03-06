package de.cerus.aoc2020.sixth;

import de.cerus.aoc2020.Challenge;
import de.cerus.aoc2020.Day;

public class Day6 implements Day {

    private final Challenge firstChallenge = new ChallengeOne();
    private final Challenge secondChallenge = new ChallengeTwo();

    @Override
    public void solveChallenges(final String input) {
        System.out.println("=== Day 6 ===");

        System.out.println("--- Challenge 1 ---");
        this.firstChallenge.solve(input);

        System.out.println("--- Challenge 2 ---");
        this.secondChallenge.solve(input);

        System.out.println();
    }

}
