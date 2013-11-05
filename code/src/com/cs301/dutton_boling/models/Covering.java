package com.cs301.dutton_boling.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/24/13
 * Time: 9:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Covering {
    List<Integer> columns;
    Map<Attribute, List<Integer>> attributeListMap = new HashMap<Attribute, List<Integer>>();

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }

    public Map<Attribute, List<Integer>> getAttributeListMap() {
        return attributeListMap;
    }

    public void addEntry(Attribute attribute, Integer index){
        if(!attributeListMap.containsKey(attribute)){
            attributeListMap.put(attribute, new ArrayList<Integer>());
        }
        List<Integer> entries = attributeListMap.get(attribute);
        if(entries.contains(index)){
            throw new IllegalStateException("Index already listed");
        }
        entries.add(index);
    }
}
