package com.cs301.dutton_boling.models;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/24/13
 * Time: 9:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Covering {
    List<Integer> columns;
    Map<Set<Attribute>, List<Entry>> attributeListMap = new HashMap<Set<Attribute>, List<Entry>>();

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }

    public Map<Set<Attribute>, List<Entry>> getAttributeListMap() {
        return attributeListMap;
    }

    public void addEntry(Set<Attribute> attrbuteSet, Entry entry){
        Boolean inMap = attributeListMap.containsKey(attrbuteSet);
        if(!inMap){
            attributeListMap.put(attrbuteSet, new ArrayList<Entry>());
        }
        List<Entry> entries = attributeListMap.get(attrbuteSet);
        if(entries.contains(entry)){
            throw new IllegalStateException("Index already listed");
        }
        entries.add(entry);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for(Set<Attribute> attributes : attributeListMap.keySet()){
            builder.append(attributes.toString());
        }
        builder.append("}");
        return builder.toString();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
