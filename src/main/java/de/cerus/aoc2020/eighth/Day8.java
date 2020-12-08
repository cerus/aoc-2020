package de.cerus.aoc2020.eighth;

import de.cerus.aoc2020.Challenge;
import de.cerus.aoc2020.Day;
import java.util.Map;

public class Day8 implements Day {

    private final Challenge firstChallenge = new ChallengeOne();
    private final Challenge secondChallenge = new ChallengeTwo();

    @Override
    public void solveChallenges(final String input) {
        System.out.println("=== Day 8 ===");

        System.out.println("--- Challenge 1 ---");
        this.firstChallenge.solve(input);

        System.out.println("--- Challenge 2 ---");
        this.secondChallenge.solve(input);

        System.out.println();
    }

    static class Bag {

        public String color;

        public Bag(final String color) {
            this.color = color;
        }

    }

    static class BagRule {

        public Bag bag;
        public Map<Bag, Integer> allowedBags;

        public BagRule(final Bag bag, final Map<Bag, Integer> allowedBags) {
            this.bag = bag;
            this.allowedBags = allowedBags;
        }

    }

}
