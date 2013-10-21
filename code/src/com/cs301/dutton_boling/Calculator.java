package com.cs301.dutton_boling;

import com.cs301.dutton_boling.models.Entry;
import com.cs301.dutton_boling.models.Rule;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {

    public List<Rule> calculateRules(List<Entry> entries){
        List<List<Entry>> coverings = calculateCoverings(entries);
        //TODO Iterate through coverings and calculate rules

        return null;
    }

    private List<List<Entry>> calculateCoverings(List<Entry> entries){
        //TODO This
        return null;
    }
}
