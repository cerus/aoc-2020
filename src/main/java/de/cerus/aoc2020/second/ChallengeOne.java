package de.cerus.aoc2020.second;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeOne implements Challenge {

    @Override
    public void solve(final String input) {
        final List<Pair<Policy, String>> lines = Arrays.stream(input.split("\n"))
                .filter(s -> s.matches(Day2.REGEX))
                .map(s -> {
                    final String[] split = s.split(":");

                    final String rawPolicy = split[0];
                    final String[] policySplit = rawPolicy.split(" ");
                    final String[] rangeSplit = policySplit[0].split("-");
                    final Policy policy = new Policy(new Range(
                            Integer.parseInt(rangeSplit[0]),
                            Integer.parseInt(rangeSplit[1])
                    ), policySplit[1].charAt(0));

                    return new Pair<>(policy, split[1].trim());
                })
                .collect(Collectors.toList());

        int corrupted = 0;
        for (final Pair<Policy, String> policyStringPair : lines) {
            if (!this.checkLine(policyStringPair)) {
                corrupted++;
            }
        }

        System.out.printf("Amount of working passwords: %d%n", lines.size() - corrupted);
        System.out.printf("Amount of corrupted passwords: %d%n", corrupted);
    }

    private boolean checkLine(final Pair<Policy, String> line) {
        final Policy policy = line.a;
        final String password = line.b;

        final char policyChar = policy.character;
        int occurrences = 0;
        for (final char c : password.toCharArray()) {
            if (c == policyChar) {
                occurrences++;
            }
        }

        return occurrences >= policy.range.from && occurrences <= policy.range.to;
    }

    private class Pair<A, B> {

        public A a;
        public B b;

        public Pair(final A a, final B b) {
            this.a = a;
            this.b = b;
        }
    }

    private class Policy {

        public Range range;
        public char character;

        public Policy(final Range range, final char character) {
            this.range = range;
            this.character = character;
        }
    }

    private class Range {

        public int from;
        public int to;

        public Range(final int from, final int to) {
            this.from = from;
            this.to = to;
        }
    }
}
