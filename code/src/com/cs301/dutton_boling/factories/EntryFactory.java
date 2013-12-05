package com.cs301.dutton_boling.factories;

import com.cs301.dutton_boling.models.Binary;
import com.cs301.dutton_boling.models.Entry;
import com.cs301.dutton_boling.models.Nominal;
import com.cs301.dutton_boling.models.Numeric;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntryFactory {

    public enum AttributeType { NOMINAL, BINARY, NUMERIC}

    private class AttributeBuilder{
        String name;
        AttributeType attributeType;
        List<String> nominals = null;

        private AttributeBuilder(String name, AttributeType attributeType) {
            this.name = name;
            this.attributeType = attributeType;
        }

        private AttributeBuilder(String name, AttributeType attributeType, String... noms) {
            this.name = name;
            this.attributeType = attributeType;
            this.nominals = Arrays.asList(noms);
        }
    }

    List<AttributeBuilder> attributes = new ArrayList<AttributeBuilder>();

    public void add(String name, AttributeType attributeType){
        if(attributeType == AttributeType.NOMINAL){
           // throw new WrongMethodTypeException("No values were provided for type nominal");
        }
        attributes.add(new AttributeBuilder(name, attributeType));
    }

    public void add(String name, AttributeType attributeType, String... values){
        if (attributeType == AttributeType.BINARY || attributeType == AttributeType.NUMERIC){
            throw new IllegalStateException("Cannot provide nominal values for non-nominal attribute");
        }

        attributes.add(new AttributeBuilder(name, attributeType, values));
    }


    public Entry buildEntry(String ... values){
        Entry returnable = new Entry();
        for(int i = 0; i < values.length; i++){
            AttributeBuilder attributeBuilder = attributes.get(i);
            String data = values[i];
            switch (attributeBuilder.attributeType){
                case NOMINAL:
                    if(attributeBuilder.nominals.contains(data)){
                        returnable.add(new Nominal(attributeBuilder.name, data.equals("?") ? null : data));
                    }else {
                        throw new IllegalStateException("Inappropriate Type Passed");
                    }
                    break;
                case BINARY:
                    returnable.add(new Binary(attributeBuilder.name, data.equals("?") ? null : Boolean.parseBoolean(data)));
                    break;
                case NUMERIC:
                    returnable.add(new Numeric(attributeBuilder.name, data.equals("?") ? null : Integer.parseInt(data)));
                    break;
            }
        }
        return returnable;
    }
}



