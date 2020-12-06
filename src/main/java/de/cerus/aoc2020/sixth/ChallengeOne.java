package de.cerus.aoc2020.sixth;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChallengeOne implements Challenge {

    private static final String QUESTIONS = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public void solve(final String input) {
        final int sum = Arrays.stream(input.split("\n\n"))
                .map(s -> s.split("\n"))
                .mapToInt(strings -> {
                    // Define set that will contain ass yes questions for this group
                    final Set<String> yesQuestions = new HashSet<>();

                    return Arrays.stream(strings)
                            .mapToInt(value -> {
                                // Define the total points of this group
                                int j = 0;

                                for (final char c : value.toCharArray()) {
                                    // If question has not yet been answered as yes = 1, if question has already been answered = 0
                                    final int i = QUESTIONS.contains(c + "") && !yesQuestions.contains(c + "") ? 1 : 0;

                                    // Add question to the set if it has been answered with yes
                                    if (i == 1) {
                                        yesQuestions.add(c + "");
                                    }

                                    // Add the result to the total points of this group
                                    j += i;
                                }

                                // Return group points
                                return j;
                            })
                            .sum();
                })
                .sum();

        System.out.printf("The sum is %d%n", sum);
    }

}
