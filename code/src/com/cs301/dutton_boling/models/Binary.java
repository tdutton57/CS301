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
    public Object getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Boolean){
            return data.equals(obj);
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
}
