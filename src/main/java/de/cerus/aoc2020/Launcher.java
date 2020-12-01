package de.cerus.aoc2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Launcher {

    public static void main(final String[] args) {
        System.out.println("Hello there!");

        final String inputDay1 = getInput(1, 1);
        new de.cerus.aoc2020.first.ChallengeOne().solve(inputDay1);
        new de.cerus.aoc2020.first.ChallengeTwo().solve(inputDay1);
    }

    /**
     * Get input for the specified challenge and the specified day
     *
     * @param day       The day
     * @param challenge The challenge
     *
     * @return The input
     */
    private static String getInput(final int day, final int challenge) {
        final InputStream inputStream = Launcher.class.getResourceAsStream(String.format("/input-day%d-%d.txt", day, challenge));
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
