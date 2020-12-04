package de.cerus.aoc2020.fourth;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;

public class ChallengeOne implements Challenge {

    private static final List<String> REQ_FIELDS = Arrays.asList(
            "byr:",
            "iyr:",
            "eyr:",
            "hgt:",
            "hcl:",
            "ecl:",
            "pid:"
    );

    @Override
    public void solve(final String input) {
        // This was almost too easy

        final long count = Arrays.stream(input.split("\n\n"))
                .filter(s -> REQ_FIELDS.stream().allMatch(s::contains))
                .count();

        System.out.printf("Found %d valid passports%n", count);
    }

}
