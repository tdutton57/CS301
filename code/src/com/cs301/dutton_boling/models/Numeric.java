package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Numeric extends Attribute {

    public Numeric(String name, Integer data) {
        super(name);
        this.data = data;
    }

    @Override
    public Integer getData() {
        return (Integer) data;
    }
}
