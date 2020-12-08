package de.cerus.aoc2020.seventh;

import de.cerus.aoc2020.Challenge;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ChallengeOne implements Challenge {

    @Override
    public void solve(final String input) {
        final Map<String, Day7.Bag> bagLookup = new HashMap<>();
        final Map<Day7.Bag, Day7.BagRule> ruleLookup = new HashMap<>();

        Arrays.stream(input.split("\n"))
                .forEach(s -> {
                    final Day7.BagRule rule = this.parseBagRule(s, bagLookup);
                    ruleLookup.put(rule.bag, rule);
                });

        ruleLookup.forEach((bag, bagRule) -> {
            System.out.println(bag.color + ": " + bagRule.allowedBags.entrySet().stream()
                    .map(bagIntegerEntry -> bagIntegerEntry.getKey().color + " x" + bagIntegerEntry.getValue())
                    .collect(Collectors.joining(", ")));
        });

        final Day7.Bag shinyGoldBag = bagLookup.get("shiny gold");
        final Set<Day7.BagRule> allChecked = new HashSet<>();
        Set<Day7.BagRule> current;

        // I'm going straight to hell. What the fuck is this
        while (true) {
            if (allChecked.isEmpty()) {
                allChecked.addAll(this.getRulesContaining(shinyGoldBag, ruleLookup));
            } else {
                while (!(current = this.getRulesContaining(allChecked.stream().map(bagRule -> bagRule.bag).collect(Collectors.toSet()), ruleLookup).stream()
                        .filter(bagRule -> !allChecked.contains(bagRule))
                        .collect(Collectors.toSet())).isEmpty()) {
                    allChecked.addAll(current);
                }
                break;
            }
        }

        System.out.println(allChecked.size());
    }

    private Set<Day7.BagRule> getRulesContaining(final Set<Day7.Bag> bags, final Map<Day7.Bag, Day7.BagRule> ruleLookup) {
        final Set<Day7.BagRule> rules = new HashSet<>();
        for (final Day7.Bag bag : bags) {
            rules.addAll(this.getRulesContaining(bag, ruleLookup));
        }
        return rules;
    }

    private Set<Day7.BagRule> getRulesContaining(final Day7.Bag bag, final Map<Day7.Bag, Day7.BagRule> ruleLookup) {
        final Set<Day7.BagRule> set = new HashSet<>();
        ruleLookup.forEach((bag1, bagRule) -> {
            if (bagRule.allowedBags.containsKey(bag)) {
                set.add(bagRule);
            }
        });
        return set;
    }

    private Day7.BagRule parseBagRule(final String s, final Map<String, Day7.Bag> bagLookup) {
        final String[] split = s.split(" bags contain ");

        final String color = split[0];
        final Day7.Bag bag = this.getOrPut(color, bagLookup);

        final String ruleStr = split[1];
        final Day7.BagRule rule = new Day7.BagRule(bag, ruleStr.equals("no other bags.")
                ? new HashMap<>() : this.parseRuleString(ruleStr, bagLookup));

        return rule;
    }

    private Map<Day7.Bag, Integer> parseRuleString(final String ruleStr, final Map<String, Day7.Bag> bagLookup) {
        final String[] split = ruleStr.split(", ");
        final Map<Day7.Bag, Integer> map = new HashMap<>();

        for (final String s : split) {
            final String bagColor = s.substring(s.indexOf(' ')).trim().substring(0, s.lastIndexOf(' ') - 2);
            final int amount = Integer.parseInt(s.substring(0, s.indexOf(' ')));

            final Day7.Bag bag = this.getOrPut(bagColor, bagLookup);
            map.put(bag, amount);
        }

        return map;
    }

    private Day7.Bag getOrPut(final String color, final Map<String, Day7.Bag> lookup) {
        if (lookup.containsKey(color)) {
            return lookup.get(color);
        }

        lookup.put(color, new Day7.Bag(color));
        return lookup.get(color);
    }

}
