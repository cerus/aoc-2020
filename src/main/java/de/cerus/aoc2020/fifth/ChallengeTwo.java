package de.cerus.aoc2020.fifth;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeTwo implements Challenge {

    @Override
    public void solve(final String input) {
        final List<Integer> allIds = Arrays.stream(input.split("\n"))
                .map(s -> Day5.walkTape(s.toCharArray()))
                .map(integerIntegerPair -> integerIntegerPair.a * 8 + integerIntegerPair.b)
                .collect(Collectors.toList());
        final int max = allIds.stream()
                .max(Comparator.comparingInt(value -> value))
                .orElse(0);
        final int min = allIds.stream()
                .min(Comparator.comparingInt(value -> value))
                .orElse(0);

        for (int i = min; i <= max; i++) {
            if (!allIds.contains(i)) {
                System.out.printf("Your seat id is %d%n", i);
                break;
            }
        }
    }

}
