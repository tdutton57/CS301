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

    private List<List<Entry>> calculateCoverings(List<Entry> entries){ //This is the RICO Algorithm
        
        
        return null;
    }
    //Type of entry is referencing if its boolean, numerical or nominal
    private List<List<Entry>> createGrouping(List<Entry> entries, int typeOfEntry) {
        if(attr instanceOf Binary)
        {
            List grouping = calculateBooleanGrouping(entries);
        }
        else if(attr.instanceOf Nominal)
        {
           List grouping = calculateNominalGrouping(entries);
        }
        else if{attr.instanceOf Numerical) {
            List grouping =calculateNumericalGrouping(entries);
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
        new zeroArray [entries.sizeOf()];
        for (Entry entry: entries) 
        {
            if(entry[i] == 0)
                zeroArray[entries] = entry;
            else
                zeroArray[entries] = X;
        }
        //I can't figure out a good way to take the values and put them into a 2d array 
        //put into two d array and return the array the first will be the os and the second will be the 1s
    }

    private List<List<Entry>> calculateNumericalGroupings( List<Entry> entries) {
        maximum = entries.max();
        for(Entries entry: entries) {

        }
    }
    private List<List<Entry>> calculateNominalCoverings(List<Entry> entries) {
        //for all the entries in the list of entries
        //intialize array1 to contain first value
        //if value is not equal to first value intialize second value
        for(Entry entry: entries) {
            if(entry[i] == "L") //when you have low or left 
                leftlowArray[i] = i;
                rightMediumArray[i] = X;
                straightLargeArray[i] = X;
            if(entry[i] == "M" || entry[i] == "R") {
                leftlowArray[i] = X;
                rightMediumArray[i] = i;
                straightLargeArray[i] = X;
            }
            if(entry[i] == "H" || entry[i] == "L") {
                leftlowArray[i] = X;
                rightMediumArray[i] = X;
                straightLargeArray[i] = i;
            }
            //now need to truncate each array to get rid of X's 
            //put all the arrays to gether 0th will be low / left 1st will be right medium 2nd will be high straight 


        }

    }
}
