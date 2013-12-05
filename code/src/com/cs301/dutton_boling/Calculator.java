package com.cs301.dutton_boling;

import com.cs301.dutton_boling.models.Attribute;
import com.cs301.dutton_boling.models.Covering;
import com.cs301.dutton_boling.models.Entry;
import com.cs301.dutton_boling.models.EntrySet;
import com.cs301.dutton_boling.models.Rule;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart and Tiffani
 * Date: 10/20/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {

    private static int maxCoveringSize = -1;

    public static Map<Covering, List<Rule>> calculateRules(EntrySet entries, int size) {
        maxCoveringSize = size;
        List<Covering> coverings = calculateCoverings(entries);

        //append all of the decision attributes to a list
        List<Integer> decisionAttributes = entries.getDecisionAttributes();
        Map<Covering, List<Rule>> coveringListMap = new HashMap<Covering, List<Rule>>();
        for (Covering covering : coverings) {
            List<Rule> rules = new ArrayList<Rule>();
            coveringListMap.put(covering, rules);
            Map<Set<Attribute>, List<Entry>> setListMap = covering.getAttributeListMap();
            Set<Set<Attribute>> keySet = setListMap.keySet();
            for (Set<Attribute> key : keySet) {
                List<Entry> entryList = setListMap.get(key);
                for (Entry entry : entryList) {
                    Set<Attribute> consequents = new HashSet<Attribute>(entry.getAttributes(decisionAttributes));
                    Rule rule = new Rule(key, consequents);
                    if (!rules.contains(rule)) {
                        rules.add(rule);
                    }
                }
            }
        }
        return coveringListMap;
    }

    private static List<Covering> calculateCoverings(EntrySet entries) { //This is the RICO Algorithm
        System.out.println("Starting covering");
        List<Covering> coverings = new ArrayList<Covering>();
        List<Integer> attributeColumns = new ArrayList<Integer>();
        for (int i = 0; i < entries.getAttributeCount(); i++) {
            attributeColumns.add(i);
        }

        long startTime = System.currentTimeMillis();
        Set<Set<Integer>> partitions = collectSets(attributeColumns, maxCoveringSize);
        System.out.println("Recursive Time: " + (System.currentTimeMillis() - startTime));
        //Removing partitions with decision attributes in them
        System.out.println("Size before: " + partitions.size());
        vailidatePartitions(entries, partitions);
        System.out.println("Size after: " + partitions.size());


        for (Set<Integer> partition : partitions) {
            Covering covering = new Covering();
            covering.setColumns(new ArrayList<Integer>(partition));
            for (Entry entry : entries.getEntries()) {
                Set<Attribute> attributes = new HashSet<Attribute>();
                for (Integer i : partition) {
                    attributes.add(entry.getAttribute(i));
                }
                covering.addEntry(attributes, entry);
            }
            coverings.add(covering);
        }

        System.out.println("Validating Coverings");
        return validCoverings(entries, coverings, entries.getDecisionAttributes());
    }

    private static void vailidatePartitions(EntrySet entries, Set<Set<Integer>> partitions) {
        Set<Integer> decAttr = new HashSet<Integer>(entries.getDecisionAttributes());
        List<Set<Integer>> invalidPartitions = new LinkedList<Set<Integer>>();
        for (Set<Integer> partition : partitions) {
            Set<Integer> temp = new HashSet<Integer>(decAttr);
            temp.retainAll(partition);
            boolean disjoint = temp.isEmpty();
            if (!disjoint) {
                invalidPartitions.add(partition);
            }
        }

        partitions.removeAll(invalidPartitions);

    }

    private static List<Covering> validCoverings(EntrySet entries, List<Covering> coverings, List<Integer> decisionAttributes) {
        //Get the decision attribute covering from the list
        Covering decision = new Covering();
        decision.setColumns(new ArrayList<Integer>(decisionAttributes));
        for (Entry entry : entries.getEntries()) {
            Set<Attribute> attributes = new HashSet<Attribute>();
            for (Integer i : decisionAttributes) {
                attributes.add(entry.getAttribute(i));
            }
            decision.addEntry(attributes, entry);
        }
        Set<Set<Entry>> decisionEntrySet = new HashSet<Set<Entry>>();
        List<Covering> invalidCoverings = new ArrayList<Covering>();


        for (Set<Attribute> attributeSet : decision.getAttributeListMap().keySet()) {
            Set<Entry> ent = new HashSet<Entry>();
            ent.addAll(decision.getAttributeListMap().get(attributeSet));
            decisionEntrySet.add(ent);
        }

        for (Covering covering : coverings) {
            Set<Set<Entry>> entrySet = new HashSet<Set<Entry>>();
            for (Set<Attribute> attributeSet : covering.getAttributeListMap().keySet()) {
                Set<Entry> ent = new HashSet<Entry>();
                ent.addAll(covering.getAttributeListMap().get(attributeSet));
                entrySet.add(ent);
            }
            if (!decisionEntrySet.equals(entrySet)) {
                invalidCoverings.add(covering);
            }
        }


        coverings.removeAll(invalidCoverings);
        System.out.println("Minimizing Coverings");
        return coverings;
    }


    private static Set<Set<Integer>> collectSets(List<Integer> originalSet, int n) {
        Set<Set<Integer>> solution = new HashSet<Set<Integer>>();
        for (int i = 1; i <= n; i++) {
            getSubsets(originalSet, i, 0, new HashSet<Integer>(), solution);
        }

        return solution;
    }

    private static void getSubsets(List<Integer> originalSet, int n, int index, Set<Integer> currentSet, Set<Set<Integer>> solution) {
        if (currentSet.size() == n) {
            solution.add(new HashSet<Integer>(currentSet));
            return;
        }

        if (index == originalSet.size())
            return;
        Integer x = originalSet.get(index);
        currentSet.add(x);
        getSubsets(originalSet, n, index + 1, currentSet, solution);
        currentSet.remove(x);
        getSubsets(originalSet, n, index + 1, currentSet, solution);
    }
}
