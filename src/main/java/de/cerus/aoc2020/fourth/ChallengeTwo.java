package de.cerus.aoc2020.fourth;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;

public class ChallengeTwo implements Challenge {

    @Override
    public void solve(final String input) {
        final long count = Arrays.stream(input.split("\n\n"))
                .map(Passport::parse)
                .filter(Passport::isValid)
                .count();

        System.out.printf("Found %d valid passports%n", count);
    }

    private static class Passport {

        public Integer birthYear;
        public Integer issueYear;
        public Integer expirationYear;
        public Height height;
        public String hairColor;
        public String eyeColor;
        public String passportId;

        private Passport() {
        }

        public Passport(final Integer birthYear,
                        final Integer issueYear,
                        final Integer expirationYear,
                        final Height height,
                        final String hairColor,
                        final String eyeColor,
                        final String passportId) {
            this.birthYear = birthYear;
            this.issueYear = issueYear;
            this.expirationYear = expirationYear;
            this.height = height;
            this.hairColor = hairColor;
            this.eyeColor = eyeColor;
            this.passportId = passportId;
        }

        static Passport parse(final String str) {
            final Passport passport = new Passport();

            // I could have solved this in a more abstract way but this is enough and it works
            // so why should I transform this into some ultra high quality enterprise code?

            for (final String s : str.split("[\n ]")) {
                switch (s.substring(0, 4)) {
                    case "byr:":
                        try {
                            passport.birthYear = Integer.parseInt(s.substring(4));
                        } catch (final NumberFormatException ignored) {
                        }
                        break;
                    case "iyr:":
                        try {
                            passport.issueYear = Integer.parseInt(s.substring(4));
                        } catch (final NumberFormatException ignored) {
                        }
                        break;
                    case "eyr:":
                        try {
                            passport.expirationYear = Integer.parseInt(s.substring(4));
                        } catch (final NumberFormatException ignored) {
                        }
                        break;
                    case "hgt:":
                        final String temp = s.substring(4);
                        if (temp.matches("\\d+(in|cm)")) {
                            passport.height = new Height(Integer.parseInt(temp.substring(0, temp.length() - 2)),
                                    temp.substring(temp.length() - 2));
                        }
                        break;
                    case "hcl:":
                        passport.hairColor = s.substring(4);
                        break;
                    case "ecl:":
                        passport.eyeColor = s.substring(4);
                        break;
                    case "pid:":
                        passport.passportId = s.substring(4);
                        break;
                }
            }

            return passport;
        }

        public boolean isValid() {
            return this.birthYear != null && this.birthYear >= 1920 && this.birthYear <= 2002 && this.issueYear != null && this.issueYear >= 2010
                    && this.issueYear <= 2020 && this.expirationYear != null && this.expirationYear >= 2020 && this.expirationYear <= 2030
                    && this.height != null && this.height.isValid() && this.hairColor != null && this.hairColor.matches("#[a-f0-9]{6}")
                    && this.eyeColor != null && this.eyeColor.matches("(amb|blu|brn|gry|grn|hzl|oth)") && this.passportId != null
                    && this.passportId.matches("\\d+") && this.passportId.length() == 9;
        }

    }

    private static class Height {

        public int num;
        public String thing;

        public Height(final int num, final String thing) {
            this.num = num;
            this.thing = thing;
        }

        public boolean isValid() {
            if (!"in".equals(this.thing) && !"cm".equals(this.thing)) {
                return false;
            }

            if (this.thing.equals("cm")) {
                return this.num >= 150 && this.num <= 193;
            } else {
                return this.num >= 59 && this.num <= 76;
            }
        }

    }

}
