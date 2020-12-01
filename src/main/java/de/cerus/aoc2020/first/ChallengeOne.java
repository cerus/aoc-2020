package de.cerus.aoc2020.first;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChallengeOne implements Challenge {

    @Override
    public void solve(final String input) {
        final List<Integer> ints = this.parseInput(input);

        System.out.println("--- Day 1, Challenge 1 ---");
        for (final int first : ints) {
            for (final int second : ints) {
                if (first + second != 2020) {
                    continue;
                }

                System.out.printf("Found %d+%d! Answer is %d%n", first, second, first * second);
                return;
            }
        }

        System.out.println("No matching integers were found :(");
    }

    private List<Integer> parseInput(final String input) {
        return Arrays.stream(input.trim().split("\n"))
                .filter(s -> s.matches("-?\\d+"))
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (final NumberFormatException ignored) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
