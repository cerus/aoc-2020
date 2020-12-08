package de.cerus.aoc2020.eighth;

import de.cerus.aoc2020.Challenge;
import java.util.HashSet;
import java.util.Set;

public class ChallengeOne implements Challenge {

    private final Set<Integer> lines = new HashSet<>();
    private int accumulator = 0;
    private int line = 0;

    @Override
    public void solve(final String input) {
        final String[] split = input.split("\n");
        while (this.line < split.length) {
            final String line = split[this.line];
            if (!this.checkLine()) {
                System.out.printf("Accumulator is %d%n", this.accumulator);
                return;
            }

            this.recordLine();

            final int lineBef = this.line;
            this.execInstruction(line);
            final int lineNow = this.line;

            if (lineBef == lineNow) {
                this.line++;
            }
        }
    }

    private void execInstruction(final String instr) {
        final String[] split = instr.split(" ");

        final boolean add = split[1].charAt(0) == '+';
        final int i = Integer.parseInt(split[1]);

        switch (split[0].toLowerCase()) {
            case "nop":
                // Do nothing
                break;
            case "acc":
                this.accumulator += i;
                break;
            case "jmp":
                this.line += i;
                break;
        }
    }

    private void recordLine() {
        this.lines.add(this.line);
    }

    private boolean checkLine() {
        return !this.lines.contains(this.line);
    }

}
