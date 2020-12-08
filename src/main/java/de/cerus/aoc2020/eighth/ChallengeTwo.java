package de.cerus.aoc2020.eighth;

import de.cerus.aoc2020.Challenge;
import java.util.HashSet;
import java.util.Set;

public class ChallengeTwo implements Challenge {

    private int nextInstrIndex = 0;

    @Override
    public void solve(final String input) {
        final String[] lines = input.split("\n");

        // Find first nop/jmp and swap
        this.findNextInstr(lines);
        this.swapInstr(lines);

        while (true) {
            // Run test
            final Interpreter interpreter = new Interpreter();
            interpreter.setBreakOnLoop(true);
            interpreter.interpret(lines);
            final boolean test = !interpreter.interrupt;

            if (test) {
                // The faulty jmp/nop was fixed!
                System.out.printf("Accumulator is %d%n", interpreter.accumulator);
                break;
            }

            // The changed nop/jmp was the wrong one
            // Reset changed instruction
            this.swapInstr(lines);
            // Find next instruction
            this.findNextInstr(lines);
            // Swap instruction
            this.swapInstr(lines);
        }
    }

    private void swapInstr(final String[] lines) {
        final String line = lines[this.nextInstrIndex];
        if (line.startsWith("jmp")) {
            lines[this.nextInstrIndex] = "nop" + line.substring(3);
        } else {
            lines[this.nextInstrIndex] = "jmp" + line.substring(3);
        }
    }

    private void findNextInstr(final String[] lines) {
        for (int i = this.nextInstrIndex + 1; i < lines.length; i++) {
            final String line = lines[i];
            if (line.startsWith("jmp") || line.startsWith("nop")) {
                this.nextInstrIndex = i;
                break;
            }
        }
    }

    private static class Interpreter {

        private final Set<Integer> lines = new HashSet<>();
        private int accumulator = 0;
        private int line = 0;
        private boolean interrupt = false;
        private boolean breakOnLoop = false;

        public void interpret(final String[] lines) {
            while (this.line < lines.length) {
                if (this.interrupt) {
                    break;
                }

                if (!this.checkLine() && this.breakOnLoop) {
                    this.interrupt = true;
                    continue;
                }

                final String line = lines[this.line];

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

        public int getAccumulator() {
            return this.accumulator;
        }

        public int getLine() {
            return this.line;
        }

        public Set<Integer> getLines() {
            return this.lines;
        }

        public boolean isInterrupt() {
            return this.interrupt;
        }

        public void setInterrupt(final boolean interrupt) {
            this.interrupt = interrupt;
        }

        public boolean isBreakOnLoop() {
            return this.breakOnLoop;
        }

        public void setBreakOnLoop(final boolean breakOnLoop) {
            this.breakOnLoop = breakOnLoop;
        }

    }

}
