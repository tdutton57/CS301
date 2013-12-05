package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Nominal extends Attribute {

    public Nominal(String name, String data) {
        super(name);
        this.data = data;
    }

    @Override
    public String getData() {
        return (String) data;
    }
}
