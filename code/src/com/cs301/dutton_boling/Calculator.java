package com.cs301.dutton_boling;

import com.cs301.dutton_boling.models.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart and Tiffani
 * Date: 10/20/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {

    public static List<Rule> calculateRules(EntrySet entries) {
        List<Covering> coverings = calculateCoverings(entries);
        //get the decision attribute.
        List<Covering> decisionAttributes = //TODO: get the decision attribute
        
        //create a list of points 
        List<Point> point;

        for(List<Covering> cover : coverings) { 
            //make the first point in each represent the column
            //ex the first point in the list will have the first value be a -1 and the second value be the column

            //point[covering][0] = {-1,cover} 


           for() // create a for loop to loop over each row
            //if it already exists as a point get rid of it 
            point[covering].x = coverings[covering][i];
            point[covering].y = decisionAttribute[covering][i];
            //this should create an x and y point where if x then y 
            //get rid of this point. 
            






        }

        System.out.println("Done");
        return null;
    }

    private static List<Covering> calculateCoverings(EntrySet entries) { //This is the RICO Algorithm
        List<Covering> coverings = new ArrayList<Covering>();
        Set<Integer> attributeColumns = new HashSet<Integer>();
        for (int i = 0; i < entries.getAttributeCount(); i++) {
            attributeColumns.add(i);
        }

        Set<Set<Integer>> groupings = powerSet(attributeColumns);

        for (Set<Integer> grouping : groupings) {
            Covering covering = new Covering();
            covering.setColumns(new ArrayList<Integer>(grouping));
            for (Entry entry : entries.getEntries()) {
                Set<Attribute> attributes = new HashSet<Attribute>();
                for (Integer i : grouping) {
                    attributes.add(entry.getAttribute(i));
                }
                covering.addEntry(attributes, entry);
            }
            coverings.add(covering);
        }


        return validCoverings(coverings, entries.getDecisionAttributes());
    }

    private static List<Covering> validCoverings(List<Covering> coverings, List<Integer> decisionAttributes) {
        //Get the decsision attribute covering from the list
        Covering decision = null;
        Set<Integer> dec = new HashSet<Integer>();
        dec.addAll(decisionAttributes);
        Set<Set<Entry>> decisionEntrySet = new HashSet<Set<Entry>>();

        for (Covering covering : coverings) {
            Set<Integer> cover = new HashSet<Integer>();
            cover.addAll(covering.getColumns());
            if (dec.equals(cover)) {
                decision = covering;
            }
        }

        coverings.remove(decision);

        List<Covering> invalidCoverings = new ArrayList<Covering>();


        for(Set<Attribute> attributeSet : decision.getAttributeListMap().keySet()){
            Set<Entry> ent = new HashSet<Entry>();
            ent.addAll(decision.getAttributeListMap().get(attributeSet));
            decisionEntrySet.add(ent);
        }

        for (Covering covering : coverings){
            Set<Set<Entry>> entrySet = new HashSet<Set<Entry>>();
            for(Set<Attribute> attributeSet : covering.getAttributeListMap().keySet()){
                Set<Entry> ent = new HashSet<Entry>();
                ent.addAll(covering.getAttributeListMap().get(attributeSet));
                entrySet.add(ent);
            }
            if(!decisionEntrySet.equals(entrySet)){
                invalidCoverings.add(covering);
            }
        }


       coverings.removeAll(invalidCoverings);

        return coverings;
    }

    public static Set<Set<Integer>> powerSet(Set<Integer> originalSet) {
        Set<Set<Integer>> sets = new HashSet<Set<Integer>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<Integer>());
            return sets;
        }
        List<Integer> list = new ArrayList<Integer>(originalSet);
        Integer head = list.get(0);
        Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
        for (Set<Integer> set : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }


}


/*Potential Covering Algorithm

If the decision attribute is just 1 attribute
    put the attribute in the front of the list of list of list
else
    combine the set into a single set

    for loop
        compare the decision attribute to the attribute in the loop
        if they are the same / is a sub set of 
            put in the rules list 
            delete attribute column
    //that gets rid of all the singls
    for loop
        for loop
            compare the second and third columns to the first (decision)
            if subset
                put in rule list
                delete
    once you've done this it should give you all the combinations of rules. 


*/
