package de.cerus.aoc2020.sixth;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChallengeTwo implements Challenge {

    private static final String QUESTIONS = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public void solve(final String input) {
        final int sum = Arrays.stream(input.split("\n\n"))
                .map(s -> s.split("\n"))
                .mapToInt(strings -> {
                    // Define map that will hold the amount that every question has been answered by this group
                    final Map<Character, Integer> yesQuestions = new HashMap<>();

                    // Loop through group answers
                    for (final String string : strings) {
                        // Loop through answered questions
                        for (final char c : string.toCharArray()) {
                            // Count
                            final int i = yesQuestions.getOrDefault(c, 0);
                            yesQuestions.put(c, i + 1);
                        }
                    }

                    // Return the amount of questions that everyone in the group has answered with yes
                    return (int) yesQuestions.entrySet()
                            .stream()
                            .filter(characterIntegerEntry -> characterIntegerEntry.getValue() == strings.length)
                            .count();
                })
                .sum();

        System.out.printf("The sum is %d%n", sum);
    }

}
