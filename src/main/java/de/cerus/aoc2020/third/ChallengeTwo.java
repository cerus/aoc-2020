package de.cerus.aoc2020.third;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeTwo implements Challenge {

    @Override
    public void solve(final String input) {
        final List<String> patterns = Arrays.stream(input.split("\n"))
                .filter(s -> s.matches(Day3.REGEX))
                .collect(Collectors.toList());
        final Day3.Grid grid = new Day3.Grid(patterns);

        // These slopes were provided by AOC
        final List<Slope> slopes = Arrays.asList(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2)
        );

        int num = 1;
        int x = 0;
        int y = 0;

        // Count trees for every slope
        for (final Slope slope : slopes) {
            int trees = 0;
            while (y < patterns.size()) {
                final char charAt = grid.getCharAt(x, y);
                if (charAt == Day3.TREE_CHAR) {
                    trees++;
                }

                x += slope.right;
                y += slope.down;
            }

            // Multiply our number by the amount of encountered trees
            num *= trees;
            x = 0;
            y = 0;
        }

        System.out.printf("Number is %d%n", num);
    }

    private class Slope {

        public int right;
        public int down;

        public Slope(final int right, final int down) {
            this.right = right;
            this.down = down;
        }

    }

}
