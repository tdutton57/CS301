package com.cs301.dutton_boling.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Entry {

    private List<Attribute> attributes = new ArrayList<Attribute>();

    public void add(Attribute attribute){
        attributes.add(attribute);
    }

    public void setDecision(Integer... attrs){
        reset();
        for(Integer attribute : attrs){
            attributes.get(attribute).setDecisionAttribute(true);
        }
    }

    public void reset(){
        for(Attribute attribute : attributes){
            attribute.setDecisionAttribute(false);
        }
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
}
