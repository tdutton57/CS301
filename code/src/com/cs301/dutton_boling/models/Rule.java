package com.cs301.dutton_boling.models;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rule {

    List<String> anecedents = null;
    List<String> consequents = null;

    public Rule(List<String> anecedents, String... consequents) {
        this.anecedents = anecedents;
        this.consequents = Arrays.asList(consequents);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String acecedent : anecedents){
            stringBuilder.append(acecedent);
            stringBuilder.append(" and ");
        }
        stringBuilder.setLength(stringBuilder.length() - 5);
        stringBuilder.append(" then ");
        for(String consequent : consequents){
            stringBuilder.append(consequent);
            stringBuilder.append(" and ");
        }
        stringBuilder.setLength(stringBuilder.length() - 5);
        return stringBuilder.toString();
    }
}
