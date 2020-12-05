package de.cerus.aoc2020.fifth;

import de.cerus.aoc2020.Challenge;
import de.cerus.aoc2020.Day;

public class Day5 implements Day {

    static final int RANGE_ROW_MIN = 0;
    static final int RANGE_ROW_MAX = 127;

    static final int RANGE_COL_MIN = 0;
    static final int RANGE_COL_MAX = 7;

    private final Challenge firstChallenge = new ChallengeOne();
    private final Challenge secondChallenge = new ChallengeTwo();

    static Pair<Integer, Integer> walkTape(final char[] tape) {
        // I just want to point out that I hate maths. This was a lot of pain for me.

        int boundMinRow = Day5.RANGE_ROW_MIN;
        int boundMaxRow = Day5.RANGE_ROW_MAX;
        int boundMinCol = Day5.RANGE_COL_MIN;
        int boundMaxCol = Day5.RANGE_COL_MAX;

        for (final char c : tape) {
            switch (c) {
                case 'F':
                    boundMaxRow = boundMaxRow - ((int) Math.ceil((boundMaxRow - boundMinRow) / 2d));
                    break;
                case 'B':
                    boundMinRow = boundMinRow + ((int) Math.ceil((boundMaxRow - boundMinRow) / 2d));
                    break;
                case 'L':
                    boundMaxCol = boundMaxCol - ((int) Math.ceil((boundMaxCol - boundMinCol) / 2d));
                    break;
                case 'R':
                    boundMinCol = boundMaxCol - ((int) Math.floor((boundMaxCol - boundMinCol) / 2d));
                    break;
            }
        }

        // boundMaxRow & boundMinRow have the same value, boundMaxCol & boundMinCol have also the same value
        return new Pair<>(boundMaxRow, boundMaxCol);
    }

    @Override
    public void solveChallenges(final String input) {
        System.out.println("=== Day 5 ===");

        System.out.println("--- Challenge 1 ---");
        this.firstChallenge.solve(input);

        System.out.println("--- Challenge 2 ---");
        this.secondChallenge.solve(input);

        System.out.println();
    }

    static class Pair<A, B> {

        public A a;
        public B b;

        public Pair(final A a, final B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + this.a +
                    ", b=" + this.b +
                    '}';
        }
    }

}
