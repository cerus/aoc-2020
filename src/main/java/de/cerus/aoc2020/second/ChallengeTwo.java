package de.cerus.aoc2020.second;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeTwo implements Challenge {

    @Override
    public void solve(final String input) {
        final List<Pair<Policy, String>> lines = Arrays.stream(input.split("\n"))
                .filter(s -> s.matches(Day2.REGEX))
                .map(s -> {
                    final String[] split = s.split(":");

                    final String rawPolicy = split[0];
                    final String[] policySplit = rawPolicy.split(" ");
                    final String[] rangeSplit = policySplit[0].split("-");
                    final Policy policy = new Policy(
                            Integer.parseInt(rangeSplit[0]),
                            Integer.parseInt(rangeSplit[1]),
                            policySplit[1].charAt(0)
                    );

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

        final boolean firstPos = password.charAt(policy.firstPos - 1) == policyChar;
        final boolean secondPos = password.charAt(policy.secondPos - 1) == policyChar;

        return firstPos ^ secondPos;
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

        public int firstPos;
        public int secondPos;
        public char character;

        public Policy(final int firstPos, final int secondPos, final char character) {
            this.firstPos = firstPos;
            this.secondPos = secondPos;
            this.character = character;
        }
    }

}
