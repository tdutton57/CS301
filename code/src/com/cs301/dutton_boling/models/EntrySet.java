package com.cs301.dutton_boling.models;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/24/13
 * Time: 2:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntrySet {
   private List<Integer> decisionAttributes;
   private List<String> attributeNames;
   private List<Entry> entries;

    public List<Integer> getDecisionAttributes() {
        return decisionAttributes;
    }

    public void setDecisionAttributes(Integer... decisionAttributes){
        this.decisionAttributes = Arrays.asList(decisionAttributes);
    }

    public void setDecisionAttributes(List<Integer> decisionAttributes) {
        this.decisionAttributes = decisionAttributes;
    }

    public List<String> getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(List<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public int getAttributeCount(){
        return attributeNames != null ? attributeNames.size() : 0;
    }

    public Set<Integer> getIndexOfEntries(Set<Entry> ent) {
        Set<Integer> returnable = new HashSet<Integer>();
        for(Entry entry : ent){
            returnable.add(getIndexOfEntry(entry));
        }
        return returnable;
    }

    private Integer getIndexOfEntry(Entry entry) {
        return entries.indexOf(entry);
    }
}
