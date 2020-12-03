package de.cerus.aoc2020.third;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeOne implements Challenge {

    private static final int SLOPE_X = 3;
    private static final int SLOPE_Y = 1;

    @Override
    public void solve(final String input) {
        final List<String> patterns = Arrays.stream(input.split("\n"))
                .filter(s -> s.matches(Day3.REGEX))
                .collect(Collectors.toList());
        final Day3.Grid grid = new Day3.Grid(patterns);

        int trees = 0;
        int x = 0;
        int y = 0;

        // Count trees
        while (y + SLOPE_Y < patterns.size()) {
            x += SLOPE_X;
            y += SLOPE_Y;

            final char charAt = grid.getCharAt(x, y);
            if (charAt == Day3.TREE_CHAR) {
                trees++;
            }
        }

        System.out.printf("Encountered %d trees%n", trees);
    }

}
