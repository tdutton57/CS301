package com.cs301.dutton_boling.models;

/**
 * Created with IntelliJ IDEA.
 * User: Stewart
 * Date: 10/20/13
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Attribute {

    protected String name;
    protected Object data;
    public abstract Object getData();

    protected Attribute(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Attribute)){
            return false;
        }
        if(!name.equals(((Attribute) obj).getName())){
            return false;
        }
        if(data != null && ((Attribute)obj).getData() != null){
            return data.equals(((Attribute)obj).getData());
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(data == null){
            return 0;
        }
        return data.hashCode();
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append("=");
        stringBuilder.append(this.data == null ? "?" : this.data);
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }
}
