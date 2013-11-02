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
        //need decision attribute
        //
        return null;
    }
    //Type of entry is referencing if its boolean, numerical or nominal
    private List<List<Entry>> createGrouping(List<Entry> entries, int typeOfEntry) {
        if(typeOfEntry == 1)
        {
            calculateBooleanCoverngs(entries);
        }
        else if(typeOfEntry == 2)
        {
            calculateNumericalCoverings(entries);
        }
        else{
            calculateNominalCoverings(entries);
        }
        //Take the decision attribute
        //create groupings
        //put each group into an array
        //return the array that exists
    }

    private Boolean compareGrouping(List<Entry> entry1, List<Entry> entry2) {
                         //Proper subsets:
                        // proper subsets exist when entry 1 has larger groupings than entry 2
                        //for example for the set [1,2,3,4,5] [1], [2,4,5] [3,4] are all subsets
    }

    private List<List<Entry>> calculateBooleanCoverngs (List<Entry> entries) {
          //for all the entries in the list of entries
          // if an entry is equal to 0 put i value in zeroArray, else put i value in oneArray
          //put arrays into one array where first value is zero and second value is one
    }

    private List<List<Entry>> calculateNumericalCoverings( List<Entry> entries) {
        //for all the entries in the list of entries
        //intalize array to contain 0
        //if value is not equal to the first
        //create a new array and put value
        //keep doing that until done with array
        //for example: values 0 0 0 0 1 1 2 2
        //array1 would contain 0 1 2 3
        //array 2 would contain 4 5
        //array 3 would contain 6 7
    }
    private List<List<Entry>> calculateNominalCoverings(List<Entry> entries) {
        //for all the entries in the list of entries
        //intialize array1 to contain first value
        //if value is not equal to first value intialize second value
    }
}
