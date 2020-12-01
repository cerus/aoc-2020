package de.cerus.aoc2020.first;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChallengeTwo implements Challenge {

    @Override
    public void solve(final String input) {
        final List<Integer> ints = this.parseInput(input);

        for (final int first : ints) {
            for (final int second : ints) {
                for (final int third : ints) {
                    if (first + second + third != 2020) {
                        continue;
                    }

                    System.out.printf("Found %d+%d+%d! Answer is %d%n", first, second, third, first * second * third);
                    return;
                }
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
