package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Nominal extends Attribute {

    String data;

    public Nominal(String name, String data) {
        super(name);
        this.data = data;
    }

    public Nominal(String name, String data, boolean decisionAttribute) {
        super(name, decisionAttribute);
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.name);
        stringBuilder.append("=");
        stringBuilder.append(this.data);
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  String){
            return data.equals(obj);
        }
        return false;
    }
}
