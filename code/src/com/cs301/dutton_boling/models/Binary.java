package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Binary extends Attribute {
    private Boolean data;

    public Binary(Boolean data) {
        this.data = data;
    }

    @Override
    public Boolean getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Binary){
            return data.equals(((Binary) obj).getData());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=");
        stringBuilder.append(data.toString());
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }


    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
