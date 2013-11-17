package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Numeric extends Attribute {

    private Integer data;

    public Numeric(Integer data) {
        this.data = data;
    }

    @Override
    public Integer getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Numeric){
            return data.equals(((Numeric)obj).getData());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=");
        stringBuilder.append(this.data);
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }


    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
