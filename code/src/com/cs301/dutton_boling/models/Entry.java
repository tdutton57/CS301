package com.cs301.dutton_boling.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Entry implements Comparable{

    private List<Attribute> attributes = new ArrayList<Attribute>();

    public void add(Attribute attribute){
        attributes.add(attribute);
    }

    public Attribute getAttribute(Integer index){
        return attributes.get(index);
    }

    public List<Attribute> getAttributes(Integer... indecies){
        List<Attribute> returnable = new ArrayList<Attribute>();
        for (Integer index : indecies){
            returnable.add(getAttribute(index));
        }
        return returnable;
    }

    public List<Attribute> getAttributes(List<Integer> indecies){
        List<Attribute> returnable = new ArrayList<Attribute>();
        for(Integer index : indecies){
            returnable.add(getAttribute(index));
        }
        return returnable;
    }

    public Set<Attribute> getAttributes(Set<Integer> indecies){
        Set<Attribute> returnable = new HashSet<Attribute>();
        for(Integer index : indecies){
            returnable.add(getAttribute(index));
        }
        return returnable;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
