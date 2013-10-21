package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Attribute {
    protected String name;
    protected boolean decisionAttribute = false;

    public Attribute(String name) {
        this.name = name;
    }

    public Attribute(String name, boolean decisionAttribute) {
        this.name = name;
        this.decisionAttribute = decisionAttribute;
    }

    public boolean isDecisionAttribute() {
        return decisionAttribute;
    }

    public void setDecisionAttribute(boolean decisionAttribute) {
        this.decisionAttribute = decisionAttribute;
    }

    public String getName() {
        return name;
    }

    public abstract Object getData();
}
