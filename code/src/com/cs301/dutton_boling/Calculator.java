package com.cs301.dutton_boling;

import com.cs301.dutton_boling.models.Entry;
import com.cs301.dutton_boling.models.Nominal;
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

    private List<List<Entry>> calculateCoverings(List<Entry> entries){ //This is the RICO Algorithm
        List groups = createGrouping(entries);
        bool subset = isProperSubset(entries,decisionAttribute);
        //RICO Algorithm
        //Pick the covering you prefer from the ones you found
        //initialize E to the set of all instances;
        //initialize rule set to empty
        //while e contains instances do
            //create a rule that uses attribute values from the first instances in e for each attribute in covering
        //add the rule to the set
        //remove instances in E covered by this rule
        
        return null;
    }
    //Type of entry is referencing if its boolean, numerical or nominal
    private List<List<Entry>> createGrouping(List<Entry> entries) {
        if(attr instanceOf Binary)
        {
            List grouping = calculateBooleanGrouping(entries);
        }
        else if(attr.instanceOf Nominal)
        {
           List grouping = calculateNominalGroupings(entries);
        }
        else if{attr.instanceOf Numerical) {
            List grouping =calculateNumericalGroupings(entries);
        }
        return grouping;
    }

    private Boolean compareGrouping(List<Entry> entry1, List<Entry> entry2) {
                         //Proper subsets:
                        // proper subsets exist when entry 1 has larger groupings than entry 2
                        //for example for the set [1,2,3,4,5] [1], [2,4,5] [3,4] are all subsets
    }

    private List<List<Entry>> calculateBooleanGrouping (List<Entry> entries) {
          //for all the entries in the list of entries
          // if an entry is equal to 0 put i value in zeroArray, else put i value in oneArray
          //put arrays into one array where first value is zero and second value is one
          ArrayList<Entry> zeroArray = new ArrayList<Entry>();
          ArrayList<Entry> oneArray = new ArrayList<Entry>();
          for (Entry entry: entries)
          {
            if(entry[entries] == 0)
               zeroArray[entries] = entries;
            else
                oneArray[entries] = entries;
          }
        //consense the arrays so that the empty spaces
        //I can't figure out a good way to take the values and put them into a 2d array 
        //put into two d array and return the array the first will be the os and the second will be the 1s
    }

    private List<List<Entry>> calculateNumericalGroupings( List<Entry> entries) {
        List.sort(entries);                                  //TODO: create sort
        int max = entries[entries.length - 1];              //TODO: create length
        int valueArray = new Int[max];
        for(i= 0; i<=max;i++)
        {
            valueArray[i] = i;
        }
        //could create a 2-d array where the first integer is the value of it and the rest is the spot in the array it
        //exists
        List<List<Entry>> [][] numericalArray = new List<List<Entry>>[entries.length][max];
        for(int i =0; i<= max;i++){
            numericalArray[i][0] = i;
            for(int k=0;k<=entries.length;k++){
                if(enteries[i] == i){
                    numericalArray[i][k] = k;
                }
            }
        }
        return numericalArray;
    }
    private List<List<Entry>> calculateNominalGroupings(List<Entry> entries) {
        //for all the entries in the list of entries
        //intialize array1 to contain first value
        //if value is not equal to first value intialize second value

        ArrayList<Entry> leftlowArray = new ArrayList<Entry>();
        ArrayList<Entry> rightMediumArray = new ArrayList<Entry>();
        ArrayList<Entry> straightLargeArray = new ArrayList<Entry>();
        for(Entry entry: entries) {
            if(entry[entries] == "L") //when you have low or left
                leftlowArray[entries] = entries;
            if(entry[i] == "M" || entry[i] == "R") {
                rightMediumArray[i] = i;
            }
            if(entry[i] == "H" || entry[i] == "L") {
                straightLargeArray[i] = i;
            }
        }
        //clean up empty values within arrays
    }
}
