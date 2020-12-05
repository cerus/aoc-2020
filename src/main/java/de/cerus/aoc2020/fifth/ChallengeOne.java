package de.cerus.aoc2020.fifth;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;

public class ChallengeOne implements Challenge {

    @Override
    public void solve(final String input) {
        Arrays.stream(input.split("\n"))
                .map(s -> Day5.walkTape(s.toCharArray()))
                .mapToInt(integerIntegerPair -> integerIntegerPair.a * 8 + integerIntegerPair.b)
                .max()
                .ifPresent(value -> System.out.printf("The highest id is %d%n", value));
    }

}
