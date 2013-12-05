package com.cs301.dutton_boling.models;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rule {

    Set<Attribute> anecedents = null;
    Set<Attribute> consequents = null;

    public Rule(Set<Attribute> anecedents, Set<Attribute> consequents) {
        this.anecedents = anecedents;
        this.consequents = consequents;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Attribute acecedent : anecedents){
            stringBuilder.append(acecedent.toString());
            stringBuilder.append(" and ");
        }
        stringBuilder.setLength(stringBuilder.length() - 5);
        stringBuilder.append(" then ");
        for(Attribute consequent : consequents){
            stringBuilder.append(consequent.toString());
            stringBuilder.append(" and ");
        }
        stringBuilder.setLength(stringBuilder.length() - 5);
        return stringBuilder.toString();
    }
}
