package de.cerus.aoc2020;

import de.cerus.aoc2020.fifth.Day5;
import de.cerus.aoc2020.first.Day1;
import de.cerus.aoc2020.fourth.Day4;
import de.cerus.aoc2020.second.Day2;
import de.cerus.aoc2020.sixth.Day6;
import de.cerus.aoc2020.third.Day3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Launcher {

    public static void main(final String[] args) {
        System.out.println("Hello there!");

        new Day1().solveChallenges(getInput(1));
        new Day2().solveChallenges(getInput(2));
        new Day3().solveChallenges(getInput(3));
        new Day4().solveChallenges(getInput(4));
        new Day5().solveChallenges(getInput(5));
        new Day6().solveChallenges(getInput(6));
    }

    /**
     * Get input for the specified day
     *
     * @param day The day
     *
     * @return The input
     */
    private static String getInput(final int day) {
        final InputStream inputStream = Launcher.class.getResourceAsStream(String.format("/input-day%d.txt", day));
        if (inputStream == null) {
            return null;
        }

        // Read input stream
        try (final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             final BufferedReader reader = new BufferedReader(inputStreamReader)) {
            final StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s).append("\n");
            }

            inputStream.close();
            return stringBuilder.toString().trim();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
