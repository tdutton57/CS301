package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Binary extends Attribute {


    public Binary(Boolean data) {
        this.data = data;
    }

    @Override
    public Boolean getData() {
        return (Boolean)data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=");
        stringBuilder.append(data.toString());
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }
}
